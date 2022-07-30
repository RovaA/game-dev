package mg.rova.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import mg.rova.game.character.Aspect.State;
import mg.rova.game.character.Player;
import mg.rova.game.input.GameAction;
import mg.rova.game.input.GameAction.Behavior;
import mg.rova.game.input.InputManager;
import mg.rova.game.tile.Tile;
import mg.rova.game.tile.TileMap;
import mg.rova.game.util.Background;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements Runnable {

	private Player player = null;
	private TileMap map = null;
	private Background background = null;
	private GameAction moveLeft = null;
	private GameAction moveRight = null;
	private GameAction jump = null;
	private GameAction fire = null;
	private int fps = Math.round(1000 / 60) * 5;

	public MainPanel() {

		map = new TileMap();
		player = map.getPlayer();
		
		background = new Background();
		background.loadImage();

		moveLeft = new GameAction("left");
		moveRight = new GameAction("right");
		jump = new GameAction("jump", Behavior.PRESSED_ONCE);
		fire = new GameAction("fire", Behavior.PRESSED_ONCE);

	}

	public void init() {
		new Thread(this).start();
	}

	public void start(InputManager manager) {
		manager.put(KeyEvent.VK_LEFT, moveLeft);
		manager.put(KeyEvent.VK_RIGHT, moveRight);
		manager.put(KeyEvent.VK_SPACE, jump);
		manager.put(KeyEvent.VK_F, fire);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paintComponent(graphics);
		background.paint(graphics);
		map.paint(graphics);
	}

	@Override
	public void run() {
		try {
			while (true) {
				update();
				repaint();
				Thread.sleep(fps);
			}
		} catch (InterruptedException e) {
		}
	}

	public void update() {

		checkInput(player);

		update(player);
	}

	public void checkInput(Player player) {
		if (moveLeft.isPressed()) {
			player.moveLeft();
		}
		if (moveRight.isPressed()) {
			player.moveRight();
		}
		if (jump.isPressed()) {
			player.jump();
		}
		if (fire.isPressed()) {
			player.fire();
		}
	}

	public void update(Player player) {

		player.update();

		Tile rightCollide = map.getRightCollide(player);

		if (rightCollide != null)
			player.setDx(-10);

		int newX = player.getX() + player.getDx();
		player.setX(newX);
		player.setDx(0);

		if (player.currentState() == State.JUMPING) {

			Tile topTile = map.getTopCollide(player);

			if (topTile != null) {
				player.setDy(0);
				player.setY(player.getY() + 5);
				player.currentState(State.WALKING);
				return;
			}

			Tile bottomTile = map.getBottomCollide(player);

			if (bottomTile == null) {
				if (player.getDy() < 0) {
					player.setDy(player.getDy() + Player.GRAVITY);
				} else {
					player.currentState(State.WALKING);
				}
				player.setOnGround(false);
			} else {
				player.setOnGround(true);
				player.currentState(State.WALKING);
				player.setY(bottomTile.getY() - 39);
				player.setDy(0);
			}

			int newY = player.getY() + player.getDy();
			player.setY(newY);
			return;
		}

		if (player.currentState() == State.WALKING) {

			Tile bottomTile = map.getBottomCollide(player);

			if (bottomTile == null) {
				player.setOnGround(false);
				player.setDy(30);
			} else {
				player.setOnGround(true);
				player.setY(bottomTile.getY() - 39);
				player.setDy(0);
			}

			int newY = player.getY() + player.getDy();
			player.setY(newY);
			player.setDy(0);
			return;
		}

	}
}

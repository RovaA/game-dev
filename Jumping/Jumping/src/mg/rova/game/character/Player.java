package mg.rova.game.character;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import mg.rova.game.character.Aspect.State;
import mg.rova.game.character.animation.Animation;

public class Player {

	private Animation currentAnimation = null;
	private Animation animationRight = null;
	private Animation animationLeft = null;
	private State currentState = null;
	private Coord coord = null;
	private Aspect aspect = null;
	private int x = 10, y = 0;
	private int dx = 0, dy = 0;
	public static int GRAVITY = 9;
	private boolean isJumping = false, onGround = true, fire = false;

	public Player(int x, int y) {

		this.x = x;
		this.y = y;

		coord = new Coord(x, y);
		aspect = new Aspect();

		currentState = State.WALKING;

		animationRight = new Animation();
		animationLeft = new Animation();
		try {
			final BufferedImage image = ImageIO.read(getClass()
					.getClassLoader().getResourceAsStream("images/mario.png"));
			BufferedImage rightImages1 = image.getSubimage(0, 12, 18, 40);
			BufferedImage rightImages2 = image.getSubimage(25, 12, 18, 40);
			BufferedImage rightImages3 = image.getSubimage(43, 12, 18, 40);
			BufferedImage rightImages4 = image.getSubimage(64, 12, 18, 40);
			animationRight.setImages(new BufferedImage[] { rightImages1,
					rightImages2, rightImages3, rightImages4 });


			BufferedImage leftImages1 = image.getSubimage(0, 12, 18, 40);
			BufferedImage leftImages2 = image.getSubimage(25, 12, 18, 40);
			BufferedImage leftImages3 = image.getSubimage(43, 12, 18, 40);
			BufferedImage leftImages4 = image.getSubimage(64, 12, 18, 40);
			animationLeft.setImages(new BufferedImage[] { leftImages1, leftImages2, leftImages3, leftImages4 });
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {
		Image image = currentAnimation.getImage();
		g.drawRect(x, y, 20, 40);
		if (image != null)
			g.drawImage(image, x, y, null);

		if (fire)
			g.drawOval(x + 20, y + 10, 20, 20);

	}

	public void update() {
		if (currentAnimation == null)
			currentAnimation = animationRight;
		currentAnimation.update();
	}

	public void moveLeft() {
		currentAnimation = animationLeft;
		currentAnimation.start();
		setDx(-10);
	}

	public void moveRight() {
		currentAnimation = animationRight;
		currentAnimation.start();
		setDx(10);
	}

	public void jump() {
		isJumping = true;
		currentState(State.JUMPING);
		setY(getY() - 5);
		setDy(-45);
	}

	public void fire() {
		fire = true;
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				fire = false;
			}
		}, 500);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public Aspect getAspect() {
		return aspect;
	}

	public void setAspect(Aspect aspect) {
		this.aspect = aspect;
	}

	public boolean isFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, 18, 40);
	}

	public State currentState() {
		return currentState;
	}

	public void currentState(State state) {
		currentState = state;
	}

}

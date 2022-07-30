package mg.rova.game.tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mg.rova.game.character.Player;

public class TileMap {

	protected Player player = null;
	protected List<Tile> tiles = new ArrayList<>();

	public TileMap() {
		try {
			final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("maps/level1.txt")));
			final List<String> lines = new ArrayList<>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
				line = null;
			}
			for (int i = 0; i < lines.size(); i++) {
				char[] temp = lines.get(i).toCharArray();
				for (int j = 0; j < temp.length; j++) {
					if (temp[j] == 'B')
						tiles.add(new Tile(j * 40, i * 40, new Rectangle(j * 40, i * 40, 40, 40)));
					if (temp[j] == 'P')
						player = new Player(j * 40, i * 40);						
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i < tiles.size(); i++) {
			final Tile tile = tiles.get(i);
			g.drawRect(tile.getX(), tile.getY(), 40, 40);
		}
		player.paint(g);
	}

	public Tile getBottomCollide(Player player) {
		for (Tile tile : tiles) {
			if (player.getRectangle().intersects(tile.getRectangle())) {
				if ((player.getX() >= tile.getX() && player.getX() <= tile.getX() + 40) && (player.getY() + 40 >= tile.getY() - 1))
					return tile;
			}
		}
		return null;
	}

	public Tile getTopCollide(Player player) {
		for (Tile tile : tiles) {
			if ((player.getX() >= tile.getX() && player.getX() <= tile.getX() + 40) && (player.getY() >= tile.getY() && player.getY() <= tile.getY() + 40))
				return tile;
		}
		return null;
	}

	public Tile getRightCollide(Player player) {
		for (Tile tile : tiles) {
			if (player.getRectangle().intersects(tile.getRectangle())) {
				if ((player.getX() + 40 >= tile.getX()) && (player.getY() >= tile.getY() && player.getY() + 40 >= tile.getY()))
					return tile;
			}
		}
		return null;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

}

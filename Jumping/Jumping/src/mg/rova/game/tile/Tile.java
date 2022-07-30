package mg.rova.game.tile;

import java.awt.Rectangle;

public class Tile {

	protected int x = 0;
	protected int y = 0;
	protected Rectangle rectangle;
	
	public Tile(int x, int y, Rectangle rectangle) {
		this.x = x;
		this.y = y;
		this.rectangle = rectangle;
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

	public Rectangle getRectangle() {
		return rectangle;
	}
	
}

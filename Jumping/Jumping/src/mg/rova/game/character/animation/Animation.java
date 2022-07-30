package mg.rova.game.character.animation;

import java.awt.Image;

public class Animation {

	private int currentFrame = 0;
	private Image[] images = null; 
	private boolean start = false;
	
	public Animation() {
		
	}
	
	public void start() {
		start = true;
	}
	
	public void stop() {
		start = false;
	}
	
	public void setImages(Image[] images) {
		this.images = images;
	}
	
	public void update() {
		if (images == null) {
			return;
		}
		currentFrame++;
		if (currentFrame >= images.length) {
			currentFrame = 0;
			start = false;
		}
	}
	
	public Image getImage() {
		if (!start)
			return images[0];
		return images[currentFrame];
	}
}

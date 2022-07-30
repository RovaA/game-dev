package mg.rova.game.character;

public class Aspect {

	public enum State {
		WALKING, JUMPING
	}

	protected boolean isOnGround = true;
	protected boolean isJumping = false;
	private State currentState = null;

	public Aspect() {

	}

	public boolean isOnGround() {
		return isOnGround;
	}

	public void setOnGround(boolean isOnGround) {
		this.isOnGround = isOnGround;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

}

package mg.rova.game.input;

public class GameAction {

	public static enum Behavior {
		PRESSED_ONCE, NORMAL
	}

	public static enum State {
		PRESSED, RELEASED, WAITING_FOR_RELEASE
	}

	private String name;
	private Behavior behavior;
	private State currentState;
	private int amount = 0;

	public GameAction(String name) {
		this(name, Behavior.NORMAL);
	}

	public GameAction(String name, Behavior behavior) {
		this.setName(name);
		this.behavior = behavior;
	}

	public void press() {
		press(1);
	}

	protected void press(int amount) {
		if (currentStateIs(State.WAITING_FOR_RELEASE))
			return;
		this.amount += amount;
		setCurrentState(State.PRESSED);
	}

	public boolean currentStateIs(State state) {
		return currentState == state;
	}

	public void setCurrentState(State state) {
		this.currentState = state;
	}

	public void release() {
		setCurrentState(State.RELEASED);
	}

	public boolean isPressed() {
		return getAmount() != 0;
	}

	public int getAmount() {
		int retVal = amount;
		if (amount != 0) {
			if (currentStateIs(State.RELEASED))
				amount = 0;
			if (behavior == Behavior.PRESSED_ONCE) {
				if (!currentStateIs(State.WAITING_FOR_RELEASE)) {
					amount = 0;
					setCurrentState(State.WAITING_FOR_RELEASE);
				}
			}
		}
		return retVal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

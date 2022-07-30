package mg.rova.game.input;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputManager implements KeyListener {

	private Map<Integer, GameAction> map = new HashMap<Integer, GameAction>();

	public InputManager(Component component) {
		component.addKeyListener(this);
	}

	public void put(Integer key, GameAction value) {
		map.put(key, value);
	}

	public void clearAllMaps() {
		map.clear();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		GameAction gameAction = map.get(arg0.getKeyCode());
		if (gameAction != null)
			gameAction.press();
		arg0.consume();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		GameAction gameAction = map.get(arg0.getKeyCode());
		if (gameAction != null)
			gameAction.release();
		arg0.consume();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}

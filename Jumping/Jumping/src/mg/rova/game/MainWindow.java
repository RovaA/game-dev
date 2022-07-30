package mg.rova.game;

import javax.swing.JFrame;

import mg.rova.game.input.InputManager;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private MainPanel manager = null;

	public MainWindow() {
		manager = new MainPanel();
		add(manager);
		setSize(500, 500);
		setTitle("Game Alpha 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init() {
		InputManager inputManager = new InputManager(this);
		manager.start(inputManager);

		manager.init();
	}

	public void showWindow() {
		setVisible(true);
	}

	public void hideWindow() {
		setVisible(false);
	}
}

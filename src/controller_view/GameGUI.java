package controller_view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

import model.AudioManager;

/**
 * The main class for the game's graphical user interface, using JavaFX.
 */
public class GameGUI extends Application {

	public AudioManager audioManager;
	private static Stage primaryStage;
	private static MenuViewPane menuViewPane;
	private static LoginPane loginPane; // Assuming this is defined somewhere
	private static GameViewPaneGUI gameViewPaneGUIEASY;
	private static GameViewPaneGUI gameViewPaneGUIMEDIUM;
	private static GameViewPaneGUI gameViewPaneGUIHARD;// Assuming this is defined somewhere
	private static BorderPane everything;

	/**
	 * Starts the application, setting up the stage and media player.
	 * 
	 * @param primaryStage The primary stage for this application, onto which the
	 *                     application scene can be set.
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.audioManager = new AudioManager();
		layoutGUI();
		Scene scene = new Scene(everything, 1300, 900);
		primaryStage.setScene(scene);
		primaryStage.show();

		String path = "src/images/spaceinvaders1.mpeg";
		audioManager.playSound(path);
	};

	/**
	 * Sets up the main GUI layout.
	 */
	private void layoutGUI() {
		everything = new BorderPane();
		menuViewPane = new MenuViewPane();
		loginPane = new LoginPane(); // Initialize other panes
		everything.setCenter(menuViewPane);
	}

	/**
	 * Changes the center pane of the BorderPane based on a specified name.
	 * 
	 * @param paneName The name of the pane to display in the center.
	 */
	public static void changePane(String paneName) {
		switch (paneName) {
		case "menuViewPane":
			everything.setCenter(menuViewPane);
			break;
		case "loginPane":
			everything.setCenter(loginPane);
			break;
		case "gameApplicationEASY":
			gameViewPaneGUIEASY = new GameViewPaneGUI(2, loginPane.getAccount(), loginPane.getCollection());
			everything.setCenter(gameViewPaneGUIEASY);
			break;
		case "gameApplicationMEDIUM":
			gameViewPaneGUIMEDIUM = new GameViewPaneGUI(3, loginPane.getAccount(), loginPane.getCollection());
			everything.setCenter(gameViewPaneGUIMEDIUM);
			break;
		case "gameApplicationHARD":
			gameViewPaneGUIHARD = new GameViewPaneGUI(4, loginPane.getAccount(), loginPane.getCollection());
			everything.setCenter(gameViewPaneGUIHARD);
			break;
		default:
			System.out.println("Pane not recognized: " + paneName);
			break;
		}
		primaryStage.sizeToScene(); // Resize the stage to fit the new pane
	}

	/**
	 * The main entry point for all JavaFX applications.
	 * 
	 * @param args the command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * A class implementing Runnable that repeats the media player action at the end
	 * of a media.
	 */

}

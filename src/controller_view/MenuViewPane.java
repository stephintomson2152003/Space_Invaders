package controller_view;

import java.util.Random;

import javafx.collections.FXCollections;

/**
 * Author : Stephin Tomson, Vickram Sullhan, Tanishq Jaiswal, Anuj Jariwala
 */

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Acount;
import model.AcountCollection;

public class MenuViewPane extends BorderPane {
	private boolean turnDark = false;
	public LoginPane loginPane;
	Label titleLabel;

	private static final String LIGHT_THEME_STYLE = "-fx-font-size: 15px;-fx-background-color: white; -fx-text-fill: black;";
	private static final String DARK_THEME_STYLE = "-fx-font-size: 15px;-fx-background-color: black; -fx-text-fill: white;";
	private static final String LIGHT_BORDER_STYLE = "-fx-border-color: white;";
	private static final String DARK_BORDER_STYLE = "-fx-border-color: black;";
	private static final String TABLE_BUTTON_LIGHT_STYLE = "-fx-base: #eeeeee;";
	private static final String TABLE_BUTTON_DARK_STYLE = "-fx-base: #666666;";
	private static final String BUTTON_DARK_MODE_STYLE = "-fx-base: #CCCCCC;"; // Light gray background for buttons
	private static final String INPUT_FIELD_DARK_MODE_STYLE = "-fx-control-inner-background: #CCCCCC;"; // Light gray
	private static final String INPUT_FIELD_LIGHT_MODE_STYLE = "-fx-control-inner-background: white;"; // Light gray

	private Button startButton;
	private Button exitButton;

	String lightBackgroundStyle = "-fx-background-color: white; " + "-fx-background-image: "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'); "
			+ "-fx-background-size: 50px, 50px, 50px, 50px, 50px, 50px, 50px, "
			+ "50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, "
			+ "50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, " + "50px, 50px, 50px; "
			+ "-fx-background-repeat: no-repeat; " + "-fx-background-position: "
			+ "25% 15%, 5% 55%, 40% 30%, 60% 5%, 80% 90%, 9% 20%, 15% 65%, 25% 80%, 45% 90%, 65% 90%, "
			+ "50% 15%, 75% 25%, 85% 35%, 95% 45%, 80% 60%, 90% 65%, 70% 75%, 95% 85%, 5% 95%, 10% 5%, "
			+ "15% 95%, 20% 85%, 25% 75%, 30% 65%, 35% 55%, 40% 45%, 45% 35%, 50% 25%, 55% 15%, 60% 5%";;

	String darkBackgroundStyle = "-fx-background-color: black; " + "-fx-background-image: "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'), "
			+ "url('images/asteroid.png'), " + "url('images/spaceship1.png'), " + "url('images/spaceship3.png'); "
			+ "-fx-background-size: 50px, 50px, 50px, 50px, 50px, 50px, 50px, "
			+ "50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, "
			+ "50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, 50px, " + "50px, 50px, 50px; "
			+ "-fx-background-repeat: no-repeat; " + "-fx-background-position: "
			+ "25% 15%, 5% 55%, 40% 30%, 60% 5%, 80% 90%, 9% 20%, 15% 65%, 25% 80%, 45% 90%, 65% 90%, "
			+ "50% 15%, 75% 25%, 85% 35%, 95% 45%, 80% 60%, 90% 65%, 70% 75%, 95% 85%, 5% 95%, 10% 5%, "
			+ "15% 95%, 20% 85%, 25% 75%, 30% 65%, 35% 55%, 40% 45%, 45% 35%, 50% 25%, 55% 15%, 60% 5%";;

	public MenuViewPane() {
		loginPane = new LoginPane();
		loginPane.setStyle(LIGHT_BORDER_STYLE);

		addButtonsAndTitle();
		updateComponentStyles(LIGHT_THEME_STYLE, TABLE_BUTTON_LIGHT_STYLE, INPUT_FIELD_LIGHT_MODE_STYLE);
		addThemeToggleButton();
		this.setPadding(new Insets(40));
		setStyle(lightBackgroundStyle);
		// Check which visual is being used and stick with it through every GUI view
		// change.
		if (turnDark == true) {
			this.applyDarkTheme();
		} else {
			this.applyLightTheme();
		}
	}

	// Method to generate repeated background image URLs

	private void addThemeToggleButton() {
		ToggleButton themeToggleButton = new ToggleButton("Dark Mode");
		themeToggleButton.setStyle(
				"-fx-background-color: #BF70F3; -fx-font-size: 12px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 15px 15px;"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		// Visual mode handler
		themeToggleButton.setOnAction(e -> {
			if (themeToggleButton.isSelected()) {
				themeToggleButton.setText("Light Mode");
				applyDarkTheme();
			} else {
				themeToggleButton.setText("Dark Mode");
				applyLightTheme();
			}
		});

		// Position the toggle button, for example, in the top right corner
		HBox topRight = new HBox(themeToggleButton);
		topRight.setAlignment(Pos.TOP_RIGHT);
		topRight.setPadding(new Insets(10));
		this.setTop(topRight);
	}

	private void applyLightTheme() {
		turnDark = false;
		setStyle(lightBackgroundStyle);
		loginPane.setLightTheme();

		titleLabel.setStyle(
				"-fx-font-size: 65px; -fx-font-family: 'Jokerman'; -fx-font-weight: bold; -fx-text-fill: black;");
		updateComponentStyles(LIGHT_THEME_STYLE, TABLE_BUTTON_LIGHT_STYLE, INPUT_FIELD_LIGHT_MODE_STYLE);
	}

	private void applyDarkTheme() {
		turnDark = true;
		setStyle(darkBackgroundStyle);
		loginPane.setDarkTheme();
		titleLabel.setStyle(
				"-fx-font-size: 65px; -fx-font-family: 'Jokerman'; -fx-font-weight: bold; -fx-text-fill: green;");
		updateComponentStyles(DARK_THEME_STYLE, BUTTON_DARK_MODE_STYLE, INPUT_FIELD_DARK_MODE_STYLE);
	}

	private void updateComponentStyles(String paneStyle, String buttonStyle, String inputFieldStyle) {

		loginPane.setStyle(paneStyle);
		startButton.setStyle(buttonStyle
				+ "-fx-background-color: #87CEEB; -fx-font-size: 20px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 25; -fx-padding: 30px 50px;"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		exitButton.setStyle(buttonStyle
				+ "-fx-background-color: #8E9492; -fx-font-size: 20px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 25; -fx-padding: 30px 80px;"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		loginPane.getAccountNameField().setStyle(inputFieldStyle); // Update the method to access the account name
																	// field.
		loginPane.getPasswordField().setStyle(inputFieldStyle); // Update the method to access the password field

	}

	private void addButtonsAndTitle() {
		startButton = new Button("Play Game");
		startButton.setOnAction(event -> {
			this.setCenter(null);
			this.setLeft(null);
			this.setRight(null);
			this.setBottom(null);
			this.setLeft(loginPane);
			// Continue with the same visual for login as being used in menu.
			if (turnDark == true) {
				this.applyDarkTheme();
			} else {
				this.applyLightTheme();
			}
		});

		exitButton = new Button("Exit");
		exitButton.setOnAction(event -> {
			System.exit(0);

		});

		titleLabel = new Label("Space Invaders");
		titleLabel.setStyle(
				"-fx-font-size: 75px; -fx-font-family: 'Jokerman'; -fx-font-weight: bold; -fx-text-fill: black;");

		VBox buttonBox = new VBox(20, startButton, exitButton);
		buttonBox.setAlignment(Pos.CENTER); // Align buttons to the center

		VBox centerBox = new VBox(30, titleLabel, buttonBox);
		centerBox.setAlignment(Pos.CENTER);

		this.setCenter(centerBox); // Set the VBox in the center region of BorderPane
	}

}
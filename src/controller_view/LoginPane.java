package controller_view;

/**
* Authors : Stephin Tomson, Vickram Sullhan, Tanishq Jaiswal, Anuj Jariwala
*/
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Acount;
import model.AcountCollection;

public class LoginPane extends BorderPane {
	public GridPane topGrid;
	public GridPane grid;
	public TextField accountField;
	public PasswordField passwordField;
	public Button loginButton;
	public Label loginAnnounce;
	public Button logoutButton;
	public Button createAccount;
	public HBox view;
	public VBox guestPlay;
	private TableView<Acount> allScoreTable;
	public AcountCollection accounts;
	public Acount curUser;
	public Button guestPlayButton;
	public Label guestNote;
	public Label accountLabel;
	public Label passwordLabel;
	public Label leaderBoard;
	public Button easyMode;
	public Button mediumMode;
	public Button hardMode;
	public GridPane rulesView;
	public Button backButton;

	public LoginPane() {
		accounts = new AcountCollection();
		accounts.openCurrentList();
		this.easyMode = new Button("Easy");
		this.mediumMode = new Button("Medium");
		this.hardMode = new Button("Hard");
		accounts.setDefaults();
		createLogin();
		setHandlers();
	}

	public void setLightTheme() {
		// Applying CSS styling to the buttons and labels:
		loginButton.setStyle(
				"-fx-background-color: #87CEEB; -fx-font-size: 12px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 15px 30px;"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		logoutButton.setStyle(
				"-fx-background-color: #8E9492; -fx-font-size: 12px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 15px 26px;"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		guestPlayButton.setStyle(
				"-fx-background-color: #87CEEB; -fx-font-size: 15px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 20px 40px;"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,1,0.75) , 4,0,0,1 );");
		guestNote.setStyle(
				"-fx-font-size: 14px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");
		loginAnnounce.setStyle(
				"-fx-font-size: 15px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");
		accountLabel.setStyle(
				"-fx-font-size: 14px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");
		passwordLabel.setStyle(
				"-fx-font-size: 14px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");
		createAccount.setStyle(
				"-fx-background-color: #87CEEB; -fx-font-size: 15px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 18; -fx-padding: 18px 30px;"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		leaderBoard.setStyle(
				"-fx-font-size: 25px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 15px; -fx-background-radius: 10;");

	}

	public void setDarkTheme() {
		// Applying CSS styling to the buttons and labels:
		loginButton.setStyle(
				"-fx-background-color: #87CEEB; -fx-font-size: 12px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 15px 30px;"
						+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 4,0,0,1);");
		logoutButton.setStyle(
				"-fx-background-color: #8E9492; -fx-font-size: 12px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 15px 26px;"
						+ "-fx-effect: dropshadow( gaussian , rgba(1,1,1,0.75) , 4,0,0,1 );");
		guestPlayButton.setStyle(
				"-fx-background-color: #87CEEB; -fx-font-size: 15px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 20px 40px;"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		guestNote.setStyle(
				"-fx-font-size: 14px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");
		loginAnnounce.setStyle(
				"-fx-font-size: 15px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");
		accountLabel.setStyle(
				"-fx-font-size: 14px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");
		passwordLabel.setStyle(
				"-fx-font-size: 14px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");
		createAccount.setStyle(
				"-fx-background-color: #87CEEB; -fx-font-size: 15px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 18; -fx-padding: 18px 30px;"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		leaderBoard.setStyle(
				"-fx-font-size: 20px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");

	}

	private void createLogin() {
		// Create "Play as Guest" button and label
		backButton = new Button("Back");

		guestPlayButton = new Button("Play as Guest");
		guestNote = new Label("*Note: Playing as a Guest will not showcase your score \n on the leaderboard.");
		leaderBoard = new Label("LeaderBoard:");
		guestPlay = new VBox(10, guestPlayButton, guestNote);
		guestPlay.setAlignment(Pos.CENTER);
		guestPlay.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		// Creating a vertical seperator
		Separator hSeparator = new Separator();
		hSeparator.setStyle("-fx-background-color: black; -fx-border-width: 0 0 4 0;");
		hSeparator.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

		Separator vSeparator = new Separator();
		vSeparator.setOrientation(Orientation.VERTICAL);
		vSeparator.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		vSeparator.setStyle("-fx-background-color: black; -fx-border-width: 0 0 4 0;");
		// Create a VBox for the "Play as Guest" feature
		VBox guestPlayBox = new VBox(10, guestPlay);
		guestPlayBox.setAlignment(Pos.TOP_CENTER);
		// Create a VBox for the login portion
		VBox loginBox = new VBox(10);
		loginBox.setAlignment(Pos.CENTER);
		// Create the existing login GridPane
		grid = new GridPane();
		accountLabel = new Label("Username:");
		passwordLabel = new Label("Password:");
		accountField = new TextField();
		passwordField = new PasswordField();
		loginButton = new Button("Login");
		loginAnnounce = new Label("Login First!!");
		logoutButton = new Button("Log out");
		createAccount = new Button("Create New Account");
		grid.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		grid.add(loginAnnounce, 1, 0);
		grid.add(accountLabel, 0, 1);
		grid.add(passwordLabel, 0, 2);
		grid.add(accountField, 1, 1);
		grid.add(passwordField, 1, 2);
		grid.add(loginButton, 2, 1);
		grid.add(logoutButton, 2, 2);
		grid.add(createAccount, 1, 3);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		setLightTheme();
		allScoreTable = new TableView<>();

		TableColumn<Acount, Number> rankCol = new TableColumn<>("Rank");
		rankCol.setPrefWidth(50);
		rankCol.setCellFactory(column -> new TableCell<Acount, Number>() {
			@Override
			protected void updateItem(Number item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText(null);
				} else {
					// Use getIndex() + 1 to display rank starting at 1
					setText(Integer.toString(getIndex() + 1));
				}
			}
		});

		TableColumn<Acount, String> playerCol = new TableColumn<>("Player");
		playerCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		playerCol.setPrefWidth(200);

		TableColumn<Acount, String> timeCol = new TableColumn<>("Time");
		timeCol.setCellValueFactory(new PropertyValueFactory<>("topScore")); // Assuming Acount has a
		timeCol.setPrefWidth(100);

		allScoreTable.getColumns().addAll(rankCol, playerCol, timeCol);

		ObservableList<Acount> allScores = FXCollections.observableArrayList(accounts.getList());
		allScoreTable.setItems(allScores);
		allScoreTable.setMaxHeight(600);
		VBox tableContainer = new VBox(75);
		tableContainer.getChildren().addAll(allScoreTable);
		tableContainer.setAlignment(Pos.CENTER);
		// Add the login GridPane to the login VBox
		loginBox.getChildren().addAll(grid);
		// Create a VBox to stack the "Play as Guest" feature and login portion with
		// some spacing

		VBox loginAndGuestBox = new VBox(75, guestPlayBox, hSeparator, loginBox);
		loginAndGuestBox.setAlignment(Pos.CENTER);

		view = new HBox(35);
		view.setPadding(new Insets(10));
		view.setAlignment(Pos.CENTER);
		view.getChildren().addAll(loginAndGuestBox, tableContainer);
		this.setCenter(view);
	}

	public Acount getAccount() {
		return this.curUser;
	}

	public boolean getloginStatus() {
		return (this.curUser != null);
	}

	public AcountCollection getCollection() {
		return accounts;
	}

	private void setHandlers() {

		this.backButton.setOnAction(e -> GameGUI.changePane("menuViewPane"));

		this.easyMode.setOnAction(e -> {
			GameGUI.changePane("gameApplicationEASY");
		});

		this.mediumMode.setOnAction(e -> {
			GameGUI.changePane("gameApplicationMEDIUM");
		});

		this.hardMode.setOnAction(e -> {
			GameGUI.changePane("gameApplicationHARD");
		});

		// login handler
		loginButton.setOnAction(event -> {
			String checkUsername = accountField.getText();
			String checkPassword = passwordField.getText();

			if (checkUsername.isEmpty() || checkPassword.isEmpty()) {
				loginAnnounce.setText("Invalid Credentials");
				return;
			}

			boolean foundAccount = false;
			for (Acount a : accounts.getList()) {
				if (a.getId().equals(checkUsername) && a.checkPassword(checkPassword)) {
					accountField.setText("");
					passwordField.setText("");
					this.curUser = a;
					foundAccount = true;
					loginAnnounce.setText("Successfully Logged In!");
					showGameView();
					break;
				}
			}

			if (!foundAccount) {
				loginAnnounce.setText("Invalid Credentials");
			}
		});

		// logout handler
		logoutButton.setOnAction(event -> {
			this.curUser = null;
			loginAnnounce.setText("Login First!!");
		});

		// New account handler
		createAccount.setOnAction(event -> {
			String checkUsername = accountField.getText();
			String checkPassword = passwordField.getText();

			if (checkUsername.isEmpty() || checkPassword.isEmpty()) {
				loginAnnounce.setText("Invalid Credentials");
				return;
			}

			for (Acount a : accounts.getList()) {
				if (a.getId().equals(checkUsername)) {
					loginAnnounce.setText("Account ID taken");
					accountField.setText("");
					passwordField.setText("");
					return;
				}
			}

			Acount newAccount = new Acount(checkUsername, checkPassword, 0);
			accounts.addAccount(newAccount);
			accountField.setText("");
			passwordField.setText("");
			this.curUser = newAccount;
			loginAnnounce.setText("Successfully Logged In!");
		});

		guestPlayButton.setOnAction(event -> {
			showGameView();
		});

	}

	private void showGameView() {
		rulesView = new GridPane();
		this.setCenter(rulesView);

		Label rulesLabel = new Label("Game Rules:");
		Label difficultyLabel = new Label("Choose Difficulty:");
		String gameRules = ("-> There are 3 different modes \n to space invaders that bring \n varying aspects to the game.\n"
				+ "-> Easy: 3 Lives\n" + "-> Medium: 2 Lives\n" + "-> Hard: 1 Life\n" + "-> Points Distribution: \n"
				+ "	Alien Row 1: 5 points\n" + "	Alien Row 2: 15 points\n" + "	Alien Row 3: 25 points\n"
				+ "	Alien Row 4: 40 points\n" + "	Boss: 150 points");
		Label showRules = new Label(gameRules);

		String playing = "How To Play: ";
		Label howToPlay = new Label(playing);

		// Applying CSS to beautify the GUI View.
		rulesLabel.setStyle(
				"-fx-font-size: 20px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 15px; -fx-background-radius: 15;");
		difficultyLabel.setStyle(
				"-fx-font-size: 20px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 15px; -fx-background-radius: 15;");
		easyMode.setStyle(
				"-fx-background-color: #87CEEB; -fx-font-size: 12px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 15px 30px;"
						+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 4,0,0,1);");
		mediumMode.setStyle(
				"-fx-background-color: #87CEEB; -fx-font-size: 12px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 15px 30px;"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		hardMode.setStyle(
				"-fx-background-color: #87CEEB; -fx-font-size: 12px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 15px 30px;"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		backButton.setStyle(
				"-fx-background-color: #FF0000; -fx-font-size: 12px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 15px 30px;"
						+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 4,0,0,1);");
		showRules.setStyle(
				"-fx-font-size: 17px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");

		howToPlay.setStyle(
				"-fx-font-size: 17px; -fx-font-family: 'Consolas', 'Arial'; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: #f0f8ff; -fx-padding: 8px; -fx-background-radius: 8;");

		// Making two different vertical boxes to store the difficulty and rules
		// explanation.
		VBox diffSelect = new VBox(50, difficultyLabel, easyMode, mediumMode, hardMode, backButton);
		diffSelect.setAlignment(Pos.TOP_CENTER);

		VBox rulesExplain = new VBox(50, rulesLabel, showRules);
		rulesExplain.setAlignment(Pos.TOP_CENTER);

		// Adding those vertical boxes to a horizontal box.
		HBox labels = new HBox(200);
		labels.getChildren().addAll(diffSelect, rulesExplain);
		labels.setAlignment(Pos.TOP_LEFT);

		VBox playingRules = new VBox(50, labels, howToPlay);

		this.setCenter(playingRules);

	}

	public Node getAccountNameField() {
		return accountField;
	}

	public Node getPasswordField() {
		return passwordField;
	}
}
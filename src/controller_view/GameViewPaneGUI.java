
package controller_view;

import javafx.application.Platform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.Acount;
import model.AcountCollection;
import model.AudioManager;
import model.LaserShot;
import model.LaserShotCollection;
import objects.AlienFour;
import objects.AlienOne;
import objects.AlienThree;
import objects.AlienTwo;
import objects.FinalBoss;
import objects.BlockCollection;
import objects.SpaceShipOne;
import objects.StaticBlock;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class GameViewPaneGUI extends BorderPane {
	private Label livesLabel;
	private AudioManager audioManager;
	private Canvas gameCanvas;
	private double scaledWidth = 40; // Example scaled width
	private double scaledHeight = 40; // Example scaled height
	private List<AlienOne> lineOneAliens;
	private List<AlienTwo> lineTwoAliens;
	private List<AlienThree> lineThreeAliens;
	private List<AlienFour> lineFourAliens;
	private List<FinalBoss> lineFinalBoss;
	private SpaceShipOne spaceShipOne;
	private LaserShotCollection activeShots;
	private GraphicsContext gc;
	private BlockCollection block1;
	private BlockCollection block2;
	private BlockCollection block3;
	private AcountCollection acounts;
	private Acount currentAcount;
	private int currentScore;
	private double startX = 0;
	private static boolean direction = true;
	private static boolean directionBoss = true;
	private Set<KeyCode> keysPressed = new HashSet<>();
	private Label label2;
	private Label label1;
	private static double yCord;
	private double startXBoss = -300;
	private long lastShotTime = 0;
	private int lives = 3;
	private Timeline gameLoop;
	private String darkBackgroundStyle = "-fx-background-color: black; ";
	private final long shotCooldown = 500;
	private double mode;
	private Button backButton;
	private Media media;
	private HBox box;

	public GameViewPaneGUI(double d, Acount ac, AcountCollection accountCollection) {

		this.audioManager = new AudioManager();
		lineOneAliens = new ArrayList<>();
		lineTwoAliens = new ArrayList<>();
		lineThreeAliens = new ArrayList<>();
		lineFourAliens = new ArrayList<>();
		lineFinalBoss = new ArrayList<>();
		if (ac != null) {
			currentAcount = ac;
		} else {
			currentAcount = new Acount("Guest", "Guest", 0);
		}
		backButton = new Button("Back");
		this.mode = d;
		yCord = 50;

		activeShots = new LaserShotCollection();

		spaceShipOne = new SpaceShipOne(450, 700, activeShots);

		block1 = new BlockCollection(200, 600);
		block2 = new BlockCollection(500, 600);
		block3 = new BlockCollection(800, 600);

		currentScore = 0;
		acounts = accountCollection;
		initaiteAlien();
		lineFinalBoss.add(new FinalBoss(startXBoss, 0, activeShots));
		initializeUI();
		backButton.setOnAction(e -> {
			handleBackButtonAction();
			gameCanvas.requestFocus();
		});
		setupKeyListeners();
	}

	private void handleBackButtonAction() {
		GameGUI.changePane("loginPane");
	}

	private void initializeUI() {

		gameCanvas = new Canvas(1300, 900);
		gameCanvas.setFocusTraversable(true);
		this.gc = gameCanvas.getGraphicsContext2D();
		this.setCenter(gameCanvas);
		drawAliens();
		drawSpaceShips();
		drawBlock();
		ScoreLabel();

		this.setPadding(new Insets(30));
		this.setStyle(darkBackgroundStyle);
	}

	private void alienShoot() {
		Random rand = new Random();
		int i = rand.nextInt(lineFourAliens.size());

		if (!lineFourAliens.get(i).isHit()) {
			if (rand.nextInt(60 - (int) mode * 2) == 0) {
				LaserShot shot = new LaserShot(lineFourAliens.get(i).getXCord() + scaledWidth / 2,
						lineFourAliens.get(i).getYCord(), false);

				shot.getImageView();
				this.activeShots.add(shot);
			}

		}

	}

	private void initaiteAlien() {
		double startY1 = 50 + yCord;
		double startY2 = 100 + yCord;
		double startY3 = 150 + yCord;
		double startY4 = 200 + yCord;

		double stepX = 50;
		for (double i = 0; i < 8; i++) {
			AlienOne alien = new AlienOne(startX + i * stepX, startY1, activeShots);
			lineOneAliens.add(alien);
		}

		for (double i = 0; i < 8; i++) {
			AlienTwo alien = new AlienTwo(startX + i * stepX, startY2, activeShots);
			lineTwoAliens.add(alien);
		}

		for (double i = 0; i < 8; i++) {
			AlienThree alien = new AlienThree(startX + i * stepX, startY3, activeShots);
			lineThreeAliens.add(alien);
		}

		for (double i = 0; i < 8; i++) {
			AlienFour alien = new AlienFour(startX + i * stepX, startY4, activeShots);
			lineFourAliens.add(alien);
		}

	}

	private void ScoreLabel() {
		label2 = new Label("SCORE: " + currentScore);
		label1 = new Label("NAME: " + currentAcount.getId());
		livesLabel = new Label("LIVES: " + lives);
		backButton.setStyle(
				"-fx-background-color: #FF0000; -fx-font-size: 20px; -fx-font-family: Arial; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 10px 15px;"
						+ "-fx-effect: dropshadow(gaussian, rgba(255, 255, 255, 0.8), 3,0,0,1);");
		livesLabel.setStyle(
				"-fx-font-size: 20px; -fx-font-family: 'Lucida Console'; -fx-text-fill: red; -fx-background-color: black;");

		// Style the labels
		label1.setStyle(
				"-fx-font-size: 20px; -fx-font-family: 'Lucida Console'; -fx-text-fill: green; -fx-background-color: black;");
		label2.setStyle(
				"-fx-font-size: 20px; -fx-font-family: 'Lucida Console'; -fx-text-fill: green; -fx-background-color: black;");

		box = new HBox(250);

		box.getChildren().addAll(backButton, livesLabel, label1, label2);
		box.setAlignment(Pos.CENTER);

		this.setTop(box);
		BorderPane.setAlignment(box, Pos.CENTER);
		box.setPadding(new Insets(10, 20, 10, 20));
	}

	private void updatePositions() {
		updateLineOne(lineOneAliens);
		updateLineTwo(lineTwoAliens);
		updateLineThree(lineThreeAliens);
		updateLineFour(lineFourAliens);
		updateFinalBoss(lineFinalBoss);
	}

	private void updateFinalBoss(List<FinalBoss> line) {
		for (FinalBoss alien : line) {
			startXBoss = alien.move(1);
		}

	}

	private void setNewScore() {
		if (currentAcount != null) {
			if (currentScore > currentAcount.getTopScore()) {
				currentAcount.setTopScore(currentScore);
			}
		}
	}

	private void updateLineOne(List<AlienOne> line) {
		for (AlienOne alien : line) {
			startX = alien.move(mode);
		}
		startX = startX - 350;
	}

	private void updateLineTwo(List<AlienTwo> line) {
		for (AlienTwo alien : line) {
			alien.move(mode);
		}
	}

	private void updateLineThree(List<AlienThree> line) {
		for (AlienThree alien : line) {
			alien.move(mode);
		}
	}

	private void updateLineFour(List<AlienFour> line) {
		for (AlienFour alien : line) {
			alien.move(mode);
		}
	}

	private void drawAliens() {

		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
		drawLineOne();
		drawLineTwo();
		drawLineThree();
		drawLineFour();
		drawFinalBoss();
	}

	public void screenFlicker() {
		Label lifeLostLabel;
		if (lives != 0) {
			lifeLostLabel = new Label("Life Lost!");
		} else {
			lifeLostLabel = new Label("Game Over!");
		}
		lifeLostLabel.setPadding(new Insets(10));
		lifeLostLabel.setFont(new Font("Arial", 24)); // Set the font size to be large
		lifeLostLabel.setTextFill(Color.RED); // Set the font color to red
		lifeLostLabel.setVisible(false); // Initially make the label invisible

		this.setTop(lifeLostLabel); // Add the label to the top of the layout
		BorderPane.setAlignment(lifeLostLabel, Pos.CENTER); // Center the label
		if (this.getScene() != null) {
			Platform.runLater(() -> {

				Timeline flicker = new Timeline(new KeyFrame(Duration.millis(150), e -> {
					drawAliens(); // Redraw only the aliens for visibility
					gc.setFill(Color.BLACK);
					gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
					lifeLostLabel.setVisible(true); // Show the label
				}), new KeyFrame(Duration.millis(250), e -> {
					drawAliens(); // Redraw only the aliens for visibility
					drawSpaceShips();
					lifeLostLabel.setVisible(true); // Ensure label is visible during alien display

				}), new KeyFrame(Duration.millis(400), e -> {
					drawAliens(); // Redraw only the aliens for visibility
					gc.setFill(Color.BLACK);
					gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
					lifeLostLabel.setVisible(true); // Show the label
				}), new KeyFrame(Duration.millis(550), e -> {
					drawAliens(); // Redraw only the aliens for visibility
					drawSpaceShips();

				}));
				flicker.setCycleCount(5); // Repeat the flicker pattern twice
				flicker.setOnFinished(event -> {
					updateLivesLabel(); // Update lives label after flicker
					this.setTop(box); // Restore the original top box after flicker ends
				});
				flicker.play();
			});
		} else {
			System.out.println("Scene is not available.");
		}
	}

	public void deathAnimation(double x, double y) {
		final int explosionSize = 70;
		Image explosionImage = new Image("File:src/images/explosion.png");

		if (explosionImage.isError()) {
			System.out.println("Error loading explosion image: " + explosionImage.getException().getMessage());
			return;
		}

		// Draw the explosion image centered on (x, y)
		gc.drawImage(explosionImage, x - explosionSize / 2, y - explosionSize / 2, explosionSize + 20, explosionSize);

		// Clear the explosion image after a longer pause
		PauseTransition pause = new PauseTransition(Duration.millis(1000)); // 1 second
		pause.setOnFinished(
				event -> gc.clearRect(x - explosionSize / 2, y - explosionSize / 2, explosionSize + 20, explosionSize));
		pause.play();
	}

	public void gameOver() {
		if (yCord == 850 || lives <= 0) {
			lives = 0;
			gc.fillText("Game Over!", 450, 450);
			gameLoop.stop();
			acounts.saveCurrentList();
			setNewScore();
			String path = "src/images/sinister.wav";
			audioManager.playSound(path);

		}

	}

	private void drawBlock() {
		for (BlockCollection blockCollection : Arrays.asList(block1, block2, block3)) {
			for (StaticBlock block : blockCollection.getBlock()) {
				Rectangle rect = block.getBlock();
				if (rect != null) {
					gc.setFill(rect.getFill());

					gc.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
				}
			}
		}
	}

	private void drawSpaceShips() {
		gc.clearRect(spaceShipOne.getXCord(), spaceShipOne.getYCord(), 60, 60);
		gc.drawImage(spaceShipOne.getImageView(), spaceShipOne.getXCord(), spaceShipOne.getYCord(), 60, 60);
	}

	private void shoot() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastShotTime >= shotCooldown) {
			double bulletStartX = spaceShipOne.getXCord() + spaceShipOne.getWidth() / 2;
			double bulletStartY = spaceShipOne.getYCord();
			LaserShot newShot = new LaserShot(bulletStartX, bulletStartY, true);
			activeShots.add(newShot);
			lastShotTime = currentTime;
			String path = "src/images/blaster.mp3";
			audioManager.playSound(path);
		}
	}

	private void handlePlayerInput() {
		if (keysPressed.contains(KeyCode.LEFT) || keysPressed.contains(KeyCode.A)) {
			if (spaceShipOne.getValidMove(spaceShipOne.getXCord() - 3, spaceShipOne.getYCord())) {
				spaceShipOne.setXCord(spaceShipOne.getXCord() - 3);
			}
		}
		if (keysPressed.contains(KeyCode.RIGHT) || keysPressed.contains(KeyCode.D)) {
			if (spaceShipOne.getValidMove(spaceShipOne.getXCord() + 3, spaceShipOne.getYCord())) {
				spaceShipOne.setXCord(spaceShipOne.getXCord() + 3);
			}
		}
		if (keysPressed.contains(KeyCode.SPACE) || keysPressed.contains(KeyCode.W)
				|| keysPressed.contains(KeyCode.UP)) {
			shoot();

		}
		if (keysPressed.contains(KeyCode.O)) {
			double bulletStartX = 0;
			double bulletStartY = spaceShipOne.getYCord() + 80;
			for (int i = 0; i < 100; i++) {
				LaserShot newShot = new LaserShot(bulletStartX + i * 10, bulletStartY, true);
				activeShots.add(newShot);
			}
		}
		drawSpaceShips(); // Ensure the spaceship is redrawn only after all input is processed
	}

	private void setupKeyListeners() {
		this.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			keysPressed.add(event.getCode());
			event.consume(); // Prevent further propagation of the key event
		});
		// Released keys remove from the Set
		this.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
			keysPressed.remove(event.getCode());
			event.consume();
		});

		// Game loop to handle continuous input
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.millis(20), ae -> updateAndDraw()); // Adjust the millisecond value as
																				// necessary for smoother gameplay
		gameLoop.getKeyFrames().add(kf);
		gameLoop.play();
	}

	public static void toggleDirection() {
		direction = !direction;
		yCord += 10;
	}

	public static void toggleDirectionBoss() {
		directionBoss = !directionBoss;
	}

	private void updateAndDraw() {
		handlePlayerInput(); // Handle the input before updating positions and drawing
		gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
		updatePositions();
		drawAliens();
		drawSpaceShips();
		drawBlock();
		drawShots();
		updateShots();
		gameOver();
		alienShoot();
		nextRound();

		activeShots.updateList();
		label2.setText(currentScore + "");
	}

	private void lostCheck() {
		if (lives <= 0) {
			gameOver();

		} else {
			gc.setFill(Color.YELLOW);
			gc.setFont(new Font("Arial", 36));
			gc.fillText("LIFE LOST", 450, 450);
		}
	}

	private void pauseGame() {
		gameLoop.pause();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));

		pause.setOnFinished(event -> {
			gc.clearRect(430, 430, 200, 50);
			gameLoop.play();
			updateAndDraw();
			lostCheck();

		});
		pause.play();

	}

	private void nextRound() {
		// Check if the boss is already present
		if (lineFinalBoss.get(0).isHit()) {
			lineFinalBoss.clear(); // Clear existing (defeated) boss
			lineFinalBoss.add(new FinalBoss(-700, 0, activeShots)); // Add new boss at specific start position

		}

		// Add a new boss if all aliens are defeated or under specific conditions
		if (lineFinalBoss.isEmpty() || allAliensDefeated()) {
			lineFinalBoss.clear(); // Clear existing (defeated) boss
			lineFinalBoss.add(new FinalBoss(-700, 0, activeShots)); // Add new boss at specific start position
		}
		if (allAliensDefeated()) {
			lineOneAliens.clear();
			lineTwoAliens.clear();
			lineThreeAliens.clear();
			lineFourAliens.clear();
			yCord = 0;
			lives += 3;
			initaiteAlien();
		}
	}

	private boolean allAliensDefeated() {
		return lineOneAliens.stream().allMatch(AlienOne::isHit) && lineTwoAliens.stream().allMatch(AlienTwo::isHit)
				&& lineThreeAliens.stream().allMatch(AlienThree::isHit)
				&& lineFourAliens.stream().allMatch(AlienFour::isHit);
	}

	private void updateShots() {

		for (LaserShot shots : activeShots.getActiveShots()) {
			if (shots.getYCord() >= 2000 || shots.getYCord() <= 0) {
				shots.setHit();
			}
			for (StaticBlock block : block1.getBlock()) {
				if (block.getXCord() <= shots.getXCord() + 30
						&& block.getXCord() + 20 >= shots.getXCord() + 30 & block.getYCord() <= shots.getYCord()
						&& block.getYCord() + 10 >= shots.getYCord() && !shots.getStatus() && shots.getType() == 0
						&& !(block.getHitStatus() == 0)) {
					shots.setHit();
					block.gotHit();
				}
			}

			for (StaticBlock block : block2.getBlock()) {
				if (block.getXCord() <= shots.getXCord() + 30
						&& block.getXCord() + 20 >= shots.getXCord() + 30 & block.getYCord() <= shots.getYCord()
						&& block.getYCord() + 20 >= shots.getYCord() && !shots.getStatus() && shots.getType() == 0
						&& !(block.getHitStatus() == 0)) {
					shots.setHit();
					block.gotHit();
				}
			}
			for (StaticBlock block : block3.getBlock()) {
				if (block.getXCord() <= shots.getXCord() + 30
						&& block.getXCord() + 20 >= shots.getXCord() + 30 & block.getYCord() <= shots.getYCord()
						&& block.getYCord() + 20 >= shots.getYCord() && !shots.getStatus() && shots.getType() == 0
						&& !(block.getHitStatus() == 0)) {
					shots.setHit();
					block.gotHit();
				}
			}

			if (spaceShipOne.getXCord() - 20 <= shots.getXCord() && spaceShipOne.getXCord() + 20 >= shots.getXCord()
					&& spaceShipOne.getYCord() - 20 <= shots.getYCord()
					&& spaceShipOne.getYCord() + 20 >= shots.getYCord() && !shots.getStatus() && !shots.getDirection()
					&& shots.getType() == 0) {
				shots.setHit();
				lives--;
				updateLivesLabel();
				screenFlicker();
				pauseGame();

			}
			if (spaceShipOne.getXCord() - 20 <= shots.getXCord() && spaceShipOne.getXCord() + 20 >= shots.getXCord()
					&& spaceShipOne.getYCord() - 20 <= shots.getYCord()
					&& spaceShipOne.getYCord() + 20 >= shots.getYCord() && !shots.getStatus() && !shots.getDirection()
					&& shots.getType() == 1) {
				shots.setHit();
				lives++;
				updateLivesLabel();
			}
			for (AlienOne alien1 : lineOneAliens) {

				if (alien1.getXCord() - 15 <= shots.getXCord()
						&& alien1.getXCord() + 15 >= shots.getXCord() & alien1.getYCord() - 15 <= shots.getYCord()
						&& alien1.getYCord() + 15 >= shots.getYCord() && !shots.getStatus() && !alien1.isHit()
						&& shots.getDirection()) {
					currentScore += alien1.getPoints();
					deathAnimation(alien1.getXCord(), alien1.getYCord());
					shots.setHit();
					alien1.setHit();
				}
			}

			for (AlienTwo alien2 : lineTwoAliens) {
				if (alien2.getXCord() - 15 <= shots.getXCord()
						&& alien2.getXCord() + 15 >= shots.getXCord() & alien2.getYCord() - 15 <= shots.getYCord()
						&& alien2.getYCord() + 15 >= shots.getYCord() && !shots.getStatus() && !alien2.isHit()
						&& shots.getDirection()) {
					currentScore += alien2.getPoints();
					deathAnimation(alien2.getXCord(), alien2.getYCord());
					shots.setHit();
					alien2.setHit();

				}
			}

			for (AlienThree alien3 : lineThreeAliens) {

				if (alien3.getXCord() - 15 <= shots.getXCord()
						&& alien3.getXCord() + 15 >= shots.getXCord() & alien3.getYCord() - 15 <= shots.getYCord()
						&& alien3.getYCord() + 15 >= shots.getYCord() && !shots.getStatus() && !alien3.isHit()
						&& shots.getDirection()) {
					currentScore += alien3.getPoints();
					deathAnimation(alien3.getXCord(), alien3.getYCord());
					shots.setHit();
					alien3.setHit();

				}
			}
			for (AlienFour alien4 : lineFourAliens) {
				if (alien4.getXCord() - 15 <= shots.getXCord()
						&& alien4.getXCord() + 15 >= shots.getXCord() & alien4.getYCord() - 15 <= shots.getYCord()
						&& alien4.getYCord() + 15 >= shots.getYCord() && !shots.getStatus() && !alien4.isHit()
						&& shots.getDirection()) {
					currentScore += alien4.getPoints();
					deathAnimation(alien4.getXCord(), alien4.getYCord());

					// Special abilities
					Random rand = new Random();
					if (rand.nextInt(3) == 0) {
						LaserShot shot = new LaserShot(alien4.getXCord() + scaledWidth / 2, alien4.getYCord(), false);
						shot.setType();
						shot.getImageView();
						this.activeShots.add(shot);

					}
					shots.setHit();
					alien4.setHit();

				}
			}
			FinalBoss alien5 = lineFinalBoss.get(0);
			if (alien5.getXCord() - 35 <= shots.getXCord()
					&& alien5.getXCord() + 35 >= shots.getXCord() & alien5.getYCord() - 35 <= shots.getYCord()
					&& alien5.getYCord() + 35 >= shots.getYCord() && !shots.getStatus()) {
				currentScore += alien5.getPoints();
				deathAnimation(alien5.getXCord(), alien5.getYCord());
				shots.setHit();
				alien5.setHit();
				String path = "src/images/impressive.wav";
				audioManager.playSound(path);

			}

		}

		Iterator<LaserShot> iterator = activeShots.getActiveShots().iterator();
		while (iterator.hasNext()) {
			LaserShot shot = iterator.next();
			if (shot.getStatus()) {
				iterator.remove(); // Safely removes the shot
			}

		}

	}

	public void updateLivesLabel() {
		livesLabel.setText("LIVES: " + lives);
	}

	private void drawShots() {
		for (LaserShot shot : activeShots.getActiveShots()) {
			if (shot != null && shot.getImageView() != null && !shot.getStatus()) {
				gc.drawImage(shot.getImageView().getImage(), shot.getImageView().getX(), shot.getImageView().getY());
			}
		}

	}

	private void drawLineOne() {
		double startY = 150 + yCord;
		double stepX = 50;
		for (int i = 0; i < lineOneAliens.size(); i++) {
			AlienOne alien = lineOneAliens.get(i);
			if (alien != null && alien.getImageView() != null && !alien.isHit()) {
				alien.setPosition(startX + i * stepX, startY);
				gc.drawImage(alien.getImageView(), alien.getXCord(), alien.getYCord(), scaledWidth, scaledHeight);
			}
		}
	}

	private void drawLineTwo() {
		double startY = 100 + yCord;
		double stepX = 50;

		for (int i = 0; i < lineTwoAliens.size(); i++) {
			AlienTwo alien = lineTwoAliens.get(i);
			if (alien != null && alien.getImageView() != null && !alien.isHit()) {
				alien.setPosition(startX + i * stepX, startY);
				gc.drawImage(alien.getImageView(), alien.getXCord(), alien.getYCord(), scaledWidth, scaledHeight);
			}
		}
	}

	private void drawLineThree() {
		double startY = 50 + yCord;
		double stepX = 50;

		for (int i = 0; i < lineThreeAliens.size(); i++) {
			AlienThree alien = lineThreeAliens.get(i);
			if (alien != null && alien.getImageView() != null && !alien.isHit()) {
				alien.setPosition(startX + i * stepX, startY);
				gc.drawImage(alien.getImageView(), alien.getXCord(), alien.getYCord(), scaledWidth, scaledHeight);
			}
		}
	}

	private void drawLineFour() {
		double startY = 0 + yCord;
		double stepX = 50;

		for (int i = 0; i < lineFourAliens.size(); i++) {
			AlienFour alien = lineFourAliens.get(i);
			if (alien != null && alien.getImageView() != null && !alien.isHit()) {
				alien.setPosition(startX + i * stepX, startY);
				gc.drawImage(alien.getImageView(), alien.getXCord(), alien.getYCord(), scaledWidth, scaledHeight);
			}
		}
	}

	private void drawFinalBoss() {
		if (!lineFinalBoss.isEmpty()) {
			double startY = 0;
			FinalBoss alien = lineFinalBoss.get(0);
			if (alien != null && alien.getImageView() != null && !alien.isHit()) {
				alien.setPosition(startXBoss, startY);
				gc.drawImage(alien.getImageView(), alien.getXCord(), alien.getYCord(), scaledWidth + 20,
						scaledHeight + 20);
			}
		}
	}

	public static boolean getDirection() {
		return direction;

	}

	public static boolean getDirectionBoss() {
		return directionBoss;

	}

}
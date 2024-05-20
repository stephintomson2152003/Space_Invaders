package objects;

import controller_view.GameViewPaneGUI;
import javafx.scene.image.Image;

import javafx.scene.layout.BorderPane;
import javafx.animation.TranslateTransition;

import javafx.util.Duration;
import model.LaserShot;
import model.LaserShotCollection;

public class AlienOne extends BorderPane {
	private String name;
	private boolean hit;
	private double points;
	private String imagePath;
	private Image imageView;
	private LaserShot shot;
	private Double xCord;
	private Double yCord;
	private LaserShotCollection activeShots;
	private TranslateTransition transition;

	// Constructor to initialize the alien object
	public AlienOne(Double xCord, Double yCord, LaserShotCollection activeShots) {
		this.name = "Alien One";
		this.hit = false;
		this.points = 20;
		this.yCord = yCord;
		this.xCord = xCord;
		this.imagePath = "File:" + "alien1.png";
		this.activeShots = activeShots;
		this.imageView = createImageView();

		// Set up the TranslateTransition
		transition = new TranslateTransition(Duration.millis(2000));
		transition.setByX(400); // move by 400 pixels horizontally
		transition.setCycleCount(TranslateTransition.INDEFINITE);
		transition.setAutoReverse(true);
	}

	public void playAnimation() {
		transition.play();
	}

	// Method to create an ImageView from the imagePath
	private Image createImageView() {
		Image image = new Image(imagePath); // Load image from the imagePath
		return image;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit() {
		this.hit = true;
		this.imagePath = null;
		this.points = 0;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public String getImagePath() {
		return imagePath;
	}

	public Double getXCord() {
		return xCord;
	}

	public Double getYCord() {
		return yCord;
	}

	public void fire() {
		shot = new LaserShot(xCord, yCord, false);
		activeShots.add(shot);
	}

	// Method to get the ImageView of the alien, for display purposes
	public Image getImageView() {
		return imageView;
	}

	public double move(double dx) {

		xCord += GameViewPaneGUI.getDirection() ? dx : -dx;

		if (xCord <= 0 || xCord >= 1050) { // Assuming the canvas width is 1000 and alien width is 50
			GameViewPaneGUI.toggleDirection();
		}
		return xCord;
	}

	public Object resetPosition() {

		return null;
	}

	public void setPosition(double d, double startY) {
		this.xCord = d;
		this.yCord = startY;

	}

	public void stopAnimation() {
		transition.stop();
	}

}

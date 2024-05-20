package objects;

import controller_view.GameViewPaneGUI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.LaserShot;
import model.LaserShotCollection;

public class AlienFour extends BorderPane {
	private String name;
	private boolean hit;
	private double points;
	private String imagePath;
	private Image imageView;
	private LaserShot shot;
	private Double xCord;
	private Double yCord;
	private LaserShotCollection activeShots;

	// Constructor to initialize the alien object
	public AlienFour(Double xCord, Double yCord, LaserShotCollection activeShots) {
		this.name = "Alien Four";
		this.hit = false;
		this.points = 40;
		this.yCord = yCord;
		this.xCord = xCord;
		this.imagePath = "File:" + "alien4.png";
		this.activeShots = activeShots;
		this.imageView = createImageView();
	}

	// Method to create an ImageView from the imagePath
	public Image createImageView() {
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

	public double move(double d) {

		xCord += GameViewPaneGUI.getDirection() ? d : -d;

		if (xCord <= 0 || xCord >= 950) { // Assuming the canvas width is 1000 and alien width is 50
			GameViewPaneGUI.toggleDirection();
		}
		return xCord;
	}

	public void fire() {
		shot = new LaserShot(xCord, yCord, false);
		activeShots.add(shot);
	}

	// Method to get the ImageView of the alien, for display purposes
	public Image getImageView() {
		return imageView;
	}

	public Object resetPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPosition(double d, double startY) {
		this.xCord = d;
		this.yCord = startY;

	}
}

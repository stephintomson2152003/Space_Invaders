package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.LaserShot;
import model.LaserShotCollection;

public class SpaceShipTwo extends BorderPane {
	private String name;
	private boolean hit;
	private String imagePath;
	private Image imageView;

	private LaserShot shot1;
	private LaserShot shot2;

	private Double xCord;
	private Double yCord;
	private LaserShotCollection activeShots;

	// Constructor to initialize the alien object
	public SpaceShipTwo(double xCord, double yCord, LaserShotCollection activeShots) {
		this.name = "Ship 2";
		this.hit = false;
		this.xCord = xCord;
		this.yCord = yCord;
		this.imagePath = "File:" + "spaceship2.png";
		this.imageView = createImageView();
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

	public boolean isHit() {
		return hit;
	}

	public void setHit() {
		this.hit = true;
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

	public void setXCord(Double xCord) {
		this.xCord = xCord;
	}

	public void setYCord(Double yCord) {
		this.yCord = yCord;
	}

	public void fire() {
		shot1 = new LaserShot(xCord - 1, yCord, true);
		shot2 = new LaserShot(xCord + 1, yCord, true);
		activeShots.add(shot1);
		activeShots.add(shot2);
	}

	// Method to get the ImageView of the alien, for display purposes
	public Image getImageView() {
		return imageView;
	}
}

package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.LaserShot;
import model.LaserShotCollection;

public class SpaceShipThree extends BorderPane {
	private String name;
	private boolean hit;
	private String imagePath;
	private Image imageView;
	private LaserShot shot;
	private Integer xCord;
	private Integer yCord;
	private LaserShotCollection activeShots;

	// Constructor to initialize the alien object
	public SpaceShipThree(Integer xCord, Integer yCord, LaserShotCollection activeShots) {
		this.name = "Ship 3";
		this.hit = false;
		this.imagePath = "File:" + "spaceship3.png";
		this.imageView = createImageView();
		this.xCord = xCord;
		this.yCord = yCord;
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

	public String getImagePath() {
		return imagePath;
	}

	public void fire() {
		shot = new LaserShot(xCord - 1, yCord, true);
		activeShots.add(shot);
	}

	public Integer getXCord() {
		return xCord;
	}

	public Integer getYCord() {
		return yCord;
	}

	public void setXCord(Integer xCord) {
		this.xCord = xCord;
	}

	public void setYCord(Integer yCord) {
		this.yCord = yCord;
	}

	// Method to get the ImageView of the alien, for display purposes
	public Image getImageView() {
		return imageView;
	}
}

package objects;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.LaserShot;
import model.LaserShotCollection;

public class SpaceShipOne extends BorderPane {
	private String name;
	private boolean hit;
	private String imagePath;
	private Image imageView;
	private LaserShot shot;
	private Integer xCord;
	private Integer yCord;
	private LaserShotCollection activeShots;

	// Constructor to initialize the alien object
	public SpaceShipOne(Integer xCord, Integer yCord, LaserShotCollection activeShots) {
		this.name = "Ship 1";
		this.hit = false;
		this.imagePath = "File:" + "spaceship1.png";
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

	public boolean isHit() {
		return hit;
	}

	public void setHit() {
		this.hit = true;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void fire() {
		shot = new LaserShot(xCord, yCord, true);
		activeShots.add(shot);
	}

	// Method to get the ImageView of the alien, for display purposes
	public Image getImageView() {
		return imageView;
	}

	public boolean getValidMove(int xCord, Integer yCord) {
		if (xCord <= 850 && xCord >= 0) {
			return true;
		}
		return false;
	}
}

package objects;

import controller_view.GameViewPaneGUI;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import model.LaserShot;
import model.LaserShotCollection;

public class FinalBoss extends BorderPane {
	private String name;
	private boolean hit;
	private int points;
	private String imagePath;
	private Image imageView;
	private LaserShot shot;
	private Double xCord;
	private Double yCord;
	private LaserShotCollection activeShots;

	// Constructor to initialize the alien object
	public FinalBoss(double xCord, double i, LaserShotCollection activeShots) {
		this.name = "Final Boss";
		this.hit = false;
		this.points = 150;
		this.yCord = i;
		this.xCord = xCord;
		this.imagePath = "File:" + "finalboss.png";
		this.activeShots = activeShots;
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

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit() {
		this.hit = true;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
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

		xCord += GameViewPaneGUI.getDirectionBoss() ? d : -d;

		if (xCord <= -1000 || xCord >= 2000) { // Assuming the canvas width is 1000 and alien width is 50
			GameViewPaneGUI.toggleDirectionBoss();
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

	public void setPosition(double d, double i) {
		this.xCord = d;
		this.yCord = i;

	}

}

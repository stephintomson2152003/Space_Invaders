package model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LaserShot {
	private double xCord;
	private double yCord;
	private boolean direction;
	private boolean status;
	private ImageView imageView;
	private int type;

	public LaserShot(double d, double yCord2, boolean direction) {
		this.xCord = d;
		this.yCord = yCord2;
		this.direction = direction;
		this.status = false;
		this.imageView = setImageView(); // Make sure to replace with actual path
		this.imageView.setX(d);
		this.imageView.setY(yCord2);
		this.type = 0;
	}

	public void setType() {
		this.type = 1;
		updateImageView();
	}

	public int getType() {
		return type;
	}

	public void propogateShot() {
		if (direction) {
			yCord -= 5; // Move up by 10 units
		} else {
			yCord += 2; // Move down by 10 units
		}
		if (imageView != null) {
			imageView.setY(yCord);
		}
	}

	public void stopShot() {
		if (imageView != null) {
			imageView.setY(-1000);
		}

	}

	private void updateImageView() {
		String imagePath = "File:bullet.png"; // Default image path
		if (direction && type == 0) {
			imagePath = "File:bullet.png";
		} else if (!direction && type == 0) {
			imagePath = "File:bullet2.png";
		} else if (!direction && type == 1) {
			imagePath = "File:bullet3.png";
		}

		this.imageView.setImage(new Image(imagePath));
		this.imageView.setX(xCord);
		this.imageView.setY(yCord);
	}

	public ImageView setImageView() {
		if (direction && type == 0) {
			this.imageView = new ImageView("File:" + "bullet.png");
			return this.imageView;
		} else if (!direction && type == 0) {

			this.imageView = new ImageView("File:" + "bullet2.png");
			return this.imageView;
		} else if (type == 1) {
			this.imageView = new ImageView("File:" + "bullet3.png");
			return this.imageView;
		} else {
			return null;
		}
	}

	public boolean getDirection() {
		return this.direction;
	}

	public ArrayList<Double> getCordinates() {
		ArrayList<Double> cord = new ArrayList<Double>();
		cord.add(xCord);
		cord.add(yCord);
		return cord;

	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setHit() {
		this.status = true;

	}

	public boolean getStatus() {
		return this.status;
	}

	public double getYCord() {
		return yCord;
	}

	public double getXCord() {
		return xCord;
	}

}

package objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StaticBlock {
	private int xCord;
	private int yCord;
	private int hitStatus;
	private Rectangle rect;

	// Constructor with parameters
	public StaticBlock(int xCord, int yCord) {
		rect = new Rectangle(xCord, yCord, 20, 20);
		this.xCord = xCord;
		this.yCord = yCord;
		this.hitStatus = 3; // Initialize hitStatus as requested
	}

	// Getter for xCord
	public int getXCord() {
		return xCord;
	}

	// Getter for yCord
	public int getYCord() {
		return yCord;
	}

	// Getter for hitStatus
	public int getHitStatus() {
		return hitStatus;
	}

	public Rectangle getBlock() {
		if (hitStatus == 3) {
			rect.setFill(Color.web("red")); // Set the color using the web color of the rectangle
		} else if (hitStatus == 2) {
			rect.setFill(Color.web("orange"));
		} else if (hitStatus == 1) {
			rect.setFill(Color.web("yellow"));
		} else if (hitStatus == 0) {
			rect.setFill(Color.web("black"));
		}
		return rect;
	}

	// If there's functionality to change the hit status, you might also need a
	// setter.
	public void gotHit() {
		this.hitStatus = hitStatus - 1;
	}
}

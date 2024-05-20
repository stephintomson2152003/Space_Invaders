package objects;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Asteroid extends BorderPane {
    private String name;
    private boolean hit;
    private String imagePath;
    private Image imageView;
    private Integer xCord;
    private Integer yCord;

    public Asteroid(Integer xCord, Integer yCord) {
        this.name = "Asteroid";
        this.hit = false;
        this.yCord = yCord;
        this.xCord = xCord;
        this.imagePath = "File:" + "asteroid.png"; 
        this.imageView = createImageView();
    }


    private Image createImageView() {
        Image image = new Image(imagePath); 
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Integer getXCord() {
        return xCord;
    }

    public Integer getYCord() {
        return yCord;
    }

    public Image getImageView() {
        return imageView;
    }

}

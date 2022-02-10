package application;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Pomme extends Rectangle {

	public Pomme() {
		Image image = new Image("application/images/pomme.png");
		this.setFill(new ImagePattern(image));
		this.setWidth(15);
		this.setHeight(15);
		
	}
}

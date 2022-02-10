package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Serpent {
	
	private List<MorceauCorps> corps;
	
	public Serpent(List<MorceauCorps> corps) {
		this.corps = corps;
	}
	
	
	
	public List<MorceauCorps> getCorps() {
		return corps;
	}
	
	public void addCorps(MorceauCorps morceau) {
		corps.add(morceau);
	}
	
	public void viderCorps() {
		this.corps.clear();
	}
	
	public void remplacerCorps( List<MorceauCorps> corps) {
		this.corps = corps;
	}
	
	public void changeImage() {
		Image image;
		for(MorceauCorps mc : corps) {
			
			switch(mc.getDirection()) {
			case "droite":
				if(mc.getIndice() == 0) {
					image = new Image("application/images/tete_serpent_droite.png");
				}else {
					if(mc.getIndice() == (corps.size()-1)) {
						image = new Image("application/images/queue_serpent_droite.png");
					}else {
						image = new Image("application/images/corps_serpent_horizontal.png");
					}

					
				}
				 
				mc.setFill(new ImagePattern(image));
				break;
			case "haut":
				if(mc.getIndice() == 0) {
					image = new Image("application/images/tete_serpent_haut.png");
				}else{
					if(mc.getIndice() == (corps.size()-1)) {
						image = new Image("application/images/queue_serpent_haut.png");
					}else {
						image = new Image("application/images/corps_serpent_vertical.png");
					}
				}
				
				mc.setFill(new ImagePattern(image));
				break;
			case "bas":
				if(mc.getIndice() == 0) {
					image = new Image("application/images/tete_serpent_bas.png");
				}else {
					if(mc.getIndice() == (corps.size()-1)) {
						image = new Image("application/images/queue_serpent_bas.png");
					}else {
						image = new Image("application/images/corps_serpent_vertical.png");
					}

					
					
					
				}
				
				mc.setFill(new ImagePattern(image));
				break;
			case "gauche":
				if(mc.getIndice() == 0) {
					image = new Image("application/images/tete_serpent_gauche.png");
				}else {
					if(mc.getIndice() == (corps.size()-1)) {
						image = new Image("application/images/queue_serpent_gauche.png");
					}else {
						image = new Image("application/images/corps_serpent_horizontal.png");
					}

				}
				mc.setFill(new ImagePattern(image));
				break;
			}
			
			
		}
	}

}

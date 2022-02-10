package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MorceauCorps extends Rectangle {

	private String direction;
	private String prochaineDirection;
	private int indice;
	private int prochainX;
	private int prochainY;
	private List<Integer> historiqueParcoursX = new ArrayList<Integer>();
	private List<Integer> historiqueParcoursY = new ArrayList<Integer>();
	private List<String> historiqueParcoursDirection = new ArrayList<String>();
	private int numeroDirection;
	public MorceauCorps(String direction,int X, int Y,int indice, String prochaineDirection){
	
		this.direction = direction;
		this.indice = indice;
		this.setWidth(40);	
		this.setHeight(40);
		this.setX(X);
		this.setY(Y);
		this.prochaineDirection=prochaineDirection;
		this.numeroDirection = 0;
	}
	
	
	public void setX() {
		
		switch(direction) {
		case "gauche":
			this.setX(this.getX()-5);
			break;
		case "droite":
			this.setX(this.getX()+5);
			break;
		}
	}
	
	public void setY() {
		switch(direction) {
		case "haut":
			this.setY(this.getY()-5);
			break;
		case "bas":
			this.setY(this.getY()+5);
			break;
		}
	}
	
	public int getIndice() {
		return indice;
	}
	
	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public void setProchaineDirection(String direction) {
		this.prochaineDirection = direction;
	}
	
	public String getProchaineDirection() {
		return prochaineDirection;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public int getProchainX() {
		return prochainX;
	}
	
	public int getProchainY() {
		return prochainY;
	}
	

	public void setProchainX(int X) {
		this.prochainX = X;
	}
	
	public void setProchainY(int Y) {
		this.prochainY = Y;
	}
	
	public void avancer() {
		setX();
		setY();
		
	}
	
	public void setHistorique() {
		historiqueParcoursX.add(prochainX);
		historiqueParcoursY.add(prochainY);
		historiqueParcoursDirection.add(prochaineDirection);
	}
	
	public void setHistorique(List<Integer> histoX, List<Integer> listoY, List<String> listoDirection) {
		historiqueParcoursX = histoX;
		historiqueParcoursY = listoY;
		historiqueParcoursDirection = listoDirection;
	}
	
	public List<Integer> getHistoriqueX(){
		return historiqueParcoursX;
	}
	
	public List<Integer> getHistoriqueY(){
		return historiqueParcoursY;
	}
	
	public List<String> getHistoriqueDirection(){
		return historiqueParcoursDirection;
	}
	
	public int getNumeroDirection() {
		return numeroDirection;
	}

	public void incrementNumeroDirection() {
		numeroDirection++;
		
	}
	
	public void setNumeroDirection(int numero) {
		this.numeroDirection = numero;
	}
	
}

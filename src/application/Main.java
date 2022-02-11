package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	private boolean debut = false;
	private boolean gameOver = false;
	private List<Integer> dimensions = new ArrayList<Integer>();
	private int pommeMangee = 0;
	private boolean agrandirSerpent = false;
	private boolean restart = false;

	@Override
	public void start(Stage primaryStage) {
		try {

			AnchorPane root = new AnchorPane();
			Text TextPommeMangee = new Text("pomme mang�e : 0");
			Text TextGameOver = new Text("GAME OVER");
			TextPommeMangee.setX(10);
			TextPommeMangee.setY(25);
			TextGameOver.setX(250);
			TextGameOver.setY(300);
			TextGameOver.setVisible(false);
			
			TextPommeMangee.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));

			TextGameOver.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

			// g�n�ration du terrain
			int decalageX = 0;
			int decalageY = 0;
			dimensions.add(decalageY);
			for (int i = 1; i <= 225; i++) {
				Rectangle nouveauCarre = new Rectangle();
				nouveauCarre.setWidth(40);
				nouveauCarre.setHeight(40);
				nouveauCarre.setX(0 + decalageX);
				nouveauCarre.setY(0 + decalageY);
				if (i % 2 == 0) {
					nouveauCarre.setFill(Color.ALICEBLUE);
				} else {
					nouveauCarre.setFill(Color.BEIGE);
				}
				root.getChildren().add(nouveauCarre);

				decalageX += 40;

				if (i % 15 == 0 && i != 225) {
					decalageX = 0;
					decalageY += 40;
					dimensions.add(decalageY);
				}
			}

			MorceauCorps queue = new MorceauCorps("droite", 120, 240, 2, "droite");
			MorceauCorps c1 = new MorceauCorps("droite", 160, 240, 1, "droite");
			MorceauCorps tete = new MorceauCorps("droite", 200, 240, 0, "droite");

			List<MorceauCorps> corps = new ArrayList<MorceauCorps>();
			corps.add(tete);
			corps.add(c1);
			corps.add(queue);

			Serpent serpent = new Serpent(corps);

			ajouterSerpent(serpent, root);
			serpent.changeImage();

			Scene scene = new Scene(root, 600, 600);
			primaryStage.setTitle("PROJET SNAKE IHM");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			// cr�ation de la pomme
			Pomme lapomme = new Pomme();
			changerPlacerPomme(dimensions, lapomme, root, serpent);
			root.getChildren().add(lapomme);
			root.getChildren().add(TextPommeMangee);
			root.getChildren().add(TextGameOver);
			

			scene.setOnKeyPressed(e -> {


				gameOver = false;
				TextGameOver.setVisible(false);

				if (e.getCode() == KeyCode.ESCAPE) {
					System.exit(0);
				}

				this.debut = true;
				MorceauCorps latete = serpent.getCorps().get(0);
				int prochainX = (int) latete.getX();
				int prochainY = (int) latete.getY();
				boolean trouvX = false;
				boolean trouvY = false;

				if (e.getCode() == KeyCode.UP && latete.getDirection() != "bas") {

					for (int i : dimensions) {

						if (latete.getDirection() == "droite") {
							if (i > latete.getX() && trouvX != true) {
								prochainX = i;
								trouvX = true;
							}
						}

						else if (latete.getDirection() == "gauche") {
							if (i < latete.getX()) {
								prochainX = i;
							}
						}

						else if (latete.getDirection() == "haut") {
							if (i < latete.getY()) {
								prochainY = i;
							}
						}

					}

					latete.setProchaineDirection("haut");
					latete.setProchainX(prochainX);
					latete.setProchainY(prochainY);

				}

				if (e.getCode() == KeyCode.LEFT && latete.getDirection() != "droite") {

					for (int i : dimensions) {

						if (latete.getDirection() == "gauche") {
							if (i < latete.getX()) {
								prochainX = i;
							}
						}

						else if (latete.getDirection() == "bas") {
							if (i > latete.getY() && trouvY != true) {
								prochainY = i;
								trouvY = true;
							}
						}

						else if (latete.getDirection() == "haut") {
							if (i < latete.getY()) {
								prochainY = i;
							}
						}

					}

					latete.setProchaineDirection("gauche");
					latete.setProchainX(prochainX);
					latete.setProchainY(prochainY);
				}

				if (e.getCode() == KeyCode.DOWN && latete.getDirection() != "haut") {

					for (int i : dimensions) {

						if (latete.getDirection() == "droite") {
							if (i > latete.getX() && trouvX != true) {
								prochainX = i;
								trouvX = true;
							}
						}

						else if (latete.getDirection() == "gauche") {
							if (i < latete.getX()) {
								prochainX = i;
							}
						}

						else if (latete.getDirection() == "bas") {
							if (i > latete.getY() && trouvY != true) {
								prochainY = i;
								trouvY = true;
							}
						}

					}
					latete.setProchaineDirection("bas");
					latete.setProchainX(prochainX);
					latete.setProchainY(prochainY);
				}

				if (e.getCode() == KeyCode.RIGHT && latete.getDirection() != "gauche") {

					for (int i : dimensions) {

						if (latete.getDirection() == "droite") {
							if (i > latete.getX() && trouvX != true) {
								prochainX = i;
								trouvX = true;
							}
						}

						else if (latete.getDirection() == "bas") {
							if (i > latete.getY() && trouvY != true) {
								prochainY = i;
								trouvY = true;
							}
						}

						else if (latete.getDirection() == "haut") {
							if (i < latete.getY()) {
								prochainY = i;
							}
						}

					}
					latete.setProchaineDirection("droite");
					latete.setProchainX(prochainX);
					latete.setProchainY(prochainY);
				}

			});

			// tache du timer

			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(12), ev -> {

				if (gameOver == true && restart != true) {
					restart(dimensions, lapomme, root, serpent, TextPommeMangee);
					TextGameOver.setVisible(true);
					restart = true;
				}

				if (debut && gameOver != true) {
					// faire avancer le serpent

					checkCollision(serpent.getCorps().get(0), root, serpent, lapomme);
					MorceauCorps latete = serpent.getCorps().get(0);

					for (MorceauCorps n : serpent.getCorps()) {

						MorceauCorps mc = (MorceauCorps) n;
						if (mc.getIndice() == 0) {

							if (mc.getX() == mc.getProchainX() && mc.getY() == mc.getProchainY()) { // changer la
																									// direction
																									// quand on
																									// arrive dans
																									// un cube
								mc.setDirection(mc.getProchaineDirection());
								mc.setProchaineDirection(mc.getProchaineDirection());
								mc.setHistorique();
							}

							if (mc.intersects(lapomme.getBoundsInLocal())) { // intersection avec la pomme
								if(pommeMangee < 222) {
									
								
								changerPlacerPomme(dimensions, lapomme, root, serpent);
								pommeMangee++;
								if (pommeMangee > 1) {
									TextPommeMangee.setText("pommes mang�es : " + pommeMangee);
								} else {
									TextPommeMangee.setText("pomme mang�e : " + pommeMangee);
								}
								agrandirSerpent = true;
								}

							}

							// si la tete touche le mur

						} else {
							// d�finir l'historique de d�placement
							if (mc.getNumeroDirection() < latete.getHistoriqueX().size()) {
								if (mc.getX() == latete.getHistoriqueX().get(mc.getNumeroDirection())
										&& mc.getY() == latete.getHistoriqueY().get(mc.getNumeroDirection())) {

									mc.setDirection(latete.getHistoriqueDirection().get(mc.getNumeroDirection()));
									mc.incrementNumeroDirection();

								}

							}
						}

						mc.avancer();
					}
				}

				serpent.changeImage();

				if (agrandirSerpent) {
					agrandirSerpent(serpent, root);
					agrandirSerpent = false;
				}

			}));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void changerPlacerPomme(List<Integer> dimensions, Pomme lapomme, Pane root, Serpent serpent) {
		int x = (int) (Math.random() * dimensions.size());
		int y = (int) (Math.random() * dimensions.size());
		boolean bonnePosition = true;

		lapomme.setY(dimensions.get(y) + 15);
		lapomme.setX(dimensions.get(x) + 15);

		for (MorceauCorps n : serpent.getCorps()) {
			MorceauCorps mc = (MorceauCorps) n;
			if (mc.intersects(lapomme.getBoundsInLocal())) {
				bonnePosition = false;
			}
		}

		while (bonnePosition == false) {

			x = (int) (Math.random() * dimensions.size());
			y = (int) (Math.random() * dimensions.size());
			bonnePosition = true;
			
			lapomme.setY(dimensions.get(y) + 15);
			lapomme.setX(dimensions.get(x) + 15);

			for (MorceauCorps n : serpent.getCorps()) {
				MorceauCorps mc = (MorceauCorps) n;
				if (mc.intersects(lapomme.getBoundsInLocal())) {
					bonnePosition = false;
				}
			}

		}
		//System.out.println("pomme : x -> "+x+"/ y -> "+y);
		

	}

	public void ajouterSerpent(Serpent serpent, Pane root) {
		for (MorceauCorps mc : serpent.getCorps()) {
			root.getChildren().add(mc);
		}
	}

	public void agrandirSerpent(Serpent serpent, Pane root) {
		int indice = serpent.getCorps().size(); // le nouvel indice le plus grand
		MorceauCorps queue = serpent.getCorps().get(indice - 1); // je r�cup�re la queue
		String directionQueue = queue.getDirection();
		MorceauCorps newMorceau;

		switch (directionQueue) {
		case "haut":
			newMorceau = new MorceauCorps(directionQueue, (int) queue.getX(), (int) queue.getY() + 40, indice,
					directionQueue);
			newMorceau.setNumeroDirection(queue.getNumeroDirection());
			serpent.addCorps(newMorceau);
			root.getChildren().add(newMorceau);
			break;
		case "bas":
			newMorceau = new MorceauCorps(directionQueue, (int) queue.getX(), (int) queue.getY() - 40, indice,
					directionQueue);
			newMorceau.setNumeroDirection(queue.getNumeroDirection());
			serpent.addCorps(newMorceau);
			root.getChildren().add(newMorceau);
			break;
		case "gauche":
			newMorceau = new MorceauCorps(directionQueue, (int) queue.getX() + 40, (int) queue.getY(), indice,
					directionQueue);
			newMorceau.setNumeroDirection(queue.getNumeroDirection());
			serpent.addCorps(newMorceau);
			root.getChildren().add(newMorceau);
			break;
		case "droite":
			newMorceau = new MorceauCorps(directionQueue, (int) queue.getX() - 40, (int) queue.getY(), indice,
					directionQueue);
			newMorceau.setNumeroDirection(queue.getNumeroDirection());
			serpent.addCorps(newMorceau);
			root.getChildren().add(newMorceau);
			break;

		}

	}

	public void restart(List<Integer> dimensions, Pomme lapomme, Pane root, Serpent serpent, Text TextPommeMangee) {

		for (MorceauCorps mc : serpent.getCorps()) {
			root.getChildren().remove(mc);
		}

		serpent.viderCorps();

		MorceauCorps queue = new MorceauCorps("droite", 120, 240, 2, "droite");
		MorceauCorps c1 = new MorceauCorps("droite", 160, 240, 1, "droite");
		MorceauCorps tete = new MorceauCorps("droite", 200, 240, 0, "droite");

		List<MorceauCorps> corps = new ArrayList<MorceauCorps>();
		corps.add(tete);
		corps.add(c1);
		corps.add(queue);

		pommeMangee = 0;
		serpent.remplacerCorps(corps);
		serpent.changeImage();
		ajouterSerpent(serpent, root);

		TextPommeMangee.setText("pomme mang�e : 0");
		changerPlacerPomme(dimensions, lapomme, root, serpent);
	}

	public void checkCollision(MorceauCorps mc, Pane root, Serpent serpent, Pomme lapomme) {

		Rectangle rectangleCollision = new Rectangle();
		rectangleCollision.setFill(Color.RED);

		switch (mc.getDirection()) {
		case "haut":
			rectangleCollision.setWidth(1);
			rectangleCollision.setHeight(1);
			rectangleCollision.setX(mc.getX() + 20);
			rectangleCollision.setY(mc.getY() + 1);
			break;
		case "bas":
			rectangleCollision.setWidth(1);
			rectangleCollision.setHeight(1);
			rectangleCollision.setX(mc.getX() + 20);
			rectangleCollision.setY(mc.getY() + 39);
			break;
		case "gauche":
			rectangleCollision.setWidth(1);
			rectangleCollision.setHeight(1);
			rectangleCollision.setX(mc.getX() + 1);
			rectangleCollision.setY(mc.getY() + 20);
			break;
		case "droite":
			rectangleCollision.setWidth(1);
			rectangleCollision.setHeight(1);
			rectangleCollision.setX(mc.getX() + 39);
			rectangleCollision.setY(mc.getY() + 20);
			break;
		}

		root.getChildren().add(rectangleCollision);

		// si le rectangle collision touche le corps
		for (MorceauCorps n2 : serpent.getCorps()) {
			MorceauCorps mc2 = (MorceauCorps) n2;
			if (mc2.getIndice() != 0) {
				if (mc2.intersects(rectangleCollision.getBoundsInLocal())) {
					System.out.println("corps touch�");
					gameOver = true;
					debut = false;
					restart = false;
				}
			}
		}

		if (rectangleCollision.getY() < 0) {
			System.out.println("touch� haut");
			gameOver = true;
			debut = false;
			restart = false;
		}
		if (rectangleCollision.getY() > 600) {
			System.out.println("touch� bas");
			gameOver = true;
			debut = false;
			restart = false;
		}
		if (rectangleCollision.getX() < 0) {
			System.out.println("touch� gauche");
			gameOver = true;
			debut = false;
			restart = false;
		}
		if (rectangleCollision.getX() > 600) {
			System.out.println("touch� droite");
			gameOver = true;
			debut = false;
			restart = false;
		}

		/*
		 * switch (mc.getDirection()) { case "haut": if (mc.getY() < 0) {
		 * System.out.println("touch� haut"); gameOver = true; debut = false; restart =
		 * false; } break; case "bas": if (mc.getY() + 35 > 600) {
		 * System.out.println("touch� bas"); gameOver = true; debut = false; restart =
		 * false; }
		 * 
		 * break; case "gauche": if (mc.getX() < 0) {
		 * System.out.println("touch� gauche"); gameOver = true; debut = false; restart
		 * = false;
		 * 
		 * } break; case "droite": if (mc.getX() + 35 > 600) {
		 * System.out.println("touch� droite"); gameOver = true; debut = false; restart
		 * = false; } break; }
		 */

		root.getChildren().remove(rectangleCollision);

	}
}

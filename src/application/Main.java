package application;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

	Random rand = new Random();
	static int SCENE_X = 800;
	static int SCENE_Y = 600;
	static Color color = Color.YELLOW;
	static int palle = 40; // MITU PALLI TAHAD?
	final int radius = 30;
	int ulejoone = 0;
	long startTime = 0;
	//Tere tulemast
	// See meetod kontrollib,kas antud ring asub väljaspool kaste
	public boolean kasVäljas(Circle circle) {
		if (circle.getLayoutX() < 125 && circle.getLayoutY() < 125
				&& circle.getFill() == Color.GREEN) {

			return false;
		}
		else if (circle.getLayoutX() > SCENE_X - 125 && circle.getLayoutY() < 125
				&& circle.getFill() == Color.RED) {

			return false;
		}
		else if (circle.getLayoutX() > SCENE_X - 125
				&& circle.getLayoutY() > SCENE_Y - 125
				&& circle.getFill() == Color.BLUE) {

			return false;
		}
		else if (circle.getLayoutX() < 125 && circle.getLayoutY() > SCENE_Y - 125
				&& circle.getFill() == Color.YELLOW) {

			return false;
		} else {
			return true;
		}
	}

	public void start(final Stage alguslava) {
		Group juur = new Group();
		BorderPane juur2 = new BorderPane();
		Group juur3 = new Group();
		final Scene algus = new Scene(juur2, 220, 100);
		final Scene lõpp = new Scene(juur3, 350, 75);
		final Label tekst = new Label();
		final Stage mangulava = new Stage();
		mangulava.setResizable(false);
		
		Label ylesanne = new Label("Ülesanne on " + palle
				+ " palli ajada kastidesse. \nVäljumiseks mängu ajal vajuta Esc.");
		juur3.getChildren().add(tekst);
		Button start = new Button("Alusta");
		final TextField arv = new TextField("Sisesta enda nimi");
		juur2.setTop(ylesanne);
		juur2.setBottom(start);
		juur2.setCenter(arv);
		final Scene scene = new Scene(juur, SCENE_X, SCENE_Y);
		mangulava.setScene(scene);
		// Anname start nupule tegevuse
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mangulava.show();
				startTime = System.currentTimeMillis();
				alguslava.close();
			}

		});
		// Loome vastavad kastid nurkadessse
		Rectangle vasak1 = new Rectangle(15, 15, 150, 150);
		vasak1.setFill(Color.WHITE);
		vasak1.setStrokeWidth(15);
		vasak1.setStroke(Color.GREEN);
		Rectangle vasak2 = new Rectangle(15, scene.getHeight() - 155, 150, 150);
		vasak2.setFill(Color.WHITE);
		vasak2.setStrokeWidth(15);
		vasak2.setStroke(Color.YELLOW);
		Rectangle parem1 = new Rectangle(scene.getWidth() - 155, 15, 150, 150);
		parem1.setFill(Color.WHITE);
		parem1.setStrokeWidth(15);
		parem1.setStroke(Color.RED);
		Rectangle parem2 = new Rectangle(scene.getWidth() - 155,
				scene.getHeight() - 155, 150, 150);
		parem2.setFill(Color.WHITE);
		parem2.setStrokeWidth(15);
		parem2.setStroke(Color.BLUE);
		// Lisame skoori ülesse
		final Label skoor = new Label(String.valueOf(ulejoone));
		skoor.setLayoutX(scene.getWidth() / 2);
		skoor.setLayoutY(10);
		juur.getChildren().add(skoor);
		juur.getChildren().add(vasak1);
		juur.getChildren().add(vasak2);
		juur.getChildren().add(parem1);
		juur.getChildren().add(parem2);

		// Loome siin pallid ja anname neile värvid
		for (int i = 1; i < palle + 1; i++) {
			final Circle circle = new Circle(radius, color);
			Physics.setRandomXY(circle);
			if (i % (palle / 4) == 0) {
				if (circle.getFill() == Color.YELLOW) {
					color = Color.RED;
				} else if (circle.getFill() == Color.RED) {
					color = Color.GREEN;
				} else if (circle.getFill() == Color.GREEN) {
					color = Color.BLUE;
				}
			}
			// Lisame ringidele äärised
			circle.setStroke(Color.BLACK);
			// Kontrollime,kas loodud ring pole juba kasti sees, kui on,siis ei
			// lase liigutada ja lisame skoorile +1
			if (kasVäljas(circle)) {
				// Lisame võimaluse ringe lohistada
				circle.setOnMouseDragged(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						// Kontrollime,kas ikka on kastidest väljas
						if (kasVäljas(circle)) {
							Physics.drag(circle, event.getSceneX(),
									event.getSceneY());
						} else {
							circle.setOnMouseReleased(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent me) {
									ulejoone++;
									skoor.setText(String.valueOf(ulejoone));
									// Kui skoor on võrdne pallide arvuga,on
									// järelikult kõik pallid sees ja tuleb ette
									// 3. stseen
									if (ulejoone == palle) {
										mangulava.close();
										tekst.setText("Tubli " + arv.getText()
												+ "!\nSa suutsid " + palle + " palli "
												+ ((System.currentTimeMillis() - startTime) / 1000)
												+ " sekundiga kastidesse paigutada.");
										alguslava.setScene(lõpp);
										alguslava.show();

									}
									// overridime sündmused,et ei saaks enam
									// klõpsata ega skoori juurde lisada
									circle.setOnMouseReleased(new TyhiEvent());
									circle.setOnMouseDragged(new TyhiEvent());
								}
							});

						}

					}

				});

			}
			juur.getChildren().add(circle);
		}
		//Lisame võimaluse ESCAPEga sulgeda mangulava
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.ESCAPE) {
					mangulava.close();
					alguslava.show();
					tekst.setText("Kahjuks mäng sai läbi, " + arv.getText()
							+ "! Sinu aeg oli: "
							+ ((System.currentTimeMillis() - startTime) / 1000)
							+ " sekundit \nja sa said "+ skoor.getText() + " palli ajada kastidesse.");
					alguslava.setScene(lõpp);

				}
			}
		});

		alguslava.setResizable(true);
		alguslava.setTitle("Ballpit!");
		alguslava.setScene(algus);
		alguslava.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

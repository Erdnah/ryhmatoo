package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

	Random rand = new Random();
	static int SCENE_X = 800;
	static int SCENE_Y = 600;
	static Color color = Color.YELLOW;
	static int palle = 4; // MITU PALLI TAHAD?
	final int radius = 30;
	int ulejoone = 0;
	long startTime = 0;

	ArrayList<Circle> seesPallid = new ArrayList<Circle>();
	
	// Tere tulemast
	// See meetod kontrollib,kas antud ring asub v�ljaspool kaste
	public boolean kasV�ljas(Circle circle) {
		if (circle.getLayoutX() < 125 && circle.getLayoutY() < 125
				&& circle.getFill() == Color.GREEN) {

			return false;
		} else if (circle.getLayoutX() > SCENE_X - 125
				&& circle.getLayoutY() < 125 && circle.getFill() == Color.RED) {

			return false;
		} else if (circle.getLayoutX() > SCENE_X - 125
				&& circle.getLayoutY() > SCENE_Y - 125
				&& circle.getFill() == Color.BLUE) {

			return false;
		} else if (circle.getLayoutX() < 125
				&& circle.getLayoutY() > SCENE_Y - 125
				&& circle.getFill() == Color.YELLOW) {

			return false;
		} else {
			return true;
		}
	}

	public void start(final Stage alguslava) {
		Group juur = new Group();
		BorderPane juur2 = new BorderPane();
		BorderPane juur3 = new BorderPane();
		final Scene algus = new Scene(juur2, 260, 150);
		final Scene l�pp = new Scene(juur3, 370, 90);
		final Label tekst = new Label();
		final Stage mangulava = new Stage();
		mangulava.setResizable(false);

		Label ylesanne = new Label(
				"�lesanne on "
						+ palle
						+ " palli ajada kastidesse. \nV�ljumiseks m�ngu ajal vajuta Esc.");

		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		VBox vb1 = new VBox();
		hb1.setPadding(new Insets(10));
		hb2.setPadding(new Insets(10));
		vb1.setSpacing(10);
		vb1.setPadding(new Insets(10));
		hb1.getChildren().add(ylesanne);
		vb1.getChildren().add(tekst);

		juur3.setCenter(vb1);
		Button start = new Button("Alusta");
		Button lopp = new Button("L�peta");
		vb1.getChildren().add(lopp);
		lopp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		final TextField arv = new TextField("Sisesta enda nimi");
		hb2.getChildren().add(start);
		juur2.setTop(hb1);
		juur2.setBottom(hb2);
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
		// Lisame skoori �lesse
		final Label skoor = new Label(String.valueOf(ulejoone));
		skoor.setLayoutX(scene.getWidth() / 2);
		skoor.setLayoutY(10);
		juur.getChildren().add(skoor);
		juur.getChildren().add(vasak1);
		juur.getChildren().add(vasak2);
		juur.getChildren().add(parem1);
		juur.getChildren().add(parem2);

		// Loome siin pallid ja anname neile v�rvid
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
			// Lisame ringidele ��rised
			circle.setStroke(Color.BLACK);
			// Lisame v�imaluse ringe lohistada
			circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent event) {
					Physics.drag(circle, event.getSceneX(),
								event.getSceneY());					
				}

			});
			circle.setOnMouseReleased(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (!kasV�ljas(circle) && !seesPallid.contains(circle)) {
						seesPallid.add(circle);
					} else {
						if (seesPallid.contains(circle) && kasV�ljas(circle)) {
							seesPallid.remove(seesPallid.indexOf(circle));
						}
					}
					skoor.setText("" + seesPallid.size());
					if (seesPallid.size() == palle) {
						mangulava.close();
						alguslava.show();
						tekst.setText("Tubli, " + arv.getText()
								+ "! Suutsid "
								+ ((System.currentTimeMillis() - startTime) / 1000)
								+ " sekundiga \najada " + skoor.getText()
								+ " palli kastidesse.");
						alguslava.setScene(l�pp);
					}
				}
			});
			juur.getChildren().add(circle);
		}
		// Lisame v�imaluse ESCAPEga sulgeda mangulava
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.ESCAPE) {
					mangulava.close();
					alguslava.show();
					tekst.setText("Kahjuks m�ng sai l�bi, " + arv.getText()
							+ "! Sinu aeg oli: "
							+ ((System.currentTimeMillis() - startTime) / 1000)
							+ " sekundit \nja sa said " + skoor.getText()
							+ " palli ajada kastidesse.");
					alguslava.setScene(l�pp);

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

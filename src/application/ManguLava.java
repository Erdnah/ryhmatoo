package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ManguLava extends Stage {

	static Label tekst = new Label();
	private ArrayList<Circle> seesPallid = new ArrayList<Circle>();
	public static Label skoor = new Label("0");
	private Group juur = new Group();
	private static long tulemus;
	final Scene scene = new Scene(juur, Main.SCENE_X, Main.SCENE_Y);
	


	static public long getTulemus() {
		return tulemus;
	}

	public static void setTulemus(long tulemus) {
		ManguLava.tulemus = tulemus;
	}

	public void init() {
		setScene(scene);
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
		// final Label skoor = new Label(String.valueOf(ulejoone));
		skoor.setLayoutX(scene.getWidth() / 2);
		skoor.setLayoutY(10);
		juur.getChildren().add(skoor);
		juur.getChildren().add(vasak1);
		juur.getChildren().add(vasak2);
		juur.getChildren().add(parem1);
		juur.getChildren().add(parem2);

		// Loome siin pallid ja anname neile v�rvid
		for (int i = 1; i < Main.palle + 1; i++) {
			final Circle circle = new Circle(Main.radius, Main.color);
			Physics.setRandomXY(circle);
			if (i % (Main.palle / 4) == 0) {
				if (circle.getFill() == Color.YELLOW) {
					Main.color = Color.RED;
				} else if (circle.getFill() == Color.RED) {
					Main.color = Color.GREEN;
				} else if (circle.getFill() == Color.GREEN) {
					Main.color = Color.BLUE;
				}
			}
			// Lisame ringidele ��rised
			circle.setStroke(Color.BLACK);
			// Lisame v?imaluse ringe lohistada
			circle.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					Physics.drag(circle, event.getSceneX(), event.getSceneY());
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
					if (seesPallid.size() == Main.palle) {
						close();
						LopuLava lopulava = new LopuLava();
						long lopp = ((System.currentTimeMillis() - Main.startTime) / 1000);
						ManguLava.setTulemus(lopp);
						try {
							Highscore.init();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lopulava.init();
						tekst.setText("Tubli, " + Main.getNimi() + "! Suutsid "
								+ lopp + " sekundiga \najada "
								+ skoor.getText() + " palli kastidesse.");
					}
				}
			});
			juur.getChildren().add(circle);
		}
		// Lisame v?imaluse ESCAPEga sulgeda mangulava
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.ESCAPE) {
					close();
					LopuLava lopulava = new LopuLava();
					long lopp = ((System.currentTimeMillis() - Main.startTime) / 1000);
					ManguLava.setTulemus(lopp);
					lopulava.init();
					tekst.setText("Kahjuks m�ng sai l�bi, " + Main.getNimi()
							+ "! Sinu aeg oli: " + lopp
							+ " sekundit \nja sa said " + skoor.getText()
							+ " palli ajada kastidesse.");

				}
			}
		});
		setTitle("Ballpit!");
		show();
	}

	// Tere tulemast
	// See meetod kontrollib,kas antud ring asub v�ljaspool kaste
	public boolean kasV�ljas(Circle circle) {
		if (circle.getLayoutX() < 130 && circle.getLayoutY() < 130
				&& circle.getFill() == Color.GREEN) {

			return false;
		} else if (circle.getLayoutX() > Main.SCENE_X - 130
				&& circle.getLayoutY() < 130 && circle.getFill() == Color.RED) {

			return false;
		} else if (circle.getLayoutX() > Main.SCENE_X - 130
				&& circle.getLayoutY() > Main.SCENE_Y - 130
				&& circle.getFill() == Color.BLUE) {

			return false;
		} else if (circle.getLayoutX() < 130
				&& circle.getLayoutY() > Main.SCENE_Y - 130
				&& circle.getFill() == Color.YELLOW) {

			return false;
		} else {
			return true;
		}
	}

}
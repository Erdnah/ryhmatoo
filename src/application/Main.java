package application;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	Random rand = new Random();
	static int SCENE_X = 800;
	static int SCENE_Y = 600;
	static Color color = Color.YELLOW;
	static int palle = 4; // MITU PALLI TAHAD?
	final static int radius = 30;
	int ulejoone = 0;
	static long startTime = 0;
	static TextField arv = null;


	public void start(final Stage alguslava) {

		BorderPane juur = new BorderPane();
		final Scene algus = new Scene(juur, 260, 150);

		Label ylesanne = new Label(
				"�lesanne on "
						+ palle
						+ " palli ajada kastidesse. \nV�ljumiseks m�ngu ajal vajuta Esc.");

		HBox hb1 = new HBox();
		HBox hb2 = new HBox();

		hb1.setPadding(new Insets(10));
		hb2.setPadding(new Insets(10));



		Button start = new Button("Alusta");
		// Anname start nupule tegevuse
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				ManguLava mangulava = new ManguLava();
				mangulava.init();
				startTime = System.currentTimeMillis();
				alguslava.close();
			}

		});

		arv = new TextField("Sisesta enda nimi");
		hb2.getChildren().add(start);
		hb1.getChildren().add(ylesanne);
		juur.setTop(hb1);
		juur.setBottom(hb2);
		juur.setCenter(arv);

		alguslava.setResizable(true);
		alguslava.setTitle("Ballpit!");
		alguslava.setScene(algus);
		alguslava.show();
	}

	public static String getNimi () {
		return arv.getText();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
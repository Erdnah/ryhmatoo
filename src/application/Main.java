package application;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	static Stage alguslava;

	@SuppressWarnings("serial")
	static class LiigaLühikeNimiErind extends Exception {
		LiigaLühikeNimiErind() {
			super();
		}

		LiigaLühikeNimiErind(String s) {
			super(s);
		}
	}
	@SuppressWarnings("serial")
	static class LiigaPikkNimiErind extends Exception {
		LiigaPikkNimiErind() {
			super();
		}

		LiigaPikkNimiErind(String s) {
			super(s);
		}
	}

	public void Kontroll(String nimi) throws LiigaLühikeNimiErind,
			LiigaPikkNimiErind {
		if (nimi.length() < 3) {
			throw new LiigaLühikeNimiErind("Liiga lühike nimi");
		} else if (nimi.length() > 30) {
			throw new LiigaPikkNimiErind("Liiga pikk nimi");
		}
	}

	public void start(final Stage alguslava) {
		
		Main.alguslava = alguslava;

		BorderPane juur = new BorderPane();
		final Scene algus = new Scene(juur, 260, 150);

		final Label ylesanne = new Label(
				"Ülesanne on "
						+ palle
						+ " palli ajada kastidesse. \nVäljumiseks mängu ajal vajuta Esc.");
		final Label error = new Label();
		error.setTextFill(Color.RED);

		VBox vb1 = new VBox();
		HBox hb2 = new HBox();
		vb1.setAlignment(Pos.CENTER);
		vb1.setPadding(new Insets(10));
		hb2.setPadding(new Insets(10));

		Button start = new Button("Alusta");
		// Anname start nupule tegevuse
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					Kontroll(arv.getText());
					ManguLava mangulava = new ManguLava();
					mangulava.init();
					startTime = System.currentTimeMillis();
					alguslava.close();
				} catch (LiigaLühikeNimiErind e) {
					e.printStackTrace();
					error.setText("Sisesta palun pikem nimi!");

				} catch (LiigaPikkNimiErind t) {
					t.printStackTrace();
					error.setText("Sisesta palun lühem nimi!");
				}		
			}

		});

		arv = new TextField("Sisesta enda nimi");
		hb2.getChildren().add(start);
		vb1.getChildren().addAll(ylesanne, error);
		juur.setTop(vb1);
		juur.setBottom(hb2);
		juur.setCenter(arv);

		alguslava.setResizable(true);
		alguslava.setTitle("Ballpit!");
		alguslava.setScene(algus);
		alguslava.show();
	}
	
	public static void showAlguslava () {
		alguslava.show();
	}

	public static String getNimi() {
		return arv.getText();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
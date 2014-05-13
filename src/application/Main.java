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
	
	static class LiigaLühikeNimiErind extends Exception{
		LiigaLühikeNimiErind(){
			super();
		}
		LiigaLühikeNimiErind(String s){
			super(s);
		}
	}
	static class LiigaPikkNimiErind extends Exception{
		LiigaPikkNimiErind(){
			super();
		}
		LiigaPikkNimiErind(String s){
			super(s);
		}
	}
	public void Kontroll(String nimi) throws LiigaLühikeNimiErind,LiigaPikkNimiErind{
		if(nimi.length()<3){
			throw new LiigaLühikeNimiErind("Liiga lühike nimi");
		}
		else if(nimi.length()>30){
			throw new LiigaPikkNimiErind("Liiga pikk nimi");	
		}
	}


	@Override
	public void start(final Stage alguslava) {

		BorderPane juur = new BorderPane();
		final Scene algus = new Scene(juur, 260, 150);

		final Label ylesanne = new Label(
				"Ülesanne on "
						+ palle
						+ " palli ajada kastidesse. \nVäljumiseks mängu ajal vajuta Esc.");

		HBox hb1 = new HBox();
		HBox hb2 = new HBox();

		hb1.setPadding(new Insets(10));
		hb2.setPadding(new Insets(10));



		Button start = new Button("Alusta");
		// Anname start nupule tegevuse
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try{
					Kontroll(arv.getText());
				}
				catch(LiigaLühikeNimiErind e){
<<<<<<< HEAD
					System.out.println(e.getMessage());
				}
				catch(LiigaPikkNimiErind t){
					System.out.println(t.getMessage());
				}
=======
					ylesanne.setText("Ülesanne on "
						+ palle
						+ " palli ajada kastidesse. \nVäljumiseks mängu ajal vajuta Esc."
						+ "\n Sisesta palun pikem nimi!");
					
				}
				catch(LiigaPikkNimiErind t){
					ylesanne.setText("Ülesanne on "
							+ palle
							+ " palli ajada kastidesse. \nVäljumiseks mängu ajal vajuta Esc."
							+ "\n Sisesta palun lühem nimi!");
					;
				}
				if(arv.getText().length()>3&&arv.getText().length()<30){
>>>>>>> d7ceb33e3fe90e70d9bc66bae1f12cfa114b3fcf
				ManguLava mangulava = new ManguLava();
				mangulava.init();
				startTime = System.currentTimeMillis();
				alguslava.close();
				}
				
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
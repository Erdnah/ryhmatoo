package application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LopuLava extends Stage  {
	
	
	public void init() {
		BorderPane juur = new BorderPane();
		final Scene l�pp = new Scene(juur, 320, 250);
		Button lopp = new Button("L�peta");
		Button uuesti = new Button("Uuesti");
		HBox hb3 = new HBox();
		juur.setTop(ManguLava.tekst);
		juur.setCenter(Highscore.tabel);
		juur.setBottom(hb3);
		hb3.setSpacing(10);
		hb3.setPadding(new Insets(10));
		hb3.getChildren().addAll(uuesti, lopp);
		lopp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		
		
	
		
		
		
		setTitle("L�pp");
		setScene(l�pp);
		show();
	}
}

package ee.oop.mang;

import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	Random rand = new Random();

	//Wtf
	
	@Override
	public void start(final Stage lava) {
		Group juur = new Group();
		final Scene scene = new Scene(juur, 1000, 500);
		
		final int radius = 25;
		
		for (int i = 0; i < 1000; i++) {
			final Circle circle = new Circle(radius);
			int x = rand.nextInt((int) (scene.getWidth()-radius));
			int y = rand.nextInt((int) (scene.getHeight()-radius));
			TranslateTransition tt = new TranslateTransition(new Duration(5000), circle);
			tt.setFromY(y-scene.getHeight());
			tt.setToY(y);
			circle.setLayoutX(x);
			circle.setLayoutY(y);
			//tt.play();
			circle.setFill(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					int x = rand.nextInt((int) (scene.getWidth()-radius));
					int y = rand.nextInt((int) (scene.getHeight()-radius));
					circle.setLayoutX(x);
					circle.setLayoutY(y);
				}
			});
			juur.getChildren().add(circle);
		}
		lava.setScene(scene);
		lava.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}


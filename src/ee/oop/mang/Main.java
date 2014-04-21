package ee.oop.mang;

import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

	Random rand = new Random();
	
	@Override
	public void start(final Stage lava) {
		Group juur = new Group();
		final Scene scene = new Scene(juur, 1000, 500);
		
		final int radius = 25;
		
		for (int i = 0; i < 100; i++) {
			final Circle circle = new Circle(radius);
			Physics.setRandomXY(circle);
			circle.setFill(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Physics.move(circle, event.getX(), event.getY()*-1);
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


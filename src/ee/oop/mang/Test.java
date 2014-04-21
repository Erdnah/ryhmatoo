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

public class Test extends Application {

	Random rand = new Random();

	@Override
	public void start(final Stage lava) {
		Group juur = new Group();
		final Scene scene = new Scene(juur, 1000, 500);

		final int radius = 25;

		final Circle circle = new Circle(radius);
		// Physics.setRandomXY(circle);
		circle.setLayoutX(200);
		circle.setLayoutY(200);
		circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//Physics.setRandomXY(circle);
				Physics.move(circle, event.getX(), event.getY()*-1);
			}
		});
		juur.getChildren().add(circle);
		lava.setScene(scene);
		lava.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

package ee.oop.mang;

import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

	Random rand = new Random();
	static double SCENE_X;
	static double SCENE_Y;
	
	@Override
	public void start(final Stage lava) {
		Group juur = new Group();
		final Scene scene = new Scene(juur, 1000, 500);
		
		final int radius = 30;
		
		for (int i = 0; i < 40; i++) {
			final Circle circle = new Circle(radius);
			Physics.setRandomXY(circle);
			if (i < 10)
				circle.setFill(Color.RED);
			else if (i > 10 && i < 20)
				circle.setFill(Color.GREEN);
			else if (i > 20 && i< 30)
				circle.setFill(Color.BLUE);
			else
				circle.setFill(Color.YELLOW);
			circle.setStroke(Color.BLACK);
			circle.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					SCENE_X = scene.getWidth();
					SCENE_Y = scene.getHeight();
					//System.out.println(scene.getHeight());
					Physics.drag(circle, event.getSceneX(), event.getSceneY());
				}
			});
			/*circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (event.isPrimaryButtonDown()) {
						System.out.println("omg");
						Physics.move(circle, event.getX(), event.getY()*-1);
					}
				}
			});*/
			juur.getChildren().add(circle);
		}
		lava.setScene(scene);
		lava.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}


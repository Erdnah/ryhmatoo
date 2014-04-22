package ee.oop.mang;

import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

	Random rand = new Random();
	static double SCENE_X = 800;
	static double SCENE_Y = 600;
	static Color color = Color.YELLOW;
	static int palle = 40; // MITU PALLI TAHAD?
	int ulejoone = 0;
	
	@Override
	public void start(final Stage lava) {
		Group juur = new Group();
		final Scene scene = new Scene(juur, SCENE_X, SCENE_Y);
		Rectangle vasak1=new Rectangle(0,0,150,150);
		vasak1.setFill(Color.GREEN);
		Rectangle vasak2=new Rectangle(0,scene.getHeight()-150,150,150);
		vasak2.setFill(Color.YELLOW);
		Rectangle parem1=new Rectangle(scene.getWidth()-150,0,150,150);
		parem1.setFill(Color.RED);
		Rectangle parem2=new Rectangle(scene.getWidth()-150,scene.getHeight()-150,150,150);
		parem2.setFill(Color.BLUE);
		final Label skoor = new Label(String.valueOf(ulejoone));
		skoor.setLayoutX(scene.getWidth()/2);
		skoor.setLayoutY(0);
		juur.getChildren().add(skoor);
		juur.getChildren().add(vasak1);
		juur.getChildren().add(vasak2);
		juur.getChildren().add(parem1);
		juur.getChildren().add(parem2);
		
		final int radius = 30;
		
		for (int i = 1; i < palle + 1; i++) {
			final Circle circle = new Circle(radius, color);
			Physics.setRandomXY(circle);
			
			if (i % (palle/4) == 0) {
				if (circle.getFill() == Color.YELLOW) {
					color = Color.RED;
					System.out.println("red");
				} else if (circle.getFill() == Color.RED) {
					System.out.println("green");
					color = Color.GREEN;
				} else if (circle.getFill() == Color.GREEN) {
					System.out.println("blue");
					color = Color.BLUE;
				}
			}
			circle.setStroke(Color.BLACK);
			circle.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					SCENE_X = scene.getWidth();
					SCENE_Y = scene.getHeight();
					Physics.drag(circle, event.getSceneX(), event.getSceneY());
				}
			});
			/*circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Physics.move(circle, event.getX(), event.getY()*-1);
				}
			});*/
			juur.getChildren().add(circle);
		}
		lava.setTitle("Ballpit!");
		lava.setScene(scene);
		lava.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}


package ee.oop.mang;

import java.util.Random;

import javafx.scene.shape.Circle;

public class Physics {

	private static Random rand = new Random();

	public static void setRandomXY(Circle circle) {
		int x = rand.nextInt((int) (1000 - 2 * circle.getRadius()));
		int y = rand.nextInt((int) (500 - 2 * circle.getRadius()));
		circle.setLayoutX(x + circle.getRadius());
		circle.setLayoutY(y + circle.getRadius());
	}

	public static void move(Circle circle, double mouseX, double mouseY) {
		double centerX = circle.getLayoutX();
		double centerY = circle.getLayoutY();
		circle.setLayoutX(centerX - mouseX);
		circle.setLayoutY(centerY + mouseY);
	}

}

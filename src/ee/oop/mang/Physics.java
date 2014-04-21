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

		double nextX = centerX - 2 * mouseX;
		double nextY = centerY + 2 * mouseY;

		if (nextX <= 1000 && nextX >= 0) {
			circle.setLayoutX(centerX - mouseX);
		} else if (nextX > 1000)
			circle.setLayoutX(1000 - 1 * circle.getRadius());
		else if (nextX < 0)
			circle.setLayoutX(circle.getRadius());

		if (nextY <= 500 && nextY >= 0) {
			circle.setLayoutY(centerY + mouseY);
		} else {
			if (nextY > 500)
				circle.setLayoutY(500 - 1 * circle.getRadius());
			else if (nextY < 0)
				circle.setLayoutY(circle.getRadius());
		}
	}

}

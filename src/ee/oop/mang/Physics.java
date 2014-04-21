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

		if (nextX <= Main.SCENE_X && nextX >= 0) {
			circle.setLayoutX(centerX - 0.5*mouseX);
		} else if (nextX > Main.SCENE_X)
			circle.setLayoutX(Main.SCENE_X - 1 * circle.getRadius());
		else if (nextX < 0)
			circle.setLayoutX(circle.getRadius());

		if (nextY <= Main.SCENE_Y && nextY >= 0) {
			circle.setLayoutY(centerY + 0.5*mouseY);
			//tt.setFromY(centerY);
			//tt.setToY(centerY + mouseY);
		} else {
			if (nextY > Main.SCENE_Y)
				circle.setLayoutY(Main.SCENE_Y - 1 * circle.getRadius());
			else if (nextY < 0)
				circle.setLayoutY(circle.getRadius());
		}
		
	}

	public static void drag(Circle circle, double x, double y) {
		if (x <= Main.SCENE_X - circle.getRadius()&& x > 0 + circle.getRadius())
			circle.setLayoutX(x);
		if (y <= Main.SCENE_Y - circle.getRadius() && y > 0 + circle.getRadius())
			circle.setLayoutY(y);
		
	}

}

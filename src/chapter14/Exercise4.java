package chapter14;

import java.util.Arrays;
import java.util.List;

public class Exercise4 {
	public static void main(String args[]) {
		List<Shape> shapes = Arrays.asList(new Circle(), new Square(), new Triangle(), new Rhomboid());
		for (Shape shape : shapes) {
			shape.draw();
		}

		// up cast to shape
		Shape shape = new Rhomboid();

		// down cast to Rhomboid
		if (shape instanceof Rhomboid) {
			Rhomboid r = (Rhomboid) shape;
		}

		// Down cast to circle, succeeds at compile time,
		// but fails at runtime with a ClassCastException
		if (shape instanceof Circle) {
			Circle c = (Circle) shape;
		}

	}
}

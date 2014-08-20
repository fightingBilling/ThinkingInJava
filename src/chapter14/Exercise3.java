package chapter14;

import java.util.Arrays;
import java.util.List;

abstract class Shape {
	void draw() {
		System.out.println(this + ".draw()");
	}

	abstract public String toString();
}

class Circle extends Shape {
	@Override
	public String toString() {
		return "Circle";
	}
}

class Square extends Shape {
	@Override
	public String toString() {
		return "Square";
	}
}

class Triangle extends Shape {
	@Override
	public String toString() {
		return "Triangle";
	}
}

class Rhomboid extends Shape {
	@Override
	public String toString() {
		return "Rhomboid";
	}
}

public class Exercise3 {
	public static void main(String args[]) {
		List<Shape> shapes = Arrays.asList(new Circle(), new Square(), new Triangle(), new Rhomboid());
		for (Shape shape : shapes) {
			shape.draw();
		}

		// up cast to shape
		Shape shape = new Rhomboid();

		// down cast to Rhomboid
		Rhomboid r = (Rhomboid) shape;

		// Down cast to circle, succeeds at compile time,
		// but fails at runtime with a ClassCastException
		Circle c = (Circle) shape;

	}
}

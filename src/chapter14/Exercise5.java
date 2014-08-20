package chapter14;

import java.util.Arrays;
import java.util.List;

abstract class RShape {
	void draw() {
		System.out.println(this + ".draw()");
	}

	abstract public String toString();

	void rotate(int degrees) {
		System.out.println("Rotaring " + this + " by " + degrees + " degrees");
	}
}

class RCircle extends RShape {

	@Override
	public String toString() {
		return "Circle";
	}
}

class RSquare extends RShape {

	@Override
	public String toString() {
		return "Square";
	}

}

class RTriangle extends RShape {

	@Override
	public String toString() {
		return "Triangle";
	}

}

public class Exercise5 {
	static void rotateAll(List<RShape> shapes) {
		for (RShape shape : shapes) {
			if (!(shape instanceof RCircle)) {
				shape.rotate(45);
			}
		}
	}
	
	public static void main(String[] args) {
		List<RShape> shapes = Arrays.asList(new RCircle(), new RSquare(), new RTriangle());
		
		rotateAll(shapes);
	}
}

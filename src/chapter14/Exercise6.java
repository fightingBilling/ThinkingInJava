package chapter14;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class HShape {
	boolean highlighted = false;

	public void highLight() {
		highlighted = true;
	}

	public void clearHighLight() {
		highlighted = false;
	}

	void draw() {
		System.out.println(this + " draw()");
	}

	public String toString() {
		return getClass().getName() + (highlighted ? "highlighted" : "normal");
	}

	static List<HShape> shapes = new ArrayList<HShape>();

	public HShape() {
		shapes.add(this);
	}

	static void highLightArrays(Class<?> type) {
		for (HShape shape : shapes) {
			if (type.isInstance(shape)) {
				shape.highLight();
			}
		}
	}

	static void clearHighLightArrays(Class<?> type) {
		for (HShape shape : shapes) {
			if (type.isInstance(shape)) {
				shape.clearHighLight();
			}
		}
	}

	// Create an applicator and reuse it. All exceptions
	// indicate programmer error, and are thus
	// RuntimeExceptions:
	static void forEach(Class<?> type, String method) {
		Method m;
		try {
			m = HShape.class.getMethod(method, (Class<?>[]) null);
			for (HShape shape : shapes) {
				try {
					m.invoke(shape, (Object[]) null);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void highLight2(Class<?> type) {
		forEach(type, "highLight");
	}

	static void clearHighLight2(Class<?> type) {
		forEach(type, "clearHighLight");
	}
}

class HCircle extends HShape {
}

class HSquare extends HShape {

}

class HTriangle extends HShape {

}

public class Exercise6 {
	public static void main(String[] args) {
		List<HShape> shapes = Arrays.asList(new HCircle(), new HSquare(), new HTriangle());

		HShape.highLightArrays(HCircle.class);
		HShape.highLight2(HCircle.class);

		for (HShape shape : shapes) {
			shape.draw();
		}
	}
}

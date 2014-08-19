package chapter14;

interface HasBatterise {
};

interface Waterproof {
};

interface Shoots {
};

class Toy {
	public Toy(int i) {

	}
}

class FancyToy extends Toy implements HasBatterise, Waterproof, Shoots {
	public FancyToy() {
		super(1);
	}
}

public class Exercise1 {
	static void printInfo(Class<?> cc) {
		System.out.println("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
		System.out.println("Simple name: " + cc.getSimpleName());
		System.out.println("Canonical name: " + cc.getCanonicalName());
	}

	public static void main(String[] args) {
		Class<?> c = null;

		try {
			c = Class.forName("chapter14.FancyToy");
		} catch (ClassNotFoundException e) {
			System.out.println("Can't find FancyToy");
			return;
		}

		printInfo(c);

		for (Class<?> face : c.getInterfaces()) {
			printInfo(face);
		}

		Class<?> up = c.getSuperclass();
		Object obj = null;

		try {
			obj = up.newInstance();
		} catch (InstantiationException e) {
			System.out.println("can not instantiate");
			return;
		} catch (IllegalAccessException e) {
			System.out.println("can not access");
			return;
		}

		printInfo(obj.getClass());
	}
}

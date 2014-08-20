package chapter14;

interface HasCPU {
};

class SuperFancyToy extends FancyToy implements HasCPU {
};

public class Exercise2 {
	static void printInfo(Class<?> cc) {
		System.out.println("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
		System.out.println("Simple name: " + cc.getSimpleName());
		System.out.println("Canonical name: " + cc.getCanonicalName());
	}

	public static void main(String[] args) {
		Class<?> c = null;

		try {
			c = Class.forName("chapter14.SuperFancyToy");
		} catch (ClassNotFoundException e) {
			System.out.println("Can't find SuperFancyToy");
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
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("can not instantiate");
			return;
		}

		printInfo(obj.getClass());
	}
}

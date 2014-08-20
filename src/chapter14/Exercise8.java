package chapter14;

public class Exercise8 {
	static void printClasses(Class<?> c) {
		if (c == null) {
			return;
		}

		System.out.println(c.getName());

		for (Class<?> face : c.getInterfaces()) {
			System.out.println("Interface: " + face.getName());
			printClasses(face.getSuperclass());
		}

		printClasses(c.getSuperclass());
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++) {
			System.out.println("Displaying " + args[i]);
			printClasses(Class.forName(args[i]));
			if (i < args.length - 1) {
				System.out.println("==========");
			}
		}
	}
}

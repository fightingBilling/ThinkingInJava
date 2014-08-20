package chapter14;

class Candy {
	static {
		System.out.println("Loading Candy");
	}
}

class Gum {
	static {
		System.out.println("Loading Gum");
	}
}

class Cookie {
	static {
		System.out.println("Loading Cookie");
	}
}


public class Exercise7 {
	public static void main(String args[]) throws Exception {
		for (String arg : args) {
			Class.forName(arg);
		}
	}
}

package chapter15;

class Holder<T> {
	private T a, b, c;

	public Holder(T a, T b, T c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public T getA() {
		return a;
	}

	public void setA(T a) {
		this.a = a;
	}

	public T getB() {
		return b;
	}

	public void setB(T b) {
		this.b = b;
	}

	public T getC() {
		return c;
	}

	public void setC(T c) {
		this.c = c;
	}
}

public class Exercise2 {
	public static void main(String[] args) {
		Holder<String> holder = new Holder("A", "B", "C");
		System.out.println(holder.getA());
		System.out.println(holder.getB());
		System.out.println(holder.getC());

		holder.setC("D");
		System.out.println(holder.getC());
	}
}

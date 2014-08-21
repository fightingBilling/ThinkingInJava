package chapter15;

public class Exercise10 {
	public static <A,B> void f(A a, B b, Boolean c) {
		System.out.println(a.getClass().getName());
		System.out.println(b.getClass().getName());
		System.out.println(c.getClass().getName());
	}
	
	public static void main(String[] args) {
		f(" ", 1, false);
	}
}

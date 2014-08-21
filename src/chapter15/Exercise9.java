package chapter15;

public class Exercise9 {
	public static <A,B,C> void f(A a, B b, C c) {
		System.out.println(a.getClass().getName());
		System.out.println(b.getClass().getName());
		System.out.println(c.getClass().getName());
	}
	
	public static void main(String[] args) {
		f(" ", 1, 1.0);
	}
}

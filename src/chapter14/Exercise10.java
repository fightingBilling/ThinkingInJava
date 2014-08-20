package chapter14;


public class Exercise10 {
	public static void main(String[] args) {
		char[] ac = "Hello, World!".toCharArray();

		System.out.println("ac.getClass() = " + ac.getClass());
		System.out.println("ac.getClass().getSuperclass() = " + ac.getClass().getSuperclass());

		int[] ia = new int[3];
		System.out.println("ia.getClass() = " + ia.getClass());

		long[] la = new long[3];
		System.out.println("la.getClass() = " + la.getClass());

		double[] da = new double[3];
		System.out.println("da.getClass() = " + da.getClass());

		String[] sa = new String[3];
		System.out.println("sa.getClass() = " + sa.getClass());

	}
}

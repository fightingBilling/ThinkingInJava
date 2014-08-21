package chapter15;

class SixTuple<A, B, C, D, E, F> {
	public final A first;
	public final B second;
	public final C third;
	public final D fourth;
	public final E fifth;
	public final F sixth;

	public SixTuple(A first, B second, C third, D fourth, E fifth, F sixth) {
		super();
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
		this.sixth = sixth;
	}

	public String toString() {
		return "(" + first + ", " + second + ", " + third + ", " + fourth + ", " + fifth + ", " + sixth + ")";
	}
}

public class Exercise3 {
	static SixTuple<String, Float, Integer, Long, Double, Byte> a() {
		return new SixTuple("hi", (float)4.7, 15, (long)10L, 1.1, (byte)1);
	}
	
	public static void main(String[] args) {
		System.out.println(a());
	}
}

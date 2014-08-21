package chapter15;

import java.util.ArrayList;
import java.util.Random;

class RandomList<T> {
	private ArrayList<T> storage = new ArrayList<T>();
	private Random rand = new Random(47);
	public void add(T item) {
		storage.add(item);
	}
	public T select() {
		return storage.get(rand.nextInt(storage.size()));
	}
}

public class Exercise6 {
	public static void main(String[] args) {
		RandomList<String> rs = new RandomList<String>();
		for (String s : "I am a good programmer".split(" ")) {
			rs.add(s);
		}
		
		for (int i = 0; i < 10; i ++) {
			System.out.println(rs.select());
		}
		
		RandomList<Integer> ri = new RandomList<Integer>();
		for (int i = 1; i < 10; i ++) {
			ri.add(i);
		}
		
		for (int i = 0; i < 10; i ++) {
			System.out.println(ri.select());
		}
	}
}

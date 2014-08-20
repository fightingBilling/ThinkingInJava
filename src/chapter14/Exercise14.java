package chapter14;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Part2 {
	public String toString() {
		return getClass().getSimpleName();
	}

	static List<Class<? extends Part2>> partClasses = Arrays.asList(FuelFilter2.class, AirFilter2.class,
			CabinAirFilter2.class, OilFilter2.class, FanBelt2.class, PowerSteeringBelt2.class, GeneratorBelt2.class);

	private static Random rand = new Random(47);

	public static Part2 createRandom() {
		int n = rand.nextInt(partClasses.size());

		try {
			return partClasses.get(n).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}

class Filter2 extends Part2 {

}

class FuelFilter2 extends Filter2 {

}

class AirFilter2 extends Filter2 {

}

class CabinAirFilter2 extends Filter2 {

}

class OilFilter2 extends Filter2 {

}

class Belt2 extends Part2 {

}

class FanBelt2 extends Belt2 {

}

class GeneratorBelt2 extends Belt2 {

}

class PowerSteeringBelt2 extends Belt2 {

}

public class Exercise14 {
	public static void main(String[] args) {
		Part2 part;
		for (int i = 0; i < 20; i++) {
			part = Part2.createRandom();
			System.out.println(part.getClass().getSimpleName() + " ");
		}

	}
}

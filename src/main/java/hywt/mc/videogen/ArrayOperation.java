package hywt.mc.videogen;

public class ArrayOperation {

	public static void print(int[] n) {
		for (int i = 0; i < n.length; i++) {
			System.out.print(i + ",");
		}
		System.out.println();
	}

	public static String toString(int[] n) {
		String out = "";
		for (int i = 0; i < n.length; i++) {
			out += i + ",";
		}
		return out;
	}

	public static int[] toIntArray(int direction) {
		int[] facing = null;
		if (direction == 0) {
			facing = Ints.positiveX;
		} else if (direction == 1) {
			facing = Ints.negativeX;
		} else if (direction == 2) {
			facing = Ints.positiveZ;
		} else if (direction == 3) {
			facing = Ints.negativeZ;
		}
		return facing;
	}
}

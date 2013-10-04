package sorter.test;

import java.util.Arrays;
import java.util.Random;

import sorters.Sorter;

public abstract class SorterTester {

	private static Random r = new Random();

	public abstract Sorter createSorter(int[] arr);

	public void test(int[] example) {
		int[] copy = Arrays.copyOf(example, example.length);

		Sorter sorter = createSorter(copy);
		System.out.println("Testing sort named: " + sorter.sortName());
		sorter.sort();

		// System.out.println(Arrays.toString(copy));
		System.out.println("Total reads: " + sorter.readCount());
		System.out.println("Total writes: " + sorter.writeCount());
		System.out.println("Total time: " + sorter.time());
	}

	public void test() throws InterruptedException {
		int[] ex = createRandomArray(50);

		test(ex);
	}

	public static int[] createRandomArray(int length) {
		int[] result = new int[length];

		for (int i = 0; i < result.length; i++) {
			result[i] = r.nextInt(100);
		}

		return result;
	}
}

package workspace;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {

		ArrayList<Integer> al = new ArrayList<Integer>();

		for(int i=0; i<5f; i++) {
			al.add(i);
		}
		al.forEach(p -> { System.out.print(p+","); });
		System.out.println();


		al.addAll(al);
		al.forEach(p -> { System.out.print(p+","); });

	};
}

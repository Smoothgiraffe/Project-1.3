import java.util.*;

public class Test{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Which rotation do you want? 0 - 5");
		int rotation = in.nextInt();
		System.out.println("Which version do you want? 0 - 3");
		int version = in.nextInt();
		char[][][] 	array = Pentomino.pent('L', rotation, version);

		for(int i = 0; i < 	array.length; i++) {
			for(int j = 0; j < 	array[0].length; j++) {
				for(int k = 0; k < 	array[0][0].length; k++) {
					System.out.println(	array[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}

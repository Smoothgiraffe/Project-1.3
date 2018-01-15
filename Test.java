public class Test{
	public static void main(String[] args) {
		char[][][] test = {{{'p', 'p'}, {'p', 'p'}, {'0', 'p'}}};

		char[][] tempArray = test[0];
		char[][] rotateArray = new char[tempArray.length][tempArray[0].length];

		for(int i = 0; i < rotateArray.length; i++) {
			for(int j = 0; j < rotateArray[0].length; j++) {
				rotateArray[i][j] = tempArray[tempArray.length - i - 1][j];
			}
		}

		char[][][] result = {rotateArray};

		for(int i = 0; i < result.length; i++) {
			for(int j = 0; j < result[0].length; j++) {
				for(int k = 0; k < result[0][0].length; k++) {
					System.out.print(result[i][j][k]);
				}
				System.out.println();
			}
		}

	}
}

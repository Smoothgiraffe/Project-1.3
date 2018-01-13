public class C extends Space{

	public static void placePent() {
		
	}

	//returns true if the entire cargo-space is full
	public static boolean isFull() {
		for (int i = x; i < space.length; i++) { //height
			for (int j = y; j < space[0].length; j++) { //width
				for (int k = z; k < space[0][0].length; k++) { //length
					if (space[i][j][k] == '\u0000' || space[i][j][k] == '0') {
						return false;
					}
				}
			}
		}
		return true;
	}



}

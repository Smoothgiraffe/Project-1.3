public class Pentomino {

	protected char name;
	protected int flipVersion;
	protected int rotation;
	protected double value;

	protected final double volume = 2.5; //0.5*5 = 2.5

	static char[][][] pentP = {{{'p','p'},{'p','p'},{'p','0'}}};
	static char[][][] pentL = {{{'l','0','0','0',},{'l','l','l','l'}}};
	static char[][][] pentT = {{{'t','t','t'},{'0','t','0'},{'0','t','0'}}};

	public Pentomino(char aName) {
		name = aName;
		if(name != 'P' && name != 'L' && name != 'T') {
			System.out.println("The pentomino with the name " + name + " was not initiated right.");
		}
	}

	public Pentomino(char aName, double aValue) {
		name = aName;
		value = aValue;
	}

	//Don't forget: 0 <= flipTation <= 3 and 0 <= aRotation <= 5
	public void setVersion(int flipTation, int aRotation) {
		flipVersion = flipTation;
		rotation = aRotation;
	}

	public char[][][] toArray() {

		return rotate(flip());

	}

	//flips the pentomino such as it still has the same dimensions, can go from 0 to 3
	private char[][][] flip() {
		char[][][] originalArray;

		if(name == 'P') {
			originalArray = getPentP();
		} else if(name == 'L') {
			originalArray = getPentL();
		} else if(name == 'T') {
			originalArray = getPentT();
		} else {
			//You land here if the name of the pentomino is not P, L or T. That's wrong!
			System.out.println();
			return null;
		}

		flipVersion = flipVersion;

		char[][] tempArray = originalArray[0];
		char[][] rotateArray = new char[tempArray.length][tempArray[0].length];

		if(flipVersion == 0) {
			return originalArray;
		} else if(flipVersion == 1) {
			for(int i = 0; i < rotateArray.length; i++) {
				for(int j = 0; j < rotateArray[0].length; j++) {
					rotateArray[i][j] = tempArray[tempArray.length - i - 1][j];
				}
			}
			char[][][] result = {rotateArray};
			return result;
		} else if(flipVersion == 2) {
			for(int i = 0; i < rotateArray.length; i++) {
				for(int j = 0; j < rotateArray[0].length; j++) {
					rotateArray[i][j] = tempArray[i][tempArray[0].length];
				}
			}
			char[][][] result = {rotateArray};
			return result;
		} else if(flipVersion == 3) {
			for(int i = 0; i < rotateArray.length; i++) {
				for(int j = 0; j < rotateArray[0].length; j++) {
					rotateArray[i][j] = tempArray[tempArray.length - i - 1][tempArray[0].length];
				}
			}
			char[][][] result = {rotateArray};
			return result;
		}
		return null;
	}

	//rotates a pentomino.
	private char[][][] rotate(char[][][] flippedPent) {
		if(rotation == 0) {
			return flippedPent;
		} else if(rotation == 1) {
			char[][][] rotatedPent = new char[flippedPent.length][flippedPent[0][0].length][flippedPent[0].length];
			for(int i = 0; i < rotatedPent.length; i++) {
				for(int j = 0; j < rotatedPent[0].length; j++) {
					for(int k = 0; k < rotatedPent[0][0].length; k++) {
						rotatedPent[i][j][k] = flippedPent[rotatedPent.length - i - 1][rotatedPent[0][0].length - k - 1][rotatedPent[0].length - j - 1];
					}
				}
			}
			return rotatedPent;
		} else if(rotation == 2) {
			char[][][] rotatedPent = new char[flippedPent[0][0].length][flippedPent.length][flippedPent[0].length];
			for(int i = 0; i < rotatedPent.length; i++) {
				for(int j = 0; j < rotatedPent[0].length; j++) {
					for(int k = 0; k < rotatedPent[0][0].length; k++) {
						rotatedPent[i][j][k] = flippedPent[rotatedPent[0].length - j - 1][rotatedPent[0][0].length - k - 1][rotatedPent.length - i - 1];
					}
				}
			}
			return rotatedPent;
		} else if(rotation == 3) {
			char[][][] rotatedPent = new char[flippedPent[0][0].length][flippedPent[0].length][flippedPent.length];
			for(int i = 0; i < rotatedPent.length; i++) {
				for(int j = 0; j < rotatedPent[0].length; j++) {
					for(int k = 0; k < rotatedPent[0][0].length; k++) {
						rotatedPent[i][j][k] = flippedPent[rotatedPent[0][0].length - k - 1][rotatedPent[0].length - j - 1][rotatedPent.length - i - 1];
					}
				}
			}
			return rotatedPent;
		}  else if(rotation == 4) {
			char[][][] rotatedPent = new char[flippedPent[0].length][flippedPent.length][flippedPent[0][0].length];
			for(int i = 0; i < rotatedPent.length; i++) {
				for(int j = 0; j < rotatedPent[0].length; j++) {
					for(int k = 0; k < rotatedPent[0][0].length; k++) {
						rotatedPent[i][j][k] = flippedPent[rotatedPent[0].length - j - 1][rotatedPent.length - i - 1][rotatedPent[0][0].length - k - 1];
					}
				}
			}
			return rotatedPent;
		} else if(rotation == 5) {
			char[][][] rotatedPent = new char[flippedPent[0].length][flippedPent[0][0].length][flippedPent.length];
			for(int i = 0; i < rotatedPent.length; i++) {
				for(int j = 0; j < rotatedPent[0].length; j++) {
					for(int k = 0; k < rotatedPent[0][0].length; k++) {
						rotatedPent[i][j][k] = flippedPent[rotatedPent[0][0].length - k - 1][rotatedPent.length - i - 1][rotatedPent[0].length - j - 1];
					}
				}
			}
			return rotatedPent;
		}
		return null;
	}

	public double getVolume() {
		return volume;
	}

	public char getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	public static char[][][] getPentP() {
		return pentP;
	}

	public static char[][][] getPentL() {
		return pentL;
	}

	public static char[][][] getPentT() {
		return pentT;
	}

}

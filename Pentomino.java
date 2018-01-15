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
	}

	public Pentomino(char aName, double aValue) {
		name = aName;
		value = aValue;
	}

	public char[][][] toArray(int aRotation, int aVersion) {

		return rotate(flip(aVersion), aRotation);

	}

	//flips the pentomino such as it still has the same dimensions, can go from 0 to 3
	public char[][][] flip(int version) {
		char[][][] originalArray;

		if(name == 'P') {
			originalArray = getPentP();
		} else if(name == 'L') {
			originalArray = getPentL();
		} else if(name == 'T') {
			originalArray = getPentT();
		} else {
			//You land here if the name of the pentomino is not P, L or T. That's wrong!
			return null;
		}

		flipVersion = version;

		char[][] tempArray = originalArray[0];
		char[][] rotateArray = new char[tempArray.length][tempArray[0].length];

		if(version == 0) {
			return originalArray;
		} else if(version == 1) {
			for(int i = 0; i < rotateArray.length; i++) {
				for(int j = 0; j < rotateArray[0].length; j++) {
					rotateArray[i][j] = tempArray[tempArray.length - i - 1][j];
				}
			}
			char[][][] result = {rotateArray};
			return result;
		} else if(version == 2) {
			for(int i = 0; i < rotateArray.length; i++) {
				for(int j = 0; j < rotateArray[0].length; j++) {
					rotateArray[i][j] = tempArray[i][tempArray[0].length];
				}
			}
			char[][][] result = {rotateArray};
			return result;
		} else if(version == 3) {
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
	public char[][][] rotate(char[][][] flippedPent, int aRotation) {
		rotation = aRotation;
		if(aRotation == 0) {
			return flippedPent;
		} else if(aRotation == 1) {
			char[][][] rotatedPent = new char[flippedPent.length][flippedPent[0][0].length][flippedPent[0].length];
			for(int i = 0; i < rotatedPent.length; i++) {
				for(int j = 0; j < rotatedPent[0].length; j++) {
					for(int k = 0; k < rotatedPent[0][0].length; k++) {
						rotatedPent[i][j][k] = flippedPent[rotatedPent.length - i - 1][rotatedPent[0][0].length - k - 1][rotatedPent[0].length - j - 1];
					}
				}
			}
			return rotatedPent;
		} else if(aRotation == 2) {
			char[][][] rotatedPent = new char[flippedPent[0][0].length][flippedPent.length][flippedPent[0].length];
			for(int i = 0; i < rotatedPent.length; i++) {
				for(int j = 0; j < rotatedPent[0].length; j++) {
					for(int k = 0; k < rotatedPent[0][0].length; k++) {
						System.out.println(i + " " + j + " " + k);
						System.out.println((rotatedPent[0][0].length - k - 1) + " " + (rotatedPent.length - i - 1)  + " " + (rotatedPent[0].length - j - 1));
						rotatedPent[i][j][k] = flippedPent[rotatedPent[0][0].length - k - 1][rotatedPent.length - i - 1][rotatedPent[0].length - j - 1];
					}
				}
			}
			return rotatedPent;
		} else if(aRotation == 3) {
			char[][][] rotatedPent = new char[flippedPent[0][0].length][flippedPent[0].length][flippedPent.length];
			for(int i = 0; i < rotatedPent.length; i++) {
				for(int j = 0; j < rotatedPent[0].length; j++) {
					for(int k = 0; k < rotatedPent[0][0].length; k++) {
						rotatedPent[i][j][k] = flippedPent[rotatedPent[0][0].length - k - 1][rotatedPent[0].length - j - 1][rotatedPent.length - i - 1];
					}
				}
			}
			return rotatedPent;
		}  else if(aRotation == 4) {
			char[][][] rotatedPent = new char[flippedPent[0].length][flippedPent.length][flippedPent[0][0].length];
			for(int i = 0; i < rotatedPent.length; i++) {
				for(int j = 0; j < rotatedPent[0].length; j++) {
					for(int k = 0; k < rotatedPent[0][0].length; k++) {
						rotatedPent[i][j][k] = flippedPent[rotatedPent[0].length - j - 1][rotatedPent.length - i - 1][rotatedPent[0][0].length - k - 1];
					}
				}
			}
			return rotatedPent;
		} else if(aRotation == 5) {
			char[][][] rotatedPent = new char[flippedPent[0].length][flippedPent[0][0].length][flippedPent.length];
			for(int i = 0; i < rotatedPent.length; i++) {
				for(int j = 0; j < rotatedPent[0].length; j++) {
					for(int k = 0; k < rotatedPent[0][0].length; k++) {
						rotatedPent[i][j][k] = flippedPent[rotatedPent[0].length - j - 1][rotatedPent[0][0].length - k - 1][rotatedPent.length - i - 1];
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

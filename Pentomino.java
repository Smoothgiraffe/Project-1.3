public class Pentomino {

	protected double value;
	protected char name;

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

	public static char[][][] toArray(char pentomino, int rotation, int version) {
		char[][][] endPentomino;

		if(pentomino == 'P') {
			endPentomino = pentP;
		} else if (pentomino == 'L') {
			endPentomino = pentL;
		} else {
			endPentomino = pentT;
		}

		return flip(rotate(endPentomino, rotation), version);

	}

	//flips the pentomino such as it still has the same dimensions, can go from 0 to 3
	public static char[][][] flip(char[][][] pent, int version) {
		char[][][] newPentomino = new char[pent.length][pent[0].length][pent[0][0].length];
		if(version == 0) {
			/*
			for(int i = 0; i < pent.length; i++) {
				for(int j = 0; j < pent[0].length; j++) {
					for(int k = 0; k < pent[0][0].length; k++) {
						newPentomino[i][j][k] = pent[i][j][k];
					}
				}
			}
			*/
			return pent;
		} else if(version == 1) {
			for(int i = 0; i < pent.length; i++) {
				for(int j = 0; j < pent[0].length; j++) {
					for(int k = 0; k < pent[0][0].length; k++) {
						newPentomino[i][j][k] = pent[pent.length - i - 1][j][k];
					}
				}
			}
		} else if(version == 2) {
			for(int i = 0; i < pent.length; i++) {
				for(int j = 0; j < pent[0].length; j++) {
					for(int k = 0; k < pent[0][0].length; k++) {
						newPentomino[i][j][k] = pent[i][pent[0].length - j - 1][k];
					}
				}
			}
		} else  if (version == 3){
			for(int i = 0; i < pent.length; i++) {
				for(int j = 0; j < pent[0].length; j++) {
					for(int k = 0; k < pent[0][0].length; k++) {
						newPentomino[i][j][k] = pent[pent.length - i - 1][pent[0].length - j - 1][k];
					}
				}
			}
		} else {
			//ERROR!
		}
		return null;
	}

	public static char[][][] rotate(char[][][] pent, int rotation) {
		if(rotation == 0) {
			return pent;
		} else if(rotation == 1) {
			char[][][] rotatedPent = new char[pent.length][pent[0][0].length][pent[0].length];
			for(int i = 0; i < pent.length; i++) {
				for(int j = 0; j < pent[0][0].length; j++) {
					for(int k = 0; k < pent[0].length; k++) {
						rotatedPent[i][j][k] = pent[i][k][j];
					}
				}
			}
			return rotatedPent;
		} else if(rotation == 2) {
			char[][][] rotatedPent = new char[pent[0].length][pent.length][pent[0][0].length];
			for(int i = 0; i < pent[0].length; i++) {
				for(int j = 0; j < pent.length; j++) {
					for(int k = 0; k < pent[0][0].length; k++) {
						rotatedPent[i][j][k] = pent[j][i][k];
					}
				}
			}
			return rotatedPent;
		} else if(rotation == 3) {
			char[][][] rotatedPent = new char[pent[0].length][pent[0][0].length][pent.length];
			for(int i = 0; i < pent[0].length; i++) {
				for(int j = 0; j < pent[0][0].length; j++) {
					for(int k = 0; k < pent.length; k++) {
						rotatedPent[i][j][k] = pent[j][k][i];
					}
				}
			}
			return rotatedPent;
		} else if(rotation == 4) {
			char[][][] rotatedPent = new char[pent[0][0].length][pent.length][pent[0].length];
			for(int i = 0; i < pent.length; i++) {
				for(int j = 0; j < pent[0].length; j++) {
					for(int k = 0; k < pent[0][0].length; k++) {
						rotatedPent[i][j][k] = pent[k][i][j];
					}
				}
			}
			return rotatedPent;
		} else if(rotation == 5) {
			char[][][] rotatedPent = new char[pent[0][0].length][pent[0].length][pent.length];
			for(int i = 0; i < pent.length; i++) {
				for(int j = 0; j < pent[0].length; j++) {
					for(int k = 0; k < pent[0][0].length; k++) {
						rotatedPent[i][j][k] = pent[k][j][i];
					}
				}
			}
			return rotatedPent;
		}
		return null;
	}

}

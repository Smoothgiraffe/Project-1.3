public class D extends Space{

	public static void placePent() {
		
	}

	public static Pentomino[] sortPentominoes(Pentomino[] pentomino) {
		Pentomino[] newPentomino = new Pentomino[][pentomino.length];
		if (pentomino[0].getValue() >= pentomino[1].getValue() && pentomino[0].getValue() >= pentomino[2].getValue()) {
			newPentomino[0] = pentomino[0];
			if (pentomino[1].getValue() >= pentomino[2].getValue()) {
				newPentomino[1] = pentomino[1];
				newPentomino[2] = pentomino[2];
			} else {
				newPentomino[1] = pentomino[2];
				newPentomino[2] = pentomino[1];
			}
		} else if (pentomino[1].getValue() >= pentomino[0].getValue() && pentomino[1].getValue() >= pentomino[2].getValue()) {
			newPentomino[0] = pentomino[1];
			if (pentomino[0].getValue() >= pentomino[2].getValue()) {
				newPentomino[1] = pentomino[0];
				newPentomino[2] = pentomino[2];
			} else {
				newPentomino[1] = pentomino[2];
				newPentomino[2] = pentomino[0];
			}
		} else if (pentomino[2].getValue() >= pentomino[0].getValue() && pentomino[2].getValue() >= pentomino[1].getValue()) {
			newPentomino[0] = pentomino[2];
			if (pentomino[0].getValue() >= pentomino[1].getValue()) {
				newPentomino[1] = pentomino[0];
				newPentomino[2] = pentomino[1];
			} else {
				newPentomino[1] = pentomino[1];
				newPentomino[2] = pentomino[0];
			}
		}
		return newPentomino;
	}

	//returns true if a certain percentage of the cargo-space is full
	public static boolean isFullEnough() {
		if (volumeInside >= stopPercentage*SPACEVOLUME) {
			return true;
		}
		return false;
	}

}

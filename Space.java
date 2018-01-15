import java.util.*;

/*
	A class meant to be the superclass for all solving algorithms.
	It saves general variables such as the size of the cargo space.
	It is able to find the coordinates for a new box, place it in there, keep track of the steps made, how full the space is, and more.
*/

public class Space {

	//these variables describe the cargo-space
	protected static final double SPACELENGTH = 16.5;
	protected static final double SPACEWIDTH = 2.5;
	protected static final double SPACEHEIGHT = 4;
	protected static final double SPACEVOLUME = SPACELENGTH*SPACEWIDTH*SPACEHEIGHT;
	protected static final double stopPercentage = 0.9;
	protected static double completeBoxVolume = 0;
	protected static ArrayList<PlacedBox> solution = new ArrayList<PlacedBox>();

	//x, y, and z are the coordinates where the next Box/pentomino is placed
	protected static int x, y, z = 0;

	//space is a three-dimensional array where every single spot acts as a 0.5*0.5*0.5 block.
	protected static char[][][] space = new char[(int)SPACELENGTH*2][(int)SPACEWIDTH*2][(int)SPACEHEIGHT*2];

	//sorts an array of boxes after value per unit and returns them
	public static Doos[] sortBoxes(Doos[] boxes) {
		Doos[] newBoxes = new Doos[boxes.length];
		if (boxes[0].getValuePerUnit() >= boxes[1].getValuePerUnit() && boxes[0].getValuePerUnit() >= boxes[2].getValuePerUnit()) {
			newBoxes[0] = boxes[0];
			if (boxes[1].getValuePerUnit() >= boxes[2].getValuePerUnit()) {
				newBoxes[1] = boxes[1];
				newBoxes[2] = boxes[2];
			} else {
				newBoxes[1] = boxes[2];
				newBoxes[2] = boxes[1];
			}
		} else if (boxes[1].getValuePerUnit() >= boxes[0].getValuePerUnit() && boxes[1].getValuePerUnit() >= boxes[2].getValuePerUnit()) {
			newBoxes[0] = boxes[1];
			if (boxes[0].getValuePerUnit() >= boxes[2].getValuePerUnit()) {
				newBoxes[1] = boxes[0];
				newBoxes[2] = boxes[2];
			} else {
				newBoxes[1] = boxes[2];
				newBoxes[2] = boxes[0];
			}
		} else if (boxes[2].getValuePerUnit() >= boxes[0].getValuePerUnit() && boxes[2].getValuePerUnit() >= boxes[1].getValuePerUnit()) {
			newBoxes[0] = boxes[2];
			if (boxes[0].getValuePerUnit() >= boxes[1].getValuePerUnit()) {
				newBoxes[1] = boxes[0];
				newBoxes[2] = boxes[1];
			} else {
				newBoxes[1] = boxes[1];
				newBoxes[2] = boxes[0];
			}
		}
		return newBoxes;
	}

	public static Pentomino[] sortPentominoes(Pentomino[] pentomino) {
		Pentomino[] newPentomino = new Pentomino[pentomino.length];
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

	//computes the coordinates for the next box/pentomino to be placed
	public static void computeNewCoordinates() {
		for (int i = x; i < space.length; i++) { //height
			for (int j = y; j < space[0].length; j++) { //width
				for (int k = z; k < space[0][0].length; k++) { //length
					if (space[i][j][k] == '\u0000' || space[i][j][k] == '0') {
						setCoordinates(i, j, k);
						return;
					}
				}
			}
		}
	}

	public static void setCoordinates(int coordinateX, int coordinateY, int coordinateZ) {
		x = coordinateX;
		y = coordinateY;
		z = coordinateZ;
	}

	//checks if a specific box//pentomino fits at a secific place with the coordinates x, y, and z
	public static boolean fits(Doos box) {
		//checks for out-of-bound-errors
		if (x + box.getArrayLength() > space.length) {
			return false;
		}
		if (y + box.getArrayWidth() > space[0].length) {
			return false;
		}
		if (z + box.getArrayHeight() > space[0][0].length) {
			return false;
		}
		//checks for every single spot in the array to be empty
		for(int i = 0; i < box.getArrayLength(); i++) {
			for (int j = 0; j < box.getArrayWidth(); j++) {
				for (int k = 0; k < box.getArrayHeight(); k++) {
					if (space[x + i][y + j][z + k] != '\u0000' || space[i][j][k] == '0') {
						return false;
					}
				}
			}
		}
		return true;
	}

	//places a box at a given point with coordinates x, y, and z
	public static void placeBoxAt(Doos box) { //CHECK IF FITS FIRST!
			for (int i = 0; i < (int) box.getArrayLength(); i++) {
				for (int j = 0; j < (int) box.getArrayWidth(); j++) {
					for (int k = 0; k < (int) box.getArrayHeight(); k++) {
						space[x + i][y + j][z + k] = box.getName();
					}
				}
			}
			completeBoxVolume = completeBoxVolume + box.getVolume(); //update the volume
			PlacedBox newBox = new PlacedBox(box.getArrayLength(), box.getArrayWidth(), box.getArrayHeight(), box.getName(), x, y, z); //create new PlacedBox-Object to add to the solution
			solution.add(newBox);
	}

	//places a pentomino at a certain positon x, y, z
	public static void placePentominoAt(char pentomino, int rotation, int version, int x, int y, int z) {
		char[][][] pent = Pentomino.toArray(pentomino, rotation, version);
		for(int i = 0; i < pent.length; i++){
			for(int j = 0; j < pent[0].length; j++) {
				for(int k = 0; k < pent[0][0].length; k++) {
					if(pent[i][j][k] != '\u0000' || space[i][j][k] == '0') {
						space[x + i][y + j][z + k] = pent[i][j][k];
					}
				}
			}
		}
	}

	//deletes the last box in the solution-Array and updates the space accordingly
	public static void deleteBox(int index) {
		//update the space
		PlacedBox deleteBox = solution.get(index);
		for (int i = 0; i < deleteBox.getArrayLength(); i++) {
			for (int j = 0; j < deleteBox.getArrayWidth(); j++) {
				for (int k = 0; k < deleteBox.getArrayHeight(); k++) {
					space[deleteBox.getX() + i][deleteBox.getY() + j][deleteBox.getZ() + k] = '\u0000';
				}
			}
		}
		//update the arrayList
		solution.remove(index);
	}

	//returns true if a certain percentage of the cargo-space is full (for exercise b and d)
	public static boolean isFullEnough() {
		if (completeBoxVolume >= stopPercentage*SPACEVOLUME) {
			return true;
		}
		return false;
	}

	//returns true if the entire cargo-space is full (for exercise a and c)
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

/*
	A class that is made to solve exercise b.
	It utilizes a so-called greedy algorithm, where we take the most valuable box first and then put it into the cargo space as often as possible.
	When there is no space for the most valuable box, the second-most valueable box is considered, and so on.
*/

public class B extends Space{

	public static void main(String[] args) {

		//initiate the three types of boxes
		Doos A = new Doos(1, 1, 2, 'A', 3);
		Doos B = new Doos(1, 1.5, 2, 'B', 4);
		Doos C = new Doos(1.5, 1.5, 1.5, 'C', 5);

		Doos[] boxes = {A, B, C}; //put them into an array
		sortBoxes(boxes); //sort the boxes by value

		while(!isFullEnough()) {

		}
	}

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

	//checks if a specific box fits at a secific place with the coordinates x, y, and z
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
					if (space[x + i][y + j][z + k] != '\u0000' || space[x + i][y + j][z + k] != '0') {
						return false;
					}
				}
			}
		}
		return true;
	}

	//places a box at a given point with coordinates x, y, and z
	public static void placeBox(Doos box) { //CHECK IF FITS FIRST!
			for (int i = 0; i < (int) box.getArrayLength(); i++) {
				for (int j = 0; j < (int) box.getArrayWidth(); j++) {
					for (int k = 0; k < (int) box.getArrayHeight(); k++) {
						space[x + i][y + j][z + k] = box.getName();
					}
				}
			}
			volumeInside = volumeInside + box.getVolume(); //update the volume
			PlacedBox newBox = new PlacedBox(box.getArrayLength(), box.getArrayWidth(), box.getArrayHeight(), box.getName(), x, y, z); //create new PlacedBox-Object to add to the solution
			solution.add(newBox);
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

	//returns true if a certain percentage of the cargo-space is full
	public static boolean isFullEnough() {
		if (volumeInside >= stopPercentage*SPACEVOLUME) {
			return true;
		}
		return false;
	}

}

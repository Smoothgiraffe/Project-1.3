public class A extends Space{

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

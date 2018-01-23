import java.util.*;

/*
	A class meant to be the superclass for all solving algorithms.
	It saves general variables such as the size of the cargo space.
	It is able to find the coordinates for a new box, place it in there, keep track of the steps made, how full the space is, and more.
*/

public class CargoSpace {

	//these variables describe the cargo-space
	protected static double SPACELENGTH = 2;
	protected static double SPACEHEIGHT = 5;
	protected static double SPACEWIDTH = 3;

	protected static final double SPACEVOLUME = SPACELENGTH*SPACEWIDTH*SPACEHEIGHT;
	protected static final double STOPPERCENTAGE = 0.8;
	protected static double cargoVolume = 0;
	protected static double cargoValue = 0;
	protected static ArrayList<PlacedParcel> solution = new ArrayList<PlacedParcel>();
	protected static ArrayList<PlacedParcel> bestSolution = new ArrayList<PlacedParcel>();
	protected static ArrayList<PlacedPentomino> pentSolution = new ArrayList<PlacedPentomino>();
	protected static ArrayList<PlacedPentomino> bestPentSolution = new ArrayList<PlacedPentomino>();

	//space is a three-dimensional array where every single spot acts as a 0.5*0.5*0.5 block.
	protected static char[][][] space = new char[(int)SPACELENGTH][(int)SPACEHEIGHT][(int)SPACEWIDTH];

	//checks if a specific box//pentomino fits at a specific place with the coordinates x, y, and z
	public static boolean fits(Parcel box, int x, int y, int z) {
		//checks for out-of-bound-errors
		if (x + box.getLength() > space.length) {
			return false;
		}
		if (y + box.getHeight() > space[0].length) {
			return false;
		}
		if (z + box.getWidth() > space[0][0].length) {
			return false;
		}
		//checks for every single spot in the array to be empty
		for(int i = 0; i < box.getLength(); i++) {
			for (int j = 0; j < box.getHeight(); j++) {
				for (int k = 0; k < box.getWidth(); k++) {
					if (space[x + i][y + j][z + k] != '\u0000' && space[x + i][y + j][z + k] != '0') {
						//System.out.println(false);
						return false;
					}
				}
			}
		}

		return true;
	}

	//checks if a specific box//pentomino fits at a specific place with the coordinates x, y, and z
	public static boolean fits(Pentomino pent, int x, int y, int z) {
		char[][][] pentomino = pent.toArray();
		//checks for out-of-bound-errors
		if (x + pentomino.length > space.length) {
			return false;
		}
		if (y + pentomino[0].length > space[0].length) {
			return false;
		}
		if (z + pentomino[0][0].length > space[0][0].length) {
			return false;
		}
		//checks for every single spot in the array to be empty
		for(int i = 0; i < pentomino.length; i++) {
			for (int j = 0; j < pentomino[0].length; j++) {
				for (int k = 0; k < pentomino[0][0].length; k++) {
					if (space[x + i][y + j][z + k] != '\u0000' && space[x + i][y + j][z + k] != '0' && pentomino[i][j][k] != '\u0000' && pentomino[i][j][k] != '0') {
						//System.out.println(false);
						return false;
					}
				}
			}
		}

		return true;
	}

	//places a box at a given point with coordinates x, y, and z
	public static void placeBoxAt(Parcel box, int x, int y, int z) { //CHECK IF FITS FIRST!
		for (int i = 0; i < box.getLength(); i++) {
			for (int j = 0; j < box.getHeight(); j++) {
				for (int k = 0; k < box.getWidth(); k++) {
					space[x + i][y + j][z + k] = box.getName();
				}
			}
		}
		cargoVolume = cargoVolume + box.getVolume(); //update the volume
		cargoValue = cargoValue + box.getValue(); //update the value

		//create new PlacedParcel-Object to add to the solution
		PlacedParcel newBox = new PlacedParcel(box.getLength(), box.getHeight(), box.getWidth(), box.getName(), (double) x / 2, (double) y / 2, (double) z / 2);
		solution.add(newBox); //add it to solution
	}

	public static void placePentominoAt(Pentomino pentomino, int x, int y, int z) {
		char[][][] pent = pentomino.toArray();
		for (int i = 0; i < pent.length; i++) {
			for (int j = 0; j < pent[0].length; j++) {
				for (int k = 0; k < pent[0][0].length; k++) {
					if(pent[i][j][k] != '0' && pent[i][j][k] != '\u0000') {
						space[x + i][y + j][z + k] = pentomino.getName();
					}
				}
			}
		}
		cargoVolume = cargoVolume + 5; //update the volume
		cargoValue = cargoValue + pentomino.getValue(); //update the value

		//create new PlacedPentomino-Object to add to the solution
		PlacedPentomino newPent = new PlacedPentomino(pentomino.getName(), (double) x / 2, (double) y / 2, (double) z / 2);
		newPent.setVersion(pentomino.getFlipVersion(), pentomino.getRotation());
		pentSolution.add(newPent); //add it to pent solution
	}

	//deletes the last box in the solution-Array and updates the space accordingly
	public static void deleteBox(int index) {
		//update the space
		PlacedParcel deleteBox = solution.get(index);
		//System.out.println("deletebox " + deleteBox.getName() + " " + deleteBox.getLength() + " " + deleteBox.getWidth() +  " " + deleteBox.getHeight());
		for (int i = 0; i < deleteBox.getLength(); i++) {
			for (int j = 0; j < deleteBox.getHeight(); j++) {
				for (int k = 0; k < deleteBox.getWidth(); k++) {
					//System.out.println(i + " " + j + " " + k + " " + solution.size() + " " + index);
					space[(int) (deleteBox.getX()*2 + i)][(int) (deleteBox.getY()*2 + j)][(int) (deleteBox.getZ()*2 + k)] = '\u0000';
				}
			}
		}
        cargoVolume = cargoVolume - deleteBox.getVolume(); //update the volume
				cargoValue = cargoValue - deleteBox.getValue(); //update the value
		//update the arrayList
		solution.remove(index); //delete it from the solution
	}

	public void deletePentomino(int index) {
		//update the space
		PlacedPentomino deletePent = pentSolution.get(index);
		char[][][] pentArray = deletePent.toArray();
		for(int i = 0; i < pentArray.length; i++) {
			for(int j = 0; j < pentArray[0].length; j++) {
				for(int k = 0; k < pentArray[0][0].length; k++) {
					if(pentArray[i][j][k] != '0' && pentArray[i][j][k] != '\u0000') {
						space[(int) (deletePent.getX()*2 + i)][(int) (deletePent.getY()*2 + j)][(int) (deletePent.getZ()*2 +k)] = '\u0000';
					}
				}
			}
		}
		cargoVolume = cargoVolume - 5; //update the volume
		cargoValue = cargoValue - deletePent.getValue(); //update the value
		pentSolution.remove(index); //delete it from the pent solution
	}

	//returns true if a certain percentage of the cargo-space is full (for exercise b and d)
	public static boolean isFullEnough() {
		if (cargoVolume >= STOPPERCENTAGE*SPACEVOLUME) {
			return true;
		}
		return false;
	}

	//returns true if the entire cargo-space is full (for exercise a and c)
	public static boolean isFull() {
		for (int i = 0; i < space.length; i++) { //height
			for (int j = 0; j < space[0].length; j++) { //width
				for (int k = 0; k < space[0][0].length; k++) { //length
					if (space[i][j][k] == '\u0000' || space[i][j][k] == '0') {
						return false;
					}
				}
			}
		}
		return true;
	}

	//returns true if the value is higher then a certain threshold
	public static boolean isValuaBleEnough(double maxValue){
		if(cargoValue > (maxValue * STOPPERCENTAGE)){
			return true;
		}
		return false;
	}
	
	public double getSpaceLength(){
		return SPACELENGTH;
	}
	public double getSpaceHeight(){
		return SPACEHEIGHT;
	}
	public double getSpaceWidth(){
		return SPACEWIDTH;
	}
	public void setSpaceLength(double SPACELENGTH){
		this.SPACELENGTH = SPACELENGTH;
	}
	
	public void setSpaceWidth(double SPACEWIDTH){
		this.SPACEWIDTH = SPACEWIDTH;
	}
	public void setSpaceHeight(double SPACEHEIGHT){
		this.SPACEHEIGHT =  SPACEHEIGHT;
	}

	//prints the entire cargo-space in array form
	public static void print() {
		for(int j = 0; j < space[0].length; j++) {
			for(int i = 0; i < space.length; i++) {
				for(int k = 0; k < space[0][0].length; k++) {
					System.out.print(space[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("length " + space.length + " heigth " + space[0].length + " width " + space[0][0].length);
	}
}

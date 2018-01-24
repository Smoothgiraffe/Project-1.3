import java.util.*;

/*
	A class meant to be the superclass for all solving algorithms.
	It saves general variables such as the size of the cargo space.
	It is able to find the coordinates for a new parcel, place it in there, keep track of the steps made, how full the space is, and more.
*/

public class CargoSpace {

	//these variables describe the cargo-space

	protected double spaceVolume;
	protected double stopPercentage = 0.9;
	protected double cargoVolume = 0;
	protected double cargoValue = 0;
	protected double bestCargoValue = 0;

	protected  ArrayList<PlacedParcel> solution = new ArrayList<PlacedParcel>();
	protected  ArrayList<PlacedPentomino> pentSolution = new ArrayList<PlacedPentomino>();

	//space is a three-dimensional array where every single spot acts as a 0.5*0.5*0.5 block.
	protected  char[][][] space;

	//checks if a specific parcel//pentomino fits at a specific place with the coordinates x, y, and z
	public  boolean fits(Parcel parcel, int x, int y, int z) {
		//checks for out-of-bound-errors
		if (x + parcel.getLength() > space.length) {
			return false;
		}
		if (y + parcel.getHeight() > space[0].length) {
			return false;
		}
		if (z + parcel.getWidth() > space[0][0].length) {
			return false;
		}
		//checks for every single spot in the array to be empty
		for(int i = 0; i < parcel.getLength(); i++) {
			for (int j = 0; j < parcel.getHeight(); j++) {
				for (int k = 0; k < parcel.getWidth(); k++) {
					if (space[x + i][y + j][z + k] != '\u0000' && space[x + i][y + j][z + k] != '0') {
						return false;
					}
				}
			}
		}

		return true;
	}

	//checks if a specific parcel//pentomino fits at a specific place with the coordinates x, y, and z
	public boolean fits(Pentomino pent, int x, int y, int z) {
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
						return false;
					}
				}
			}
		}

		return true;
	}

	//places a parcel at a given point with coordinates x, y, and z
	public void placeParcelAt(Parcel parcel, int x, int y, int z) { //CHECK IF FITS FIRST!
		for (int i = 0; i < parcel.getLength(); i++) {
			for (int j = 0; j < parcel.getHeight(); j++) {
				for (int k = 0; k < parcel.getWidth(); k++) {
					space[x + i][y + j][z + k] = parcel.getName();
				}
			}
		}
		cargoVolume += parcel.getVolume(); //update the volume
		cargoValue += parcel.getValue(); //update the value

		//create new PlacedParcel-Object to add to the solution
		PlacedParcel newParcel = new PlacedParcel(parcel.getLength(), parcel.getHeight(), parcel.getWidth(), parcel.getName(), parcel.getValue(),(double) x / 2, (double) y / 2, (double) z / 2);
		solution.add(newParcel); //add it to solution
	}

	public void placePentominoAt(Pentomino pentomino, int x, int y, int z) {
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
		cargoVolume += 5; //update the volume
		cargoValue += pentomino.getValue(); //update the value

		//create new PlacedPentomino-Object to add to the solution
		PlacedPentomino newPent = new PlacedPentomino(pentomino.getName(), pentomino.getValue(), (double) x / 2, (double) y / 2, (double) z / 2);
		newPent.setVersion(pentomino.getFlipVersion(), pentomino.getRotation());
		pentSolution.add(newPent); //add it to pent solution
	}

	//deletes the last parcel in the solution-Array and updates the space accordingly
	public void deleteParcel(int index) {
		//update the space
		PlacedParcel deleteParcel = solution.get(index);
		for (int i = 0; i < deleteParcel.getLength(); i++) {
			for (int j = 0; j < deleteParcel.getHeight(); j++) {
				for (int k = 0; k < deleteParcel.getWidth(); k++) {
					space[(int) (deleteParcel.getX()*2 + i)][(int) (deleteParcel.getY()*2 + j)][(int) (deleteParcel.getZ()*2 + k)] = '\u0000';
				}
			}
		}
        cargoVolume -= deleteParcel.getVolume(); //update the volume
		cargoValue -= deleteParcel.getValue(); //update the value
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
		cargoVolume -= 5; //update the volume
		cargoValue -= deletePent.getValue(); //update the value
		pentSolution.remove(index); //delete it from the pent solution
	}

	//returns true if a certain percentage of the cargo-space is full (for exercise b and d)
	public boolean isFullEnough() {
		if (cargoVolume >= stopPercentage*spaceVolume) {
			return true;
		}
		return false;
	}

	//returns true if the entire cargo-space is full (for exercise a and c)
	public boolean isFull() {
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
	public boolean isValuaBleEnough(double maxValue){
		if(cargoValue > (maxValue * stopPercentage)){
			return true;
		}
		return false;
	}

	//prints the entire cargo-space in array form
	public void print() {
		for(int j = 0; j < space[0].length; j++) {
			for(int i = 0; i < space.length; i++) {
				for(int k = 0; k < space[0][0].length; k++) {
					System.out.print(space[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}

import java.util.*;

/*
	A class meant to be the superclass for all solving algorithms.
	It saves general variables such as the size of the cargo space and the volume of the cargo space
	It is able to find the coordinates for a new object, place it in there, check how full the space is, and more.
*/

public class Space {

	//these variables describe the cargo-space
	protected static final double SPACELENGTH = 16.5;
	protected static final double SPACEWIDTH = 2.5;
	protected static final double SPACEHEIGHT = 4;
	protected static final double SPACEVOLUME = SPACELENGTH*SPACEWIDTH*SPACEHEIGHT;
	protected static final double stopPercentage = 0.9;
	protected static double volumeInside = 0;
	protected static ArrayList<PlacedBox> solution = new ArrayList<PlacedBox>();

	//x, y, and z are the coordinates where the next Box/pentomino is placed
	protected static int x, y, z = 0;

	//space is a three-dimensional array where every single spot acts as a 0.5*0.5*0.5 block.
	protected static char[][][] space = new char[(int)SPACELENGTH*2][(int)SPACEWIDTH*2][(int)SPACEHEIGHT*2];

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

	//sets the coordinates to be xPos, yPos, and zPos
	public static void setCoordinates(int xPos, int yPos, int zPos) {
		x = xPos;
		y = yPos;
		z = zPos;
	}

	//places a pentomino at a certain positon x, y, z
	public static void placePentominoAt(char pentomino, int rotation, int version, int x, int y, int z) {
		char[][][] pent = Pentomino.getPentomino(pentomino, version);
		for(int i = 0; i < pent.length; i++){
			for(int j = 0; j < pent[0].length; j++) {
				for(int k = 0; k < pent[0][0].length; k++) {
					if(pent[i][j][k] != '\u0000') {
						space[x + i][y + j][z + k] = pent[i][j][k];
					}
				}
			}
		}
	}

}

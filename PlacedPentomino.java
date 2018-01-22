/*
	This class makes a new pentomino which is already placed and is able to return its x, y and z-coordinates
	Is used for CargoSpace to make it keep every placedPentomino up to the current point
*/
public class PlacedPentomino extends Pentomino{

	private double placedX;
	private double placedY;
	private double placedZ;

	//Constructor without value
	public PlacedPentomino(char aName, double aPlaceX, double aPlaceY, double aPlaceZ) {
		super(aName);
		placedX = aPlaceX;
		placedY = aPlaceY;
		placedZ = aPlaceZ;

	}

	//Constructor with value
	public PlacedPentomino(char aName, double aValue, double aPlaceX, double aPlaceY, double aPlaceZ) {
		super(aName, aValue);
		placedX = aPlaceX;
		placedY = aPlaceY;
		placedZ = aPlaceZ;

	}

	//Next the getters:

	public int getRotation() {
		return rotation;
	}

	public double getX() {
		return placedX;
	}

	public double getY() {
		return placedY;
	}

	public double getZ() {
		return placedZ;
	}

	public char[][][] getPlacedPentomino() {
		return super.toArray();
	}

}

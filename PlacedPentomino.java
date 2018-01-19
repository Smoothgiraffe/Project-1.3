/*
	This class makes a new pentomino which is already placed and is able to return its x, y and z-coordinates
	Is used for space so the cargo space to make it hold every placed Parcel up to the current point
*/
public class PlacedPentomino extends Pentomino{
	protected double value;
	protected char name;
	private int version;
	private int rotation;
	private int placedX;
	private int placedY;
	private int placedZ;

	//Constructor without value
	public PlacedPentomino(char aName, int aPlaceX, int aPlaceY, int aPlaceZ) {
		super(aName);
		placedX = aPlaceX;
		placedY = aPlaceY;
		placedZ = aPlaceZ;

	}

	//Constructor with value
	public PlacedPentomino(char aName, double aValue, int aPlaceX, int aPlaceY, int aPlaceZ) {
		super(aName, aValue);
		placedX = aPlaceX;
		placedY = aPlaceY;
		placedZ = aPlaceZ;

	}

	public int getRotation() {
		return rotation;
	}

	public int getVersion() {
		return version;
	}

	public int getX() {
		return placedX;
	}

	public int getY() {
		return placedY;
	}

	public int getZ() {
		return placedZ;
	}

	public char[][][] getPlacedPentomino() {
		return super.toArray();
	}

}

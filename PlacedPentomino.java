public class PlacedPentomino extends Pentomino{
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

	public int getX() {
		return placedX;
	}

	public int getY() {
		return placedY;
	}

	public int getZ() {
		return placedZ;
	}
}

/*
	This class makes a new box which is already placed and is able to return its x, y and z-coordinates
*/
public class PlacedBox extends Doos {
	private double placedX;
	private double placedY;
	private double placedZ;

	//Constructor without value
	public PlacedBox(double aLength, double aHeight, double aWidth, char aName, double aPlaceX, double aPlaceY, double aPlaceZ) {
		super(aLength, aHeight, aWidth, aName);
		placedX = aPlaceX;
		placedY = aPlaceY;
		placedZ = aPlaceZ;
	}

	//Constructor with value
	public PlacedBox(double aLength, double aHeight, double aWidth, char aName, double aValue, double aPlaceX, double aPlaceY, double aPlaceZ) {
		super(aLength, aHeight, aWidth, aName, aValue);
		placedX = aPlaceX;
		placedY = aPlaceY;
		placedZ = aPlaceZ;
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

}

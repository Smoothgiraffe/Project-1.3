/*
	This class makes a new parcel which is already placed and is able to return its x, y and z-coordinates
	Is used for space so the cargo space to make it hold every placed Parcel up to the current point
*/
public class PlacedParcel extends Parcel {
	private double placedX;
	private double placedY;
	private double placedZ;

	//Constructor without value
	public PlacedParcel(double aLength, double aHeight, double aWidth, char aName, double aPlaceX, double aPlaceY, double aPlaceZ) {
		super(aLength, aHeight, aWidth, aName);
		placedX = aPlaceX;
		placedY = aPlaceY;
		placedZ = aPlaceZ;
	}

	//Constructor with value
	public PlacedParcel(double aLength, double aHeight, double aWidth, char aName, double aValue, double aPlaceX, double aPlaceY, double aPlaceZ) {
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

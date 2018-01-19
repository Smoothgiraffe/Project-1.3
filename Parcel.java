/*
* The class Parcel is called for creating parcel objects with customizable sizes, names and, if needed value
* The parcels can be returned in a rotated state
*/

public class Parcel {

	protected double length;
	protected double width;
	protected double height;
	protected char name;
	protected double valuePerUnit;
	protected double volume;
	protected double value;

	//Constructor without value
	public Parcel(double aLength, double aHeight, double aWidth, char aName) {
		length = aLength;
		width = aWidth;
		height = aHeight;
		name = aName;
		volume = height*length*width;
	}

	//Constructor with value
	public Parcel(double aLength, double aHeight, double aWidth, char aName, double aValue) {
		length = aLength;
		width = aWidth;
		height = aHeight;
		name = aName;
		value = aValue;
		valuePerUnit = (((aValue/aHeight)/aLength)/aWidth);
		volume = height*length*width;
	}

	//Calculates the value per unit (1x1x1 square)
	public double getValuePerUnit() {
		return valuePerUnit;
	}

	public double getLength() {
		return length;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public char getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	public double getVolume() {
		return volume;
	}

	//Returns the box which is rotated based on the number, numbers from 0 to 5 can be used (6 rotations are possible)
	public Parcel rotate(int rotation) {
		Parcel rotatedBox = null;
		if (rotation == 0) {
			rotatedBox = new Parcel(length, height, width, name, value);
		} else if (rotation == 1) {
			rotatedBox = new Parcel(length, width, height, name, value);
		} else if (rotation == 2) {
			rotatedBox = new Parcel(width, length, height, name, value);
		} else if (rotation == 3) {
			rotatedBox = new Parcel(width, height, length, name, value);
		} else if (rotation == 4) {
			rotatedBox = new Parcel(height, width, length, name, value);
		} else if (rotation == 5) {
			rotatedBox = new Parcel(height, length, width, name, value);
		}
		return rotatedBox;
	}

}

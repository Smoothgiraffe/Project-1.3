/*
* The class box is called for creating one box with its height, length and width, and perhaps a value
* The box can return a rotated version and the value per unit
* The other classes are to get the height, length, width and/or value
*/

public class Doos {

	protected double length;
	protected double width;
	protected double height;
	protected int arrayLength;
	protected int arrayWidth;
	protected int arrayHeight;
	protected char name;
	protected double valuePerUnit;
	protected double volume;
	protected double value;

	//Constructor without value
	public Doos(double aLength, double aWidth, double aHeight, char aName) {
		length = aLength;
		width = aWidth;
		height = aHeight;
		arrayLength = (int) aLength *2;
		arrayWidth = (int) aWidth *2;
		arrayHeight = (int) aHeight *2;
		name = aName;
		volume = height*length*width;
	}

	//Constructor with value
	public Doos(double aLength, double aWidth, double aHeight, char aName, double aValue) {
		length = aLength;
		width = aWidth;
		height = aHeight;
		arrayLength = (int) aLength *2;
		arrayWidth = (int) aWidth *2;
		arrayHeight = (int) aHeight *2;
		name = aName;
		value = aValue;
		valuePerUnit = (((aValue/aHeight)/aLength)/aWidth);
		volume = height*length*width;
	}

	//Calculates the value per unit (1x1x1 square)
	public double getValuePerUnit() {
		return valuePerUnit;
	}

	public int getArrayLength() {
		return arrayLength;
	}

	public int getArrayWidth() {
		return arrayWidth;
	}

	public int getArrayHeight() {
		return arrayHeight;
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
	public Doos rotate(int rotation) {
		Doos rotatedBox = null;
		if (rotation == 0) {
			rotatedBox = new Doos(length, width, height, name, value);
		} else if (rotation == 1) {
			rotatedBox = new Doos(length, height, width, name, value);
		} else if (rotation == 2) {
			rotatedBox = new Doos(width, length, height, name, value);
		} else if (rotation == 3) {
			rotatedBox = new Doos(width, height, length, name, value);
		} else if (rotation == 4) {
			rotatedBox = new Doos(height, width, length, name, value);
		} else if (rotation == 5) {
			rotatedBox = new Doos(height, length, width, name, value);
		}
		return rotatedBox;
	}

	/*
	public char[][][] returnAsArray() { //returns a box as an array for the cargo space
		char[][][] arrayBox = new char[length*2][width*2][height*2];

		for (int i = 0; i < arrayBox.length; i++) {
			for (int j = 0; j < arrayBox[i].length, j++) {
				for (int k = 0; k < arrayBox[i][j].length; k++) {
					arrayBox[i][j][k] = name;
				}
			}
		}
		return arrayBox;
	}
	*/

}

/*
* The class box is called for creating one box with its height, length and width, and perhaps a value
* The box can return a rotated version and the value per unit
* The other classes are to get the height, length, width and/or value
*/

public class Box {

	private double height;
	private double length;
	private double width;
	private char name;

	private double value;

	//Constructor without value
	public Box(double aLength, double aWidth, double aHeight, char aName) {
		height = aHeight;
		length = aLength;
		width = aWidth;
		name = aName;
		value = 0;
	}

	//Constructor with value
	public Box(double aLength, double aWidth, double aHeight, char aName, double aValue) {
		height = aHeight;
		length = aLength;
		width = aWidth;
		name = aName;
		value = aValue;
	}

	//Calculates the value per unit (1x1x1 square)
	public double getValuePerUnit() {
		return (((value/height)/length)/width);
	}

	public double getHeight() {
		return height;
	}

	public double getLength() {
		return length;
	}

	public double getWidth() {
		return width;
	}

	public char getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	//Returns the box which is rotated based on the number, numbers from 0 to 5 can be used (6 rotations are possible)
	public Box rotate(int rotation) {
		Box rotatedBox = null;
		if (rotation == 1) {
			rotatedBox = new Box(length, height, width, name, value);
		} else if (rotation == 2) {
			rotatedBox = new Box(width, length, height, name, value);
		} else if (rotation == 3) {
			rotatedBox = new Box(width, height, length, name, value);
		} else if (rotation == 4) {
			rotatedBox = new Box(height, width, length, name, value);
		} else if (rotation == 5) {
			rotatedBox = new Box(height, length, width, name, value);
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

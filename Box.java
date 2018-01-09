/*
* The class box is called for creating one box with its height, length and width
* The box has a certain value
* One class is to get the value per unit(1x1x1)
* The other classes are to get the height, length, width and/or value
*/

public class Box {

	private double height;
	private double length;
	private double width;

	private double value;
	
	//Constructor without value
	public Box(double aHeight, double aLength, double aWidth) {
		height = aHeight;
		length = aLength;
		width = aWidth;
		value = 0;
	}
	
	//Constructor with value
	public Box(double aHeight, double aLength, double aWidth, double aValue) {
		height = aHeight;
		length = aLength;
		width = aWidth;
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

	public double getValue() {
		return value;
	}
}

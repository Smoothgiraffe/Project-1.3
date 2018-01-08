public class Box {

	private double height;
	private double length;
	private double width;

	private double value;

	public Box(double aHeight, double aLength, double aWidth) {
		height = aHeight;
		length = aLength;
		width = aWidth;
		value = 0;
	}
	public Box(double aHeight, double aLength, double aWidth, double aValue) {
		height = aHeight;
		length = aLength;
		width = aWidth;
		value = aValue;
	}
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

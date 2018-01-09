/*
  This class makes a new box which is already placed and is able to return its x, y and z-coordinates
*/
public class PlacedBox extends Box {
  private double placedX;
  private double placedY;
  private double placedZ;

  //Constructor without value
  public PlacedBox(double aLength, double aWidth, double aHeight, char aName, int aPlaceX, int aPlaceY, int aPlaceZ) {
    super(aLength, aWidth, aHeight, aName);
    placedX = aPlaceX;
    placedY = aPlaceY;
    placedZ = aPlaceZ;

  }

  //Constructor with value
  public PlacedBox(double aLength, double aWidth, double aHeight, char aName ,double aValue, int aPlaceX, int aPlaceY, int aPlaceZ) {
    super(aLength, aWidth, aHeight,aName, aValue);
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

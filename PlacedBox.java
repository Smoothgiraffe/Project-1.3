public class PlacedBox extends Box {
  private double placedX;
  private double placedY;
  private double placedZ;

  public PlacedBox(double aLength, double aWidth, double aHeight, char aName, int aPlaceX, int aPlaceY, int aPlaceZ) {
    super(aLength, aWidth, aHeight, aName);
    placedX = aPlaceX;
    placedY = aPlaceY;
    placedZ = aPlaceZ;

  }

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

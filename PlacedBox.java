public class PlacedBox extends Box {
  private double placedX;
  private double placedY;
  private double placedZ;

  public PlacedBox(double aHeight, double aLength, double aWidth, double aPlaceX, double aPlaceY, double aPlaceZ) {
    super(aHeight, aLength, aWidth);
    placedX = aPlaceX;
    placedY = aPlaceY;
    placedZ = aPlaceZ;

  }

  public PlacedBox(double aHeight, double aLength, double aWidth, double aValue, double aPlaceX, double aPlaceY, double aPlaceZ) {
    super(aHeight, aLength, aWidth, aValue);
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

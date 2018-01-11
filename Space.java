/*
  A class meant to be the superclass for all solving algorithms.
  It saves general variables such as the size of the cargo space.
  It is able to find the coordinates for a new box, place in there, keep track of the steps made, and more
*/

public class Space {

  //these variables describe the cargo-space
  protected static final double SPACELENGTH = 16.5;
  protected static final double SPACEWIDTH = 2.5;
  protected static final double SPACEHEIGHT = 4;
  protected static final double SPACEVOLUME = SPACELENGTH*SPACEWIDTH*SPACEHEIGHT*8;
  protected static final double stopPercentage = 0.9;
  protected static double completeBoxVolume = 0;

  //x, y, and z are the coordinates where the next
  protected static int x, y, z = 0;

  //space is a three-dimensional array where every single spot acts as a 0.5*0.5*0.5 block.
  protected static char[][][] space = new char[(int)SPACELENGTH*2][(int)SPACEWIDTH*2][(int)SPACEHEIGHT*2];

  //computes the coordinates for the next box/pentomino to be placed, this returns false if the space is full
  public static boolean computeNewCoordinates() {
    for (int i = x; i < space.length; i++) { //height
      for (int j = y; j < space[0].length; j++) { //width
        for (int k = z; k < space[0][0].length; k++) { //length
          if (space[i][j][k] == '\u0000') {
            x = i;
            y = j;
            z = k;
            return true;
          }
        }
      }
    }
    return false;
  }

  //checks if a specific box//pentomino fits at a secific place with the coordinates x, y, and z
  public static boolean fits(Doos box, int x, int y, int z) {
    //checks for out-of-bound-errors
    if (x + box.getLength() > space.length) {
      return false;
    }
    if (y + box.getWidth() > space[0].length) {
      return false;
    }
    if (z + box.getHeight() > space[0][0].length) {
      return false;
    }
    //checks for every single spot in the array to be empty
    for(int i = 0; i < box.getLength(); i++) {
      for (int j = 0; j < box.getWidth(); j++) {
        for (int k = 0; k < box.getHeight(); k++) {
          if (space[x + i][y + j][z + k] != '\u0000') {
            return false;
          }
        }
      }
    }
    return true;
  }

  //sorts an array of boxes after value per unit and returns them
  public static Doos[] sortBoxes(Doos[] boxes) {
    Doos[] newBoxes = new Doos[boxes.length];
    if (boxes[0].getValuePerUnit() >= boxes[1].getValuePerUnit() && boxes[0].getValuePerUnit() >= boxes[2].getValuePerUnit()) {
      newBoxes[0] = boxes[0];
      if (boxes[1].getValuePerUnit() >= boxes[2].getValuePerUnit()) {
        newBoxes[1] = boxes[1];
        newBoxes[2] = boxes[2];
      } else {
        newBoxes[1] = boxes[2];
        newBoxes[2] = boxes[1];
      }
    } else if (boxes[1].getValuePerUnit() >= boxes[0].getValuePerUnit() && boxes[1].getValuePerUnit() >= boxes[2].getValuePerUnit()) {
      newBoxes[0] = boxes[1];
      if (boxes[0].getValuePerUnit() >= boxes[2].getValuePerUnit()) {
        newBoxes[1] = boxes[0];
        newBoxes[2] = boxes[2];
      } else {
        newBoxes[1] = boxes[2];
        newBoxes[2] = boxes[0];
      }
    } else if (boxes[2].getValuePerUnit() >= boxes[0].getValuePerUnit() && boxes[2].getValuePerUnit() >= boxes[1].getValuePerUnit()) {
      newBoxes[0] = boxes[2];
      if (boxes[0].getValuePerUnit() >= boxes[1].getValuePerUnit()) {
        newBoxes[1] = boxes[0];
        newBoxes[2] = boxes[1];
      } else {
        newBoxes[1] = boxes[1];
        newBoxes[2] = boxes[0];
      }
    }
    return newBoxes;
  }

  //places a box at a given point with coordinates x, y, and z
  public static PlacedBox[] placeBoxAt(Doos box, int x, int y, int z, PlacedBox[] boxArray) {
    if (fits(box, x, y, z)) {
      for (int i = 0; i < (int) box.getLength(); i++) {
        for (int j = 0; j < (int) box.getWidth(); j++) {
          for (int k = 0; k < (int) box.getHeight(); k++) {
            space[x + i][y + j][z + k] = box.getName();
          }
        }
      }
      PlacedBox newBox = new PlacedBox(box.getLength(), box.getWidth(), box.getHeight(), box.getName(), x, y, z);
      completeBoxVolume = completeBoxVolume + box.getVolume();
      return trackBox(boxArray, newBox);
    }
    return boxArray;
  }

  public static void placePentominoAt() {

  }

  //keeps track of the boxes placed and puts them into an array
  public static PlacedBox[] trackBox(PlacedBox[] boxArray, PlacedBox placedBox) {
    PlacedBox[] newBoxArray = new PlacedBox[boxArray.length + 1];
    for (int i = 0; i < boxArray.length; i++) {
      newBoxArray[i] = boxArray[i];
    }
    newBoxArray[newBoxArray.length - 1] = placedBox;
    return newBoxArray;
  }

  //deletes the last box in the boxArray and returns a new boxArray with the last entry deleted
  public static PlacedBox[] deleteBox(PlacedBox[] boxArray) {
    PlacedBox lastBox = boxArray[boxArray.length - 1].copy(); //here the width, length and height of the box is multiplied by 2 two times
    for (int i = 0; i < lastBox.getLength(); i++) {
      for (int j = 0; j < lastBox.getWidth(); j++) {
        for (int k = 0; k < lastBox.getHeight(); k++) {
          space [lastBox.getX() + i][lastBox.getY() + j][lastBox.getZ() + k] = '\u0000';
        }
      }
    }
    PlacedBox[] newBoxArray = new PlacedBox[boxArray.length - 1];
    for (int i = 0; i < newBoxArray.length; i++) {
      newBoxArray[i] = boxArray[i];
    }
    completeBoxVolume = completeBoxVolume - lastBox.getVolume();
    return newBoxArray;
  }

  public static boolean isFullEnough() {
    if (completeBoxVolume >= stopPercentage*SPACEVOLUME) {
      return true;
    }
    return false;
  }

}

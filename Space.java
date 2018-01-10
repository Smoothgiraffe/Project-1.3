/*
  A class meant to be the superclass for all solving algorithms.
  It saves general variables such as the size of the cargo space.
*/

public class Space {

  //these variables describe the cargo-space
  private static final double SPACELENGTH = 16.5;
  private static final double SPACEWIDTH = 2.5;
  private static final double SPACEHEIGHT = 4;

  //x, y, and z are the coordinates where the next
  private static int x, y, z = 0;

  //space is a three-dimensional array where every single spot acts as a 0.5*0.5*0.5 block.
  private static char[][][] space = new char[(int)SPACELENGTH*2][(int)SPACEWIDTH*2][(int)SPACEHEIGHT*2];

  //computes the coordinates for the next box/pentomino to be placed, this returns false if the space is full
  public static boolean computeNewCoordinates() {
    for (int i = x; i < space[0][0].length; i++) { //height
      for (int j = y; j < space[0].length; j++) { //width
        for (int k = z; k < space.length; k++) { //length
          if (space[i][j][k] == '0') {
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
  public static boolean fits(Box box, int x, int y, int z) {
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
    for(int i = 0; i < box.getHeight(); i++) {
      for (int j = 0; j < box.getWidth(); j++) {
        for (int k = 0; k < box.getLength(); k++) {
          if (box[i][j][k] != '0' && space[x + i][y + j][z +k] != '0') {
            return false;
          }
        }
      }
    }
    return true;
  }

  //sorts an array of boxes after value per unit and returns them
  public static Box[] sortBoxes(Box[] boxes) {
    Box[] newBoxes = new Box[boxes.length];
    if (boxes[0].getValuePerUnit() > boxes[1].getValuePerUnit() && boxes[0].getValuePerUnit() > boxes[2].getValuePerUnit()) {
      newBoxes[0] = boxes[0];
      if (boxes[1].getValuePerUnit() > boxes[2].getValuePerUnit()) {
        newBoxes[1] = boxes[1];
        newBoxes[2] = boxes[2];
      } else {
        newBoxes[1] = boxes[2];
        newBoxes[2] = boxes[1];
      }
    } else if (boxes[1].getValuePerUnit() > boxes[0].getValuePerUnit() && boxes[1].getValuePerUnit() > boxes[2].getValuePerUnit()) {
      newBoxes[0] = boxes[1];
      if (boxes[0].getValuePerUnit() > boxes[2].getValuePerUnit()) {
        newBoxes[1] = boxes[0];
        newBoxes[2] = boxes[2];
      } else {
        newBoxes[1] = boxes[2];
        newBoxes[2] = boxes[0];
      }
    } else if (boxes[2].getValuePerUnit() > boxes[0].getValuePerUnit() && boxes[2].getValuePerUnit() > boxes[1].getValuePerUnit()) {
      newBoxes[0] = boxes[2];
      if (boxes[0].getValuePerUnit() > boxes[1].getValuePerUnit()) {
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
  public static void placeBoxAt(Box box, int x, int y, int z, PlacedBox[] boxArray) {
    if (fits(box, x, y, z)) {
      for (int i = 0; i < (int) box.getLength(); i++) {
        for (int j = 0; j < (int) box.getWidth(); j++) {
          for (int k = 0; k < (int) box.getHeight(); k++) {
            space [x + i][y + j][z + k] = box.getName();
          }
        }
      }
      trackBox(boxArray, box);
    }
  }

  public static void placePentominoAt() {

  }

  //keeps track of the boxes placed and puts them into an array
  public static PlacedBox[] trackBox(PlacedBox[] boxArray, PlacedBox placedBox) {
    PlacedBox[] newBoxArray = new PlacedBox[boxArray.length + 1];
    for (int i = 0; i < boxArray.length; i++) {
      newBoxArray[i] = boxArray[i];
    }
    newBoxArray[newBoxArray - 1] = placedBox;
    return newBoxArray;
  }

  public static void deleteBox() {

  }

}

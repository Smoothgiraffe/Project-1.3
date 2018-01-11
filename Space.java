import java.util.*;

/*
  A class meant to be the superclass for all solving algorithms.
  It saves general variables such as the size of the cargo space.
  It is able to find the coordinates for a new box, place it in there, keep track of the steps made, how full the space is, and more.
*/

public class Space {

  //these variables describe the cargo-space
  protected static final double SPACELENGTH = 16.5;
  protected static final double SPACEWIDTH = 2.5;
  protected static final double SPACEHEIGHT = 4;
  protected static final double SPACEVOLUME = SPACELENGTH*SPACEWIDTH*SPACEHEIGHT;
  protected static final double stopPercentage = 0.9;
  protected static double completeBoxVolume = 0;
  protected static ArrayList<PlacedBox> solution = new ArrayList<PlacedBox>();

  //x, y, and z are the coordinates where the next Box/pentomino is placed
  protected static int x, y, z = 0;

  //space is a three-dimensional array where every single spot acts as a 0.5*0.5*0.5 block.
  protected static char[][][] space = new char[(int)SPACELENGTH*2][(int)SPACEWIDTH*2][(int)SPACEHEIGHT*2];

  //computes the coordinates for the next box/pentomino to be placed
  public static void computeNewCoordinates() {
    for (int i = x; i < space.length; i++) { //height
      for (int j = y; j < space[0].length; j++) { //width
        for (int k = z; k < space[0][0].length; k++) { //length
          if (space[i][j][k] == '\u0000') {
            setCoordinates(i, j, k);
          }
        }
      }
    }
  }

  public static void setCoordinates(int coordinateX, int coordinateY, int coordinateZ) {
    x = coordinateX;
    y = coordinateY;
    z = coordinateZ;
  }

  //checks if a specific box//pentomino fits at a secific place with the coordinates x, y, and z
  public static boolean fits(Doos box, int x, int y, int z) {
    //checks for out-of-bound-errors
    if (x + box.getArrayLength() > space.length) {
      return false;
    }
    if (y + box.getArrayWidth() > space[0].length) {
      return false;
    }
    if (z + box.getArrayHeight() > space[0][0].length) {
      return false;
    }
    //checks for every single spot in the array to be empty
    for(int i = 0; i < box.getArrayLength(); i++) {
      for (int j = 0; j < box.getArrayWidth(); j++) {
        for (int k = 0; k < box.getArrayHeight(); k++) {
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
  public static void placeBoxAt(Doos box, int x, int y, int z) { //CHECK IF FITS FIRST!
      for (int i = 0; i < (int) box.getArrayLength(); i++) {
        for (int j = 0; j < (int) box.getArrayWidth(); j++) {
          for (int k = 0; k < (int) box.getArrayHeight(); k++) {
            space[x + i][y + j][z + k] = box.getName();
          }
        }
      }
      completeBoxVolume = completeBoxVolume + box.getVolume(); //update the volume
      PlacedBox newBox = new PlacedBox(box.getArrayLength(), box.getArrayWidth(), box.getArrayHeight(), box.getName(), x, y, z); //create new PlacedBox-Object to add to the solution
      solution.add(newBox);
  }

  public static void placePentominoAt() {

  }

  //deletes the last box in the solution-Array and updates the space accordingly
  public static void deleteLastBox() {
    //update the space
    PlacedBox lastBox = solution.get(solution.size() - 1);
    for (int i = 0; i < lastBox.getArrayLength(); i++) {
      for (int j = 0; j < lastBox.getArrayWidth(); j++) {
        for (int k = 0; k < lastBox.getArrayHeight(); k++) {
          space[lastBox.getX() + i][lastBox.getY() + j][lastBox.getZ() + k] = '\u0000';
        }
      }
    }
    solution.remove(solution.size() - 1);
  }

  public static boolean isFullEnough() {
    if (completeBoxVolume >= stopPercentage*SPACEVOLUME) {
      return true;
    }
    return false;
  }

  public static boolean isFull() {
    for (int i = x; i < space.length; i++) { //height
      for (int j = y; j < space[0].length; j++) { //width
        for (int k = z; k < space[0][0].length; k++) { //length
          if (space[i][j][k] == '\u0000') {
            return false;
          }
        }
      }
    }
    return true;
  }

}

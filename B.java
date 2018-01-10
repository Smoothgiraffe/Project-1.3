/*
  A class that is made to solve exercise b.
  It utilizes a so-called greedy algorithm, where we take the most valuable box first and then put it into the crgo space as often as possible.
  When there is no space for the most valuable box, the second-most valueable box is considered, and so on.
*/

public class B {
  //these variables describe the cargo-space
  private static double spaceLength = 16.5;
  private static double spaceWidth = 2.5;
  private static double spaceHeight = 4;

  //x, y, and z are the coordinates where the next
  private static int x, y, z = 0;

  //space is a three-dimensional array where every single spot acts as a 0.5*0.5*0.5 block.
  private static char[][][] space = new char[spaceLength*2][spaceWidth*2][spaceHeight*2];

  public static void main(String[] args) {
    //initiate the three types of boxes
    Box A = new Box(1, 1, 2, 'A', 3);
    Box B = new Box(1, 1.5, 2, 'B', 4);
    Box C = new Box(1.5, 1.5, 1.5, 'C', 5);

    //put them into an array
    Box[] boxes = {A, B, C};
    //sort the boxes by value
    sortBoxes(boxes);


  }

  //computes the coordinates for the next box to be placed, this returns false if the space is full
  public static boolean computeNewCoordinates() {
    for (int i = x; i < space[0][0].length; i++) {
      for (int j = y; j < space[0].length; j++) {
        for (int k = z; k < space.length; k++) {
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

  //places a box at a given point with coordinates x, y, and z
  public static void placeBoxAt(Box box, int x, int y, int z) {
    if (fits(box, x, y, z)) {
      for (int i = 0; i < (int) box.getLength()*2; i++) {
        for (int j = 0; j < (int) box.getWidth()*2; j++) {
          for (int k = 0; k < (int) box.getHeight()*2; k++) {
            space [x + i][y + j][z + k] = box.getName();
          }
        }
      }
    }
  }

  //checks if a specific box fits at a secific place with the coordinates x, y, and z
  public static boolean fits(Box box, int x, int y, int z) {
    if (x + box.getLength() > space.length) {
      return false;
    }
    if (y + box.getWidth() > space[0].length) {
      return false;
    }
    if (z + box.getHeight() > space[0][0].length) {
      return false;
    }

    for (int i = 0; box.getWidth()) {

    }

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
}

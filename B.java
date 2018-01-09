public class B {
  private static double spaceLength = 16.5;
  private static double spaceWidth = 2.5;
  private static double spaceHeight = 4;

  private static int x, y, z = 0;

  private static char[][][] space = new char[33][5][8];

  public static void main(String[] args) {
    Box A = new Box(1, 1, 2, 'A', 3);
    Box B = new Box(1, 1.5, 2, 'B', 4);
    Box C = new Box(1.5, 1.5, 1.5, 'C', 5);

    Box[] boxes = {A, B, C};
    sortBoxes(boxes);


  }

  public static boolean computeNewCoordinates() { //computes the coordinates for the next box to be placed, this returns false if the space is full
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

  public static void setNextCoordinates(int newX, int newY, int newZ) { //places a box
    x = newX;
    y = newY;
    z = newZ;
  }

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

  public static Box[] sortBoxes(Box[] boxes) { //sorts an array of boxes after value per unit and returns them
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

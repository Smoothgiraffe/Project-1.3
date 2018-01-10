/*
  A class that is made to solve exercise b.
  It utilizes a so-called greedy algorithm, where we take the most valuable box first and then put it into the crgo space as often as possible.
  When there is no space for the most valuable box, the second-most valueable box is considered, and so on.
*/

public class B extends Space{

  public static void main(String[] args) {

    //initiate the three types of boxes
    Box A = new Box(1, 1, 2, 'A', 3);
    Box B = new Box(1, 1.5, 2, 'B', 4);
    Box C = new Box(1.5, 1.5, 1.5, 'C', 5);

    Box[] boxes = {A, B, C}; //put them into an array
    sortBoxes(boxes); //sort the boxes by value

  }
}

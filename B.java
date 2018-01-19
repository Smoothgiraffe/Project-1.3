/*
	If parcels of type A, B and C represent values of 3, 4 and 5 units respectively, then what is the maximum value that you can store in your cargo-space?

	A class that is made to solve exercise b.
	It utilizes a so-called greedy algorithm, where we take the most valuable box first and then put it into the cargo space as often as possible.
	When there is no space for the most valuable box, the second-most valueable box is considered, and so on.
*/

public class B extends CargoSpace{

	public static void main(String[] args) {

		//initiate the three types of boxes
		Parcel A = new Parcel(1, 1, 2, 'A');
		Parcel B = new Parcel(1, 1.5, 2, 'B');
		Parcel C = new Parcel(1.5, 1.5, 1.5, 'C');

		Parcel[] boxes = {A, B, C}; //put them into an array
		sortBoxes(boxes); //sort the boxes by value

		while(!isFullEnough()) {

		}
	}
 //sorts an array of boxes after value per unit and returns them
  public static Parcel[] sortBoxes(Parcel[] boxes) {
	Parcel[] newBoxes = new Parcel[boxes.length];
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
}

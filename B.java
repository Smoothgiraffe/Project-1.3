/*
	If parcels of type A, B and C represent values of 3, 4 and 5 units respectively, then what is the maximum value that you can store in your cargo-space?

	A class that is made to solve exercise b.
	It utilizes a so-called greedy algorithm, where we take the most valuable box first and then put it into the cargo space as often as possible.
	When there is no space for the most valuable box, the second-most valueable box is considered, and so on.
*/

public class B extends Space{

	public static void main(String[] args) {

		//initiate the three types of boxes
		Doos A = new Doos(1, 1, 2, 'A', 3);
		Doos B = new Doos(1, 1.5, 2, 'B', 4);
		Doos C = new Doos(1.5, 1.5, 1.5, 'C', 5);

		Doos[] boxes = {A, B, C}; //put them into an array
		sortBoxes(boxes); //sort the boxes by value

		while(!isFullEnough()) {

		}
	}
}

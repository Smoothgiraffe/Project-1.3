import java.util.ArrayList;

/*
	If parcels of type A, B and C represent values of 3, 4 and 5 units respectively, then what is the maximum value that you can store in your cargo-space?

	A class that is made to solve exercise b.
	It utilizes a so-called greedy algorithm, where we take the most valuable box first and then put it into the cargo space as often as possible.
	When there is no space for the most valuable box, the second-most valueable box is considered, and so on.
*/

public class B extends CargoSpace{

	//initiate the three types of boxes
	private Parcel A = new Parcel(2, 2, 4,'A', 3);
	private Parcel B = new Parcel(2, 3, 4, 'B', 4);
	private Parcel C = new Parcel(3, 3, 3, 'C', 5);
	private boolean solutionFound = false;
	Parcel[] parcels = {A, B, C}; //put them into an array

	public B() {
		sortBoxes(parcels);
		fillSpace();
		print();
		
		Display display = new Display(solution);
		if(!solutionFound){
			System.out.println("No solution found!");
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

	private void fillSpace(){
		if(!isFullEnough()) {
			for(int i = 0; i < space.length; i++){
				for(int j = 0; j < space[0].length; j++){
					for(int k = 0; k < space[0][0].length; k++){
						for(int l = 0; l < parcels.length; l++){
							for(int m = 0; m < 6; m++){
								if(fits(parcels[l].rotate(m), i, j, k)){
									placeBoxAt(parcels[l].rotate(m), i, j, k);
									fillSpace();
									if(isFull()){
										solutionFound = true;
										return;
									}
									deleteBox(solution.size() - 1);
									//display.show(solution);
								}
							}
						}
					}
				}
			}
		} else{
			solutionFound = true;
			return;
		}
	}


	public static void main(String[] args) {

		new B();

	}
}

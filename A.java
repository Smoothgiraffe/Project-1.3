import java.util.ArrayList;

/*
	Is it possible to fill the complete cargo space with A, B and/or C parcels, without having any gaps?

	A class that is made to solve exercise B.
	It utilizes a so-called backtrack algorithm, where we try every single possibility.
*/
public class A extends CargoSpace{
	//initiate the three types of boxes
	private Parcel A = new Parcel(2, 2, 4, 'A');
	private Parcel B = new Parcel(2, 3, 4, 'B');
	private Parcel C = new Parcel(3, 3, 3, 'C');
	private boolean solutionFound = false;
	Parcel[] parcels = {A, B, C}; //put them into an array


	public A(){
		fillSpace();
		print();
		Display display = new Display(solution);
		if(!solutionFound){
			System.out.println("No solution found!");
		}
	}

	/*
	Loop through all of the space and all parcels and their rotation, see if they fit and then place them
	After placing, call itself recursively, after backtracking delete the box and then continue the loop
	 */
	private void fillSpace(){
		if(!isFull()) {
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

	public static void main(String args[]){
		new A();
	}
}

import java.util.ArrayList;

/*
	Is it possible to fill the complete cargo-space with A, B and/or C parcels, without having any gaps?
	A class that is made to solve exercise A.
	It utilizes a so-called backtrack algorithm, where we try every single possibility.
*/
public class A extends CargoSpace{
	//initiate the three types of boxes
	private boolean solutionFound = false;
	Parcel[] parcels = null;


	public A(Parcel[] parcels){
		this.parcels = parcels;
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
		 Parcel A = new Parcel(2, 2, 4, 'A');
		 Parcel B = new Parcel(2, 3, 4, 'B');
		 Parcel C = new Parcel(3, 3, 3, 'C');
		 Parcel[] parcels = {A,B,C};
		new A(parcels);
	}
}

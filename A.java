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
	long startTime;


	public A(Parcel[] parcels, double storageLength, double storageHeight, double storageWidth){
		space = new char[(int)storageLength][(int)storageHeight][(int)storageWidth];
		spaceVolume = storageLength*storageHeight*storageWidth;
		this.parcels = parcels;

		startTime = System.nanoTime();
		fillSpace();
		print();
		long passedTime = System.nanoTime() - startTime;
		Display display = new Display(solution);
		System.out.println(passedTime + " time passed");
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
									placeParcelAt(parcels[l].rotate(m), i, j, k);
									fillSpace();
									if(isFull()){
										solutionFound = true;
										return;
									}
									deleteParcel(solution.size() - 1);
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
}

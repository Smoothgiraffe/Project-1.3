import java.util.ArrayList;

/*
	If parcels of type A, B and C represent values of 3, 4 and 5 units respectively, then what is the maximum value that you can store in your cargo-space?
	A class that is made to solve exercise B.
	It utilizes a so-called greedy algorithm, where we take the most valuable parcel first and then put it into the cargo space as often as possible.
	When there is no space for the most valuable parcel, the second-most valuable parcel is considered, and so on.
*/

public class B extends CargoSpace{

	//initiate the three types of parceles
	private boolean solutionFound = false;
	Parcel[] parcels; //put them into an array
	Parcel[] sortedParcels;
	Display display = new Display(solution);
	long startTime;
	double maxValue;

	public B(Parcel[] parcels, double storageLength, double storageHeight, double storageWidth){
		space = new char[(int)storageLength][(int)storageWidth][(int)storageHeight];
		spaceVolume = storageLength*storageHeight*storageWidth;

		this.parcels = parcels;
		sortedParcels = sortParcels(parcels);
		maxValue = sortedParcels[0].getValuePerUnit() * storageLength * storageHeight * storageWidth;
		//display.show(solution);
		System.out.println(maxValue);
		startTime = System.nanoTime();
		fillSpace();
		print();
		System.out.println("The value of the entire cargo is " + cargoValue + ".");
		//new Display(solution);
		display.show(solution);
		if(!solutionFound){
			System.out.println("No solution found!");
		}
	}

	/*
     Loop through all of the cargo-space and all parcels and their rotation, see if they fit and then place them
     Start with placing the first parcel of the sorted array, if it doesn't fit anymore go to the next parcel in the array
    */
	private void fillSpace(){
		if(!isFullEnough()) {
			for(int i = 0; i < space.length; i++){
				for(int j = 0; j < space[0].length; j++){
					for(int k = 0; k < space[0][0].length; k++){
						for(int l = 0; l < parcels.length; l++){
							for(int m = 0; m < 6; m++){
								if(fits(sortedParcels[l].rotate(m), i, j, k)){
									placeParcelAt(sortedParcels[l].rotate(m), i, j, k);
									fillSpace();
									if(cargoValue > bestCargoValue){
										bestCargoValue = cargoValue;
										long passedTime = System.nanoTime() - startTime;
										System.out.println("Time passed: " + passedTime + " current highest value: " + bestCargoValue);
										display.show(solution);
									}
									if(isValuaBleEnough(maxValue)){
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
	//sorts an array of parceles by value per unit in descending order and returns them
   public static Parcel[] sortParcels(Parcel[] parceles) {
 	Parcel[] newParcels = new Parcel[parceles.length];
 	if (parceles[0].getValuePerUnit() >= parceles[1].getValuePerUnit() && parceles[0].getValuePerUnit() >= parceles[2].getValuePerUnit()) {
 	  newParcels[0] = parceles[0];
 	  if (parceles[1].getValuePerUnit() >= parceles[2].getValuePerUnit()) {
 		newParcels[1] = parceles[1];
 		newParcels[2] = parceles[2];
 	  } else {
 		newParcels[1] = parceles[2];
 		newParcels[2] = parceles[1];
 	  }
 	} else if (parceles[1].getValuePerUnit() >= parceles[0].getValuePerUnit() && parceles[1].getValuePerUnit() >= parceles[2].getValuePerUnit()) {
 	  newParcels[0] = parceles[1];
 	  if (parceles[0].getValuePerUnit() >= parceles[2].getValuePerUnit()) {
 		newParcels[1] = parceles[0];
 		newParcels[2] = parceles[2];
 	  } else {
 		newParcels[1] = parceles[2];
 		newParcels[2] = parceles[0];
 	  }
 	} else if (parceles[2].getValuePerUnit() >= parceles[0].getValuePerUnit() && parceles[2].getValuePerUnit() >= parceles[1].getValuePerUnit()) {
 	  newParcels[0] = parceles[2];
 	  if (parceles[0].getValuePerUnit() >= parceles[1].getValuePerUnit()) {
 		newParcels[1] = parceles[0];
 		newParcels[2] = parceles[1];
 	  } else {
 		newParcels[1] = parceles[1];
 		newParcels[2] = parceles[0];
 	  }
 	}
 	return newParcels;
   }
}

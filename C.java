/*
	Is it possible to fill the complete cargo space with L, P and/or T parcels, without having any gaps?
*/
public class C extends CargoSpace{

	private Pentomino l = new Pentomino('l');
	private Pentomino t = new Pentomino('t');
	private Pentomino p = new Pentomino('p');
	private boolean solutionFound = false;
	Pentomino[] pents = {p, t, l}; //put them into an array
	long startTime;


	public C(double storageLength, double storageHeight, double storageWidth){
		space = new char[(int)storageLength][(int)storageHeight][(int)storageWidth];
		spaceVolume = storageLength*storageHeight*storageWidth;

		startTime = System.nanoTime();
		fillSpace();
		print();
		long passedTime = System.nanoTime() - startTime;
		System.out.println(passedTime + " time passed");

		Display display = new Display(pentSolution);
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
						for(int l = 0; l < pents.length; l++){
							for(int m = 0; m < 4; m++){
								for(int n = 0; n < 6; n++) {
									Pentomino clone = pents[l].clone();
									clone.setVersion(m, n);
									if(fits(clone, i, j, k)){
										placePentominoAt(clone, i, j, k);
										fillSpace();
										if(isFull()){
											solutionFound = true;
											return;
										}
										deletePentomino(pentSolution.size() - 1);
										//display.show(solution);
									}
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

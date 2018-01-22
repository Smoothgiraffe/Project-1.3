/*
	If parcels of type L, P and T represent values of 3, 4 and 5 units respectively, then what is the maximum value that you can store in your cargo-space?
*/

public class D extends CargoSpace{
	private Pentomino l = new Pentomino('l');
	private Pentomino t = new Pentomino('t');
	private Pentomino p = new Pentomino('p');
	private boolean solutionFound = false;
	Pentomino[] pents = {l, t, p}; //put them into an array


	public D(){
		Pentomino[] sortedPents = sortPentominoes(pents);
		fillSpace();
		print();
		Display display = new Display(solution);
		if(!solutionFound){
		System.out.println("No solution found!");
		}
	}

	public static Pentomino[] sortPentominoes(Pentomino[] pentomino) {
		Pentomino[] newPentomino = new Pentomino[pentomino.length];
		if (pentomino[0].getValue() >= pentomino[1].getValue() && pentomino[0].getValue() >= pentomino[2].getValue()) {
			newPentomino[0] = pentomino[0];
			if (pentomino[1].getValue() >= pentomino[2].getValue()) {
				newPentomino[1] = pentomino[1];
				newPentomino[2] = pentomino[2];
			} else {
				newPentomino[1] = pentomino[2];
				newPentomino[2] = pentomino[1];
			}
		} else if (pentomino[1].getValue() >= pentomino[0].getValue() && pentomino[1].getValue() >= pentomino[2].getValue()) {
			newPentomino[0] = pentomino[1];
			if (pentomino[0].getValue() >= pentomino[2].getValue()) {
				newPentomino[1] = pentomino[0];
				newPentomino[2] = pentomino[2];
			} else {
				newPentomino[1] = pentomino[2];
				newPentomino[2] = pentomino[0];
			}
		} else if (pentomino[2].getValue() >= pentomino[0].getValue() && pentomino[2].getValue() >= pentomino[1].getValue()) {
			newPentomino[0] = pentomino[2];
			if (pentomino[0].getValue() >= pentomino[1].getValue()) {
				newPentomino[1] = pentomino[0];
				newPentomino[2] = pentomino[1];
			} else {
				newPentomino[1] = pentomino[1];
				newPentomino[2] = pentomino[0];
			}
		}
		return newPentomino;
	}

	/*
	Loop through all of the space and all parcels and their rotation, see if they fit and then place them
	After placing, call itself recursively, after backtracking delete the box and then continue the loop
	 */
	private void fillSpace(){
		if(!isFullEnough()) {
			for(int i = 0; i < space.length; i++){
				for(int j = 0; j < space[0].length; j++){
					for(int k = 0; k < space[0][0].length; k++){
						for(int l = 0; l < sortedPents.length; l++){
							for(int m = 0; m < 4; m++){
								for(int n = 0; n < 6; n++) {
									Pentomino clone = sortedPents[l].clone();
									clone.setVersion(m, n);
									if(fits(clone, i, j, k)){
										placePentominoAt(clone, i, j, k);
										fillSpace();
										if(isFullEnough()){
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

	public static void main(String args[]){
		new C();
	}
}

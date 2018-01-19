import java.util.ArrayList;

/*
	Is it possible to fill the complete cargo space with A, B and/or C parcels, without having any gaps?
*/
public class A extends CargoSpace{
	//initiate the three types of boxes
	private Doos A = new Doos(2, 2, 4, 'A');
	private Doos B = new Doos(2, 3, 4, 'B');
	private Doos C = new Doos(3, 3, 3, 'C');
	private boolean solutionFound = false;
	Doos[] boxes = {A, B, C}; //put them into an array


	public A(){
		fillSpace();
		print();
		Display display = new Display(solution);
		if(!solutionFound){
			System.out.println("no solution found");
		}
	}

	private void fillSpace(){
		if(!isFull()) {
			for(int i = 0; i < space.length; i++){
				for(int j = 0; j < space[0].length; j++){
					for(int k = 0; k < space[0][0].length; k++){
						for(int l = 0; l < boxes.length; l++){
							for(int m = 0; m < 6; m++){
								//System.out.println(i + " " + j + " " + k + " " + l + " " + m);
								if(fits(boxes[l].rotate(m), i, j, k)){
									//System.out.println(boxes[l].getName());
									//System.out.println(boxes[l].getName() + " " + i + " " + j + " " + k);
									placeBoxAt(boxes[l].rotate(m), i, j, k);
									//display.show(solution);
									//print();
									fillSpace();
									if(isFull()){
										//System.out.println("full");
										solutionFound = true;
										return;
									}
									//System.out.println("before " + solution.size());
									deleteBox(solution.size() - 1);
									//display.show(solution);
									//System.out.println("after" + solution.size());
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

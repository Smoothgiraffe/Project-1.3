import java.util.ArrayList;

/*
	Is it possible to fill the complete cargo space with A, B and/or C parcels, without having any gaps?
*/
public class A extends Space{
	//initiate the three types of boxes
	private Doos A = new Doos(2, 2, 4, 'A', 3);
	private Doos B = new Doos(2, 3, 4, 'B', 4);
	private Doos C = new Doos(3, 3, 3, 'C', 5);
	private boolean solutionFound = false;
	Doos[] boxes = {A, B, C}; //put them into an array

	public A(){
		fillSpace();
		new Display(solution);
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
							if(fits(boxes[l], i, j, k)){
								System.out.println(boxes[l].getName() + " " + i + " " + j + " " + k);
								placeBoxAt(boxes[l], i, j, k);
								fillSpace();
								if(isFull()){
									return;
								}
								deleteBox(solution.size() - 1);
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

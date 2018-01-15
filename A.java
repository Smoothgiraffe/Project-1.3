import java.util.ArrayList;

/*
	Is it possible to fill the complete cargo space with A, B and/or C parcels, without having any gaps?
*/
public class A extends Space{
	//initiate the three types of boxes
	private Doos A = new Doos(1, 1, 2, 'A', 3);
	private Doos B = new Doos(1, 1.5, 2, 'B', 4);
	private Doos C = new Doos(1.5, 1.5, 1.5, 'C', 5);
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
		if(solutionFound){
			System.out.println("found");
			return;
		} else if(!isFull()) {
			for(int i = 0; i < space.length; i++){
				for(int j = 0; j < space[0].length; j++){
					for(int k = 0; k < space[0][0].length; k++){
						for(int l = 0; l < boxes.length; l++){
							if(fits(boxes[l], i, j, k)){
								placeBoxAt(boxes[l], i, j, k);
								fillSpace();
								deleteBox(solution.size() - 1, i, j, k);
							}
						}
					}
				}
			}
		} else{
			solutionFound = true;
		}
	}
	public static void main(String args[]){
		new A();
	}
}

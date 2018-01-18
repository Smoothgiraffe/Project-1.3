public class Test extends CargoSpace{
	public static void main(String[] args) {
		Doos doos1 = new Doos(1,1,2,'A');
		Doos doos2 = new Doos(1,1,1,'B');
		Doos doos3 = new Doos(1,1,1, 'C');
		Doos doos4 = new Doos(1,1,2, 'C');
		Doos doos5 = new Doos(1,1,1, 'C');
		Doos doos6 = new Doos(1,1,1, 'C');

		placeBoxAt(doos1,0,1,1);
		placeBoxAt(doos2,1,1,1);
		placeBoxAt(doos3,2,1,1);

		placeBoxAt(doos4,0,2,1);
		placeBoxAt(doos5,1,2,1);
		placeBoxAt(doos6,2,2,1);
		print();
		System.out.println("after");
		deleteBox(solution.size() -1);
		print();

		new Display(solution);
	}
}

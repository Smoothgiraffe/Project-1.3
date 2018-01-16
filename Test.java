public class Test extends Space{
	public static void main(String[] args) {
		Doos doos1 = new Doos(1,1,1,'A');
		Doos doos2 = new Doos(1,1,1,'B');
		Doos doos3 = new Doos(1,1,1, 'C');

		placeBoxAt(doos1,0,1,1);
		placeBoxAt(doos2,1,1,1);
		placeBoxAt(doos3,2,1,1);
		new Display(solution);
	}
}

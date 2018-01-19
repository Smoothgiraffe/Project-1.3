public class Test extends CargoSpace{
	public static void main(String[] args) {
		Parcel doos1 = new Parcel(1,1,1,'A');
		Parcel doos2 = new Parcel(1,1,1,'B');
		Parcel doos3 = new Parcel(1,1,1, 'C');
		Parcel doos4 = new Parcel(1,1,2, 'C');
		Parcel doos5 = new Parcel(1,1,1, 'C');
		Parcel doos6 = new Parcel(1,1,1, 'A');

		placeBoxAt(doos1,0,1,1);
		placeBoxAt(doos2,1,1,1);
		placeBoxAt(doos3,2,1,1);

		placeBoxAt(doos4,0,2,1);
		placeBoxAt(doos5,1,2,1);
		placeBoxAt(doos6,2,2,1);
		print();
		new Display(solution);
		System.out.println();
		deleteBox(solution.size() -1);
		print();

		new Display(solution);
	}
}

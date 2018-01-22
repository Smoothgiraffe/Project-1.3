import java.util.ArrayList;

public class PentToBox {

	public static ArrayList<PlacedParcel> convert(ArrayList<PlacedPentomino> pentArray){
		double scalar = 1.05;
		ArrayList<PlacedParcel> boxArray = new ArrayList<PlacedParcel>();
		System.out.println("Converting to boxes ");
		for (int p = 0; p < pentArray.size(); p++) {
			PlacedPentomino pent = pentArray.get(p);

			char[][][] Array = pent.toArray();

			for(int i = 0; i < Array.length; i++){
				for(int j = 0; j < Array[0].length; j++){
					for(int	k = 0; k < Array[0][0].length; k++){
						if(Array[i][j][k] == 'p'){
							boxArray.add(new PlacedParcel(1, 1, 1, 'A', pent.getX() * scalar + ((double) i / 2), pent.getY() * scalar  + ((double) j / 2), pent.getZ() * scalar  + ((double) k / 2)));
						}
						if(Array[i][j][k] == 'l'){
							boxArray.add(new PlacedParcel(1, 1, 1, 'B', pent.getX() * scalar  + ((double) i / 2), pent.getY() * scalar  + ((double) j / 2), pent.getZ() * scalar  + ((double) k / 2)));
						}
						if(Array[i][j][k] == 't'){
							boxArray.add(new PlacedParcel(1, 1, 1, 'C', pent.getX() * scalar  + ((double) i / 2), pent.getY() * scalar  + ((double) j / 2), pent.getZ() * scalar  + ((double) k / 2)));
						}
					}
				}
			}
		}
		return boxArray;
	}
}

public class Pentomino {

  protected double value;
  protected char name;
  protected final double volume = 2.5; //0.5*5 = 2.5

  char[][][] pentP = {{{'p','p'},{'p','p'},{'p','\u0000'}}};
  char[][][] pentL = {{{'l','\u0000','\u0000','\u0000',},{'l','l','l','l'}}};
  char[][][] pentT = {{{'t','t','t'},{'\u0000','t','\u0000'},{'\u0000','t','\u0000'}}};

  public Pentomino(char aName) {
    name = aName;
  }

  public Pentomino(char aName, double aValue) {
    name = aName;
    value = aValue;
  }

  public double getVolume() {
    return volume;
  }

  public char[][][] getPentP() {
    return pentP;
  }

  public char[][][] getPentL() {
    return pentL;
  }

  public char[][][] getPentT() {
    return pentT;
  }

  public char[][][] getPentomino(char pentomino, int version) {
    char[][][] endPentomino = (getPent + pentomino)();
    // 24 version for rotations (included the mirrored pentominoes
    if(version < 6) {
      return rotate(endPentomino, version);
    } else if(version < 12){
        char[][][] tempPentomino = rotate(endPentomino, version - 6);
        return rotate(tempPentomino, version - 6);
    } else if(version < 24){
        char[][][] temp1Pentomino = rotate(endPentomino, version - 12);
        char[][][] temp2Pentomino = rotate(endPentomino, version - 6);
        return rotate(temp2Pentomino, version - 6);
    } else {
      System.out.println("Error!"); //put a real error here!
    }
  }

  public char[][][] rotate(char[][][] pent, int rotation) {
    if(rotation == 0) {
      return pent;
    } else if(rotation == 1) {
      char[][][] rotatedPent = new char[pent.length][pent[0][0].length][pent[0].length];
      for(int i = 0; i < pent.length; i++) {
        for(int j = 0; j < pent[0][0].length; j++) {
          for(int k = 0; k < pent[0].length; k++) {
            rotatedPent[i][j][k] = pent[i][k][j];
          }
        }
      }
      return rotatedPent;
    } else if(rotation == 2) {
      char[][][] rotatedPent = new char[pent[0].length][pent.length][pent[0][0].length];
      for(int i = 0; i < pent[0].length; i++) {
        for(int j = 0; j < pent.length; j++) {
          for(int k = 0; k < pent[0][0].length; k++) {
            rotatedPent[i][j][k] = pent[j][i][k];
          }
        }
      }
      return rotatedPent;
    } else if(rotation == 3) {
      char[][][] rotatedPent = new char[pent[0].length][pent[0][0].length][pent.length];
      for(int i = 0; i < pent[0].length; i++) {
        for(int j = 0; j < pent[0][0].length; j++) {
          for(int k = 0; k < pent.length; k++) {
            rotatedPent[i][j][k] = pent[j][k][i];
          }
        }
      }
      return rotatedPent;
    } else if(rotation == 4) {
      char[][][] rotatedPent = new char[pent[0][0].length][pent.length][pent[0].length];
      for(int i = 0; i < pent[0][0].length; i++) {
        for(int j = 0; j < pent.length; j++) {
          for(int k = 0; k < pent[0].length; k++) {
            rotatedPent[i][j][k] = pent[k][i][j];
          }
        }
      }
      return rotatedPent;
    } else if(rotation == 5) {
      char[][][] rotatedPent = new char[pent[0][0].length][pent[0].length][pent.length];
      for(int i = 0; i < pent[0][0].length; i++) {
        for(int j = 0; j < pent[0].length; j++) {
          for(int k = 0; k < pent.length; k++) {
            rotatedPent[i][j][k] = pent[k][j][i];
          }
        }
      }
      return rotatedPent;
    }
    return null;
  }

}

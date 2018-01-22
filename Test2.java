public class Test2 {

    public void toNumber(Pentomino[] pent){
        for(int i = 0; i < pent.length; i++){
            for(int j = 0; j < pent[0].length; j++){
                for(int k = 0; k < pent[0][0].length; k++){
                    if(pent[i][j][k] = pent.getName()){
                        pent[i][j][k] = 1;
                    }
                    else{
                        pent[i][j][k] = 0;
                    }
                }
            }
        }
    }

    public void rotate(){

    }

    public void toChar(){
        for(int i = 0; i < pent.length; i++){
            for(int j = 0; j < pent[0].length; j++){
                for(int k = 0; k < pent[0][0].length; k++){
                    if(pent[i][j][k] = 1){
                        pent[i][j][k] = pent.getName();
                    }
                    else{
                        pent[i][j][k] = '0';
                    }
                }
            }
        }
    }
}

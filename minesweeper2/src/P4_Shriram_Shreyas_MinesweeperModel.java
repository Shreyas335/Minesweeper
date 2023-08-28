import java.util.ArrayList;

public class P4_Shriram_Shreyas_MinesweeperModel implements P4_Shriram_Shreyas_MSModelInterface{
    private ArrayList<ArrayList<P4_Shriram_Shreyas_Tiles>> arr = new ArrayList<ArrayList<P4_Shriram_Shreyas_Tiles>>();
    public P4_Shriram_Shreyas_MinesweeperModel(ArrayList<ArrayList<P4_Shriram_Shreyas_Tiles>> arr){
        this.arr = arr;

    }

    @Override
    public int getNumRows() {
        return arr.get(0).size();
    }

    @Override
    public int getNumCols() {
        return arr.size();
    }

    @Override
    public int getNumAdjBombs(int row, int col) {
        int numBombs = 0;
        if(col != 0) {
            for (int i = 0; i < 3; i++) {
                if(!(i == 0 && row == 0) && !(i == 2 && row == arr.size() - 1)) {
                    if (arr.get(row - 1 + i).get(col - 1).getState() % 2 == 0) {
                        numBombs++;
                    }
                }
            }
        }
        if(col != arr.get(0).size() - 1) {
            for (int i = 0; i < 3; i++) {
                if(!(i == 0 && row == 0) && !(i == 2 && row == arr.size() - 1)) {
                    if (arr.get(row - 1 + i).get(col + 1).getState() % 2 == 0) {
                        numBombs++;
                    }
                }
            }
        }
        if(row != 0) {
            if (arr.get(row - 1).get(col).getState() % 2 == 0) {
                numBombs++;
            }
        }
        if(row != arr.size() - 1) {
            if (arr.get(row + 1).get(col).getState() % 2 == 0) {
                numBombs++;
            }
        }
        return numBombs;

    }

    @Override
    public boolean hasLost() {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(0).size(); j++) {
                if(arr.get(i).get(j).getState() == 0 && arr.get(i).get(j).isRevealed()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isMine(int row, int col) {
        if(arr.get(row).get(col).getState() == 0){return true;}
        return false;
    }

    @Override
    public boolean isFlag(int row, int col) {
        if(arr.get(row).get(col).getState() == 2 || arr.get(row).get(col).getState() == 3 ){return true;}
        return false;
    }

    @Override
    public boolean isQuestion(int row, int col) {
        if(arr.get(row).get(col).getState() == 4 || arr.get(row).get(col).getState() == 5){return true;}
        return false;
    }

    @Override
    public boolean win() {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(0).size(); j++) {
                if(arr.get(i).get(j).getState() == 1 && !arr.get(i).get(j).isRevealed()){return false;}
            }
        }
        return true;
    }

    @Override
    public void setFlag(int row, int col) {
        arr.get(row).get(col).flag();

    }
    public void setRegular(int row, int col) {
        arr.get(row).get(col).regular();

    }

    @Override
    public void setQuestion(int row, int col) {
        arr.get(row).get(col).question();
    }

    @Override
    public boolean isRevealed(int row, int col) {
        return arr.get(row).get(col).isRevealed();
    }

    @Override
    public void revealCell(int row, int col) {
        arr.get(row).get(col).reveal();
        if(arr.get(row).get(col).getState() == 2 || arr.get(row).get(col).getState() == 4 ){
            arr.get(row).get(col).setState(0);
        }
        if(arr.get(row).get(col).getState() == 3 || arr.get(row).get(col).getState() == 5 ){
            arr.get(row).get(col).setState(1);
        }
    }

    @Override
    public int getTotalBombs() {
        int bombs = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(0).size(); j++) {
                if(arr.get(i).get(j).getState() == 0 || arr.get(i).get(j).getState() == 2 ||arr.get(i).get(j).getState() == 4 ){
                    bombs++;
                }
            }
        }
        return bombs;
    }

    @Override
    public void resetGame(int rows, int cols, int mines) {

    }

    @Override
    public void clearMarks(int row, int col) {
        arr.get(row).get(col).regular();
    }

    @Override
    public int getTotalFlags() {
        int flags = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(0).size(); j++) {
                if(arr.get(i).get(j).getState() == 2 || arr.get(i).get(j).getState() == 3  ){
                    flags++;
                }
            }
        }
        return flags;
    }
    public P4_Shriram_Shreyas_Tiles getValueAt(int row, int col){
        return arr.get(row).get(col);
    }


    public void copy(ArrayList<ArrayList<P4_Shriram_Shreyas_Tiles>> arr) {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(0).size(); j++) {
                this.arr.get(i).set(j, arr.get(i).get(j));
            }
        }
    }

    //0 = bomb; 1 = notbomb; 2 = flagged bomb;3 = flagged not bomb;4 = question bomb;5 = question not bomb
    public String printTile(int row, int col){
        if(arr.get(row).get(col).isRevealed()){
            if(arr.get(row).get(col).getState() == 1){
                return "" + getNumAdjBombs(row, col);
            }
        }else{
            if(arr.get(row).get(col).getState() == 1 || arr.get(row).get(col).getState() == 0){
                return "-";
            }else if(arr.get(row).get(col).getState() == 2 || arr.get(row).get(col).getState() == 3){
                return "P";
            }else{
                return "?";
            }
        }
        return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    }
    public void recursiveReveal(int row, int col){
        if(row >= 0 && row < arr.size() && col >= 0 && col < arr.get(0).size()) {
                if (getNumAdjBombs(row, col) == 0 && !arr.get(row).get(col).isRevealed()) {
                    arr.get(row).get(col).reveal();
                    P4_Shriram_Shreyas_MinesweeperController.update(row, col);

                    recursiveReveal(row - 1, col - 1);//fl
                    recursiveReveal(row, col - 1);//l
                    recursiveReveal(row + 1, col - 1);//bl
                    recursiveReveal(row - 1, col);//u
                    recursiveReveal(row + 1, col);//d
                    recursiveReveal(row - 1, col + 1);
                    recursiveReveal(row, col + 1);
                    recursiveReveal(row + 1, col + 1);
                }else{
                    arr.get(row).get(col).reveal();
                    P4_Shriram_Shreyas_MinesweeperController.update(row, col);
                }


        }

    }
    public int getType(int row, int col){
        if(!arr.get(row).get(col).isRevealed()){
            if(arr.get(row).get(col).getState() == 2 || arr.get(row).get(col).getState() == 3){
                return 10;
            }else {
                return 0;
            }
        }else{
            if(arr.get(row).get(col).getState() == 1){
                return getNumAdjBombs(row, col) + 1;
            }else{
                return 11;
            }

        }
    }

    //0 = bomb; 1 = notbomb; 2 = flagged bomb;3 = flagged not bomb;4 = question bomb;5 = question not bomb
    public String printTileDebug(int row, int col){

        if(arr.get(row).get(col).getState() == 1){
            return "" + getNumAdjBombs(row, col);
        }else if(arr.get(row).get(col).getState() == 0){
            return "B";
        }else if(arr.get(row).get(col).getState() == 2){
            return "P";
        }else if(arr.get(row).get(col).getState() == 3){
            return "p";
        }

        return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    }


}

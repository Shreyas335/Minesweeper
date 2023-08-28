public class P4_Shriram_Shreyas_Tiles {
    //0 = bomb; 1 = notbomb; 2 = flagged bomb;3 = flagged not bomb;4 = question bomb;5 = question not bomb
    private int state;

    private boolean isRevealed;
    public P4_Shriram_Shreyas_Tiles() {
        state = 1;
        isRevealed = false;
    }
    public void makeBomb(){
        state = 0;
        isRevealed = false;
    }
    public int getState(){
        return state;
    }
    public boolean isRevealed(){return isRevealed;}

    public void setState(int a){state = a;}
    public void reveal(){
        isRevealed = true;
        if(state == 2 || state == 4){state = 0;}
        if(state == 3 || state == 5){state = 1;}
    }

    public void flag(){
        if(state == 0 || state == 4){
            state = 2;
        }
        if(state == 1 || state == 5){
            state = 3;
        }
    }
    public void question(){
        if(state == 0 || state == 2){
            state = 4;
        }
        if(state == 1 || state == 3){
            state = 5;
        }
    }
    public void regular(){
        if(state == 2 || state == 4){
            state = 0;
        }
        if(state == 3 || state == 5){
            state = 1;
        }
    }

}
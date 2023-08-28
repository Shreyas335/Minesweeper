import java.util.ArrayList;

/*
Shreyas Shriram Gosakan
Time: 20 mins
 */
public interface P4_Shriram_Shreyas_MSModelInterface {
    int getNumRows();
    int getNumCols();
    int getNumAdjBombs(int row, int col);
    boolean hasLost(); // hasLost()
    boolean isMine(int row, int col);
    boolean isFlag(int row, int col);
    boolean isQuestion(int row, int col);
    boolean win(); // hasWon()
    void setFlag(int row, int col);
    void setQuestion(int row, int col);
    boolean isRevealed(int row, int col);
    void revealCell(int row, int col);
    int getTotalBombs();
    void resetGame(int rows, int cols, int mines);
    void clearMarks(int row, int col);
    int getTotalFlags();

}

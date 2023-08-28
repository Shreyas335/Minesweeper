import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
/*
Shreyas Shriram Gosakan
Per: 4
Time: 5 hours
 */
public class P4_Shriram_Shreyas_MinesweeperController extends Application {
    private static Image num0 = new Image("num_0.gif");
    private static Image num1 = new Image("num_1.gif");
    private static Image num2 = new Image("num_2.gif");
    private static Image num3 = new Image("num_3.gif");
    private static Image num4 = new Image("num_4.gif");
    private static Image num5 = new Image("num_5.gif");
    private static Image num6 = new Image("num_6.gif");
    private static Image num7 = new Image("num_7.gif");
    private static Image num8 = new Image("num_8.gif");
    private static Image blank = new Image("blank.gif");
    private Image bomb_death = new Image("bomb_death.gif");
    private static Image bomb_flagged = new Image("bomb_flagged.gif");
    private static Image bomb_revealed = new Image("bomb_revealed.gif");
    private Image bomb_wrong = new Image("bomb_wrong.gif");
    static BorderPane root;
    MenuBar menubar;
    Menu file, options, help;
    MenuItem newGame, exit, mineCount, howToPlay, about;
    static GridPane board;
    Label time, mines;
    int row = 8;
    int col = 8;
    int numMines = 10;
    static P4_Shriram_Shreyas_MinesweeperModel model;
    static AnimationTimer timer;
    static boolean run = true;
    boolean firstClick = true;
    Label win = new Label("You Win! :)");
    static Label lose = new Label("You lose :(");
    int bombCount;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        root = new BorderPane();
        newGame(10, 8);

        mines = new Label("Bombs remaining: " + (bombCount - model.getTotalFlags()));
        mines.setFont(new Font(25));
        Label lab = new Label("hello");
        root.setCenter(board);
        root.setLeft(time);
        root.setRight(mines);
        //root.setTop(menubar);

        Slider s = new Slider();
        s.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                newGame((int)(s.getValue()), 8);
            }
        });
        newGame = new MenuItem("New Game");

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                firstClick = true;
                run = true;
                newGame(10, 8);
            }
        });

        Scene scene = new Scene(root, 1200, 700);
        exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        mineCount = new MenuItem("number of Mines: ", s);


        file = new Menu("file");
        file.getItems().addAll(newGame, exit, mineCount);
        menubar = new MenuBar();
        menubar.getMenus().add(file);
        //scene
        root.setTop(menubar);


        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();


    }

    public static void update(int row, int col){
        getImageView(row, col).setImage(getNewImage(row, col));
    }

    public static ImageView getImageView(int row, int column) {
        ImageView ret = null;
        for (Node node : board.getChildren()) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                ret = (ImageView)(node);
                return ret;
            }
        }

        return ret;
    }

    public static Image getNewImage(int row, int col){
        switch(getType(row, col)){
            case 0:
                return blank;
            case 1:
                return num0;
            case 2:

                return num1;

            case 3:
                return num2;
            case 4:
                return num3;

            case 5:
                return num4;
            case 6:
                return num5;
            case 7:
                return num6;
            case 8:
                return num7;
            case 9:
                return num8;
            case 10:
                return bomb_flagged;
            case 11:
                run = false;
                timer.stop();
                loseMessage();
                return bomb_revealed;
        }
        return blank;
    }
    public static int getType(int row, int col){
        return model.getType(row, col);
    }

    public void newGame(int NMines, int size){
        bombCount = NMines;
        this.row = size;
        this.col = size;
        this.numMines = NMines;
        ArrayList<ArrayList<P4_Shriram_Shreyas_Tiles>> temp = new ArrayList<ArrayList<P4_Shriram_Shreyas_Tiles>>();
        for(int i = 0; i < size; i++){
            temp.add(new ArrayList<P4_Shriram_Shreyas_Tiles>());
            for(int j = 0; j < size; j++){
                temp.get(i).add(new P4_Shriram_Shreyas_Tiles());
            }
        }

        for(int i = 0; i < numMines; i++){
            int xPos = (int)(Math.random() * size);
            int yPos = (int)(Math.random() * size);
            if(temp.get(yPos).get(xPos).getState() == 0){
                i--;
            }
            temp.get(yPos).get(xPos).makeBomb();

        }


        model = new P4_Shriram_Shreyas_MinesweeperModel(temp);
        time = new Label("Time Taken: 0.00s");
        time.setFont(new Font(25));
        board = new GridPane();
        //board.setPadding(new Insets(10, 10, 10, 10));
        board.setAlignment(Pos.CENTER);


        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
                ImageView a = new ImageView(blank);
                board.add(a, i, j);
                a.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (run) {
                            if (firstClick) {
                                long startTime = System.currentTimeMillis();
                                firstClick = false;
                                timer = new AnimationTimer() {
                                    @Override
                                    public void handle(long l) {
                                        time.setText("Time Taken : " + ((System.currentTimeMillis() - startTime) / 1000) + "s");
                                    }
                                };
                                timer.start();

                            }
                            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                                model.recursiveReveal(GridPane.getRowIndex(a), GridPane.getColumnIndex(a));
                                if (model.win()) {
                                    winMessage();
                                    run = false;
                                    timer.stop();
                                }
                            } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                                if(!model.isFlag(GridPane.getRowIndex(a), GridPane.getColumnIndex(a))) {
                                    model.setFlag(GridPane.getRowIndex(a), GridPane.getColumnIndex(a));
                                }else{
                                    model.setRegular(GridPane.getRowIndex(a), GridPane.getColumnIndex(a));

                                }
                                update(GridPane.getRowIndex(a), GridPane.getColumnIndex(a));
                                mines.setText("Bombs remaining: " + (NMines - model.getTotalFlags()));
                            }
                        }
                    }
                });
            }
        }
        root.setCenter(board);
    }

    public void winMessage(){

        if(win.getText().equals("You Win! :)")){
            win.setText("");
        }else{
            win.setText("You Win! :)");
        }
        win.setFont(new Font(30));
        BorderPane temp = new BorderPane();
        temp.setCenter(win);
        root.setBottom(temp);
        //root.setBottom(win);
    }

    public static void loseMessage(){
        if(lose.getText().equals("You Lose :(")){
            lose.setText("");
        }else{
            lose.setText("You Lose :(");
        }
        lose.setFont(new Font(30));
        BorderPane temp = new BorderPane();
        temp.setCenter(lose);
        root.setBottom(temp);
        //root.setBottom(win);
    }
}

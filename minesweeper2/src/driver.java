import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class driver extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Image im = new Image("blank.gif");
        ImageView image = new ImageView(im);
        Group root = new Group();
        GridPane grid = new GridPane();
        grid.add(image, 0, 0);
        grid.add(new ImageView(im), 1, 1);
        Scene scene = new Scene(grid);
        //root.getChildren().add(image);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}

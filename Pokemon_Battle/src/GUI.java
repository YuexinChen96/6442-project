
import Pages.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;



//Set up basic GUI
public class GUI extends Application {
    // Dimensions of the display window
    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 760;



    // Page indicator
    private int page_number = 0;


    private final Group root = new Group();
    private final Pane board = new Pane();
    private final Group controls = new Group();



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pokemon Battle");
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);


        page_initial(page_number);


        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public void page_initial(int page_number){
        if (page_number == 0){
            Page0 page0 = new Page0();
            root.getChildren().add(board);
            root.getChildren().add(controls);
            page0_initial();
        } else if (page_number == 1){
            Page1 page = new Page1();
        }
    }

    public void page0_initial(){
        ImageView background = new ImageView();
        final String PAGE0_BACKGROUND_URI = getClass().getResource("Pics/page1_background_example.jpg").toString();
        background.setImage(new Image(PAGE0_BACKGROUND_URI));
        background.setFitHeight(WINDOW_HEIGHT);
        background.setPreserveRatio(true);
        background.setLayoutX(0);
        background.setLayoutY(0);
        background.toBack();

        Text introduction = new Text("Brief introduction");
        introduction.setFill(Color.BLACK);
        introduction.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        introduction.setLayoutY(30 + 50);
        introduction.setLayoutY(30 + 760 + 30);
        board.getChildren().add(introduction);

        Text instructionsText = new Text("balabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabala" +
                "balabalabalabalabalabalabalabalabalabalabala");
        instructionsText.setFill(Color.BLACK);
        instructionsText.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        instructionsText.setLayoutY(180 + 50);
        instructionsText.setLayoutY(180 + 180 +55);
        instructionsText.setWrappingWidth(600);
        board.getChildren().add(instructionsText);


    }
}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("say hello world");

        btn.setOnAction((e) -> {
            System.out.println("hello world。。。");
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        //场景
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);

        primaryStage.show();

    }
}

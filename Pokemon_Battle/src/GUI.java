import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicReference;


//Set up basic GUI
public class GUI extends Application {
    // Dimensions of the display window
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 740;

    char[][] map = new char[100][100];
    char[][] showmap=new char[40][24];//char record the type of piece

    // Page indicator
    private int page_number = 0; // change for page testing


    private final Group root = new Group();
    private final Pane board = new Pane();
    private final Group controls = new Group();

    Pokemon user = new Pokemon();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pokemon.Pokemon Pokemon.Battle");
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        root.getChildren().add(board);
        root.getChildren().add(controls);


        page0_initial();


        primaryStage.setScene(scene);

        primaryStage.show();

    }

//-------------------------------------------------------------
//                 Page0_initial (Game Home)
// ------------------------------------------------------------
    // Chloe
    public void page0_initial(){
        ImageView background = new ImageView();
        final String PAGE0_BACKGROUND_URI = getClass().getResource("Pics/page1_background_example.jpg").toString();
        background.setImage(new Image(PAGE0_BACKGROUND_URI));
        background.setFitHeight(600);
        background.setPreserveRatio(true);
        board.getChildren().add(background);
        board.setLayoutX(0);
        board.setLayoutY(0);
        board.toBack();

        Text introduction = new Text("Brief introduction");
        introduction.setFill(Color.BLACK);
        introduction.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        introduction.setLayoutY(30 + 50);
        introduction.setLayoutY(30 + 30);
        board.getChildren().add(introduction);

        Text instructionsText = new Text("balabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabalabala" +
                "balabalabalabalabalabalabalabalabalabalabala");
        instructionsText.setFill(Color.BLACK);
        instructionsText.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        instructionsText.setLayoutY(180 + 50);
        instructionsText.setLayoutY(180 + 180 +55);
        instructionsText.setWrappingWidth(600);
        board.getChildren().add(instructionsText);

        Button btn1 = new Button("Start");
        btn1.setLayoutX(200);
        btn1.setLayoutY(200);
        btn1.setOnAction(e -> {
            board.getChildren().removeAll(board.getChildren());
            page1_initial();
        });
        board.getChildren().add(btn1);

    }

//-------------------------------------------------------------
//                 Page1_initial (Role selection)
// ------------------------------------------------------------
    // Chloe
    public void page1_initial(){
        System.out.println("Pokemon.Pokemon Select");
        ImageView background = new ImageView();
        final String PAGE0_BACKGROUND_URI = getClass().getResource("Pics/page1_background_example.jpg").toString();
        background.setImage(new Image(PAGE0_BACKGROUND_URI));
        background.setFitHeight(600);
        background.setPreserveRatio(true);
        board.getChildren().add(background);
        board.setLayoutX(0);
        board.setLayoutY(0);
        board.toBack();

        int n = 3;
        int l = 4;
        int r = 5;

        Rectangle i = new Rectangle(600, 600);
        i.setFill(Color.TRANSPARENT);
        i.setLayoutX(-100);
        i.setLayoutY(-100);

        Image img = new Image("Pics/pic0.jpg");
        Rectangle rect = new Rectangle(300, 300, 200, 200);
        rect.setFill(new ImagePattern(img));
        Path path = new Path();
        path.getElements().add(new MoveTo(300, 300));
        path.getElements().add(new LineTo(700, 300));
        path.getElements().add(new LineTo(700, 700));
        path.getElements().add(new LineTo(300, 700));
        path.getElements().add(new LineTo(300,300));

        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(5000));
        pt.setNode(rect);
        pt.setPath(path);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setAutoReverse(true);

        Button play = new Button("Play");
        play.setLayoutX(200);
        play.setLayoutY(200);
        play.setOnAction(e->pt.play());

        board.getChildren().add(i);
        board.getChildren().add(rect);
        board.getChildren().add(path);
        board.getChildren().add(play);

        // ------待完善-----
        int id=0;  // 改成：int id=选择的角色（page1）
        this.user.initialroleAttr(id);

        Button btn1 = new Button("Start");
        btn1.setLayoutX(1000);
        btn1.setLayoutY(500);
        btn1.setOnAction(e -> {
            board.getChildren().removeAll(board.getChildren());
            page2_initial();
        });
        board.getChildren().add(btn1);



    }



//-------------------------------------------------------------
//                 Page2_initial (Game Map)
// ------------------------------------------------------------
// Kath & Natalie
Map mapclass;
    public void page2_initial() {
        System.out.println("Map");
        System.out.println("Current Role:"+user.toString());

        //create and show the map
        initialMap();
        showPartOfMap(map);

        //showRole;
        int[] role_pos=user.getPosition();
        String role_image=user.getImage();
        Rectangle rect = new Rectangle(role_pos[0]* 30, role_pos[1] * 30, 30, 30);
        rect.setFill(new ImagePattern(new Image(role_image)));
        board.getChildren().add(rect);
        rect.toFront();
        addKeyPressed(rect, board);

    }
    public void addKeyPressed(Node node, Node board) {
        board.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            node.requestFocus();
            node.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            });
        });
        node.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            System.out.println("keyevent able");
            mapclass = new Map();
            if(mapclass.ifTerminal(user,map))page4_initial();
            /*
            if(mapclass.ifBattle(user,map)){
                page3_initial(0);
            }*/
            KeyCode keyCode = e.getCode();
            if (keyCode.equals(KeyCode.RIGHT)) {
                boolean canMove = mapclass.checkMoveEnable(user,'R',map);//check move or terminal or battle
                if(canMove) {
                    user.setPosition(new int[]{user.getPosition()[0] + 1, user.getPosition()[1]});
                    node.setLayoutX(node.getLayoutX() + 30);
                }
                System.out.println(user);
                if(mapclass.ifTerminal(user,map))page4_initial();
            } else if (keyCode.equals(KeyCode.LEFT)) {
                System.out.println("left");
                boolean canMove = mapclass.checkMoveEnable(user,'L',map);//check move or terminal or battle
                System.out.println(canMove);
                if(canMove) {
                    user.setPosition(new int[]{user.getPosition()[0] - 1, user.getPosition()[1]});
                    node.setLayoutX(node.getLayoutX() - 30);
                }
                System.out.println(user);
                if(mapclass.ifTerminal(user,map))page4_initial();
            } else if (keyCode.equals(KeyCode.UP)) {
                System.out.println("up");
                boolean canMove = mapclass.checkMoveEnable(user,'U',map);//check move or terminal or battle
                System.out.println(canMove);
                if(canMove) {
                    user.setPosition(new int[]{user.getPosition()[0], user.getPosition()[1] - 1});
                    node.setLayoutY(node.getLayoutY() - 30);
                }
                System.out.println(user);
                if(mapclass.ifTerminal(user,map))page4_initial();
            } else if (keyCode.equals(KeyCode.DOWN)) {
                System.out.println("down");
                boolean canMove = mapclass.checkMoveEnable(user,'D',map);//check move or terminal or battle
                System.out.println(canMove);
                if(canMove) {
                    user.setPosition(new int[]{user.getPosition()[0], user.getPosition()[1] + 1});
                    node.setLayoutY(node.getLayoutY() + 30);
                }
                System.out.println(user);
                if(mapclass.ifTerminal(user,map))page4_initial();
            }


        });
    }

    // fx: add pieces to board (board只能显示map中的40*24个pieces)
    //-----------------暂时不需要（Note:不用更新地图, 目前只实现40*24）-------------------------
    public void showPartOfMap(char[][] showmap){
        for (int i = 0; i < 40; i++){
            for (int j = 0; j < 24; j++){
                Rectangle rect = new Rectangle(i * 30, j * 30, 30, 30);
                rect.setFill(new ImagePattern(new Image("Pics/Maps/" + showmap[i][j] + ".png")));
                board.getChildren().add(rect);
            }
        }
        //记录当前的board中所有piece的type和position
    }


    // ----待设计
    // map[][]里放每个图片名char型  s石头 g草 w水,  终点t
    // Kath
    public void initialMap(){
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                if (i == 10){
                    map[i][j] = 's';
                }
                else map[i][j] = 'g'; // grass
            }
        }
        map[10][5]='g';
        map[39][23]='t';
    }





//-------------------------------------------------------------
//                 Page3_initial (Battle)
// ------------------------------------------------------------
    // Kevin
    public void page3_initial(int enemy_id) {
        System.out.println("Battle page");


    }




//-------------------------------------------------------------
//                 Page4_initial (Game end page)
// ------------------------------------------------------------
    public void page4_initial(){
        board.getChildren().removeAll(board.getChildren());

        Text endingText = new Text("Game over");
        endingText.setFill(Color.BLACK);
        endingText.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
        endingText.setLayoutX(500);
        endingText.setLayoutY(300);
        board.getChildren().add(endingText);
    }
}

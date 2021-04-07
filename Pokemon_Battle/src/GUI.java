import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


//Set up basic GUI
public class GUI extends Application {
    // Dimensions of the display window
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 740;

    char[][] map = new char[80][48];
    char[][] showmap = new char[40][24];//char record the type of piece

    // Page indicator
    private int page_number = 0; // change for page testing


    private final Group root = new Group();
    private final Pane board = new Pane();
    private final Group controls = new Group();

    Pokemon user;
    private static final DropShadow dropShadow;
    private static final DropShadow borderGlow;

    static {
        dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.color(0, 0, 0, .4));
    }

    // Static initializer to initialize borderGlow
    static {
        borderGlow = new DropShadow();
        borderGlow.setColor(Color.RED);
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pokemon.Pokemon Pokemon.Battle");
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        root.getChildren().add(board);
        root.getChildren().add(controls);


//        page3_initial(3);//change for test
        page1_initial();

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    //-------------------------------------------------------------
//                 Page0_initial (Game Home)
// ------------------------------------------------------------
    // Chloe
    public void page0_initial() {
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
        instructionsText.setLayoutY(180 + 180 + 55);
        instructionsText.setWrappingWidth(600);
        board.getChildren().add(instructionsText);

        Button btn1 = new Button("Start");
        btn1.setLayoutX(200);
        btn1.setLayoutY(200);
        btn1.setMinSize(10, 10);
        btn1.setOnAction(e -> {
            board.getChildren().removeAll(board.getChildren());
            page1_initial();
        });
        board.getChildren().add(btn1);

        Button btn2 = new Button("Play");
        btn2.setLayoutX(200);
        btn2.setLayoutY(200);
        btn2.setMinSize(10, 10);
        btn2.setOnAction(e -> {
            board.getChildren().removeAll(board.getChildren());
            page1_initial();
        });
        board.getChildren().add(btn2);

        Button btn3 = new Button("Role");
        btn3.setLayoutX(200);
        btn3.setLayoutY(200);
        btn3.setMinSize(10, 10);
        btn3.setOnAction(e -> {
            board.getChildren().removeAll(board.getChildren());
            page2_initial();
        });
        board.getChildren().add(btn3);

    }

    //-------------------------------------------------------------
//                 Page1_initial (Role selection)
// ------------------------------------------------------------
    // Chloe
    public void page1_initial() {
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

        Image img = new Image("Pics/Pokemon/pic0.jpg");
        Rectangle rect = new Rectangle(300, 300, 200, 200);
        rect.setFill(new ImagePattern(img));
        Path path = new Path();
        path.getElements().add(new MoveTo(300, 300));
        path.getElements().add(new LineTo(700, 300));
        path.getElements().add(new LineTo(700, 700));
        path.getElements().add(new LineTo(300, 700));
        path.getElements().add(new LineTo(300, 300));

        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(5000));
        pt.setNode(rect);
        pt.setPath(path);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setAutoReverse(true);

        Button play = new Button("Play");
        play.setLayoutX(200);
        play.setLayoutY(200);
        play.setOnAction(e -> pt.play());

        board.getChildren().add(i);
        board.getChildren().add(rect);
        board.getChildren().add(path);
        board.getChildren().add(play);

        // ------待完善-----
        int id = 0;  // 改成：int id=选择的角色（page1）
        user = pokemonLoadFromJson(id);

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
        System.out.println("Current Role:" + user.toString());

        //create and show the map
        initialMap();
        showPartOfMap(map);

        //showRole;
        int[] role_pos = user.getPosition();
        Rectangle rect = new Rectangle(role_pos[0] * 30, role_pos[1] * 30, 30, 30);
        System.out.println(System.getProperty("user.dir") + "/src/Pics/Pokemon/pic0.jpg");
//        rect.setFill(new ImagePattern(new Image(user.getImgUrl())));
//        rect.setFill(new ImagePattern(new Image(System.getProperty("user.dir") + "/src/Pics/Pokemon/pic0.jpg")));
        rect.setFill(new ImagePattern(new Image("Pics/Pokemon/pic0.jpg")));
        board.getChildren().add(rect);
        rect.toFront();
        startShowAnimation(rect);
        addKeyPressed(rect, board);

    }

    public void addKeyPressed(Node node, Node board) {
        board.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            node.requestFocus();
            node.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            });
        });
        board.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            node.requestFocus();
            node.setEffect(null);
        });
        node.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            System.out.println("keyEvent able");
            mapclass = new Map();
            if (mapclass.ifTerminal(user, map)) page4_initial();
            if (mapclass.ifBattle(user, map)) page3_initial(3);
            KeyCode keyCode = e.getCode();
            if (keyCode.equals(KeyCode.RIGHT)) {
                boolean canMove = mapclass.checkMoveEnable(user, 'R', map);
                if (canMove) {
                    int x = user.getPosition()[0];
                    int y = user.getPosition()[1];
                    moveAnimation(node, x * 30, y * 30, (x + 1) * 30, y * 30);
                    user.setPosition(new int[]{x + 1, y});
                }
            } else if (keyCode.equals(KeyCode.LEFT)) {
                System.out.println("left");
                boolean canMove = mapclass.checkMoveEnable(user, 'L', map);
                System.out.println(canMove);
                if (canMove) {
                    int x = user.getPosition()[0];
                    int y = user.getPosition()[1];
                    moveAnimation(node, x * 30, y * 30, (x - 1) * 30, y * 30);
                    user.setPosition(new int[]{x - 1, y});
                }
            } else if (keyCode.equals(KeyCode.UP)) {
                System.out.println("up");
                boolean canMove = mapclass.checkMoveEnable(user, 'U', map);
                System.out.println(canMove);
                if (canMove) {
                    int x = user.getPosition()[0];
                    int y = user.getPosition()[1];
                    moveAnimation(node, x * 30, y * 30, x * 30, (y - 1) * 30);
                    user.setPosition(new int[]{x, y - 1});
                }
            } else if (keyCode.equals(KeyCode.DOWN)) {
                System.out.println("down");
                boolean canMove = mapclass.checkMoveEnable(user, 'D', map);
                System.out.println(canMove);
                if (canMove) {
                    int x = user.getPosition()[0];
                    int y = user.getPosition()[1];
                    moveAnimation(node, x * 30, y * 30, x * 30, (y + 1) * 30);
                    user.setPosition(new int[]{x, y + 1});
                }
            }
            System.out.println(user);
            if (mapclass.ifTerminal(user, map)) page4_initial();
            if (mapclass.ifBattle(user, map)) page3_initial(3);
        });
    }

    public void startShowAnimation(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(200), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(9);
        ft.setAutoReverse(true);
        ft.play();
    }

    public void moveAnimation(Node node, double now_x, double now_y, double next_x, double next_y) {
        node.setEffect(borderGlow);
        int adjsut = 30;
        Path path = new Path();
        path.getElements().add(new MoveTo(now_x + 0.5 * adjsut, now_y + 0.5 * adjsut));
        path.getElements().add(new LineTo(next_x + 0.5 * adjsut, next_y + 0.5 * adjsut));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1300));
        pathTransition.setPath(path);
        pathTransition.setNode(node);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(true);
        //pathTransition.play();

        FadeTransition ft2 = new FadeTransition(Duration.millis(70), node);
        ft2.setFromValue(1);
        ft2.setToValue(0);
        ft2.setCycleCount(2);
        ft2.setAutoReverse(true);
        FadeTransition ft = new FadeTransition(Duration.millis(70), node);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        //ft.play();
        //Playing Sequential Transition
        SequentialTransition seqTransition = new SequentialTransition(ft2, pathTransition, ft);
        seqTransition.play();
    }

    // fx: add pieces to board (board只能显示map中的40*24个pieces)
    //-----------------暂时不需要（Note:不用更新地图, 目前只实现40*24）-------------------------
    public void showPartOfMap(char[][] showmap) {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 24; j++) {
                Rectangle rect = new Rectangle(i * 30, j * 30, 30, 30);
                if (showmap[i][j] != 'r') {
                    rect.setFill(new ImagePattern(new Image("Pics/Maps/" + showmap[i][j] + ".png")));
                }
                board.getChildren().add(rect);
            }
        }
        //记录当前的board中所有piece的type和position
    }

    // ----待设计
    // map[][]里放每个图片名char型  s石头,g草,w水,h商店(暂不启用),r是可移动的路线,起点b,终点t
    // Kath
    public void initialMap() {
        //地图
        String battleMap = "src/battleMap1.txt";
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(battleMap));
            String l;
            int row = 0;
            while ((l = bfr.readLine()) != null) {
                for (int i = 0; i < l.length(); i++) {
//                    System.out.println("row:" + row + ", col:"+i+", char:"+l.charAt(i));
                    map[i][row] = l.charAt(i);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Pokemon pokemonLoadFromJson(int id) {
        Gson gson = new Gson();
        JsonReader jsonReader = null;
        final Type CUS_LIST_TYPE = new TypeToken<List<Pokemon>>() {
        }.getType();
        try {
            jsonReader = new JsonReader(new FileReader(System.getProperty("user.dir") + "/src/Pokemon.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Pokemon> pl = gson.fromJson(jsonReader, CUS_LIST_TYPE);
        return pl.get(id);
    }


    //-------------------------------------------------------------
//                 Page3_initial (Battle)
// ------------------------------------------------------------
    // Kevin
    public void page3_initial(int enemy_id) {
        // this line use for test
        this.user = pokemonLoadFromJson(0);

        System.out.println("Battle page");
        // Background area
        page3_setupBackground();
        // buttons
        page3_setupButton();
        // enemy load
        Enemy enemy = enemy_loading(enemy_id);
        // start Battle
        Battle battle = new Battle(this.user, enemy);


//        while (!battle.gameover_test()) {
//            if (battle.getTurn() % 2 == 0){
//                // able control for buttons
//            } else {
//
//            }
//        }


    }

    public void page3_setupBackground() {
        ImageView background = new ImageView();
        final String PAGE3_BACKGROUND_URI = getClass().getResource("Pics/page3_background.png").toString();
        background.setImage(new Image(PAGE3_BACKGROUND_URI));
        background.setFitHeight(600);
        background.setPreserveRatio(true);
        board.getChildren().add(background);
        // User area
        Rectangle user_area = new Rectangle(150, 360, 240, 240);
        user_area.setFill(new ImagePattern(new Image("Pics/Pokemon/pic0.jpg")));
        board.getChildren().add(user_area);
        // Enemy area
        Rectangle enemy_area = new Rectangle(690, 0, 240, 240);
        enemy_area.setFill(new ImagePattern(new Image("Pics/Pokemon/pic1.jpg")));
        board.getChildren().add(enemy_area);

        // control area
        Rectangle control_area = new Rectangle(0, 600, 1080, 300);
        control_area.setFill(new ImagePattern(new Image("Pics/page3_control_area.jpg")));
        board.getChildren().add(control_area);
    }

    public void page3_setupButton() {
        Button btn1 = new Button("Attack");
        btn1.setLayoutX(60);
        btn1.setLayoutY(640);
        btn1.setMinSize(130, 60);
        btn1.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        board.getChildren().add(btn1);

        Button btn2 = new Button("Spell1");
        btn2.setLayoutX(310);
        btn2.setLayoutY(640);
        btn2.setMinSize(130, 60);
        btn2.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        board.getChildren().add(btn2);

        Button btn3 = new Button("Spell2");
        btn3.setLayoutX(560);
        btn3.setLayoutY(640);
        btn3.setMinSize(130, 60);
        btn3.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        board.getChildren().add(btn3);

        Button btn4 = new Button("Spell3");
        btn4.setLayoutX(810);
        btn4.setLayoutY(640);
        btn4.setMinSize(130, 60);
        btn4.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        board.getChildren().add(btn4);
    }

    public Enemy enemy_loading(int enemy_id) {
        Gson gson = new Gson();
        JsonReader jsr = null;
        try {
            jsr = new JsonReader(new FileReader(System.getProperty("user.dir") + "/src/Enemy.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Type CUS_LIST_TYPE = new TypeToken<List<Enemy>>() {
        }.getType();
        assert jsr != null;
        List<Enemy> enemyList = gson.fromJson(jsr, CUS_LIST_TYPE);
        return enemyList.get(enemy_id - 1);
    }


    //-------------------------------------------------------------
//                 Page4_initial (Game end page)
// ------------------------------------------------------------
    public void page4_initial() {
        board.getChildren().removeAll(board.getChildren());

        Text endingText = new Text("Game over");
        endingText.setFill(Color.BLACK);
        endingText.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
        endingText.setLayoutX(500);
        endingText.setLayoutY(300);
        board.getChildren().add(endingText);
    }
}

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.property.*;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
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
    private static final int mapLength = 80;
    private static final int mapHeight = 48;
    char[][] map = new char[mapLength][mapHeight];
    char[][] showMap0 = new char[mapLength / 2][mapHeight / 2];
    char[][] showMap1 = new char[mapLength / 2][mapHeight / 2];
    char[][] showMap2 = new char[mapLength / 2][mapHeight / 2];
    char[][] showMap3 = new char[mapLength / 2][mapHeight / 2];
    int currentMapIndex = -1;

    // Page indicator
    //private int page_number = 0; // change for page testing

    private final Group root = new Group();
    private final Pane board = new Pane();
    private final Group controls = new Group();

    Pokemon user;
    private static final DropShadow dropShadow;
    private static final DropShadow borderGlow;

    // UI and backend connect value
    private StringProperty textInfo;
    private StringProperty user_HP_info = new SimpleStringProperty();
    private StringProperty user_MP_info = new SimpleStringProperty();
    private StringProperty user_AD_info = new SimpleStringProperty();
    private StringProperty enemy_HP_info = new SimpleStringProperty();
    private StringProperty enemy_MP_info = new SimpleStringProperty();
    private StringProperty enemy_AD_info = new SimpleStringProperty();
    private StringProperty user_LEVEL = new SimpleStringProperty();

    private DoubleProperty user_HP_bar = new SimpleDoubleProperty();
    private DoubleProperty user_MP_bar = new SimpleDoubleProperty();
    private DoubleProperty enemy_HP_bar = new SimpleDoubleProperty();
    private DoubleProperty enemy_MP_bar = new SimpleDoubleProperty();

    private StringProperty number_HP_poison = new SimpleStringProperty("2");//the number of red bottles you picked up
    private StringProperty number_MP_poison = new SimpleStringProperty("2");//the number of blue bottles you picked up

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

        initialAllMap();
        initialShowMap();

        //page3_initial(3);//change for test
        page0_initial();

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    //-------------------------------------------------------------
//                 Page0_initial (Game Home)
// ------------------------------------------------------------
    // Chloe
    public void page0_initial() {
        ImageView background = new ImageView();
        final String PAGE0_BACKGROUND_URI = getClass().getResource("Pics/bg-unsplash.jpg").toString();
        background.setImage(new Image(PAGE0_BACKGROUND_URI));
        background.setFitHeight(780);
        background.setPreserveRatio(true);
        board.getChildren().add(background);
        board.setLayoutX(0);
        board.setLayoutY(0);
        board.toBack();

        Text introduction = new Text("BRIEF INTRODUCTION");
        introduction.setFill(Color.rgb(9, 97, 228));
        introduction.setFont(Font.font("Avenir Next", FontWeight.BOLD, 24));
        introduction.setLayoutX(80);
        introduction.setLayoutY(100);
        board.getChildren().add(introduction);

        Text instructionsText = new Text("Once upon a time, on a small island of Far East, lives a tribe of elves. " +
                "The lived happily together. However, one day the evil elves of another tribe want to occupy this quiet and peaceful island, thus the war begins...");
        instructionsText.setFill(Color.WHITE);
        instructionsText.setFont(Font.font("Avenir Next", FontWeight.LIGHT, 18));
        instructionsText.setLayoutX(80);
        instructionsText.setLayoutY(450);
        instructionsText.setWrappingWidth(500);
        board.getChildren().add(instructionsText);

        Button btn1 = new Button("Start");
        btn1.setMaxSize(100, 40);
        btn1.setMinSize(100, 40);
        btn1.setLayoutX(1000);
        btn1.setFont(Font.font("Arial", FontWeight.BOLD,20));
        btn1.setLayoutY(400);
        btn1.setOnAction(e -> {
            board.getChildren().removeAll(board.getChildren());
            page1_initial();
        });
        board.getChildren().add(btn1);

    }

    //-------------------------------------------------------------
//                 Page1_initial (Role selection)
// ------------------------------------------------------------
    // Chloe & Natalie
    public void page1_initial() {
        System.out.println("Pokemon.Pokemon Select");
        ImageView background = new ImageView();
        final String PAGE0_BACKGROUND_URI = getClass().getResource("Pics/bg-unsplash.jpg").toString();
        background.setImage(new Image(PAGE0_BACKGROUND_URI));
        background.setFitHeight(780);
        background.setPreserveRatio(true);
        board.getChildren().add(background);
        board.setLayoutX(0);
        board.setLayoutY(0);
        board.toBack();

        Rectangle rect = new Rectangle(10, 300, 150, 150);
        rect.setFill(new ImagePattern(new Image("Pics/Pokemon/user0.png")));
        Rectangle rect2 = new Rectangle(170, 300, 150, 150);
        rect2.setFill(new ImagePattern(new Image("Pics/Pokemon/user1.png")));
        Rectangle rect3 = new Rectangle(330, 300, 150, 150);
        rect3.setFill(new ImagePattern(new Image("Pics/Pokemon/user2.png")));
        Rectangle rect4 = new Rectangle(10, 500, 150, 150);
        rect4.setFill(new ImagePattern(new Image("Pics/Pokemon/user3.png")));
        Rectangle rect5 = new Rectangle(200, 500, 150, 150);
        rect5.setFill(new ImagePattern(new Image("Pics/Pokemon/user4.png")));
        rect.setOpacity(0.9);
        rect2.setOpacity(0.9);
        rect3.setOpacity(0.9);
        rect4.setOpacity(0.9);
        rect5.setOpacity(0.9);
        board.getChildren().addAll(rect, rect2, rect3, rect4, rect5);

        Text text = new Text("you haven't selected role,\nso cannot start!");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Avenir Next", FontWeight.LIGHT, 18));
        text.setLayoutX(950);
        text.setLayoutY(350);
        text.setWrappingWidth(500);
        board.getChildren().add(text);

        Button btn1 = new Button("Start");
        btn1.setMaxSize(100, 40);
        btn1.setMinSize(100, 40);
        btn1.setLayoutX(1000);
        btn1.setLayoutY(400);
        btn1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        btn1.setOnAction(e -> {
            board.getChildren().removeAll(board.getChildren());
            page2_initial();
        });
        board.getChildren().add(btn1);
        btn1.setDisable(true);

        Text chosenrole = new Text("select a Pokemon from \nthe following roles");
        chosenrole.setFill(Color.WHITE);
        chosenrole.setFont(Font.font("Avenir Next", FontWeight.LIGHT, 40));
        chosenrole.setLayoutX(40);
        chosenrole.setLayoutY(100);
        chosenrole.setWrappingWidth(500);
        board.getChildren().add(chosenrole);


        Text youchose = new Text("you choose:\n\n\n\n\n\n\n\nits attributes: ");
        youchose.setFill(Color.WHITE);
        youchose.setFont(Font.font("Avenir Next", FontWeight.LIGHT, 30));
        youchose.setLayoutX(600);
        youchose.setLayoutY(150);
        youchose.setWrappingWidth(550);
        board.getChildren().add(youchose);

        Label attrinfo = new Label();
        attrinfo.setLayoutX(600);
        attrinfo.setLayoutY(500);
        attrinfo.setStyle("-fx-font-color:black ;-fx-fond-size:40");
        board.getChildren().add(attrinfo);

        ImageView roleImg = new ImageView();
        roleImg.setFitWidth(200);
        roleImg.setFitHeight(200);
        roleImg.setLayoutX(700);
        roleImg.setLayoutY(200);
        board.getChildren().add(roleImg);
        addClickRoles(rect, btn1, text, roleImg,attrinfo);
        addClickRoles(rect2, btn1, text, roleImg,attrinfo);
        addClickRoles(rect3, btn1, text, roleImg,attrinfo);
        addClickRoles(rect4, btn1, text, roleImg,attrinfo);
        addClickRoles(rect5, btn1, text, roleImg,attrinfo);

    }
    Node selectedrole;
    public int getIdFromSelectedRole(){
        if(selectedrole==null) {
            System.out.println('n');return 0;}
        int x=(int)selectedrole.localToScene(selectedrole.getBoundsInLocal()).getCenterX() - 75;
        int y=(int)selectedrole.localToScene(selectedrole.getBoundsInLocal()).getCenterY() - 75;
        System.out.println(x+","+y);
        if(x==170&&y==300) return 1;
        else if(x==330&&y==300) return 2;
        else if(x==10&&y==500)return 3;
        else if(x==200&&y==500) return 4;
        else return 0; //default
    }
    public void addClickRoles(Node node, Button btn,Text text,ImageView roleImg,Label attrinfo) {
        board.addEventHandler(EventType.ROOT, e -> {
            node.requestFocus();
            node.addEventHandler(MouseEvent.MOUSE_CLICKED, keyEvent -> {
            });
        });
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("clicked");
            node.setEffect(borderGlow);
            node.setOpacity(1);
            for (Node r: this.board.getChildren()){
                if(r!=node&&r.getClass()==new Rectangle().getClass()){
                   r.setEffect(null);
                   r.setOpacity(0.9);
               }
            }
            selectedrole=node;
            System.out.println(selectedrole.localToScene(selectedrole.getBoundsInLocal()).getCenterX() - 75 );
            System.out.println(selectedrole.localToScene(selectedrole.getBoundsInLocal()).getCenterY() - 75 );
            int id = getIdFromSelectedRole();
            System.out.println(id);
            user = new Pokemon(id);
            user = pokemonLoadFromJson(user.getid());
            user.setPosition(new int[]{1, 0});
            currentMapIndex = 0;
            this.board.getChildren().remove(text);
            roleImg.setImage(new Image("Pics/Pokemon/user" + user.getid() + ".png"));
            String name = "Name:" + user.getName();
            String attr = "Level:" + user.getLevel() + ", HP:" + user.getHP() + "/" + user.getmaxHP() +
                    ", MP:" + user.getMP() + "/" + user.getMaxMP()
                    + ", Defense:" + user.getDefence() + ", Attack:" + user.getAttack() + ", Experience:" + user.getExp();
            String able = "grass_able:" + user.getGrassAble() + ", water_able:" + user.getWaterAble() + ", stone_able:" + user.getStoneAble();
            String pack = "My backpack: " + "HP poison(" + number_HP_poison.get() + ")" + ", MP poison(" + number_MP_poison.get() + ")";//+", Sword("+countSword+")";
            attrinfo.setText(name + "\n\n" + attr + "\n\n" + able + "\n\n" + pack);
            btn.toFront();
            btn.setDisable(false);

        });

    }

    //-------------------------------------------------------------
//                 Page2_initial (Game Map)
// ------------------------------------------------------------
// Kath & Natalie
    Map mapclass;
    //have four maps
    boolean keyable;

    public void page2_initial() {
        board.getChildren().removeAll(board.getChildren());
        System.out.println("Map");
        System.out.println("Current Role:" + user.toString());

        //create and show the map
        showMap(whichMap(currentMapIndex));
        System.out.println("already show map");

        //showPokemon
        int[] role_pos = user.getPosition();
        Rectangle rect = new Rectangle(role_pos[0] * 30, role_pos[1] * 30, 30, 30);
        rect.setFill(new ImagePattern(new Image("Pics/Pokemon/user" + user.getid() + ".png")));
        board.getChildren().add(rect);
        rect.toFront();
        //some functions of Pokemon
        startShowAnimation(rect);
        keyable = true;
        addKeyPressed(rect, board);

        //show attributes of pokemon
        Button attributes = new Button("My attributes");
        attributes.setLayoutX(5);
        attributes.setLayoutY(720);
        attributes.setMaxSize(100, 20);
        attributes.setMinSize(100, 20);
        attributes.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        attributes.setFocusTraversable(false);
        board.getChildren().add(attributes);
        Rectangle rec_attr = new Rectangle(5, 580, 500, 140);
        rec_attr.setFill(Color.WHITE);
        rec_attr.setOpacity(0.9);
        Label attrinfo = new Label();
        attrinfo.setLayoutX(10);
        attrinfo.setLayoutY(590);
        attrinfo.setStyle("-fx-font-color:black");
        attributes.setOnMousePressed(e -> {
            String name = "Name:" + user.getName();
            String attr = "Level:" + user.getLevel() + ", HP:" + user.getHP() + "/" + user.getmaxHP() +
                    ", MP:" + user.getMP() + "/" + user.getMaxMP()
                    + ", Defense:" + user.getDefence() + ", Attack:" + user.getAttack() + ", Experience:" + user.getExp();
            String able = "grass_able:" + user.getGrassAble() + ", water_able:" + user.getWaterAble() + ", stone_able:" + user.getStoneAble();
            String pack = "My backpack: " + "HP poison(" + number_HP_poison.get() + ")" + ", MP poison(" + number_MP_poison.get() + ")";//+", Sword("+countSword+")";
            attrinfo.setText(name + "\n\n" + attr + "\n\n" + able + "\n\n" + pack);
            board.getChildren().addAll(rec_attr, attrinfo);
        });
        attributes.setOnMouseReleased(e -> board.getChildren().removeAll(rec_attr, attrinfo));
        //show enemy info
        Button enemyinfo = new Button("Enemy info");
        enemyinfo.setLayoutX(1000);
        enemyinfo.setLayoutY(720);
        enemyinfo.setMaxSize(100, 20);
        enemyinfo.setMinSize(100, 20);
        enemyinfo.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        enemyinfo.setFocusTraversable(false);
        board.getChildren().add(enemyinfo);
        Label label_einfo = new Label();
        label_einfo.setLayoutX(150);
        label_einfo.setLayoutY(722);
        enemyinfo.setOnMousePressed(e -> {
            Rectangle rec_einfo = new Rectangle(1000, 620, 100, 100);
            rec_einfo.setFill(Color.BLUE);
            rec_einfo.setOpacity(0.7);
            String einfo = "";//To be completed
        });
    }

    public void addKeyPressed(Node node, Node board) {
        mapclass = new Map();
        board.addEventHandler(EventType.ROOT, e -> {
            node.requestFocus();
            node.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            });
        });
        board.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            node.requestFocus();
            node.setEffect(null);
        });
        node.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (this.keyable) {
                System.out.println("key able");
                KeyCode keyCode = e.getCode();
                boolean mapEnd = mapclass.ifMapEnd(user, whichMap(currentMapIndex));

                if (keyCode.equals(KeyCode.RIGHT)) {
                    System.out.println("right");
                    boolean canMove = mapclass.checkMoveEnable(user, 'R', whichMap(currentMapIndex));
                    int x = user.getPosition()[0];
                    int y = user.getPosition()[1];
                    if (mapEnd) {
                        boolean NextMap = mapclass.nextMap(user, 'R', map, currentMapIndex);
                        boolean LastMap = mapclass.lastMap(user, 'R', map, currentMapIndex);
                        if (NextMap) {
                            currentMapIndex++;
                            user.setPosition(mapclass.startPosition(currentMapIndex));
                            System.out.println("which map:" + currentMapIndex);
                            page2_initial();
                        } else if (LastMap) {
                            currentMapIndex--;
                            user.setPosition(mapclass.endPosition(currentMapIndex));
                            System.out.println("which map:" + currentMapIndex);
                            page2_initial();
                        }
                    }
                    if (canMove) {
                        moveAnimation(node, x * 30, y * 30, (x + 1) * 30, y * 30);
                        user.setPosition(new int[]{x + 1, y});
                    }
                } else if (keyCode.equals(KeyCode.LEFT)) {
                    System.out.println("left");
                    boolean canMove = mapclass.checkMoveEnable(user, 'L', whichMap(currentMapIndex));
                    int x = user.getPosition()[0];
                    int y = user.getPosition()[1];
                    if (mapEnd) {
                        boolean NextMap = mapclass.nextMap(user, 'L', map, currentMapIndex);
                        boolean LastMap = mapclass.lastMap(user, 'L', map, currentMapIndex);
                        if (NextMap) {
                            currentMapIndex++;
                            user.setPosition(mapclass.startPosition(currentMapIndex));
                            System.out.println("which map:" + currentMapIndex);
                            page2_initial();
                        } else if (LastMap) {
                            currentMapIndex--;
                            user.setPosition(mapclass.endPosition(currentMapIndex));
                            System.out.println("which map:" + currentMapIndex);
                            page2_initial();
                        }
                    }
                    if (canMove) {
                        moveAnimation(node, x * 30, y * 30, (x - 1) * 30, y * 30);
                        user.setPosition(new int[]{x - 1, y});
                    }
                } else if (keyCode.equals(KeyCode.UP)) {
                    System.out.println("up");
                    boolean canMove = mapclass.checkMoveEnable(user, 'U', whichMap(currentMapIndex));
                    int x = user.getPosition()[0];
                    int y = user.getPosition()[1];
                    if (mapEnd) {
                        boolean NextMap = mapclass.nextMap(user, 'U', map, currentMapIndex);
                        boolean LastMap = mapclass.lastMap(user, 'U', map, currentMapIndex);
                        if (NextMap) {
                            currentMapIndex++;
                            user.setPosition(mapclass.startPosition(currentMapIndex));
                            System.out.println("which map:" + currentMapIndex);
                            page2_initial();
                        } else if (LastMap) {
                            currentMapIndex--;
                            user.setPosition(mapclass.endPosition(currentMapIndex));
                            System.out.println("which map:" + currentMapIndex);
                            page2_initial();
                        }
                    }
                    if (canMove) {
                        moveAnimation(node, x * 30, y * 30, x * 30, (y - 1) * 30);
                        user.setPosition(new int[]{x, y - 1});
                    }
                } else if (keyCode.equals(KeyCode.DOWN)) {
                    System.out.println("down");
                    boolean canMove = mapclass.checkMoveEnable(user, 'D', whichMap(currentMapIndex));
                    int x = user.getPosition()[0];
                    int y = user.getPosition()[1];
                    if (mapEnd) {
                        boolean NextMap = mapclass.nextMap(user, 'D', map, currentMapIndex);
                        boolean LastMap = mapclass.lastMap(user, 'D', map, currentMapIndex);
                        if (NextMap) {
                            currentMapIndex++;
                            user.setPosition(mapclass.startPosition(currentMapIndex));
                            System.out.println("which map:" + currentMapIndex);
                            page2_initial();
                        } else if (LastMap) {
                            currentMapIndex--;
                            user.setPosition(mapclass.endPosition(currentMapIndex));
                            System.out.println("which map:" + currentMapIndex);
                            page2_initial();
                        }
                    }
                    if (canMove) {
                        moveAnimation(node, x * 30, y * 30, x * 30, (y + 1) * 30);
                        user.setPosition(new int[]{x, y + 1});
                    }
                }
            }
        });
    }

    public void startShowAnimation(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(200), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(7);
        ft.setAutoReverse(true);
        ft.play();
    }

    public void moveAnimation(Node node, double now_x, double now_y, double next_x, double next_y) {
        keyable = false;
        node.setEffect(borderGlow);
        int adjsut = 30;
        Path path = new Path();
        path.getElements().add(new MoveTo(now_x + 0.5 * adjsut, now_y + 0.5 * adjsut));
        path.getElements().add(new LineTo(next_x + 0.5 * adjsut, next_y + 0.5 * adjsut));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(200));
        pathTransition.setPath(path);
        pathTransition.setNode(node);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(true);
        //Playing Sequential Transition
        Timeline check3or4 = new Timeline(new KeyFrame(Duration.millis(1), ae -> {
            char e = whichMap(currentMapIndex)[user.getPosition()[0]][user.getPosition()[1]];
            System.out.println(user.strPos());
            if (mapclass.ifTerminal(user, whichMap(currentMapIndex))) page4_initial();
            if (mapclass.ifBattle(user, whichMap(currentMapIndex))) page3_initial((int) e - 48);
            if (e == 'h' || e == 'm' || e == 'a' || e == 'f' || e == 'x' || e == 'y' || e == 'z') {
                if (e == 'h') number_HP_poison.set(String.valueOf(Integer.parseInt(number_HP_poison.get()) + 1));
                else if (e == 'm') number_MP_poison.set(String.valueOf(Integer.parseInt(number_MP_poison.get()) + 1));
                else if(e=='a') user.setAttack(user.getAttack()+1);
                else if(e=='f') user.setDefence(user.getDefence()+1);
                else if(e=='x') user.setGrassAble(true);
                else if(e=='y') user.setWaterAble(true);
                else if(e=='z') user.setStoneAble(true);
                whichMap(currentMapIndex)[user.getPosition()[0]][user.getPosition()[1]] = 'r';
                //remove this rect on map
                Node result = null;
                for (Node r : board.getChildren()) {
                    if (r.getClass() == new Rectangle().getClass() &&
                            r.localToScene(r.getBoundsInLocal()).getCenterX() - 15 == user.getPosition()[0] * 30 &&
                            r.localToScene(r.getBoundsInLocal()).getCenterY() - 15 == user.getPosition()[1] * 30) {
                        result = r;
                        break;
                    }
                }
                board.getChildren().remove(result);
            }

            this.keyable = true;
            node.setEffect(null);
        }));
        SequentialTransition seqTransition = new SequentialTransition(pathTransition, new PauseTransition(Duration.millis(230)), check3or4);
        seqTransition.play();
    }

    // fx: add pieces to board (board只能显示map中的40*24个pieces)
    public void showMap(char[][] showmap) {
        for (int i = 0; i < mapLength / 2; i++) {
            for (int j = 0; j < mapHeight / 2; j++) {
                Rectangle rect = new Rectangle(i * 30, j * 30, 30, 30);
                if (showmap[i][j] != 'r') {
                    if (showmap[i][j] > 47 && showmap[i][j] < 56) {
                        int enemyID = showmap[i][j] - '0';
                        rect.setFill(new ImagePattern(new Image("Pics/Pokemon/enemy" + enemyID + ".png")));
                    } else {
                        rect.setFill(new ImagePattern(new Image("Pics/Maps/" + showmap[i][j] + ".png")));
                    }
                } else {
                    rect.setFill(Color.WHITE);
                }
                board.getChildren().add(rect);
            }
        }
        //记录当前的board中所有piece的type和position
    }


    // Kath
    //-------------------------------------------------
    // 调一次
    public void initialAllMap() {
        String battleMap = "src/battleMap.txt";
        try {
            // initial whole map:
            BufferedReader bfr1 = new BufferedReader(new FileReader(battleMap));
            String l1;
            int row1 = 0;
            while ((l1 = bfr1.readLine()) != null) {
                for (int i = 0; i < l1.length(); i++) {
                    map[i][row1] = l1.charAt(i);
                }
                row1++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //只用于initialShowMap
    public void initialMap(int mapIndex, char[][] showmap) {
        String partMap = "src/battleMap" + mapIndex + ".txt";
        try {
            // initial showMap:
            BufferedReader bfr = new BufferedReader(new FileReader(partMap));
            String l0;
            int row0 = 0;
            while ((l0 = bfr.readLine()) != null) {
                for (int i = 0; i < l0.length(); i++) {
                    showmap[i][row0] = l0.charAt(i);
                }
                row0++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //只用一次
    public void initialShowMap() {
        initialMap(0, showMap0);
        initialMap(1, showMap1);
        initialMap(2, showMap2);
        initialMap(3, showMap3);
    }

    //-------------------------------------------------------
    // find current map
    public char[][] whichMap(int mapIndex) {
        switch (mapIndex) {
            case 0:
                return showMap0;
            case 1:
                return showMap1;
            case 2:
                return showMap2;
            default:
                return showMap3;
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
        assert jsonReader != null;
        List<Pokemon> pl = gson.fromJson(jsonReader, CUS_LIST_TYPE);
        return pl.get(id);
    }

    public void page3_to_page2(boolean win, Pokemon changeduser,boolean levelup) {
        if(levelup){
            Rectangle rec = new Rectangle(0,0,1200,740);
            rec.setFill(Color.WHITE);
            rec.setOpacity(0.95);
            Rectangle levelupimg = new Rectangle(150,200,300,230);
            levelupimg.setFill(new ImagePattern(new Image("Pics/Spell/levelup.jpg")));
            board.getChildren().addAll(rec,levelupimg);
            Label message = new Label();
            message.setLayoutX(450);
            message.setLayoutY(200);
            message.setStyle("-fx-font-color:Black; -fx-font-size:40");
            message.setText("UPGRADE!!\n\nyour level is "+changeduser.getLevel());
            Button exit = new Button("exit");
            exit.setLayoutX(950);
            exit.setLayoutY(680);
            exit.setMaxSize(100, 40);
            exit.setMinSize(100, 40);
            exit.setFont(Font.font("Arial", FontWeight.BOLD,20));
            exit.setFocusTraversable(false);
            board.getChildren().addAll(exit,message);
            exit.setOnMouseClicked(e->{
                if (win) {
                    System.out.println("go to page2test");
                    this.user = changeduser;
                    //System.out.println(user.strPos());
                    whichMap(currentMapIndex)[user.getPosition()[0]][user.getPosition()[1]] = 'r';
                    page2_initial();
                } else {
                    page4_initial();
                }
            });
        }
        else {
            System.out.println("---yiyang ");
            if (win) {
                System.out.println("go to page2test");
                this.user = changeduser;
                //System.out.println(user.strPos());
                whichMap(currentMapIndex)[user.getPosition()[0]][user.getPosition()[1]] = 'r';
                page2_initial();
            } else {
                page4_initial();
            }
        }
    }


    //-------------------------------------------------------------
//                 Page3_initial (Battle)
// ------------------------------------------------------------
    // Kevin
    public void page3_initial(int enemy_id) {
        // For safety, remove all elements
        board.getChildren().removeAll(board.getChildren());

        // this line use for test ------------------------------------------------------ need remove in future
        //this.user = pokemonLoadFromJson(0);

        System.out.println("Battle page");

        // enemy load, create enemy
        Enemy enemy = enemy_loading(enemy_id);
        // start Battle
        Battle battle = new Battle(this.user, enemy);
        // Background area
        page3_setupBackground(enemy, battle);
        // Information Box
        textInfo = new SimpleStringProperty("Now is your turn... Choose one action.");
        page3_setupStaticInfoBoxes(enemy);
        page3_setupDynamicInfoBoxes(enemy);
        // buttons
        page3_setupButton(battle);
        // poison buttons
        page3_setupPoison();
    }

    public void page3_setupPoison() {
        // two images
        Rectangle HP_poison = new Rectangle(1100, 40, 30, 30);
        HP_poison.setFill(new ImagePattern(new Image("Pics/Maps/h.png")));
        board.getChildren().add(HP_poison);
        Rectangle MP_poison = new Rectangle(1100, 110, 30, 30);
        MP_poison.setFill(new ImagePattern(new Image("Pics/Maps/m.png")));
        board.getChildren().add(MP_poison);
        // two buttons
        Button HP_btn = new Button();
        HP_btn.setLayoutX(1130);
        HP_btn.setLayoutY(40);
        HP_btn.setMinSize(30, 30);
        HP_btn.textProperty().bindBidirectional(number_HP_poison);
        HP_btn.setOnAction(e -> {
            int i = Integer.parseInt(number_HP_poison.get());
            if (i != 0 && user.getHP() != user.getmaxHP()) {
                i--;
                poisonUsed(true);
                number_HP_poison.set(i + "");
            }
        });
        board.getChildren().add(HP_btn);

        Button MP_btn = new Button();
        MP_btn.setLayoutX(1130);
        MP_btn.setLayoutY(110);
        MP_btn.setMinSize(30, 30);
        MP_btn.textProperty().bindBidirectional(number_MP_poison);
        MP_btn.setOnAction(e -> {
            int i = Integer.parseInt(number_MP_poison.get());
            // full MP or no poison
            if (i != 0 && user.getMP() != 100) {
                i--;
                poisonUsed(false);
                number_MP_poison.set(i + "");
            }
        });
        board.getChildren().add(MP_btn);
    }

    // execute poison, HorM: true for HP, false for MP
    public void poisonUsed(boolean HorM) {
        if (HorM) {
            if (user.getHP() >= 0.4 * user.getmaxHP()) {
                user.setHP(user.getmaxHP());
            } else {
                user.setHP((int) (user.getHP() + user.getmaxHP() * 0.6));
            }
        } else {
            if (user.getMP() >= 60) user.setMP(100);
            else user.setMP(user.getMP() + 40);
        }
        updateUserUI();
    }

    // update UI for HP and MP
    public void updateUserUI() {
        user_HP_info.setValue(user.getHP() + "/" + user.getmaxHP());
        user_MP_info.setValue(user.getMP() + "/100");
        user_HP_bar.set((user.getHP() * 1.0) / (user.getmaxHP() * 1.0) * 200);
        user_MP_bar.set(user.getMP() * 1.0);
        //user_AD_info.setValue(user.getAttack() + "-" + user.getDefence());
        //user_LEVEL.setValue("lvl " + user.getLevel());
    }

    // information boxes with static structure -- finished (less changed)
    public void page3_setupStaticInfoBoxes(Enemy enemy) {
        // Text message info box
        Label infoBox = new Label();
        infoBox.setLayoutX(20);
        infoBox.setLayoutY(610);
        infoBox.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        infoBox.textProperty().bindBidirectional(textInfo);
        board.getChildren().add(infoBox);

        // Two Static boxes for user and enemy
        // Double boxes to create border
        Rectangle user_info_box = new Rectangle(700, 440, 300, 100);
        box_format1(user_info_box);
        Rectangle enemy_info_box = new Rectangle(120, 40, 300, 100);
        box_format1(enemy_info_box);
        board.getChildren().add(user_info_box);
        board.getChildren().add(enemy_info_box);

        // User Information UI - Name part
        Label user_info = new Label(this.user.getName());
        user_info.setFont(Font.font("Arial", FontWeight.BLACK, 18));
        user_info.setLayoutX(725);
        user_info.setLayoutY(450);
        board.getChildren().add(user_info);
        // user level
        Label user_level = new Label();
        user_level.setFont(Font.font("Arial", FontWeight.BLACK, 18));
        user_level.setLayoutX(925);
        user_level.setLayoutY(450);
        user_level.textProperty().bindBidirectional(user_LEVEL);
        user_LEVEL.setValue("lvl " + user.getLevel());
        board.getChildren().add(user_level);

        // HP and MP bar
        Rectangle user_hp_bg = new Rectangle(725, 480, 202, 12);
        box_format2(user_hp_bg);
        board.getChildren().add(user_hp_bg);
        Rectangle user_mp_bg = new Rectangle(725, 500, 102, 12);
        box_format2(user_mp_bg);
        board.getChildren().add(user_mp_bg);

        // enemy Information UI - Name part
        Label enemy_info = new Label(enemy.getName());
        enemy_info.setFont(Font.font("Arial", FontWeight.BLACK, 18));
        enemy_info.setLayoutX(145);
        enemy_info.setLayoutY(50);
        board.getChildren().add(enemy_info);
        // Black and White border
        Rectangle enemy_hp_bg = new Rectangle(145, 80, 202, 12);
        box_format2(enemy_hp_bg);
        board.getChildren().add(enemy_hp_bg);
        Rectangle enemy_mp_bg = new Rectangle(145, 100, 102, 12);
        box_format2(enemy_mp_bg);
        board.getChildren().add(enemy_mp_bg);

    }

    // helper function - format1
    public void box_format1(Rectangle rect) {
        rect.setArcHeight(15);
        rect.setArcWidth(15);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);
    }

    // helper function - format2
    public void box_format2(Rectangle rect) {
        rect.setArcHeight(5);
        rect.setArcWidth(5);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(1);
    }

    // highly related to Static position
    // dynamic graphing HP bar and MP bar, so as the detail number -- finished (less changed)
    public void page3_setupDynamicInfoBoxes(Enemy enemy) {
        // dynamic bar, user part
        Rectangle user_hp_bar = new Rectangle(726, 481, 200, 10);
        user_HP_bar.setValue((user.getHP() * 1.0) / (user.getmaxHP() * 1.0) * 200);
        user_hp_bar.widthProperty().bindBidirectional(user_HP_bar);
        user_hp_bar.setFill(Color.RED);
        user_hp_bar.setArcHeight(5);
        user_hp_bar.setArcWidth(5);
        board.getChildren().add(user_hp_bar);
        Rectangle user_mp_bar = new Rectangle(726, 501, 100, 10);
        user_MP_bar.setValue(user.getMP() * 1.0);
        user_mp_bar.widthProperty().bindBidirectional(user_MP_bar);
        user_mp_bar.setFill(Color.BLUE);
        user_mp_bar.setArcHeight(5);
        user_mp_bar.setArcWidth(5);
        board.getChildren().add(user_mp_bar);
        // enemy part
        Rectangle enemy_hp_bar = new Rectangle(146, 81, 200, 10);
        enemy_HP_bar.setValue((enemy.getHP() * 1.0) / (enemy.getmaxHP() * 1.0) * 200);
        enemy_hp_bar.widthProperty().bindBidirectional(enemy_HP_bar);
        enemy_hp_bar.setFill(Color.RED);
        enemy_hp_bar.setArcHeight(5);
        enemy_hp_bar.setArcWidth(5);
        board.getChildren().add(enemy_hp_bar);
        Rectangle enemy_mp_bar = new Rectangle(146, 101, 100, 10);
        enemy_MP_bar.setValue(enemy.getMP() * 1.0);
        enemy_mp_bar.widthProperty().bindBidirectional(enemy_MP_bar);
        enemy_mp_bar.setFill(Color.BLUE);
        enemy_mp_bar.setArcHeight(5);
        enemy_mp_bar.setArcWidth(5);
        board.getChildren().add(enemy_mp_bar);

        // Detailed Info Display - display format: HP / MaxHP ...
        Label user_hp_info = new Label();
        user_hp_info.setLayoutX(940);
        user_hp_info.setLayoutY(479);
        user_HP_info.setValue(user.getHP() + "/" + user.getmaxHP());
        user_hp_info.textProperty().bindBidirectional(user_HP_info);
        user_hp_info.setFont(Font.font("Arial", FontWeight.BLACK, 12));
        board.getChildren().add(user_hp_info);
        // User MP
        Label user_mp_info = new Label();
        user_mp_info.setLayoutX(840);
        user_mp_info.setLayoutY(499);
        user_MP_info.setValue(user.getMP() + "/100");
        user_mp_info.textProperty().bindBidirectional(user_MP_info);
        user_mp_info.setFont(Font.font("Arial", FontWeight.BLACK, 12));
        // User att/def
        Label user_ad_info = new Label();
        user_ad_info.setLayoutX(726);
        user_ad_info.setLayoutY(519);
        user_AD_info.setValue(user.getAttack() + " - " + user.getDefence());
        user_ad_info.textProperty().bindBidirectional(user_AD_info);
        user_ad_info.setFont(Font.font("Arial", FontWeight.BLACK, 12));
        board.getChildren().add(user_ad_info);
        board.getChildren().add(user_mp_info);
        // Enemy HP
        Label enemy_hp_info = new Label();
        enemy_hp_info.setLayoutX(360);
        enemy_hp_info.setLayoutY(79);
        enemy_HP_info.setValue(enemy.getHP() + "/" + enemy.getmaxHP());
        enemy_hp_info.textProperty().bindBidirectional(enemy_HP_info);
        enemy_hp_info.setFont(Font.font("Arial", FontWeight.BLACK, 12));
        board.getChildren().add(enemy_hp_info);
        // Enemy MP
        Label enemy_mp_info = new Label();
        enemy_mp_info.setLayoutX(260);
        enemy_mp_info.setLayoutY(99);
        enemy_MP_info.setValue(enemy.getMP() + "/100");
        enemy_mp_info.textProperty().bindBidirectional(enemy_MP_info);
        enemy_mp_info.setFont(Font.font("Arial", FontWeight.BLACK, 12));
        board.getChildren().add(enemy_mp_info);
        // Enemy att/def
        Label enemy_ad_info = new Label();
        enemy_ad_info.setLayoutX(146);
        enemy_ad_info.setLayoutY(119);
        enemy_AD_info.setValue(enemy.getAttack() + " - " + enemy.getDefence());
        enemy_ad_info.textProperty().bindBidirectional(enemy_AD_info);
        enemy_ad_info.setFont(Font.font("Arial", FontWeight.BLACK, 12));
        board.getChildren().add(enemy_ad_info);
    }

    // Page3 basic background image and structure -- finished (less changed)
    public void page3_setupBackground(Enemy enemy, Battle battle) {
        // Background image part
        ImageView background = new ImageView();
        final String PAGE3_BACKGROUND_URI = getClass().getResource("Pics/page3_background.png").toString();
        background.setImage(new Image(PAGE3_BACKGROUND_URI));
        background.setFitHeight(599);
        background.setPreserveRatio(true);
        board.getChildren().add(background);
        // User image
        Rectangle user_area = new Rectangle(150, 360, 220, 220);
        user_area.setFill(new ImagePattern(new Image("Pics/Pokemon/user" + user.getid() + ".png")));
        board.getChildren().add(user_area);
        battle.setP_user(user_area);
        // Enemy image
        Rectangle enemy_area = new Rectangle(690, 20, 220, 220);
        enemy_area.setFill(new ImagePattern(new Image("Pics/Pokemon/enemy" + enemy.getId() + ".png")));
        board.getChildren().add(enemy_area);
        battle.setP_tar(enemy_area);
        // control area
        Rectangle control_area = new Rectangle(0, 600, 1080, 300);
        control_area.setFill(new ImagePattern(new Image("Pics/page3_control_area.png")));
        board.getChildren().add(control_area);

        // Structure Line
        Line line0 = new Line(0, 599, 1080, 599);
        line0.setStrokeWidth(2);
        board.getChildren().add(line0);
        Line line1 = new Line(1080, 0, 1080, WINDOW_HEIGHT);
        line1.setStrokeWidth(2);
        board.getChildren().add(line1);
        Line line2 = new Line(700, 599, 700, WINDOW_HEIGHT);
        line2.setStrokeWidth(2);
        board.getChildren().add(line2);
    }

    // Page3 buttons and actions -- may add buttons in future
    public void page3_setupButton(Battle battle) {
        Button btn1 = new Button("Attack");
        btn1.setLayoutX(760);
        btn1.setLayoutY(620);
        btn1.setMinSize(100, 40);
        btn1.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        btn1.setOnAction(e -> {
            if (battle.button_able) {
                battle.user_action(0, textInfo, user_HP_info, user_MP_info, enemy_HP_info, enemy_MP_info, user_HP_bar
                        , user_MP_bar, enemy_HP_bar, enemy_MP_bar, user_AD_info, enemy_AD_info, user_LEVEL, board, this);
            }
        });
        board.getChildren().add(btn1);

        Button btn2 = new Button("Spell1");
        btn2.setLayoutX(920);
        btn2.setLayoutY(620);
        btn2.setMinSize(100, 40);
        btn2.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        btn2.setOnAction(e -> {
            if (battle.button_able) {
                if (this.user.getMP() >= 20) {
                    battle.user_action(1, textInfo, user_HP_info, user_MP_info, enemy_HP_info, enemy_MP_info, user_HP_bar
                            , user_MP_bar, enemy_HP_bar, enemy_MP_bar, user_AD_info, enemy_AD_info, user_LEVEL, board, this);
                } else {
                    textInfo.setValue("You need at least 20 magic power to use this spell.");
                }
            }
        });
        board.getChildren().add(btn2);

        Button btn3 = new Button("Spell2");
        btn3.setLayoutX(760);
        btn3.setLayoutY(680);
        btn3.setMinSize(100, 40);
        btn3.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        btn3.setOnAction(e -> {
            if (battle.button_able) {
                if (this.user.getMP() >= 20) {
                    battle.user_action(2, textInfo, user_HP_info, user_MP_info, enemy_HP_info, enemy_MP_info, user_HP_bar
                            , user_MP_bar, enemy_HP_bar, enemy_MP_bar, user_AD_info, enemy_AD_info, user_LEVEL, board, this);
                } else {
                    textInfo.setValue("You need at least 20 magic power to use this spell.");
                }
            }
        });
        board.getChildren().add(btn3);

        Button btn4 = new Button("Spell3");
        btn4.setLayoutX(920);
        btn4.setLayoutY(680);
        btn4.setMinSize(100, 40);
        btn4.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        btn4.setOnAction(e -> {
            if (battle.button_able) {
                if (this.user.getMP() >= 80) {
                    battle.user_action(3, textInfo, user_HP_info, user_MP_info, enemy_HP_info, enemy_MP_info, user_HP_bar
                            , user_MP_bar, enemy_HP_bar, enemy_MP_bar, user_AD_info, enemy_AD_info, user_LEVEL, board, this);
                } else {
                    textInfo.setValue("You need at least 80 magic power to use this spell.");
                }
            }
        });
        board.getChildren().add(btn4);
    }

    // loading enemy from Json file
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
        return enemyList.get(enemy_id);
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

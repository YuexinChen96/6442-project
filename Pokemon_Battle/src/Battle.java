import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class Battle {
    private int turn = 0;

    private Enemy tar;
    private Pokemon user;

    private Rectangle p_user;
    private Rectangle p_tar;

    public Rectangle getP_user() {
        return p_user;
    }

    public void setP_user(Rectangle p_user) {
        this.p_user = p_user;
    }

    public Rectangle getP_tar() {
        return p_tar;
    }

    public void setP_tar(Rectangle p_tar) {
        this.p_tar = p_tar;
    }

    public int getTurn() {
        return turn;
    }

    public Battle(){} // constructor

    public Battle (Pokemon user, Enemy tar){
        this.tar = tar;
        this.user = user;
    }

    public boolean gameover_test (){
        return this.tar.getHP() == 0 || this.user.getHP() == 0;
    }

    // return true for user turn, false for enemy turn
    public boolean user_turn (){
        return this.turn % 2 == 0;
    }

    // execute user action, 0 for attack, 1 for spell-1, 2 for spell-2, ...
    public void user_action (int action,StringProperty textInfo,StringProperty user_HP_info,StringProperty user_MP_info,
                             StringProperty enemy_HP_info, StringProperty enemy_MP_info,DoubleProperty user_HP_bar,
                             DoubleProperty user_MP_bar,DoubleProperty enemy_HP_bar,DoubleProperty enemy_MP_bar,
                             StringProperty user_AD_info,StringProperty enemy_AD_info, Pane board){

        System.out.println("Execute action: " + action);

        Timeline t1 = null;
        if (action == 0) {
            t1 = new Timeline(new KeyFrame(Duration.millis(10),ae->{
                PathTransition pt1 = action1_animation(board);
                pt1.play();
            }));
            // animation may go here


            int dmg = user.getAttack() - tar.getDefence();
            System.out.println("The damage is: " + dmg);
            tar.setHP(damageInRange(tar.getHP(), dmg));

        }

        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(4000),ae->
                UIupdate(user_HP_info,user_MP_info,enemy_HP_info,enemy_MP_info,user_HP_bar,user_MP_bar,enemy_HP_bar
                ,enemy_MP_bar,user_AD_info,enemy_AD_info)));

        SequentialTransition seqT = new SequentialTransition(t1, t2);
        seqT.play();

        this.turn ++;


    }



    public PathTransition action1_animation(Pane board) {
        p_user.toFront();
        Path path = new Path();
        path.getElements().add(new MoveTo(260,470));
        path.getElements().add(new LineTo(800,130));
        path.getElements().add(new LineTo(260,470));
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(4000));
        pt.setPath(path);
        pt.setNode(p_user);
        pt.setAutoReverse(true);
        pt.setCycleCount(1);
        return pt;
    }

    public void UIupdate(StringProperty user_HP_info,StringProperty user_MP_info,
                         StringProperty enemy_HP_info, StringProperty enemy_MP_info,DoubleProperty user_HP_bar,
                         DoubleProperty user_MP_bar,DoubleProperty enemy_HP_bar,DoubleProperty enemy_MP_bar,
                         StringProperty user_AD_info,StringProperty enemy_AD_info){
        user_HP_info.setValue(user.getHP() + "/" + user.getmaxHP());
        user_MP_info.setValue(user.getMP() + "/100");
        enemy_HP_info.setValue(tar.getHP() + "/" + tar.getmaxHP());
        enemy_MP_info.setValue(tar.getMP() + "/100");
        user_HP_bar.set((user.getHP() * 1.0) / (user.getmaxHP() * 1.0) * 200);
        user_MP_bar.set(user.getMP() * 1.0);
        enemy_HP_bar.set((tar.getHP() * 1.0) / (tar.getmaxHP() * 1.0) * 200);
        enemy_MP_bar.set(tar.getMP() * 1.0);
        user_AD_info.setValue(user.getAttack() + "-" + user.getDefence());
        enemy_AD_info.setValue(tar.getAttack() + "-" + tar.getDefence());
    }

    public int damageInRange(int HP, int dmg){
        if (HP <= dmg) return 0;
        else return HP - dmg;
    }

}


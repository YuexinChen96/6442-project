import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;


public class Battle {

    private Enemy tar;
    Pokemon user;

    private Rectangle p_user;
    private Rectangle p_tar;

    public boolean button_able = true;

    public void setP_user(Rectangle p_user) {
        this.p_user = p_user;
    }

    public void setP_tar(Rectangle p_tar) {
        this.p_tar = p_tar;
    }


    public Battle(){} // constructor

    public Battle (Pokemon user, Enemy tar){
        this.tar = tar;
        this.user = user;
    }

    public boolean gameover_test (){return this.user.getHP() == 0;}

    public boolean win_test () { return this.tar.getHP() == 0;}


    // execute user action, 0 for attack, 1 for spell-1, 2 for spell-2, ...
    public void user_action (int action,StringProperty textInfo,StringProperty user_HP_info,StringProperty user_MP_info,
                             StringProperty enemy_HP_info, StringProperty enemy_MP_info,DoubleProperty user_HP_bar,
                             DoubleProperty user_MP_bar,DoubleProperty enemy_HP_bar,DoubleProperty enemy_MP_bar,
                             StringProperty user_AD_info,StringProperty enemy_AD_info, StringProperty user_LEVEL, Pane board, GUI gui){

        System.out.println("Execute action: " + action);

        Timeline t1 = null;
        Circle ball = new Circle();
        button_able = false;
        if (action == 0) {
            // animation may go here
            t1 = new Timeline(new KeyFrame(Duration.millis(1),ae->{
                PathTransition pt1 = action1_animation(board, true);
                pt1.play();
            }));

            int dmg = user.getAttack() - tar.getDefence();
            System.out.println("The damage is: " + dmg);
            tar.setHP(damageInRange(tar.getHP(), dmg));

        } else if (action == 1) {
            Spell my = new Spell();
            t1 = new Timeline(new KeyFrame(Duration.millis(1),ae->{
                PathTransition pt1 = my.execute(0,user,tar,true,board, ball);
                pt1.play();
            }));
        }

        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(1500),ae->{
            UIupdate(user_HP_info,user_MP_info,enemy_HP_info,enemy_MP_info,user_HP_bar,user_MP_bar,enemy_HP_bar
                    ,enemy_MP_bar,user_AD_info,enemy_AD_info, user_LEVEL);
            textInfo.setValue("Waiting for enemy's response...");
            board.getChildren().remove(ball);
            int ret = end_check(textInfo);
            if (ret != 0) {
                gui.page3_to_page2(ret == 1,this.user);
            }
        }));

        // enemy action
        Timeline t3 = new Timeline(new KeyFrame(Duration.millis(1),ae->{
            // Simple agent
            if (tar.getId() < 4) {
                PathTransition pt2 = action1_animation(board, false);
                pt2.play();
                int dmg = tar.getAttack() - user.getDefence();
                user.setHP(damageInRange(user.getHP(), dmg));
            }
            // advanced agent
        }));

        Timeline t4 = new Timeline(new KeyFrame(Duration.millis(1500),ae->{
            end_turn_cal();
            UIupdate(user_HP_info,user_MP_info,enemy_HP_info,enemy_MP_info,user_HP_bar,user_MP_bar,enemy_HP_bar
                    ,enemy_MP_bar,user_AD_info,enemy_AD_info, user_LEVEL);
            textInfo.setValue("Now is your turn... Choose one action.");
            //winorlose.set(end_check());
            //System.out.println(winorlose.get());
            button_able = true;
            int ret = end_check(textInfo);
            if (ret != 0) {
                gui.page3_to_page2(ret == 1,this.user);
            }
        }));

        SequentialTransition seqT = new SequentialTransition(t1, t2, t3, t4);
        seqT.play();


        // return end_check();
    }

    public void end_turn_cal(){
        user.setMP(mana_gain(user.getMP(), 10));
        if (tar.getId() > 3) tar.setMP(mana_gain(tar.getMP(), 10));
    }

    public int mana_gain(int curr, int gain){
        return Math.min(curr + gain, 100);
    }


    public PathTransition action1_animation(Pane board, boolean user) {
        p_user.toFront();
        Path path = new Path();
        PathTransition pt = new PathTransition();

        if (user) {
            p_user.toFront();
            path.getElements().add(new MoveTo(260,470));
            path.getElements().add(new LineTo(800,130));
            path.getElements().add(new LineTo(260,470));
            pt.setNode(p_user);
        } else {
            p_tar.toFront();
            path.getElements().add(new MoveTo(800,130));
            path.getElements().add(new LineTo(260,470));
            path.getElements().add(new LineTo(800,130));
            pt.setNode(p_tar);
        }


        pt.setDuration(Duration.millis(1500));
        pt.setPath(path);

        pt.setAutoReverse(true);
        pt.setCycleCount(1);
        return pt;
    }

    public void UIupdate(StringProperty user_HP_info,StringProperty user_MP_info,
                         StringProperty enemy_HP_info, StringProperty enemy_MP_info,DoubleProperty user_HP_bar,
                         DoubleProperty user_MP_bar,DoubleProperty enemy_HP_bar,DoubleProperty enemy_MP_bar,
                         StringProperty user_AD_info,StringProperty enemy_AD_info, StringProperty user_LEVEL){
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
        user_LEVEL.setValue("lvl " + user.getLevel());
    }

    // end check works -- need API from Natalie
    public int end_check(StringProperty textInfo){
        if (gameover_test()){
            System.out.println("Game Over.");
            return -1;
        } else if (win_test()){
            System.out.println("You have win this game.");
            winCal(textInfo);
            return 1;
        }
        return 0;
    }

    // gain EXP basic on enemy level
    public void winCal(StringProperty textInfo){
        System.out.println("Check here.");
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(1),ae-> textInfo.setValue("Enemy loses all HP. You win !!!")));
        // exp gain
        user.setExp(user.getExp() + tar.getEXP());
        // check level
        int new_level;
        // 1 - 10
        if (user.getLevel() == 40) new_level = 40;
        else if (user.getExp() <= 100) new_level = (user.getExp() / 10);
        else if (user.getExp() <= 600) new_level = ((user.getExp() - 100) / 50) + 10;
        else if (user.getExp() <= 5600) new_level = ((user.getExp() - 600) / 500) + 20;
        else new_level = ((user.getExp() - 5600) / 2000) + 30;
        if (new_level == user.getLevel()){
            System.out.println("Level up !!!");
            levelUpCal(user.getLevel(), new_level);
        }

    }

    public void levelUpCal(int from, int to){
        while (from != to) {
            levelCal(user.getid(), (from / 10), from % 5 == 0 && from != 0);
            from++;
        }
        user.setHP(user.getmaxHP());
    }

    public void levelCal(int id, int base, boolean lvl5){
        user.setAttack((int) (user.getAttack() + Math.pow(2, base)));
        if (lvl5){
            switch(user.getid()) {
                case 0:
                    user.setDefence(user.getDefence() + 3 * base);
                    user.setmaxHP(user.getmaxHP() + 25 * base);
            }
        }
    }


    public int damageInRange(int HP, int dmg){
        if (HP <= dmg) return 0;
        else return HP - dmg;
    }

}


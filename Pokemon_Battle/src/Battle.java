import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

//Author: Yuexin Chen
public class Battle {

    // Two players
    private Enemy tar;
    Pokemon user;
    // Check for level up
    private boolean levelup;

    // Two pointers point to the images from GUI
    private Rectangle p_user;
    private Rectangle p_tar;

    // Block duplicate click on buttons
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

    // check whether game over: user HP is 0
    public boolean gameover_test (){return this.user.getHP() == 0;}
    // check user win the game: enemy HP is 0
    public boolean win_test () { return this.tar.getHP() == 0;}


    // execute user action, 0 for attack, 1 for spell-1, 2 for spell-2, ...
    public void user_action (int action,StringProperty textInfo,StringProperty user_HP_info,StringProperty user_MP_info,
                             StringProperty enemy_HP_info, StringProperty enemy_MP_info,DoubleProperty user_HP_bar,
                             DoubleProperty user_MP_bar,DoubleProperty enemy_HP_bar,DoubleProperty enemy_MP_bar,
                             StringProperty user_AD_info,StringProperty enemy_AD_info, StringProperty user_LEVEL, Pane board, GUI gui){

        System.out.println("Execute action: " + action);
        System.out.println("User HP" + user.getHP());
        Timeline t1 = null;
        Circle ball = new Circle();
        Circle ball2 = new Circle();
        button_able = false;

        AtomicBoolean terminal_occur = new AtomicBoolean(false);
        // user's turn
        if (action == 0) {
            // animation may go here
            t1 = new Timeline(new KeyFrame(Duration.millis(1),ae->{
                PathTransition pt1 = action1_animation(board, true);
                pt1.play();
            }));
            // action settlement
            int dmg = user.getAttack() - tar.getDefence();
            System.out.println("The damage is: " + dmg);
            tar.setHP(damageInRange(tar.getHP(), dmg));
        // using Spell by Spell Class
        } else if (action == 1 || action == 2 || action == 3) {
            Spell my = new Spell();
            // Spell class return an animation with settlement
            t1 = new Timeline(new KeyFrame(Duration.millis(1), ae -> {
                PathTransition pt1 = my.execute(user.getSkillList()[action - 1], user, tar, true, board, ball);
                if (action == 3) user.setMP(user.getMP() - 80);
                else user.setMP(user.getMP() - 20);
                pt1.play();
            }));
        }
        // user's animation and GUI update
        Timeline t2 = new Timeline(new KeyFrame(Duration.millis(1500),ae->{
            // user mana recover
            if (action == 0) user.setMP(mana_gain(user.getMP(), 8));

            UIupdate(user_HP_info,user_MP_info,enemy_HP_info,enemy_MP_info,user_HP_bar,user_MP_bar,enemy_HP_bar
                    ,enemy_MP_bar,user_AD_info,enemy_AD_info, user_LEVEL);
            textInfo.setValue("Waiting for enemy's response...");
            board.getChildren().remove(ball);
            int ret = end_check();
            if (ret != 0) {
                textInfo.setValue("Enemy loses all HP. You win !!!");
                gui.page3_to_page2(ret == 1,this.user,levelup);
                terminal_occur.set(true);
            }
        }));
        // enemy's turn
        AtomicBoolean use_spell = new AtomicBoolean(false);
        // enemy action
        Timeline t3 = new Timeline(new KeyFrame(Duration.millis(1), ae -> {
            if (!terminal_occur.get()) {
                // Simple agent
                if (tar.getId() < 4) {
                    // attack
                    PathTransition pt2 = action1_animation(board, false);
                    pt2.play();
                    int dmg = tar.getAttack() - user.getDefence();
                    user.setHP(damageInRange(user.getHP(), dmg));
                } else {
                    if (tar.getMP() < 30) {
                        // attack
                        PathTransition pt2 = action1_animation(board, false);
                        pt2.play();
                        int dmg = tar.getAttack() - user.getDefence();
                        user.setHP(damageInRange(user.getHP(), dmg));
                    } else {
                        // enemy with spell
                        Spell enemy_spell = new Spell();
                        if (tar.getId() < 6) {
                            Random r = new Random();
                            int spell = r.nextInt(2);
                            System.out.println("Enemy use action: " + spell + " Spell number:" + tar.skill_list[spell]);
                            PathTransition pt2 = enemy_spell.execute(tar.skill_list[spell], user, tar, false, board, ball2);
                            use_spell.set(true);
                            tar.setMP(tar.getMP() - 30);
                            pt2.play();
                        } else {
                            // action selector for enemy 7 and enemy 8
                            PathTransition pt2;
                            // get action from advanced agent
                            int enemy_action = ai();
                            System.out.println("Enemy use action: " + enemy_action);
                            if (enemy_action == -1) {
                                // attack to collect mana
                                pt2 = action1_animation(board, false);
                                int dmg = tar.getAttack() - user.getDefence();
                                user.setHP(damageInRange(user.getHP(), dmg));
                            } else {
                                // use spell
                                use_spell.set(true);
                                pt2 = enemy_spell.execute(tar.skill_list[enemy_action], user, tar, false, board, ball2);
                                tar.setMP((enemy_action == 2) ? tar.getMP() - 50 : tar.getMP() - 30);
                            }
                            pt2.play();
                        }
                    }
                }
            }
        }));
        // enemy animation
        Timeline t4 = new Timeline(new KeyFrame(Duration.millis(1500), ae -> {
            if (!terminal_occur.get()) {
                // get mana for enmey
                if (tar.getId() > 3 && !use_spell.get()) tar.setMP(mana_gain(tar.getMP(), 10));

                UIupdate(user_HP_info, user_MP_info, enemy_HP_info, enemy_MP_info, user_HP_bar, user_MP_bar, enemy_HP_bar
                        , enemy_MP_bar, user_AD_info, enemy_AD_info, user_LEVEL);
                textInfo.setValue("Now is your turn... Choose one action.");
                board.getChildren().remove(ball2);
                // end turn check
                button_able = true;
                int ret = end_check();
                if (ret != 0) gui.page3_to_page2(ret == 1, this.user,levelup);
            }
        }));
        // play animation
        SequentialTransition seqT = new SequentialTransition(t1, t2, t3, t4);
        seqT.play();

    }

    // AI agent return with action (attack: -1, Spell1: 0, Spell2: 1, Spell3: 2)
    public int ai(){
        if (tar.getId() == 6) {
            // use spell 3
            if (tar.getMP() >= 50 && tar.getHP() > tar.getmaxHP() * 0.3) {
                return 2;
            }
            // check wait for spell 3
            else if (tar.getMP() == 40 && tar.getHP() > tar.getmaxHP() * 0.3 + (user.getAttack() - tar.getDefence())) {
                return -1;
            }
            // check wait for spell 3
            else if (tar.getMP() == 30 && tar.getHP() > tar.getmaxHP() * 0.3 + (user.getAttack() - tar.getDefence()) * 2) {
                return -1;
            }
            // better use spell 2 for increase damage for spell 1
            else if (tar.getAttack() < user.getAttack() && tar.getHP() > (user.getAttack() - tar.getDefence())) {
                return 1;
            } else return 0;
        } else {
            // use Spell 3
            if (tar.getHP() < tar.getmaxHP() * 0.3) return 0;
            else if (enemy8checkSpell3(0) && tar.getMP() == 50) return 2;
            else if (enemy8checkSpell3(1) && tar.getMP() == 40) return -1;
            else if (enemy8checkSpell3(2) && tar.getMP() == 30) return -1;
            else if (tar.getHP() > tar.getmaxHP() * 0.5) return 1;
            else return 0;
        }
    }

    // i stand for turns of mana gain, return whether optimal to use spell T or F.
    public boolean enemy8checkSpell3(int i) {
        // health needed
        double health = tar.getmaxHP() * 0.4;
        // turn remained
        int turn = (tar.getHP() / (user.getAttack() - tar.getDefence())) + 1;
        double damageGain = turn * tar.getAttack() * 0.5;
        System.out.println(health);
        System.out.println(damageGain);
        return damageGain > health + i * (user.getAttack() - tar.getDefence());
    }

    public int mana_gain(int curr, int gain){
        return Math.min(curr + gain, 100);
    }

    // attack animation, user: T for user's turn, F for enemy's turn
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

    // Update all UI infor: HP bar, MP bar, attack - defence, information box...
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

    // end check works: -1 for game over, 0 for not win, 1 for user win
    public int end_check(){
        if (gameover_test()){
            System.out.println("Game Over.");
            return -1;
        } else if (win_test()){
            System.out.println("You have win this game.");
            winCal();
            return 1;
        }
        return 0;
    }

    // gain EXP basic on enemy level, need to check level up.
    public void winCal(){
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
        if (new_level != user.getLevel()){
            System.out.println("Level up !!!");
            this.levelup=true;
            levelUpCal(user.getLevel(), new_level);
        }

    }

    // level up calculation, turn on ability based on level
    public void levelUpCal(int from, int to){
        while (from != to) {
            levelCal(from / 10, from % 5 == 4, from);
            from++;
        }
        user.setHP(user.getmaxHP());
        user.setLevel(to);
        System.out.println(user.getHP());
        if (user.getLevel() > 9) user.setGrassAble(true);
        if (user.getLevel() > 19) user.setWaterAble(true);
        if (user.getLevel() > 29) user.setStoneAble(true);
    }

    // at level 5 special gain for different pokemon.
    public void levelCal(int base, boolean lvl5, int from){
        // attack gain
        user.setAttack((int) (user.getAttack() + Math.pow(2, base)));
        // max HP gain
        if (from < 5) user.setmaxHP(user.getmaxHP() + 5);
        else {
            user.setmaxHP((int) (user.getmaxHP() + Math.pow(2,((from - 5) / 10) + 3)));
        }
        if (lvl5){
            switch(user.getid()) {
                case 0:
                    user.setDefence((int) (user.getDefence() + 1 * Math.pow(2,base)));
                    break;
                case 1:
                    user.setAttack((int) (user.getAttack() + 1 * Math.pow(2,base)));
                    user.setDefence((int) (user.getDefence() + 1 * Math.pow(2,base)));
                    break;
                case 2:
                    user.setDefence((int) (user.getDefence() + 2 * Math.pow(2,base)));
                    break;
                case 3:
                    user.setAttack((int) (user.getDefence() + 2 * Math.pow(2,base)));
                    user.setDefence((int) (user.getDefence() + 2 * Math.pow(2,base)));
                    break;
                case 4:
                    user.setDefence((int) (user.getDefence() + 1 * Math.pow(2,base)));
                    break;
                default:
                    break;
            }
        }
    }

    // block negative HP appear
    public int damageInRange(int HP, int dmg){
        if (dmg <= 0) dmg = 1;
        if (HP <= dmg) return 0;
        else return HP - dmg;
    }
    public Pokemon giveupBattle(){
        return this.user;
    }

}


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Battle {
    private int turn = 0;

    private Enemy tar;
    private Pokemon user;



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

        if (action == 0) {
            // animation may go here
            action1_animation(board);

            int dmg = user.getAttack() - tar.getDefence();
            System.out.println("The damage is: " + dmg);
            tar.setHP(damageInRange(tar.getHP(), dmg));

        }

        UIupdate(user_HP_info,user_MP_info,enemy_HP_info,enemy_MP_info,user_HP_bar,user_MP_bar,enemy_HP_bar
                ,enemy_MP_bar,user_AD_info,enemy_AD_info);

        this.turn ++;

        this.enemy_action(textInfo,user_HP_info,user_MP_info,enemy_HP_info,enemy_MP_info,user_HP_bar,user_MP_bar,enemy_HP_bar
                ,enemy_MP_bar,user_AD_info,enemy_AD_info);
    }

    public void enemy_action (StringProperty textInfo,StringProperty user_HP_info,StringProperty user_MP_info,
                              StringProperty enemy_HP_info,StringProperty enemy_MP_info,DoubleProperty user_HP_bar,
                              DoubleProperty user_MP_bar,DoubleProperty enemy_HP_bar,DoubleProperty enemy_MP_bar,
                              StringProperty user_AD_info,StringProperty enemy_AD_info) {
        System.out.println("Now for enemy turn.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textInfo.setValue("Waiting for enemy's response...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //textInfo.setValue("Now is your turn... Choose one action.");
        //this.turnInfo.setValue("Now is your turn, choose your action.");
        this.turn ++;

    }

    public void action1_animation(Pane board) {
        //Circle attackBall = new Circle()
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


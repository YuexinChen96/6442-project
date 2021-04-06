
import javafx.beans.property.StringProperty;

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
    public void user_action (int action, StringProperty info){
        System.out.println("Execute action: " + action);
        this.turn ++;
        info.setValue("Waiting for enemy response.");
        System.out.println(info.get());
        this.enemy_action(info);
    }

    public void enemy_action (StringProperty info) {
        System.out.println("Now for enemy turn.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.turn ++;
        info.setValue("Now is your turn.");
        System.out.println(info.get());
        System.out.println("Enemy turn finished.");

    }



}


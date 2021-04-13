import javafx.scene.layout.Pane;

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
    public void user_action (int action){
        System.out.println("Execute action: " + action);




        //this.turnInfo.setValue("Waiting for enemy's response.");

        this.turn ++;

        this.enemy_action();
    }

    public void enemy_action () {
        System.out.println("Now for enemy turn.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        //this.turnInfo.setValue("Now is your turn, choose your action.");
        this.turn ++;

    }



}


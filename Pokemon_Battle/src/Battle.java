
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

    public void user_turn (int action){

    }

    public void enemy_turn () {

    }



}


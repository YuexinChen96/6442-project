public class Map {

    //terminal
    public boolean ifTerminal(Pokemon user,char[][] map){
        int posx=user.getPosition()[0];
        int posy=user.getPosition()[1];
        if(map[posx][posy]=='t'){
            return true;
        }
        return false;
    }

    //battle
    public boolean ifBattle(Pokemon user,char[][] map){
        int posx=user.getPosition()[0];
        int posy=user.getPosition()[1];
        if(map[posx][posy]=='a') return true;
        return false;
    }

    //canMove
    public boolean checkMoveEnable(Pokemon user, char act ,char[][] map){
        int posx=user.getPosition()[0];
        int posy=user.getPosition()[1];
        //3. move: right  left  up  down
        int next_posx=posx;
        int next_posy=posy;
        if(act=='R') next_posx=posx+1;
        else if(act=='L') next_posx = posx - 1;
        else if(act=='U') next_posy=posy-1;
        else if(act=='D') next_posy=posy+1;
        if((next_posx<0 || next_posx>=40)||(next_posy<0||next_posy>=24))return false;
        char type=map[next_posx][next_posy];
        if(type=='r'|| type=='h'||
                type=='t'|| type=='a'||
                (type=='g'&&user.getGrassAble())||
                (type=='s'&& user.getStoneAble()) ||
                (type=='w'&& user.getWaterAble())){
            return true;
        }
        else return false;
    }

}

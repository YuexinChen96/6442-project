public class Map {

    //need to change to another map:
    public boolean ifMapEnd(Pokemon user,char[][] map){
        int posx=user.getPosition()[0];
        int posy=user.getPosition()[1];
        if(map[posx][posy]=='o' || map[posx][posy]=='i' || map[posx][posy]=='t'){
            return true;
        }
        return false;
    }

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
        if(map[posx][posy]>47 && map[posx][posy] < 56) return true;
        return false;
    }

    //canMove(user, action, currentmap)
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
        if((type > 47 && type < 56) ||
                type=='r'|| type=='h'||
                type=='t'|| type=='a'|| type=='i' || type=='o' || type=='d' || type=='m' ||
                (type=='g'&&user.getGrassAble())||
                (type=='b'&& user.getStoneAble()) ||
                (type=='w'&& user.getWaterAble())){
            return true;
        }
        else return false;
    }

    // position on 'o', change to next map
    public boolean nextMap(Pokemon user,char act, char[][] map, int currentMapIndex) {
        int posx=user.getPosition()[0];
        int posy=user.getPosition()[1];
        int mapxy[] = showMapToMap(posx,posy,currentMapIndex);
        int mapPosx = mapxy[0];
        int mapPosy = mapxy[1];
        int next_posx=mapPosx;
        int next_posy=mapPosy;
        if(act=='R') next_posx=mapPosx+1;
        else if(act=='L') next_posx = mapPosx - 1;
        else if(act=='U') next_posy=mapPosy-1;
        else if(act=='D') next_posy=mapPosy+1;
        if(next_posx < 0 || next_posy < 0 || next_posx > 79 || next_posy > 47) {
            return false;
        }
        if(map[mapPosx][mapPosy] == 'o' && map[next_posx][next_posy] == 'i') {
            return true;
        }
        else return false;
    }

    // position on 'i', change to last map
    public boolean lastMap(Pokemon user,char act, char[][] map, int currentMapIndex) {
        int posx=user.getPosition()[0];
        int posy=user.getPosition()[1];
        int mapxy[] = showMapToMap(posx,posy,currentMapIndex);
        int mapPosx = mapxy[0];
        int mapPosy = mapxy[1];
        int next_posx=mapPosx;
        int next_posy=mapPosy;
        if(act=='R') next_posx=mapPosx+1;
        else if(act=='L') next_posx = mapPosx - 1;
        else if(act=='U') next_posy=mapPosy-1;
        else if(act=='D') next_posy=mapPosy+1;
        if(next_posx < 0 || next_posy < 0 || next_posx > 79 || next_posy > 47) {
            return false;
        }
        if(map[mapPosx][mapPosy] == 'i' && map[next_posx][next_posy] == 'o') {
            return true;
        }
        else return false;
    }

    public int[] startPosition(int mapIndex) {
        switch(mapIndex) {
            case 0:return new int[]{1,0};
            case 1:return new int[]{38,0};
            case 2:return new int[]{0,22};
            case 3:return new int[]{38,23};
            default: return new int[]{0,0};
        }
    }

    public int[] endPosition(int mapIndex) {
        switch(mapIndex) {
            case 0:return new int[]{38,23};
            case 1:return new int[]{39,22};
            case 2:return new int[]{38,0};
            case 3:return new int[]{39,1};
            default: return new int[]{0,0};
        }
    }

    public int[] showMapToMap(int showMapx,int showMapy,int mapindex) {
        switch(mapindex) {
            case 1: showMapy = showMapy + 24;return new int[]{showMapx,showMapy};
            case 2: showMapx = showMapx + 40; showMapy = showMapy + 24;return new int[]{showMapx,showMapy};
            case 3: showMapx = showMapx + 40;return new int[]{showMapx,showMapy};
            default: return new int[]{showMapx,showMapy};
        }
    }


}

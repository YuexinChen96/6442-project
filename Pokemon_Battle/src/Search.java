import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import javax.sql.PooledConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class Search {

    char[][] map;
    char goaltype;
    Pokemon user;
    class SearchNode{
        Integer[] position;//state
        SearchNode parent;
        String action;
        int depth;
        public SearchNode(Integer[] position,String action,SearchNode parent,int depth){
            this.position=position;
            this.action=action;
            this.parent=parent;
            this.depth=depth;
        }
    }

    public List<String> search(Integer[] initial_state, char goaltype,char[][] map,Pokemon user) {
        this.map=map;
        this.goaltype=goaltype;
        this.user=user;
        initial_state=initial_state;
        SearchNode initial_node= new SearchNode(initial_state,null,null,0);


        if(testgoal(initial_state)) {
            System.out.println("qidian jiushi "); return null;}

        Queue<SearchNode> frontier = new LinkedList();
        frontier.add(initial_node);
        Set<Integer[]> explored = new HashSet<>();

        while(true){
            if(frontier.isEmpty()) {
                System.out.println("f empty");return null;}
            //strfrontier(frontier);
            //strexplored(explored);
            System.out.println("f:"+frontier.size()+", e:"+explored.size());
            SearchNode expand_node= frontier.poll();
            Integer[] expand_state=expand_node.position;
            explored.add(expand_state);


            for(Integer[] n:get_successors(expand_state)) {
                SearchNode child = new SearchNode(n, getAction(expand_state, n), expand_node, expand_node.depth + 1);
                if (!explored.contains(n) && !frontier.contains(child)) frontier.add(child);
            }
            if (testgoal(expand_state)){
                System.out.println("find:"+str(expand_node.position));
                return getsolution(expand_node);}
        }

    }
    public String str(Integer[] pos){
        return "("+pos[0]+","+pos[1]+")";
    }
    public void strfrontier(Queue<SearchNode> fron){
        String st="frontier:[";
        for(SearchNode sn:fron){
            st+=str(sn.position)+" ,";
        }
        System.out.println(st+"]");
    }
    public void strexplored(Set<Integer[]> ex){
        String st="explored:[";
        for(Integer[] sn:ex){
            st+=str(sn)+" ,";
        }
        System.out.println(st+"]");
    }
    public List<Integer[]> get_successors(Integer[] expand_state) {
        int x = expand_state[0], y = expand_state[1];
        List<Integer[]> successors = new ArrayList<>();

        //right
        if ((x + 1 >= 0 && x + 1 < 40) && (y >= 0 && y < 24)) {
            if (isAccessPos(map[x + 1][y])) successors.add(new Integer[]{x + 1, y});
        }
        if ((x - 1 >= 0 && x - 1 < 40) && (y >= 0 && y < 24)) {
            if (isAccessPos(map[x - 1][y])) successors.add(new Integer[]{x - 1, y});
        }
        if ((x>= 0 && x < 40) && (y-1 >= 0 && y-1 < 24)) {
            if (isAccessPos(map[x ][y-1])) successors.add(new Integer[]{x , y-1});
        }
        if ((x >= 0 && x < 40) && (y+1 >= 0 && y+1 < 24)) {
            if (isAccessPos(map[x ][y+1])) successors.add(new Integer[]{x , y+1});
        }
        return successors;
    }
    public boolean isAccessPos(char type){
        if ((type > 47 && type < 56) ||
                type == 'r' || type == 'h' ||
                type == 't' || type == 'a' || type == 'f' || type == 'i' || type == 'o' || type == 'd' || type == 'm' ||
                type == 'x' || type == 'y' || type == 'z' || type == 't' ||
                (type == 'g' && user.getGrassAble()) ||
                (type == 'b' && user.getStoneAble()) ||
                (type == 'w' && user.getWaterAble())) {
            return true;
        } else return false;
    }
    public String getAction(Integer[] currentPos,Integer[] nextpos){
        int cx=currentPos[0],cy=currentPos[1];
        int nx=nextpos[0],ny=nextpos[1];
        if(nx==cx+1&&ny==cy) return "Right";
        else if(nx==cx-1&&ny==cy) return "Left";
        else if(nx==cx&&ny==cy-1) return "Up";
        else if(nx==cx&&ny==cy+1) return "Down";
        else return "error";
    }
    public boolean testgoal(Integer[] pos){
        if(map[pos[0]][pos[1]]==goaltype) return true;
        else return false;
    }
    public List<String> getsolution(SearchNode goalnode){
        List<String> sol=new ArrayList<>();
        SearchNode node=goalnode;
        while(node.parent!=null){
            sol.add(node.action);
            node=node.parent;
        }
        List<String> reversesol=new ArrayList<>();
        for(int i=sol.size()-1;i>=0;i--){
            reversesol.add(sol.get(i));
        }
        return reversesol;
    }

    public static void main(String[] args) {
        char[][] showmap=new char[40][24];

        String partMap = "src/battleMap0.txt";
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


        //---------
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
        Pokemon user=pl.get(0);


        //------------

        Search s=new Search();
        System.out.println(s.search(new Integer[]{15,7},'h',showmap,user));




    }

}



import java.util.*;
//Author: Qixuan Ren
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
    //use bfs(breadth first search) algorithm to the find the shortest path to a nearby target.
    public List<String> bfs(Integer[] initial_state, char goaltype,char[][] map,Pokemon user) {
        this.map=map;
        this.goaltype=goaltype;
        this.user=user;
        SearchNode initial_node= new SearchNode(initial_state,null,null,0);

        if(testgoal(initial_state)) {
            System.out.println("you are at the goal!"); return null;}

        Queue<SearchNode> frontier = new LinkedList();
        frontier.add(initial_node);
        Set<Integer[]> explored = new HashSet<>();

        while(true){
            if(frontier.isEmpty()) {
                System.out.println("frontier empty, error!");return null;}
            //strfrontier(frontier); //strexplored(explored); //System.out.println("f:"+frontier.size()+", e:"+explored.size());
            if(frontier.size()>30000){
                System.out.println("There are no goal which is very close to you"); return null;
            }
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
    //according map and current position(expand state), find the surrounding positions(successors)
    public List<Integer[]> get_successors(Integer[] expand_state) {
        int x = expand_state[0], y = expand_state[1];
        List<Integer[]> successors = new ArrayList<>();
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
    //check if this type of the pos is an accessible position
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
    //get action(right, left, up, down) according current position and next position.
    public String getAction(Integer[] currentPos,Integer[] nextpos){
        int cx=currentPos[0],cy=currentPos[1];
        int nx=nextpos[0],ny=nextpos[1];
        if(nx==cx+1&&ny==cy) return "Right";
        else if(nx==cx-1&&ny==cy) return "Left";
        else if(nx==cx&&ny==cy-1) return "Up";
        else if(nx==cx&&ny==cy+1) return "Down";
        else return "error";
    }
    //test if this pos is the goal, means if this position' type is same as goal type
    public boolean testgoal(Integer[] pos){
        //System.out.println(map[pos[0]][pos[1]]);
        if(goaltype!='7') {
            if (map[pos[0]][pos[1]] == goaltype) return true;
            else return false;
        }
        else{
            char type=map[pos[0]][pos[1]];
            if (type>47&&type<56) return true;
            else return false;
        }
    }
    //get solution(Order of movement)
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
        System.out.println(reversesol);
        return reversesol;
    }


    //format position(used for debug)
    public String str(Integer[] pos){
        return "("+pos[0]+","+pos[1]+")";
    }
    //print out frontier(used for debug)
    public void strfrontier(Queue<SearchNode> fron){
        String st="frontier:[";
        for(SearchNode sn:fron){
            st+=str(sn.position)+" ,";
        }
        System.out.println(st+"]");
    }
    //print out explored(used for debug)
    public void strexplored(Set<Integer[]> ex){
        String st="explored:[";
        for(Integer[] sn:ex){
            st+=str(sn)+" ,";
        }
        System.out.println(st+"]");
    }

}



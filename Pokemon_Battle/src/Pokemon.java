
import java.io.Serializable;

public class Pokemon implements Serializable {
    private String name;

    private int id;
    private int level;
    private int HP;
    private int MP;
    private int defence;
    private int attack;
    private int exp;
    private boolean grass_able;
    private boolean water_able;
    private boolean stone_able;


    private int max_HP;
    final private int MAX_MP = 100;

    private String imgUrl;
    private int[] position; //[x,y]

    private int[] skill_list;//contain 3 skill types

    public Pokemon(int id){
        this.id=id;
    }
    public Pokemon(String name, int id, int level, int HP, int MP, int defence,
                int attack, int exp,boolean grass_able,boolean water_able, boolean stone_able,int max_HP,
                   String imgUrl,int[] position,int[] skill_list) {
        this.name = name;
        this.id = id;

        this.level = level;
        this.HP = HP;
        this.MP = MP;
        this.defence = defence;
        this.attack = attack;
        this.exp=exp;
        this.grass_able=grass_able;
        this.water_able = water_able;
        this.stone_able = stone_able;

        this.max_HP=max_HP;

        this.imgUrl=imgUrl;
        this.position=position;

        this.skill_list=skill_list;
    }

    //---------------------------
    public int[] getPosition() {return position; }
    public void setPosition(int[] position) { this.position = position; }

    public void setImgUrl(String imgUrl){this.imgUrl=imgUrl;}
    public String getImgUrl() { return imgUrl; }

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public int getid(){return id;}
    public void setid(int id){this.id=id;}

    public int getLevel(){return level;}
    public void setLevel(int level){this.level=level;}


    public int getHP(){return HP;}
    public void setHP(int newHP){this.HP=newHP;}
    public int getmaxHP(){return max_HP;}
    public void setmaxHP(int max_HP){this.max_HP=max_HP;}

    public int getMP(){return MP;}
    public void setMP(int newMP){this.MP=newMP;}
    public int getMaxMP(){return MAX_MP;}

    public int getDefence(){return defence;}
    public void setDefence(int defence){this.defence=defence;}
    public int getAttack(){return attack;}
    public void setAttack(int attack){this.attack=attack;}

    public int getExp(){return exp;}
    public void setExp(int exp){this.exp=exp;}

    public boolean getGrassAble(){return grass_able;}
    public void setGrassAble(boolean grass_able){this.grass_able=grass_able;}
    public boolean getStoneAble(){return stone_able;}
    public void setStoneAble(boolean stone_able){this.stone_able=stone_able;}
    public boolean getWaterAble(){return water_able;}
    public void setWaterAble(boolean water_able){this.water_able=water_able;}

    public int[] getSkillList(){return skill_list;}
    public void setSkillList(int[] skill_list){this.skill_list=skill_list;}


    //===========================================================================
    //skill_id: 1-spell1, 2-spell2, 3-spell3 (index of skill_list)
    public void skillFromId(int skill_id,Enemy enemy){
        int sk_type=skill_list[skill_id-1];
        //many skill_type
        if(sk_type==1){
            enemy.setHP(enemy.getHP()-this.getAttack()+enemy.getDefence());
        } else if(sk_type==2){
            this.setHP(this.getHP()-50);
            this.setAttack(this.getAttack()+100);
        }else if(sk_type==3){
            this.setMP(this.getMaxMP());
        }else if(sk_type==4){

        }else if(sk_type==5){

        }else if(sk_type==6){

        }else if(sk_type==7){

        }else if(sk_type==8){

        }else if(sk_type==9){

        }else if(sk_type==10){

        }
    }
    //--------------------------------------------------------
    public String strPos() {
        if(position==null) return "Position: null";
        return "Position:(" + this.position[0] + "," + this.position[1] + ")";
    }
    public String strSkillList(){
        if(skill_list==null) return "Skill_list: null";
        return "Skill_list:[" + this.skill_list[0] + "," + this.skill_list[1]+ ","  + this.skill_list[2]+ "]";
    }
    @Override
    public String toString() {
        return "id:" + this.id + ", name:" + this.name +  ", level:" + this.level + ", HP:" + this.HP +"/"+this.max_HP+", MP:" + this.MP +"/"+this.MAX_MP
                + ", defense:" + this.defence + ", attack:" + this.attack +", experience:"+this.exp+", water_able:" + this.water_able + ", stone_able:" + this.stone_able
                + "\n" + this.strPos()+", image:"+this.imgUrl+", "+strSkillList();
    }

}

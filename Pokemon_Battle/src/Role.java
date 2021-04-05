import java.io.Serializable;

public abstract class Role implements Serializable {
    private String name;

    private int id;
    private int level;
    private int HP;
    private int MP;
    private int defense;
    private int attack;
    private boolean grass_able;
    private boolean water_able;
    private boolean stone_able;


    private int max_HP;
    final private int MAX_MP = 10;

    private String imgUrl;
    private int[] position; //[x,y]
    final private int[] startPostion={1,0};

    public abstract void skill1(Role role);
    public abstract void skill2(Role role);
    public abstract void skill3();


    public Role(String name, int id, int level, int HP, int MP, int defense,
                   int attack, boolean grass_able,boolean water_able, boolean stone_able,int max_HP,String imgUrl,int[] position) {
        this.name = name;
        this.id = id;

        this.level = level;
        this.HP = HP;
        this.MP = MP;
        this.defense = defense;
        this.attack = attack;
        this.grass_able=grass_able;
        this.water_able = water_able;
        this.stone_able = stone_able;

        this.max_HP=max_HP;

        this.imgUrl=imgUrl;
        this.position=position;
    }

    //---------------------------
    public int[] getStartPostion(){return startPostion;}
    public int[] getPosition() {return position; }
    public void setPosition(int[] position) { this.position = position; }

    public void setImgUrl(String imgUrl){this.imgUrl=imgUrl;}
    public String getImgUrl() { return imgUrl; }

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public int getid(){return id;}

    public int getLevel(){return level;}
    public void setLevel(int level){this.level=level;}


    public int getHP(){return HP;}
    public void setHP(int newHP){this.HP=newHP;}
    public int getmaxHP(){return max_HP;}
    public void setmaxHP(int max_HP){this.max_HP=max_HP;}

    public int getMP(){return MP;}
    public void setMP(int newMP){this.MP=newMP;}
    public int getMaxMP(){return MAX_MP;}

    public int getDefense(){return defense;}
    public void setDefense(int defense){this.defense=defense;}
    public int getAttack(){return attack;}
    public void setAttack(int attack){this.attack=attack;}

    public boolean getGrassAble(){return grass_able;}
    public void setGrassAble(boolean grass_able){this.grass_able=grass_able;}
    public boolean getStoneAble(){return stone_able;}
    public void setStoneAble(boolean stone_able){this.stone_able=stone_able;}
    public boolean getWaterAble(){return water_able;}
    public void setWaterAble(boolean water_able){this.water_able=water_able;}




    //--------------------------------------------------------
    public String strPos() {
        if(position==null) return "Position: null";
        return "Position:(" + this.position[0] + "," + this.position[1] + ")";
    }
    @Override
    public String toString() {
        return "id:" + this.id + ", name:" + this.name +  ", level:" + this.level + ", HP:" + this.HP + ", MP:" + this.MP
                + ", defense:" + this.defense + ", attack:" + this.attack + ", water_able:" + this.water_able + ", stone_able:" + this.stone_able
                + "\n" + this.strPos()+", image:"+this.imgUrl;
    }
}

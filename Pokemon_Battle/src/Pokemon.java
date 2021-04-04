

public class Pokemon {

    // Create a Attribute class in order to deliver role info more conveniently
    // contain all attributes of a role
    public class RoleAttr {
        private String name;
        private int id;

        private int level;
        private int HP;
        private int MP;
        private int defense;
        private int attack;
        private boolean water_able;
        private boolean stone_able;

        private int max_HP;
        final private int MAX_MP = 100;

        RoleAttr(String name, int id, int level, int HP, int MP, int defense, int attack, boolean water_able,
                 boolean stone_able,int max_HP) {
            this.name = name;
            this.id = id;
            this.level = level;
            this.HP = HP;
            this.MP = MP;
            this.defense = defense;
            this.attack = attack;
            this.water_able = water_able;
            this.stone_able = stone_able;
            this.max_HP=max_HP;
        }
        @Override
        public String toString(){
            String str="name:"+this.name+", id:"+this.id+", level:"+this.level+"\n" +
                    "HP:"+this.HP+"/"+this.max_HP+", MP:"+this.MP+"/"+this.MAX_MP
                    +", defense:"+this.defense+", attack:"+this.attack +", water_able:"+this.water_able+", stone_able:"+this.stone_able;
            return str;
        }
    }


    private RoleAttr attr;
    private String image;
    private int[] position; //[x,y]
    final private int[] startposition=new int[]{2,2};

    //Initial roles' attributes
    public void initialroleAttr(int id) {
        System.out.println("initial");
        if (id == 0) {
            // name, id, level, HP, MP, defense, attack, water_able, stone_able, max_HP
            this.attr = new RoleAttr("Pikachu", id, 2, 70, 100, 5 ,5, false, false, 70);
            this.image = "Pics/pic" + id + ".jpg";
            this.position=startposition;
        } else if (id == 1) {

        } else if (id == 2) {

        } else if (id == 3) {

        }

    }

    //-----------------调用--修改------------
    public int[] getPosition() {
        return position;
    }
    public void setPosition(int[] position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public String getname(){return attr.name;}
    public int getid(){return attr.id;}
    public int getlevel(){return attr.level;}

    public int getHP(){return attr.HP;}
    public void setHP(int newHP){this.attr.HP=newHP;}
    public int getMaxHP(){return attr.max_HP;}

    public int getMP(){return attr.MP;}
    public void setMP(int newMP){this.attr.MP=newMP;}
    public int getMaxMP(){return attr.MAX_MP;}

    public int getDefense(){return attr.defense;}
    public int getAttack(){return attr.attack;}

    public boolean getStoneAble(){return attr.stone_able;}
    public boolean getWaterAble(){return attr.water_able;}



    public String strPos(){
        if(this.position!=null&&position.length!=0)return "Position: ("+this.position[0]+","+this.position[1]+")";
        else return "Position: None";
    }

    @Override
    public String toString(){
        return this.attr.toString()+", "+this.strPos();
    }

}



public class Pokemon {

    // Create a Attribute class in order to deliver role info more conveniently
    // contain all attributes of a role
    public class RoleAttr {
        private String name;
        private int id;

        private int level;
        private double HP;
        private int defense;
        private int attack;

        private Skill skill;

        RoleAttr(String name, int id, int level, double HP, int defense, int attack, Skill skill) {
            this.name = name;
            this.id = id;
            this.level = level;
            this.HP = HP;
            this.defense = defense;
            this.attack = attack;
            this.skill = skill;
        }
    }


    private RoleAttr attr;
    private String image;
    private int[] position; //[x,y]

    //Initial roles' attributes
    public void initialroleAttr(int id) {
        if (id == 0) {
            this.attr = new RoleAttr("Pikachu", id, 2, 50, 60, 8, Skill.STONE);
            this.image= "Pics/pic"+id+".jpg";
        } else if (id == 1) {

        } else if (id == 2) {

        }
        else if(id==3){

        }
        //...
    }
    public RoleAttr getRoleAllAttr(){
        return attr;
    }

    public int[] getPosition(){
        return position;
    }
    public void setPosition(int[] position) {
        this.position = position;
    }

    public String getImage(){
        return image;
    }



    //update roles' HP(after battling with enemy)
    public void updateRoleHP(double newHP) {
        this.attr.HP = newHP;
    }






    public enum Skill {
        // The skill means that the role can cross special terrain
        STONE("stone"), WATER("water"), FIRE("fire");
        final private String landfrom;

        Skill(String landform) {
            this.landfrom = landform;
        }

        public static Skill getSkillfromString(String land) {
            if (land == "stone") {
                return Skill.STONE;
            } else if (land == "water") {
                return Skill.WATER;
            } else {
                return Skill.FIRE;
            }
        }
    }


}

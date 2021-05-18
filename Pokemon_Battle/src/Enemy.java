import java.io.Serializable;
import java.util.EmptyStackException;
//Author: Yuexin Chen
public class Enemy implements Serializable {

    private int id;
    private String name;
    private int attack;
    private int defence;
    private int HP;
    private int MP;

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    private int EXP;
    public int[] skill_list = new int[3];

    public String getImgUrl() {
        return imgUrl;
    }

    //public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    private int maxHP;
    private String imgUrl;

    public int getmaxHP() {
        return maxHP;
    }

    public void setmaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    final private int maxMP = 100;

    public Enemy(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enemy(int id, String name, int attack, int defence, int HP, int MP, int[]skill_list){
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.HP = HP;
        this.MP = MP;
        this.skill_list = skill_list;
    }

}

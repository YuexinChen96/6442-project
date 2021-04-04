import java.io.Serializable;
import java.util.EmptyStackException;

public class Enemy implements Serializable {

    private int id;
    private String name;
    private int attack;
    private int defence;
    private int HP;
    private int MP;
    private int maxHP;
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

    public Enemy(int id, String name, int attack, int defence, int HP, int MP){
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.HP = HP;
        this.MP = MP;
        this.maxHP = HP;
    }


}

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testEnemy {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    Enemy e0 = GUI.enemy_loading(0);
    Enemy e1 = GUI.enemy_loading(1);
    Enemy e2 = GUI.enemy_loading(2);
    Enemy e3 = GUI.enemy_loading(3);
    Enemy e4 = GUI.enemy_loading(4);
    Enemy e5 = GUI.enemy_loading(5);
    Enemy e6 = GUI.enemy_loading(6);
    Enemy e7 = GUI.enemy_loading(7);

    @Test
    public void testEnemyInfo() {
        assertEquals(e0.getName(),"Enemy1");
        assertEquals(e1.getAttack(),8);
        assertEquals(e2.getHP(),60);
        assertEquals(e3.getEXP(),40);
        assertEquals(e4.getMP(),0);
        assertEquals(e5.getId(),5);
        assertEquals(e6.getDefence(),25);
        assertEquals(e7.getmaxHP(),928);
        assertEquals(e7.getImgUrl(),null);
        e0.setHP(0);
        e0.setMP(0);
        e0.setAttack(0);
        e0.setDefence(0);
        e0.setEXP(0);
        e0.setId(0);
        e0.setmaxHP(0);
        e0.setName("Bob");
        assertEquals(e0.getName(),"Bob");
        assertEquals(e0.getAttack(),0);
        assertEquals(e0.getHP(),0);
        assertEquals(e0.getEXP(),0);
        assertEquals(e0.getMP(),0);
        assertEquals(e0.getId(),0);
        assertEquals(e0.getDefence(),0);
        assertEquals(e0.getmaxHP(),0);

        Enemy e=new Enemy(1,"Lisa",1,2,3,4,new int[]{0,1,2});
        assertEquals(e.getHP(),3);

    }

}

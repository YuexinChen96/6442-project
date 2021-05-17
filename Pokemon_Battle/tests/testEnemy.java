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
    }

}

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

public class testBattle {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    Pokemon p0 = GUI.pokemonLoadFromJson(0);
    Enemy e0 = GUI.enemy_loading(0);
    Battle b1 = new Battle(p0, e0);

    @Test
    public void testEnemyInfo() {
        assertEquals(e0.getName(),"Enemy1");

    }

}

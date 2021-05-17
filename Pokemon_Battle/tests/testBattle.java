import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testBattle {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    Pokemon p0 = GUI.pokemonLoadFromJson(0);
    Enemy e6 = GUI.enemy_loading(6);
    Battle b1 = new Battle(p0, e6);

    @Test
    public void testBattleResults() {
        p0.setHP(0);
        assertTrue(b1.gameover_test());
        p0.setHP(30);
        e6.setHP(0);
        assertTrue(b1.win_test());

    }

    @Test
    public void testBAI() {


    }

}

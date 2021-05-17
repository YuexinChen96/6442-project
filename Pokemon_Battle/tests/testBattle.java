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
    public void testAI() {
        p0.setAttack(150);
        e6.setMP(80);
        e6.setHP(500);
        assertEquals(b1.ai(),2);
        e6.setMP(40);
        assertEquals(b1.ai(),-1);
        e6.setMP(30);
        assertEquals(b1.ai(),-1);
        e6.setMP(10);
        assertEquals(b1.ai(),1);
        e6.setHP(100);
        assertEquals(b1.ai(),0);
    }

    @Test
    public void testAbility() {
        p0.setLevel(9);
        b1.levelUpCal(p0.getLevel(),p0.getLevel()+1);
        assertTrue(p0.getGrassAble());
        p0.setLevel(19);
        b1.levelUpCal(p0.getLevel(),p0.getLevel()+1);
        assertTrue(p0.getWaterAble());
        p0.setLevel(29);
        b1.levelUpCal(p0.getLevel(),p0.getLevel()+1);
        assertTrue(p0.getStoneAble());
    }

    @Test
    public void testCal() {
        p0.setLevel(5);

    }

}

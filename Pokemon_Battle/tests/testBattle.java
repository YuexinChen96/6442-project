import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testBattle {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    Pokemon p0 = GUI.pokemonLoadFromJson(0);
    Pokemon p1 = GUI.pokemonLoadFromJson(1);
    Pokemon p2 = GUI.pokemonLoadFromJson(2);
    Pokemon p3 = GUI.pokemonLoadFromJson(3);
    Pokemon p4 = GUI.pokemonLoadFromJson(4);
    Enemy e0 = GUI.enemy_loading(0);
    Enemy e6 = GUI.enemy_loading(6);
    Battle b0 = new Battle(p0, e6);
    Battle b1 = new Battle(p1, e0);
    Battle b2 = new Battle(p2, e0);
    Battle b3 = new Battle(p3, e0);
    Battle b4 = new Battle(p4, e0);

    @Test
    public void testBattleResults() {
        p0.setHP(0);
        assertTrue(b0.gameover_test());
        p0.setHP(30);
        e6.setHP(0);
        assertTrue(b0.win_test());

    }

    @Test
    public void testAI() {
        p0.setAttack(150);
        e6.setMP(80);
        e6.setHP(500);
        assertEquals(b0.ai(),2);
        e6.setMP(40);
        assertEquals(b0.ai(),-1);
        e6.setMP(30);
        assertEquals(b0.ai(),-1);
        e6.setMP(10);
        assertEquals(b0.ai(),1);
        e6.setHP(100);
        assertEquals(b0.ai(),0);

        e0.setHP(100);
        assertEquals(b1.ai(),1);
    }

    @Test
    public void testAbility() {
        p0.setLevel(9);
        b0.levelUpCal(p0.getLevel(),p0.getLevel()+1);
        assertTrue(p0.getGrassAble());
        p0.setLevel(19);
        b0.levelUpCal(p0.getLevel(),p0.getLevel()+1);
        assertTrue(p0.getWaterAble());
        p0.setLevel(29);
        b0.levelUpCal(p0.getLevel(),p0.getLevel()+1);
        assertTrue(p0.getStoneAble());
    }

    @Test
    public void testCal() {
        p0.setLevel(5);
        int DBefore0 = p0.getDefence();
        b0.levelCal(0,true,5);
        assertEquals(DBefore0+1,p0.getDefence());

        p1.setLevel(5);
        int DBefore1 = p1.getDefence();
        int ABefore1 = p1.getAttack();
        b1.levelCal(0,true,5);
        assertEquals(ABefore1+2,p1.getAttack());
        assertEquals(DBefore1+1,p1.getDefence());

        p2.setLevel(5);
        int DBefore2 = p2.getDefence();
        b2.levelCal(0,true,5);
        assertEquals(DBefore2+2,p2.getDefence());

        p3.setLevel(5);
        int DBefore3 = p3.getDefence();
        b3.levelCal(0,true,5);
        assertEquals(DBefore3+2,p3.getAttack());
        assertEquals(DBefore3+2,p3.getDefence());

        p4.setLevel(5);
        int DBefore4 = p4.getDefence();
        b4.levelCal(0,true,5);
        assertEquals(DBefore4+1,p4.getDefence());


    }

}

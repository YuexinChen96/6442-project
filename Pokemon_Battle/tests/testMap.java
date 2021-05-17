import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class testMap {

    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    Map map = new Map();
    Pokemon p = GUI.pokemonLoadFromJson(0);
    char[][] testMap = new char[5][5];

//    testMap:
//    i 0 1 2 3
//    r 4 5 6 7
//    g w b h m
//    a f s o t
//    x y z i r
    public void initialMap() {
        String m = "tests/testMap.txt";
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(m));
            String l;
            int row = 0;
            while ((l = bfr.readLine()) != null) {
                for (int i = 0; i < l.length(); i++) {
                    testMap[i][row] = l.charAt(i);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMapEnd() {
        initialMap();
        p.setPosition(new int[] {0,0});
        assertTrue(map.ifMapEnd(p,testMap));
        p.setPosition(new int[] {3,3});
        assertTrue(map.ifMapEnd(p,testMap));
        p.setPosition(new int[] {4,3});
        assertTrue(map.ifMapEnd(p,testMap));
        assertTrue(map.ifTerminal(p,testMap));
    }

    @Test
    public void testIfBattle() {
        initialMap();
        for(int i=1; i<5; i++) {
            for(int j=0; j<2; j++) {
                p.setPosition(new int[]{i,j});
                assertTrue(map.ifBattle(p,testMap));
            }
        }
    }

    @Test
    public void testMove() {
        initialMap();
        p.setPosition(new int[] {0,0});
        assertTrue(map.checkMoveEnable(p,'D',testMap));
        assertFalse(map.checkMoveEnable(p,'L',testMap));
        p.setPosition(new int[] {0,2});
        assertFalse(map.checkMoveEnable(p,'R',testMap));
        p.setWaterAble(true);
        assertTrue(map.checkMoveEnable(p,'R',testMap));
    }

    @Test
    public void testMapSwitch() {
        initialMap();
        p.setPosition(new int[] {3,3});
        assertTrue(map.nextMap(p,'D',testMap,0));
        assertFalse(map.nextMap(p,'R',testMap,0));
        p.setPosition(new int[] {3,4});
        assertTrue(map.lastMap(p,'U',testMap,0));
        assertFalse(map.lastMap(p,'R',testMap,0));
    }

    @Test
    public void testPos() {
        assertEquals(map.startPosition(0)[0],1);
        assertEquals(map.endPosition(3)[1],1);
    }

}

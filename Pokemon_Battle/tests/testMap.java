import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class testMap {

    @Rule
    public Timeout globalTimeout = Timeout.millis(5000);

    Map map = new Map();
    Pokemon p = GUI.pokemonLoadFromJson(0);
    char[][] testMap1 = new char[5][5];
    char[][] testMap2 = new char[24][40];

//    testMap1:
//    i 0 1 2 3
//    r 4 5 6 7
//    g w b h m
//    a f s o t
//    x y z i r

//    testMap2:
//    rrs1smg1bbh
//    rrs1shg1hb2
//    srrrrrrrrrh
//    rrsh12s0ssm
    public void initialMap(int index) {
        String m = "tests/testMap"+index+".txt";
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(m));
            String l;
            int row = 0;
            while ((l = bfr.readLine()) != null) {
                for (int i = 0; i < l.length(); i++) {
                    if(index == 1) {
                        testMap1[i][row] = l.charAt(i);
                    }
                    else {
                        testMap2[i][row] = l.charAt(i);
                    }
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMapEnd() {
        initialMap(1);
        p.setPosition(new int[] {0,0});
        assertTrue(map.ifMapEnd(p,testMap1));
        p.setPosition(new int[] {3,3});
        assertTrue(map.ifMapEnd(p,testMap1));
        p.setPosition(new int[] {4,3});
        assertTrue(map.ifMapEnd(p,testMap1));
        assertTrue(map.ifTerminal(p,testMap1));
        p.setPosition(new int[] {1,3});
        assertFalse(map.ifMapEnd(p,testMap1));
        assertFalse(map.ifTerminal(p,testMap1));
    }

    @Test
    public void testIfBattle() {
        initialMap(1);
        for(int i=1; i<5; i++) {
            for(int j=0; j<2; j++) {
                p.setPosition(new int[]{i,j});
                assertTrue(map.ifBattle(p,testMap1));
            }
        }

        p.setPosition(new int[]{4,4});
        assertFalse(map.ifBattle(p,testMap1));
    }

    @Test
    public void testMove() {
        initialMap(1);
        p.setPosition(new int[] {0,0});
        assertTrue(map.checkMoveEnable(p,'D',testMap1));
        assertFalse(map.checkMoveEnable(p,'L',testMap1));
        p.setPosition(new int[] {0,2});
        assertFalse(map.checkMoveEnable(p,'R',testMap1));
        p.setWaterAble(true);
        assertTrue(map.checkMoveEnable(p,'R',testMap1));
    }

    @Test
    public void testMapSwitch() {
        initialMap(1);
        p.setPosition(new int[] {3,3});
        assertTrue(map.nextMap(p,'D',testMap1,0));
        assertFalse(map.nextMap(p,'R',testMap1,0));
        p.setPosition(new int[] {3,4});
        assertTrue(map.lastMap(p,'U',testMap1,0));
        assertFalse(map.lastMap(p,'R',testMap1,0));
        p.setPosition(new int[] {4,4});
        assertFalse(map.lastMap(p,'D',testMap1,0));
        assertFalse(map.lastMap(p,'R',testMap1,0));
    }

    @Test
    public void testPos() {
        assertEquals(map.startPosition(0)[0],1);
        assertEquals(map.startPosition(1)[1],0);
        assertEquals(map.startPosition(2)[1],22);
        assertEquals(map.startPosition(3)[1],23);
        assertEquals(map.startPosition(9)[1],0);

        assertEquals(map.endPosition(0)[0],38);
        assertEquals(map.endPosition(1)[0],39);
        assertEquals(map.endPosition(2)[1],0);
        assertEquals(map.endPosition(3)[1],1);
        assertEquals(map.endPosition(5)[0],0);
    }

}

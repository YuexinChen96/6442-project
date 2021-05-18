import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class testSearch {

    Map map = new Map();
    Pokemon p = GUI.pokemonLoadFromJson(0);
    char[][] testMap3 = new char[40][24];
    char[][] testMap2=new char[40][24];
    //    testMap1:
//    i 0 1 2 3
//    r 4 5 6 7
//    g w b h m
//    a f s o t
//    x y z i r
    public void initialMap(int index) {
        String m = "tests/testMap"+index+".txt";
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(m));
            String l;
            int row = 0;
            while ((l = bfr.readLine()) != null) {
                for (int i = 0; i < l.length(); i++) {
                    if(index == 3) {
                        testMap3[i][row] = l.charAt(i);
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
    public void testSearch() {
        initialMap(2);
        initialMap(3);
        Search s = new Search();

        List<String> rst5 = s.bfs(new Integer[]{3,4},'7',testMap3,p);
        assertEquals(rst5.get(0),"Down");
        assertEquals(rst5.get(6),"Up");
        assertEquals(rst5.size(),9);

        List<String> rst1 = s.bfs(new Integer[]{0,0},'h',testMap2,p);
        assertEquals(rst1.get(0),"Right");
        List<String> rst2 = s.bfs(new Integer[]{0,0},'m',testMap2,p);
        assertEquals(rst2.get(8),"Up");
        List<String> rst3 = s.bfs(new Integer[]{0,0},'x',testMap2,p);
        assertNull(rst3);
        List<String> rst4 = s.bfs(new Integer[]{3,0},'1',testMap2,p);
        assertNull(rst4);

    }

}

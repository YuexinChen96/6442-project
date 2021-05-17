import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertTrue;

public class testEnemy {

    Enemy enemy = new Enemy();

    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);
    private void doTest() {
        assertTrue("1",true);
    }

    @Test
    public void test1() {

    }

}

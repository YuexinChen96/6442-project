import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testPokemon {

    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    Pokemon p0 = GUI.pokemonLoadFromJson(0);
    Pokemon p1 = GUI.pokemonLoadFromJson(1);
    Pokemon p2 = GUI.pokemonLoadFromJson(2);
    Pokemon p3 = GUI.pokemonLoadFromJson(3);
    Pokemon p4 = GUI.pokemonLoadFromJson(4);

    @Test
    public void testPokemonInfo() {
        assertEquals(p0.getName(),"Pokemon1");
        assertEquals(p1.getmaxHP(),27);
        assertEquals(p2.getAttack(),3);
        assertEquals(p3.getHP(),24);
        assertEquals(p4.getMP(),0);
        assertEquals(p0.getDefence(),0);
        assertEquals(p1.getLevel(),1);
        assertEquals(p2.getExp(),10);
        assertEquals(p3.getMaxMP(),100);
        assertEquals((p4.getGrassAble() || p4.getWaterAble() || p4.getStoneAble()),false);
        assertEquals(p0.toString(),"id:0, name:Pokemon1, level:1, HP:30/30, MP:0/100, defense:0, attack:4, experience:10, water_able:false, stone_able:false\n" +
                "Position: null, image:null, Skill_list:[0,1,10]");
        assertEquals(p1.toString(),"id:1, name:Pokemon2, level:1, HP:27/27, MP:0/100, defense:0, attack:5, experience:10, water_able:false, stone_able:false\n" +
                "Position: null, image:null, Skill_list:[2,3,11]");
        assertEquals(p2.toString(),"id:2, name:Pokemon3, level:1, HP:34/34, MP:0/100, defense:1, attack:3, experience:10, water_able:false, stone_able:false\n" +
                "Position: null, image:null, Skill_list:[4,5,12]");
        assertEquals(p3.toString(),"id:3, name:Pokemon4, level:1, HP:24/24, MP:0/100, defense:0, attack:3, experience:10, water_able:false, stone_able:false\n" +
                "Position: null, image:null, Skill_list:[6,7,13]");
        assertEquals(p4.toString(),"id:4, name:Pokemon5, level:1, HP:30/30, MP:0/100, defense:0, attack:4, experience:10, water_able:false, stone_able:false\n" +
                "Position: null, image:null, Skill_list:[8,9,14]");

        Pokemon p=new Pokemon("Tom",1,3,4,5,3,4,5,true,false,false,90,"dsdf",new int[]{2,1},new int[]{1,2,3});
        p.setPosition(new int[]{2,4});
        p.setAttack(13);
        p.setGrassAble(false);
        p.setHP(3);
        //assertEquals(p.getPosition(),new int[]{2,4});
        assertEquals(p.getHP(),3);
        assertEquals(p.getGrassAble(),false);
        assertEquals(p.getAttack(),13);


        Pokemon pp=new Pokemon(3);
        assertEquals(pp.getid(),3);

    }

}

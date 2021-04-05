import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class Pokemon extends Role{

    public Pokemon loadjson(int id){
        this.setPosition(super.getStartPostion());
        Gson gson = new Gson();
        JsonReader jsonReader = null;
        final Type CUS_LIST_TYPE = new TypeToken<List<Pokemon>>() {}.getType();
        try{
            jsonReader = new JsonReader(new FileReader("D:\\JavaProject\\comp6442-rpg\\Pokemon_Battle\\src\\pokemon.json"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        List<Pokemon> pl=gson.fromJson(jsonReader, CUS_LIST_TYPE);
        for(Pokemon p:pl) {
            if (id == p.getid()) {return p;}
        }
        return null;
    }
    public Pokemon(int id) {
        super("", id, 0, 0, 0, 0 ,0, false,false, false, 0,"",null);
        // name, id, level, HP, MP, defense, attack, grass_able, water_able, stone_able, max_HP, image, position
        Pokemon pkm=loadjson(id);
        this.setName(pkm.getName());
        this.setLevel(pkm.getLevel());
        this.setHP(pkm.getHP());
        this.setMP(pkm.getMP());
        this.setDefense(pkm.getDefense());
        this.setAttack(pkm.getAttack());
        this.setGrassAble(pkm.getGrassAble());
        this.setWaterAble(pkm.getWaterAble());
        this.setStoneAble(pkm.getStoneAble());
        this.setmaxHP(pkm.getmaxHP());
        this.setImgUrl(pkm.getImgUrl());
        this.setPosition(super.getStartPostion());
    }


    @Override
    public void skill1(Role role) {
        String showMessage= "I use skill1...";
        role.setHP(role.getHP()-this.getAttack()+role.getDefense());
    }

    @Override
    public void skill2(Role role) {
        //...??
    }

    @Override
    public void skill3() {
        //??
    }

}

import Items.Item;
import NPC.NPC;

import java.util.ArrayList;
import java.util.List;

public class Room {
    int number;
    String name;
    List<String> desc = new ArrayList<String>();
    List<String> exits = new ArrayList<String>();
    List<NPC> npc =new ArrayList<>();
    ArrayList<Item> item = new ArrayList<Item>();
    List<Integer> connectedRm=new ArrayList<>();




    public int getNumber(){
        return this.number;
    }

    public Room(int x) {number=x;}
}

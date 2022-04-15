import Items.Item;

import java.util.ArrayList;

public class Player {
    String name;
    int    hp;
    int    accuracy;
    int inRoom=0;
    ArrayList<Item> item=new ArrayList<Item>();
    ArrayList<Item> wornItems=new ArrayList<Item>();

    public void look(){
        System.out.println("You take a good look at yourself......");
        System.out.println("Name:"+name);
        System.out.println("Health:"+hp);
        System.out.println("Accuracy:"+accuracy);

    }
    public void remove(String[] x){
        for(int i=0; i< wornItems.size();i++){
            if(wornItems.get(i).id.equals(x[1])){
                System.out.println("You remove a "+wornItems.get(i).id);
                item.add(wornItems.get(i));
                wornItems.remove(i);
            }
        }
    }
    public  void equipment(){
        for(int i=0;i<wornItems.size();i++){
            System.out.println(wornItems.get(i).name+":"+wornItems.get(i).wearloc);
        }
    }
    public void wear(String[] x) {
        if (wornItems.size() == 0) {
            for (int i = 0; i < item.size(); i++) {
                if (x[1].equalsIgnoreCase(item.get(i).id) && item.get(i).isWearable) {
                    wornItems.add(item.get(i));
                    System.out.println("You wear a " + item.get(i).name);
                    item.remove(i);

                    break;
                }
            }
        } else {
            boolean isWearing = false;
            for (int i = 0; i < wornItems.size(); i++) {
                for (int z = 0; z < item.size(); z++) {
                    if (x[1].equalsIgnoreCase(item.get(z).id) && item.get(z).isWearable){
                        if(item.get(z).wearloc.equals(wornItems.get(i).wearloc)){
                            System.out.println("You already have something worn in that location.");
                            isWearing=true;
                        }
                    }
                }
                if(!isWearing){
                    wornItems.add(item.get(i));
                    System.out.println("You wear a "+item.get(i).name);
                    item.remove(i);

                    break;
                }
            }
        }
    }
}

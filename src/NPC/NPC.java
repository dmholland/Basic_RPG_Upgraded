package NPC;

public class NPC {
  public  String name;
 public  String id;
  public  String desc;
  public  int hp;
  public  int accuracy;

public NPC(){
  id = "NPC";
  name="";
  desc="";
  hp=0;
  accuracy=0;
}
  public void look()
  {
    System.out.println(name);
    System.out.print("accuracy:"+accuracy);
    System.out.println("hp:"+hp);
  }

    }



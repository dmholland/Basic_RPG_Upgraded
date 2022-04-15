import NPC.NPC;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Monster_Thread {
    Game_Logic currentGL;

    public Monster_Thread(Game_Logic gl){
        currentGL = gl;
    }
    public void startMonsterThread(){
        Thread one = new Thread(){
            public void run(){
                try{
                    while (true){
                        //System.out.println("Something Happens");
                        populateGame();
                        Thread.sleep(1000);

                        //System.out.println("Something Happens Again");
                    }
                }catch(InterruptedException | ClassNotFoundException v){
                    System.out.println(v);
                }
            }
        };
        one.start();
    }
    public void populateGame() throws ClassNotFoundException {
        int roomMobCount=0;

        List<String> lines = readInNPCFile("C:\\Users\\kenos\\IdeaProjects\\Basic_RPG\\TextFiles\\MonsterLocs");
        List<String[]>toBeProcessed=makeListing(lines);
        Map<String,String> monsterMapping = makeMapping(toBeProcessed);



        for(Room rm: Game_Objects.room){//look at every room
            if(checkRm(rm,monsterMapping)){  //checks if the room is in the monster mapping
           for(Map.Entry<String, String> monster : monsterMapping.entrySet()){
               if(checkForNPC(rm,monster)) {//if map key contains room number
                   try{
                       if(checkIfCreationNeeded(rm,monster)){  //if room npc listing contains map value
                           try{
                               rm.npc.add((NPC)Class.forName("NPC."+monster.getValue()).getDeclaredConstructor().newInstance());
                           }catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                               e.printStackTrace();
                           }
                       roomMobCount++;


                   }}
                       catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }
           }
            }
        }roomMobCount=0;
        }





    }

    //reads text file and makes a listing of strings
    public List<String> readInNPCFile(String filename){
        List<String>roomInfo=new ArrayList<>();
        try{
            roomInfo = currentGL.readLines(filename);
        }catch(IOException e){
            e.printStackTrace();
        }
        return roomInfo;
    }
    //checks if monster is in room
    public boolean checkForNPC(Room rm, Map.Entry<String,String> entry){
        return Integer.toString(rm.number).equals(entry.getKey());
    }

    public boolean checkIfCreationNeeded(Room rm, Map.Entry<String,String> entry)throws ClassNotFoundException{
      List<String> classList = rm.npc.stream().map(l ->l.id).collect(Collectors.toList());
        return (!classList.contains(entry.getValue()) || countNPCs(classList,entry.getValue(),6));
    }


    public boolean countNPCs(List<String> listing,String npc, int number){
        long count;
        count=listing.stream().filter(l -> l.equals(npc)).count();

        return count<number;
    }


    //checks if room is in key
    public boolean checkRm(Room room,Map<String,String> monsterMapping){
        return monsterMapping.containsKey(((Integer) room.number).toString());
    }

    public List<String[]>makeListing(List<String> list){
        return list.stream().map(l -> l.split(" "))
                .collect(Collectors.toList());
    }

    public Map<String,String> makeMapping(List<String[]> list){

        return list.stream()
                .filter(l -> l[0].equalsIgnoreCase("Name:"))
                .collect(Collectors.toMap(x ->x[2],x->x[1]));
    }

}

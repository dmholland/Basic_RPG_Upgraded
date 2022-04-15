import Items.Item;
import NPC.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class Game_Logic {
    public Game_Logic(){

        Game_Objects.room.add(new Room(0));
        List<String> roomInfo = new ArrayList<>();
        try{
            roomInfo = readLines("C:\\Users\\kenos\\IdeaProjects\\Basic_RPG\\TextFiles\\RoomDescriptions.txt");
        }catch(IOException e){
            e.printStackTrace();
        }


        for(int i=0;i<roomInfo.size();i++) {
            String[] firstWord= roomInfo.get(i).split(" ");//splits first word from buffered reader (Room/Number/Desc/Exit) in a line
            String[] everythingElse= roomInfo.get(i).split(":");// splits the other stuff from the semicolon

            if(firstWord[0].equals("Room:")){//Reads a line where Room: is the first value
                int currentRoomSize = Game_Objects.room.size();
                Game_Objects.room.add(new Room(currentRoomSize)); //adds a new room
                Game_Objects.room.get(Game_Objects.room.size()-1).name = everythingElse[1]; //gives it a name
                Game_Objects.room.get(Game_Objects.room.size() -1).number = currentRoomSize;//updates room size

                int roomcount =0;
                for(int z =0; z< roomInfo.size();z++){//first loop counts rooms
                    String[] nextFirstWord = roomInfo.get(z).split(" ");
                    if(nextFirstWord[0].equals("Room:")){
                        roomcount++;
                    }
                    if(roomcount==currentRoomSize){
                        if(nextFirstWord[0].equals("Desc:")){
                            String[] nextEverythingElse = roomInfo.get(z).split(":");
                            Game_Objects.room.get(Game_Objects.room.size()-1).desc.add(nextEverythingElse[1]);
                        }
                    }
                }
                roomcount =0;
                for(int z =0; z< roomInfo.size();z++){
                    String[] nextFirstWord= roomInfo.get(z).split(" ");
                    if(nextFirstWord[0].equals("Room:")){
                        roomcount++;
                    }
                    if(roomcount == currentRoomSize){
                        if(nextFirstWord[0].equals("Exit:")){
                            String[] nextEverthingElse = roomInfo.get(z).split(":");
                            Game_Objects.room.get(Game_Objects.room.size()-1).exits.add(nextEverthingElse[1]);
                        }
                    }
                }
            }
        }

    }







    public List<String> readLines(String filename) throws IOException{
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String>lines = new ArrayList<String>();
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            lines.add(line);
        }
        bufferedReader.close();
        return lines;
    }

    public void waitforCommand() {
        if(Game_Objects.player.inRoom == 0){
            createCharacter();
        }
        System.out.println("What will you do?");
        Scanner sc = new Scanner(System.in);
        String com = sc.nextLine();
        // parse the command by spaces
        //read each word into an array valueString s="This is a simple sentence"
        String[] words= com.split(" ");
        processCommand(words);
    }
    public void processCommand(String[] x){
       if(x[0].equals("look"))
       {
           look(x);
       }
       if(x[0].equals("summon")){
           summon(x);
       }
       if(x[0].equals("create")){
           create_Item(x);
       }
       if(x[0].equals("get")){
           get(x);
       }
       if(x[0].equals("move")){
           move(x);
       }
       if(x[0].equals("equip")){
           Game_Objects.player.equipment();
       }
       if(x[0].equals("wear")){
           Game_Objects.player.wear(x);
       }
       if(x[0].equals("attack")){
           Game_Objects.combat.attack(x);
       }
    }
public void get(String[] x){
        if(x.length==1){System.out.println("Get What?");}

        if(x.length==2){
            for(int i=0; i<Game_Objects.ItemDataBase.size();i++) {


                for (int y = 0; y < Game_Objects.room.size(); y++) {
 if(Game_Objects.room.get(y).number == Game_Objects.player.inRoom){
     for(int z =0; z< Game_Objects.room.get(y).item.size();z++) {
         if(x[1].equalsIgnoreCase((Game_Objects.room.get(y).item.get(z).id))){
         Item localItem =Game_Objects.room.get(y).item.get(z);
         Game_Objects.player.item.add(localItem);
         System.out.println(("You Pick up a " + localItem.name));
         Game_Objects.room.get(y).item.remove(z);
         break;}
     }
}
                }
            }
        }//finish this
}
    public void look(String[] x){
        if(x.length ==1){
            for(int i =0;i < Game_Objects.room.size();i++){
                if(Game_Objects.room.get(i).number == Game_Objects.player.inRoom){
                    System.out.println(Game_Objects.room.get(i).name);
                    for(int y = 0;y<Game_Objects.room.get(i).desc.size(); y++) {
                     System.out.println(Game_Objects.room.get(i).desc.get(y));
                    }
                    System.out.println("Exits:");
                    for(int y =0; y<Game_Objects.room.get(i).exits.size();y++){

                        String exitNameFull =Game_Objects.room.get(i).exits.get(y);
                        String[] exitName = exitNameFull.split(" ");
                        System.out.println(exitName[1]);
                    }
                    for (int y=0; y<Game_Objects.room.get(i).npc.size();y++){
                        System.out.println(Game_Objects.room.get(i).npc.get(y).desc);
                    }
                    for (int y=0; y<Game_Objects.room.get(i).item.size();y++){
                        System.out.println(Game_Objects.room.get(i).item.get(y).desc);
                    }
                }
            }
        }
        //If user typed in someting after look
        if(x.length ==2){
            if(x[1].equals("self")){Game_Objects.player.look();
            System.out.println("You are Carrying:");
            for(int i=0;i<Game_Objects.player.item.size();i++){
                System.out.println(Game_Objects.player.item.get(i).name);
                }}

       for(int y =0; y<Game_Objects.room.size();y++){
           if(Game_Objects.room.get(y).number == Game_Objects.player.inRoom){
               for(int i=0; i< Game_Objects.room.get(y).npc.size();i++){
                   if(x[1].equalsIgnoreCase(Game_Objects.room.get(y).npc.get(i).id)){
                       Game_Objects.room.get(y).npc.get(i).look();}
               }
           }
       }
        }


    }
public void summon(String[] x){
    if (x.length == 1) {

        System.out.println("Summon what exactly?");

    }
    if(x.length ==2){
        for(int i= 0; i< Game_Objects.NPCDataBase.size();i++){
            NPC localNPC=(NPC) Game_Objects.NPCDataBase.get(i);
            if(localNPC.id.equalsIgnoreCase(x[1])){
                for(int y =0; y<Game_Objects.room.size();y++){
                    if(Game_Objects.room.get(y).number==Game_Objects.player.inRoom){
                        try{
                            Game_Objects.room.get(y).npc.add((NPC)Class.forName("NPC."+localNPC.id)
                                    .getDeclaredConstructor().newInstance());
System.out.println("You summon a "+ Game_Objects.room.get(y).npc
        .get(Game_Objects.room.get(y).npc.size() - 1).name);
                            }catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
    public void createCharacter(){
        System.out.println("Welcome to the Game. What is your name?");
        Scanner sc = new Scanner(System.in);
        Game_Objects.player.name=sc.next();
        System.out.println("For the sake of simplicity, the gods are giving you 100 hp and 75 accuracy.");
        Game_Objects.player.hp = 100;
        Game_Objects.player.accuracy = 75;
        Game_Objects.player.inRoom = 1;
    }

    public void create_Item(String[] x){
        if(x.length==1){
    System.out.println("Create What Exactly?");
        }
        if(x.length==2){
            for(int i=0;i<Game_Objects.ItemDataBase.size();i++){
                Item localItem=(Item)Game_Objects.ItemDataBase.get(i);
           if(localItem.id.equalsIgnoreCase(x[1])){
               for(int y =0;y<Game_Objects.room.size();y++){
                   if(Game_Objects.room.get(y).number == Game_Objects.player.inRoom){
                       try{
                           Game_Objects.room.get(y).item.add((Item)Class.forName("Items."+localItem.id).getDeclaredConstructor().newInstance());
                       }catch(InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e){
                           e.printStackTrace();
                       }
                       System.out.println("You create a "+Game_Objects.room.get(y).item.get(Game_Objects.room.get(y).item.size() -1).name);
                   }
               }
           }
            }
        }
    }
    public void move(String[] x){
        if(x.length ==1) {
            System.out.println("Move Where?");
        }
            if (x.length == 2){
                for(int i =0; i< Game_Objects.room.size(); i++){
                    if(Game_Objects.room.get(i).number == Game_Objects.player.inRoom){
                        for(int y=0; y<Game_Objects.room.get(i).exits.size();y++){
                            String exitRequested=Game_Objects.room.get(i).exits.get(y);
                            String[]exitArray=exitRequested.split(" ");
                            if(x[1].equalsIgnoreCase(exitArray[1])){
                                Game_Objects.player.inRoom=Integer.parseInt(exitArray[2]);
                                System.out.println("You leave "+exitArray[1]);
                                String[] badProgramming=new String[1];
                                badProgramming[0]="nada";
                                look(badProgramming);
                                break;
                            }

                        }break;
                    }
                }
            }
        }




    }



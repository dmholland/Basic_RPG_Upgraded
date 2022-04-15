public class Simple_RPG {

    static Game_Logic gl = new Game_Logic();

    public static void main(String[] args){
        Monster_Thread monster_thread = new Monster_Thread(gl);
        Game_Objects.initializeNPCArray();
        Game_Objects.initializeItemArray();
        monster_thread.startMonsterThread();
               while(true){
                   game_loop();
               }
    }
    public static void game_loop(){
        gl.waitforCommand();
    }


}

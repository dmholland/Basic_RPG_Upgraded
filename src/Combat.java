public class Combat {
    public void attack(String[] x) {
        if(x.length==1)System.out.println("Attack who?");
        else{
        for (int i = 0; i < Game_Objects.room.size(); i++) {
            if (Game_Objects.room.get(i).number == Game_Objects.player.inRoom) {
                for (int y = 0; y < Game_Objects.room.get(i).npc.size(); y++) {
                    if (Game_Objects.room.get(i).npc.get(y).id.equalsIgnoreCase(x[1])) {

                        int npc_hit = Game_Objects.rng.returnRandom(100);
                        npc_hit = npc_hit + (Game_Objects.room.get(i).npc.get(y).accuracy / 2);
                        if (npc_hit > 50) {
                            int npc_damage = Game_Objects.rng.returnRandom(10);
                            System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " hit you for " + npc_damage + " health points.");
                        } else {
                            System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " misses!");
                        }

                        int pc_hit = Game_Objects.rng.returnRandom(100);
                        pc_hit = npc_hit + (Game_Objects.room.get(i).npc.get(y).accuracy / 2);
                        if (pc_hit > 50) {
                            int pc_damage = Game_Objects.rng.returnRandom(10);
                            Game_Objects.room.get(i).npc.get(y).hp = Game_Objects.room.get(i).npc.get(y).hp - pc_damage;
                            System.out.println("You hit " + Game_Objects.room.get(i).npc.get(y).name + " for " + pc_damage);
                            if (Game_Objects.room.get(i).npc.get(y).hp <= 0) {

                                npc_death(i, y);
                            }
                        } else {
                            System.out.println("You missed!");
                        }

                    }
                }
            }
        }
        }//end of else block (if x.length > 1)

    }

    public void npc_death(int i, int y) {
        System.out.println(Game_Objects.room.get(i).npc.get(y).name + " perishes.");
        Game_Objects.room.get(i).npc.remove(y);
    }



}

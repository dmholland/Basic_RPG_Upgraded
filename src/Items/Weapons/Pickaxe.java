package Items;

public class Pickaxe extends Item{
    int accuracy = 10;
    int damage = 20;
    public Pickaxe()
    {
        id= "Pickaxe";
        name= "Pickaxe";
        desc = "Miners from your village have used this type to strike stone and shovel hard dirt. It reminds you of your father";
        isWearable = true;
        wearloc ="wield";
    }
}

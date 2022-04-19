package Items;

public class Polished_Axe extends Item{
    int accuracy = 10;
    int damage = 20;
    public Polished_Axe()
    {
        id= "Polished_Axe";
        name= "Polished_Axe";
        desc = "The axe sharpened against the boulder is now unbelievably sharp.";
        isWearable = true;
        wearloc ="wield";
    }
}

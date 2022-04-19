package Items;

public class Diamond_Ring extends Item{
    int accuracy = 10;
    int damage = 20;
    public Diamond_Ring()
    {
        id= "Diamond_Ring";
        name= "Diamond_Ring";
        desc = "A Diamond Ring lies Here";
        isWearable = true;
        wearloc ="finger";
    }

}

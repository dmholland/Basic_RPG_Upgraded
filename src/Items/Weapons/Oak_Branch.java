package Items;

public class Oak_Branch extends Item{
    int accuracy = 70;
    int damage = 7;
    public Oak_Branch()
    {
        id= "Oak_Branch";
        name= "Oak_Branch";
        desc = "Sturdy branch from the fallen tree. Your mother has smacked a many coyotes with one such as this.";
        isWearable = true;
        wearloc ="wield";
    }
}

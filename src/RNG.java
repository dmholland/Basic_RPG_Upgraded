import java.util.Random;

public class RNG{



    public int returnRandom(int x) {
        int number = new Random().nextInt(100);
        return number;
    }
    }
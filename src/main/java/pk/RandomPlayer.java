package pk;

import java.util.Random;

public class RandomPlayer extends Player{
    public RandomPlayer(boolean debugMode){
        super(debugMode);
        
    }

    public void chooseDiceToKeep(int diceLeft){
        Random random = new Random();
        
        int randomRemove = random.nextInt(Integer.max(1, diceLeft - 2));

        for (int i = 0; i < randomRemove; i++){
            super.diceList.remove(random.nextInt(super.diceList.size()));
        }
    }
}

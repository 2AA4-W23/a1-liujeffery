package pk;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Player {
    protected List<Faces> diceList;
    boolean debugMode;
    private static final Logger logger = LogManager.getLogger(RandomPlayer.class);
    
    public abstract void chooseDiceToKeep(int diceLeft);

    public Player(boolean debugMode){
        this.debugMode = debugMode;
        diceList = new ArrayList<Faces>();
    }

    public void refillDice(int diceLeft){
        int diceListSize = diceList.size();
        Dice myDice = new Dice();

        for (int i = 0; i < (diceLeft - diceListSize); i++){
            diceList.add(myDice.roll());
        }
    }

    public int calculateSkulls(int diceLeft){
        while (diceList.contains(Faces.SKULL)){
            diceLeft --;
            diceList.remove(Faces.SKULL);
        }
        
        if (debugMode){
            logger.debug("Dice left: " + diceLeft);
        }

        return diceLeft;
    }

    public int calculatePoints(){
        int points = 0;

        for (int i = 0; i < Faces.values().length; i++){
            int combo = 0;
            for (int l = 0; l < diceList.size(); l++){
                if (diceList.get(l).equals(Faces.values()[i])){
                    combo ++;
                }
            }
            switch (combo){
                case 3:
                    points += 100;
                    break;
                case 4:
                    points += 200;
                    break;
                case 5:
                    points += 500;
                    break;
                case 6:
                    points += 1000;
                    break;
                case 7:
                    points += 2000;
                    break;
                case 8:
                    points += 4500;
                    break;
            }
            
        }

        if (debugMode){
            logger.debug(points + " points for combo");
        }

        int numOfGold = 0;
        int numOfDiamond = 0;

        while (diceList.contains(Faces.GOLD)){
            numOfGold++;
            diceList.remove(Faces.GOLD);
        }

        while (diceList.contains(Faces.DIAMOND)){
            numOfDiamond++;
            diceList.remove(Faces.DIAMOND);
        }

        points += (numOfGold + numOfDiamond) * 100;

        return points;
    }

    public void showDice(){
        String diceNotRemoved = "Dice not removed: ";
        for (int i = 0; i < diceList.size(); i++){
            diceNotRemoved += (diceList.get(i) + " ");
        }
        logger.debug(diceNotRemoved);
    }
}

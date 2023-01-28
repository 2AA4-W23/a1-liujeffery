import pk.Dice;
import pk.Faces;
import java.util.ArrayList;
import java.util.Random;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PiratenKarpen {
    private static final Logger logger = LogManager.getLogger(PiratenKarpen.class);
    public static void main(String[] args) {
        int [] wins = new int [2];
        Random random = new Random();
        Dice myDice = new Dice();
        boolean debugMode = false;

        if (args.length >= 1 && args[args.length - 1].equals("debug")){
            debugMode = true;
        }
        
        for (int k = 1; k <= 42; k++){
            int [] pointsPerPlayer = new int[2];

            for (int j = 0; j < 2; j++){
                int numOfGold = 0;
                int numOfDiamond = 0;
                int numOfSkull = 0;
                int diceLeft = 8;

                ArrayList<Faces> diceList = new ArrayList<>();

                while (numOfSkull < 3){
                    int diceListSize = diceList.size();
                    
                    for (int i = 0; i < (diceLeft - diceListSize); i++){
                        diceList.add(myDice.roll());
                    }

                    while (diceList.contains(Faces.SKULL)){
                        diceLeft --;
                        numOfSkull ++;
                        diceList.remove(Faces.SKULL);
                    }

                    int randomRemove = random.nextInt(Integer.max(1, diceLeft - 2));
                    
                    for (int i = 0; i < randomRemove; i++){
                        diceList.remove(random.nextInt(diceList.size()));
                    }
                    
                    String diceNotRemoved = "Dice not removed: ";
                    for (int i = 0; i < diceList.size(); i++){
                        diceNotRemoved += (diceList.get(i) + " ");
                    }
                    if (debugMode){
                        logger.debug(diceNotRemoved);
                    }
                }

                while (diceList.contains(Faces.GOLD)){
                    numOfGold++;
                    diceList.remove(Faces.GOLD);
                }

                while (diceList.contains(Faces.DIAMOND)){
                    numOfDiamond++;
                    diceList.remove(Faces.DIAMOND);
                }

                int points = (numOfGold + numOfDiamond) * 100;
                pointsPerPlayer[j] = points;
                
            }
            if (pointsPerPlayer[0] > pointsPerPlayer[1]){
                if (debugMode){
                    logger.debug("Player 1 wins round " + k);
                }
                wins[0] ++;
            }
            else if (pointsPerPlayer[0] < pointsPerPlayer[1]){
                if (debugMode){
                    logger.debug("Player 2 wins round " + k);
                }
                wins[1] ++; 
            }
            else{
                if (debugMode){
                    logger.debug("Round " + k + " is a tie");
                }
            }
        }
        System.out.println("Player 1 won " + Math.round(wins[0]/0.42) + "% of times.");
        System.out.println("Player 2 won " + Math.round(wins[1]/0.42) + "% of times. ");
        System.out.println("The remainder were ties.");
    }
}

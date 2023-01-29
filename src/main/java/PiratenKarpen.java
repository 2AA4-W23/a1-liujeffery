import pk.RandomPlayer;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PiratenKarpen {
    public static boolean debugMode = false;

    private static final Logger logger = LogManager.getLogger(PiratenKarpen.class);
    public static void main(String[] args) {
        int [] wins = new int [2];
        
        int gamesToPlay = 3;

        if (args.length >= 1 && args[args.length - 1].equals("debug")){
            debugMode = true;
        }
        //RandomPlayer player2 = new RandomPlayer(debugMode);
        
        for (int k = 1; k <= gamesToPlay; k++){
            int [] pointsPerPlayer = new int[2];

            for (int j = 0; j < 2; j++){

                RandomPlayer player1 = new RandomPlayer(debugMode);
                int diceLeft = 8;

                while (true){
                    player1.refillDice(diceLeft);
                    diceLeft = player1.calculateSkulls(diceLeft);

                    if (diceLeft <= 5){
                        break;
                    }

                    player1.chooseDiceToKeep(diceLeft);
                }
                
                if (debugMode){
                    player1.showDice();
                }

                int points = player1.calculatePoints();
                pointsPerPlayer[j] = points;
                
                if (debugMode){
                    logger.debug("Total points for player " + (j + 1) + ": " + points);
                }
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
        System.out.println("Player 1 won " + Math.round(wins[0]/(gamesToPlay / 100.0)) + "% of times.");
        System.out.println("Player 2 won " + Math.round(wins[1]/(gamesToPlay / 100.0)) + "% of times. ");
        System.out.println("The remainder were ties.");
    }
}

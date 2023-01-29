import pk.RandomPlayer;
import pk.ComboPlayer;
import pk.Player;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PiratenKarpen {
    public static boolean debugMode = false;

    private static final Logger logger = LogManager.getLogger(PiratenKarpen.class);
    public static void main(String[] args) {
        int [] wins = new int [2];
        int gamesToPlay = 42;

        if (args.length >= 1 && args[args.length - 1].equals("debug")){
            debugMode = true;
        }
        //RandomPlayer player2 = new RandomPlayer(debugMode);
        
        for (int k = 1; k <= gamesToPlay; k++){
            int [] pointsPerPlayer = new int[2];

            Player player1 = new RandomPlayer(debugMode);
            Player player2 = new ComboPlayer(debugMode);

            pointsPerPlayer[0] = playTurn(player1);
            pointsPerPlayer[1] = playTurn(player2);
            
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

    public static int playTurn(Player player){
        int diceLeft = 8;

        while (true){
            player.refillDice(diceLeft);
            diceLeft = player.calculateSkulls(diceLeft);
            if (diceLeft <= 5){
                break;
            }
            player.chooseDiceToKeep(diceLeft);
        }
        
        if (debugMode){
            player.showDice();
        }

        int points = player.calculatePoints();
        
        if (debugMode){
            logger.debug("Total points for player : " + points);
        }

        return points;
    }
}

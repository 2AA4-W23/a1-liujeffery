import pk.RandomPlayer;
import pk.BattlePlayer;
import pk.Cards;
import pk.ComboPlayer;
import pk.Deck;
import pk.Player;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PiratenKarpen {
    public static boolean debugMode = false;

    private static final Logger logger = LogManager.getLogger(PiratenKarpen.class);
    public static void main(String[] args) {
        int [] wins = new int [2];
        int gamesToPlay = 42;

        if (args.length >= 1){
            if (args[args.length - 1].equals("debug")){
                debugMode = true;
            }
        }

        Player player1 = new RandomPlayer(debugMode);
        Player player2 = new ComboPlayer(debugMode);

        if (args.length >= 3){
            if (args[0].equals("combo")){
                player1 = new ComboPlayer(debugMode);
            }
            else if (args[0].equals("battle")){
                player1 = new BattlePlayer(debugMode);
            }
            if(args[1].equals("random")){
                player2 = new RandomPlayer(debugMode);
            }
            else if (args[1].equals("battle")){
                player2 = new BattlePlayer(debugMode);
            }
        }
        
        Deck deck = new Deck(debugMode);

        for (int k = 1; k <= gamesToPlay; k++){
            int [] pointsPerPlayer = new int[2];

            Cards card = deck.draw();

            pointsPerPlayer[0] = playTurn(player1, card);
            pointsPerPlayer[1] = playTurn(player2, card);
            
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

            player1.reset();
            player2.reset();
        }
        System.out.println("Player 1 won " + Math.round(wins[0]/(gamesToPlay / 100.0)) + "% of times.");
        System.out.println("Player 2 won " + Math.round(wins[1]/(gamesToPlay / 100.0)) + "% of times. ");
        System.out.println("The remainder were ties.");
    }

    public static int playTurn(Player player, Cards card){
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

        int points = player.calculatePoints(card);
        
        if (debugMode){
            logger.debug("Total points for player : " + points);
        }

        return points;
    }
}

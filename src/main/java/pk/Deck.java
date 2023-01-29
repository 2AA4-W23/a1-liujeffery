package pk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Deck {
    private List<Cards> deck;
    private boolean debugMode;
    private static final Logger logger = LogManager.getLogger(Deck.class);

    public Deck(boolean debugMode){
        this.debugMode = debugMode;
        deck = new ArrayList<>();
        int numOfSeaBattle4 = 2;
        int numOfSeaBattle3 = 4;
        int numOfNop = 29;

        for (int i = 0; i < numOfSeaBattle4; i++){
            deck.add(Cards.SEA_BATTLE_4);
        }
        for (int i = 0; i < numOfSeaBattle3; i++){
            deck.add(Cards.SEA_BATTLE_3);
        }
        for (int i = 0; i < numOfNop; i++){
            deck.add(Cards.NOP);
        }

        shuffle();
    }

    public void shuffle(){
        Random random = new Random();

        for (int j = deck.size() - 1; j > 0; j--){
            int randomValue = random.nextInt(j);

            Cards temp = deck.get(randomValue);
            deck.set(randomValue, deck.get(j));
            deck.set(j, temp);
        }

        if (debugMode){
            String output = "Shuffled deck: ";
            for (int i = 0; i < deck.size(); i++){
                output += deck.get(i).name().charAt(0);
            }
            logger.debug(output);
        }
    }

    public Cards draw(){
        if (deck.size() == 0){
            return Cards.NOP;
        }

        Cards drawnCard = deck.remove(0);

        if (debugMode){
            logger.debug("Drawn: " + drawnCard);
        }
        return drawnCard;
    }
}

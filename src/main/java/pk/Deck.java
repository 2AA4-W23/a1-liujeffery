package pk;

import java.util.List;

public class Deck {
    public List<Cards> deck;

    public Deck(){
        int numOfSeaBattle = 6;
        int numOfNop = 29;
        
        for (int i = 0; i < numOfSeaBattle; i++){
            deck.add(Cards.SEA_BATTLE);
        }
        for (int i = 0; i < numOfNop; i++){
            deck.add(Cards.NOP);
        }
    }
}

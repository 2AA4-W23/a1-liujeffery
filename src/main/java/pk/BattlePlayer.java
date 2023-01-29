package pk;

public class BattlePlayer extends Player{

    public BattlePlayer(boolean debugMode) {
        super(debugMode);
    }

    public void chooseDiceToKeep(int diceLeft) {
        int numOfSwords = 0;
        for (int i = 0; i < diceList.size();i ++){
            if (diceList.get(i).equals(Faces.SABER)){
                numOfSwords ++;
            }
        }

        diceList.clear();
        for (int i = 0; i < Integer.min(numOfSwords, diceLeft - 2); i++){
            diceList.add(Faces.SABER);
        }

        if (debugMode){
            showDice();
        }
    }
    
}

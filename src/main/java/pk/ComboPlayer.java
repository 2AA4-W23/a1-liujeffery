package pk;

import java.util.HashMap;
import java.util.Map;

public class ComboPlayer extends Player{

    public ComboPlayer(boolean debugMode) {
        super(debugMode);
    }

    public void chooseDiceToKeep(int diceLeft) {
        Map<Faces, Integer> diceCount = new HashMap<>();

        diceList.forEach((n)->{
            if(!diceCount.containsKey(n)){
                diceCount.put(n, 1);
            }
            else{
                diceCount.put(n, diceCount.get(n) + 1);
            }
        });
        
        Faces dieToKeep = Faces.GOLD;
        int maxCount = 0;
        
        for (Map.Entry<Faces, Integer> entry: diceCount.entrySet()){
            if (maxCount < entry.getValue()){
                maxCount = entry.getValue();
                dieToKeep = entry.getKey();
            }
        }

        diceList.clear();
        for (int i = 0; i < Integer.min(maxCount, diceLeft - 2); i++){
            diceList.add(dieToKeep);
        }

        if (debugMode){
            showDice();
        }
    }
}

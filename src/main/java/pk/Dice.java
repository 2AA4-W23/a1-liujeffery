package pk;
import java.util.Random;
import java.util.ArrayList;

public class Dice {

    public Faces roll() {
        int howManyFaces = Faces.values().length;
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    public Faces[] keep(){
        ArrayList<Faces> keptDice = new ArrayList<>();
        Random bag = new Random();

        for (int i = 0; i < 8; i++){
            int keep = bag.nextInt(2);
            if (keep == 1){
                int howManyFaces = Faces.values().length;
                keptDice.add(Faces.values()[bag.nextInt(howManyFaces)]);
            }
        }

        Faces[] keptDiceArray = new Faces[keptDice.size()];
        for (int i = 0; i < keptDiceArray.length; i++){
            keptDiceArray[i] = keptDice.get(i);
            System.out.println("Kept dice: " + keptDiceArray[i]);
        }

        return keptDiceArray;
    }
    
}

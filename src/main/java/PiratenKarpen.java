import pk.Dice;
import pk.Faces;

public class PiratenKarpen {
    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling eight dice");
        
        int numOfGold = 0;
        int numOfDiamond = 0;
        int numOfSkull = 0;

        Dice myDice = new Dice();

        Faces[] keptDice = myDice.keep();
        for (int i = 0; i < keptDice.length; i++){
            if (keptDice[i] == Faces.GOLD){
                numOfGold ++;
            }
            else if (keptDice[i] == Faces.DIAMOND){
                numOfDiamond ++;
            }
            else if (keptDice[i] == Faces.SKULL){
                numOfSkull ++;
            }
        }

        int diceLeft = 8 - keptDice.length;
        int numOfSkullTemp = 0;
        int numOfGoldTemp = 0;
        int numOfDiamondTemp = 0;

        while (numOfSkull + numOfSkullTemp < 3){
            numOfGoldTemp = 0;
            numOfDiamondTemp = 0;
            numOfSkullTemp = 0;

            for (int i = 0; i < diceLeft; i ++){
                Faces roll = myDice.roll();
                System.out.println(roll);
                if (roll == Faces.GOLD){
                    numOfGoldTemp ++;
                }
                else if (roll == Faces.DIAMOND){
                    numOfDiamondTemp ++;
                }
                else if (roll == Faces.SKULL){
                    numOfSkullTemp ++;
                }
            }
        }
        
        numOfSkull += numOfSkullTemp;
        numOfGold += numOfGoldTemp;
        numOfDiamond += numOfDiamondTemp;

        System.out.println("Number of golds: " + numOfGold);
        System.out.println("Number of diamonds: " + numOfDiamond);
        System.out.println("Number of skulls: " + numOfSkull);
        System.out.println("That's all folks!");
    }
}

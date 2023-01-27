import pk.Dice;
import pk.Faces;

public class PiratenKarpen {
    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling eight dice");
        
        int numOfGold = 0;
        int numOfDiamond = 0;

        for (int i = 0; i < 8; i ++){
            Dice myDice = new Dice();
            Faces roll = myDice.roll();
            System.out.println(roll);
            if (roll == Faces.GOLD){
                numOfGold ++;
            }
            else if (roll == Faces.DIAMOND){
                numOfDiamond ++;
            }
        }
        System.out.println("Number of golds: " + numOfGold);
        System.out.println("Number of diamonds: " + numOfDiamond);
        System.out.println("That's all folks!");
    }
    
}

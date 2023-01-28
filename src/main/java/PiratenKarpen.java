import pk.Dice;
import pk.Faces;
import java.util.ArrayList;
import java.util.Random;

public class PiratenKarpen {
    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        int [] wins = new int [2];
        Random random = new Random();
        Dice myDice = new Dice();
        
        for (int k = 1; k <= 42; k++){
            int [] pointsPerPlayer = new int[2];

            for (int j = 0; j < 2; j++){
                int numOfGold = 0;
                int numOfDiamond = 0;
                int numOfSkull = 0;
                int diceLeft = 8;

                ArrayList<Faces> diceList = new ArrayList<>();

                while (numOfSkull < 3){
                    
                    int diceListSize = diceList.size();
                    
                    for (int i = 0; i < (diceLeft - diceListSize); i++){
                        diceList.add(myDice.roll());
                    }

                    while (diceList.contains(Faces.SKULL)){
                        diceLeft --;
                        numOfSkull ++;
                        diceList.remove(Faces.SKULL);
                    }
                    
                    int randomRemove = random.nextInt(diceLeft - 2);
                    
                    for (int i = 0; i < randomRemove; i++){
                        diceList.remove(random.nextInt(diceList.size()));
                    }
                }

                while (diceList.contains(Faces.GOLD)){
                    numOfGold++;
                    diceList.remove(Faces.GOLD);
                }

                while (diceList.contains(Faces.DIAMOND)){
                    numOfDiamond++;
                    diceList.remove(Faces.DIAMOND);
                }

                int points = (numOfGold + numOfDiamond) * 100;
                pointsPerPlayer[j] = points;
                
            }
            if (pointsPerPlayer[0] > pointsPerPlayer[1]){
                System.out.println("Player 1 wins round " + k);
                wins[0] ++;
            }
            else if (pointsPerPlayer[0] < pointsPerPlayer[1]){
                System.out.println("Player 2 wins round " + k);
                wins[1] ++; 
            }
            else{
                System.out.println("Round " + k + " is a tie");
            }
        }
        System.out.println("Player 1 won " + Math.round(wins[0]/0.42) + "% of times.");
        System.out.println("Player 2 won " + Math.round(wins[1]/0.42) + "% of times. ");
        System.out.println("The remainder were ties.");
    }
}

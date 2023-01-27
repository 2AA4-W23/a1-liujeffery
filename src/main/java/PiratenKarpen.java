import pk.Dice;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling eight dice");
        for (int i = 0; i < 8; i ++){
            Dice myDice = new Dice();
            System.out.println(myDice.roll());
        }
        System.out.println("That's all folks!");
    }
    
}

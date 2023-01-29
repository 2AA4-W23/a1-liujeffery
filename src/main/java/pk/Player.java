package pk;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Player {
    protected List<Faces> diceList;
    boolean debugMode;
    private static final Logger logger = LogManager.getLogger(Player.class);
    
    public abstract void chooseDiceToKeep(int diceLeft);

    public Player(boolean debugMode){
        this.debugMode = debugMode;
        diceList = new ArrayList<Faces>();
    }

    public void refillDice(int diceLeft){
        int diceListSize = diceList.size();
        Dice myDice = new Dice();

        for (int i = 0; i < (diceLeft - diceListSize); i++){
            diceList.add(myDice.roll());
        }
    }

    public int calculateSkulls(int diceLeft){
        while (diceList.contains(Faces.SKULL)){
            diceLeft --;
            diceList.remove(Faces.SKULL);
        }

        return diceLeft;
    }

    public int calculatePoints(Cards card){
        int points = 0;
        boolean seaBattle4 = false;
        boolean seaBattle3 = false;
        boolean monkeyBusiness = false;

        switch (card){
            case SEA_BATTLE_4:
                seaBattle4 = true;
                break;
            case SEA_BATTLE_3:
                seaBattle3 = true;
                break;
            case MONKEY_BUSINESS:
                monkeyBusiness = true;
            case NOP:
                break;
        }
        
        int numOfGold = 0;
        int numOfDiamond = 0;
        int monkeyBusinessCombo = 0;

        for (int i = 0; i < diceList.size(); i++){
            if (diceList.get(i).equals(Faces.GOLD)){
                numOfGold++;
            }
            else if (diceList.get(i).equals(Faces.DIAMOND)){
                numOfDiamond++;
            }
            else if (monkeyBusiness && (diceList.get(i).equals(Faces.MONKEY) ||
            diceList.get(i).equals(Faces.PARROT))){
                monkeyBusinessCombo ++;
            }
        }

        points += pointsOffCombo(monkeyBusinessCombo);
        points += (numOfGold + numOfDiamond) * 100;

        if (debugMode){
            logger.debug("Points before combo: " + points);
        }

        for (int i = 0; i < Faces.values().length; i++){
            if(monkeyBusiness && (Faces.values()[i].equals(Faces.MONKEY) || 
            Faces.values()[i].equals(Faces.PARROT))){
                continue;
            }

            int combo = 0;
            for (int l = 0; l < diceList.size(); l++){
                if (diceList.get(l).equals(Faces.values()[i])){
                    combo ++;
                }
            }

            points += pointsOffCombo(combo);

            if (seaBattle4 && Faces.values()[i].equals(Faces.SABER)){
                if (combo >= 4){
                    points += 1000;
                }
                else{
                    points = 0;
                    break;
                }
            }
            else if (seaBattle3 && Faces.values()[i].equals(Faces.SABER)){
                if (combo >= 3){
                    points += 500;
                }
                else{
                    points = 0;
                    break;
                }
            }
        }

        if (debugMode){
            logger.debug(points + " points for combo");
        }

        return points;
    }

    public void showDice(){
        String diceNotRemoved = "Dice not removed: ";
        for (int i = 0; i < diceList.size(); i++){
            diceNotRemoved += (diceList.get(i) + " ");
        }
        logger.debug(diceNotRemoved);
    }

    public void reset(){
        diceList.clear();
    }

    public int pointsOffCombo(int combo){
        switch (combo){
            case 3:
                return 100;
            case 4:
                return 200;
            case 5:
                return 500;
            case 6:
                return 1000;
            case 7:
                return 2000;
            case 8:
                return 4500;
        }
        return 0;
    }
}

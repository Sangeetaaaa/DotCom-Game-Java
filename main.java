import java.io.*;
import java.util.*;

class DotComBust {
    // instantiate the GameHelper instance variable, named helper
    GameHelper helper = new GameHelper();
    // nstantiate an ArrayList to hold the list of DotComs (initially three) Call it dotComsList
    private ArrayList<dotcom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        // make three DotCom objects and name them
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");

        //ADD the DotComs to the dotComsList ( the ArrayList).
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);

        System.out.println("Your goal is to sink three dot coms.");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink them all in the fewest number of guesses");

        // one.setLocation(helper.placeDotCom());
        // two.setLocation(helper.placeDotCom());
        // three.setLocation(helper.placeDotCom());


        // dotComToSet things stored is String so why using it as a class type
        for (DotCom dotComToSet : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(3); //What is this 3 which is passed 
            // They could have used a String newLocation instead of ArrayList
            dotComToSet.setLocationCells(newLocation);
        }
    }

    private void startPlaying() {
        while(!dotComsList.isEmpty()) {
            //We could have also written "Enter a guess" in helpers get User Input then why here 
            String userGuess = helper.getUserInput("Enter a guess");
            helper.checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "miss";
        for (DotCom dotComToSet : dotComsList) {
            result = DotCom.checkYourself(userGuess);
            if(result.equals("hit")) {
                break;
            }
            if(result.equals("kill")) {
                dotComsList.remove(dotComToSet);
                break;
            }
            System.out.println(result);
        }
    }

    private void finishGame() {
        System.out.println("Game Over");
        if (userGuess <= 18) { 
            System.out.println("Congratulations.. You took only " + numOfGuesses + " guesses");
            System.out.println(" You got out before your options sank.");
        } else {
            System.out.println(":( You took " + numOfGuesses + " much guesses");
            System.out.println("Fish are dancing with your options.");
        }
    }

    public static void main (String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}




public class DotCom {
    private ArrayList<String> locationCells;
    private String name;

    // getting the random generated location places and adding it to locationCells
    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    } 

    //getting the name i.e pets.com or whatever and setting it in the name field 
    public void setName(String n) {
        name = n;
    }

    // Getting the userinput and then checking if that number is in locationCells, accordingly
    // sending back hit(and deleting that userInput if hit from the locationCells), miss or kill

    public String checkYourself(String userInput) {
        String result = "miss";
        int index = locationCells.indexOf(userInput);
        if(index >= 0) {
            locationCells.remove(index);

            if (locationCells.isEmpty()) {
                result = "kill";
                System.out.println("Ouch! You sunk " + name + " :(");
            } else {
                result = "hit";
            }
        }
        return result;
    }
}


// This is the helper class for the game. Besides the user input method
// (that prompts the user and reads input from the command-line), the
// helper’s Big Service is to create the cell locations for the DotComs.

public class GameHelper {

}

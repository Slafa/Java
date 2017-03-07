

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by slafa on 30.11.2016.
 */
public class SteinSaksPapir
{

    int computerPoints;
    int userPoints;
    int bestOff;
    String result;
    String userWin = "You win this round";
    String compWin = "Computer win this round";
    String draw = "*****Draw*****";
    Random rng = new Random();
    Scanner input = new Scanner(System.in);

    public void close(String text){
        if (text.toLowerCase().equals("exit"))
            System.exit(0);
    }


    public SteinSaksPapir(){

         bestOff();
         start();

    }
    public void bestOff() { // how many games will be played to determine the winner.
        computerPoints = 0;
        userPoints = 0;// set points to zero.
        int bestOff = 0;
        String check;
        boolean oddNumber = false;
        while (!oddNumber) {
            System.out.println("best off?: ");
            System.out.println("Please input an odd number");
            check = input.nextLine();
            close(check);
            if( check.isEmpty()) {
                System.out.println("feil verdi. prøv igjen");

                continue;
            }

            try
            {

                //nextLine will throw NumberFormatException
                //if the next token does not match the Integer
                //regular expression, or is out of range

                check = "" + check.charAt(0);
                  bestOff =Integer.parseInt(check);


            }
            catch(NumberFormatException exception)
            {
                //Print "This is not an integer"
                //when user put other than integer
                System.out.println("This is not an integer \n");

                continue;
            }

            if (bestOff % 2 == 1)
                oddNumber = true;
            else {
                System.out.println("Need to be an odd number");
                continue;
            }

        }
        this.bestOff = bestOff;
    }


    public int rngNumber(){
        return rng.nextInt(3);
    }//generate a random number 0-2.

    public String compInput(int rnd ){
        String nextHand ="";

            switch (rnd)
            {
                case 0: nextHand = "stein";
                break;
                case 1: nextHand = "saks";
                break;
                case 2: nextHand = "papir";
                break;
            }
            return nextHand;
    }
    public String userInput() {
        String userHand ="";
        boolean check = false;
        while (check == false) {
            System.out.println("stein - saks - papir ?");
            userHand = input.nextLine().toLowerCase();
            close(userHand);

            if (userHand.equals("stein")  || userHand.equals("saks") || userHand.equals("papir"))
                check = true;


            else {
                System.out.println("**** FEIL VERDI ****");
                System.out.println("**** PRØV IGJEN ****");
                continue;
            }
        }
        return userHand;
    }
    public String userHand(String userHand, String compHand){

        if(userHand.equals(compHand)){
             result = draw;

        }
        else if(userHand.equals("saks")  && compHand.equals("papir")||
                userHand.equals("stein") && compHand.equals("saks") ||
                userHand.equals("papir") && compHand.equals("stein")){
            result = userWin;

        }
            else{
            result = compWin;

        }

        System.out.println("you chose: " + userHand  +
                            "\n*************** \n"
                            +"computer chose: " + compHand +
                            "\n*************** \n" +
                              result +
                            "\n*************** \n");
        return result;
    }

    public void points(String result)
    {
        if (result.equals(userWin)){
            userPoints ++;
        }
        else if(result.equals(compWin)){
            computerPoints ++;
        }
        System.out.println("curent user points: " + userPoints);
        System.out.println("curent computer points: " + computerPoints);
        System.out.println();
        System.out.println("*****Best of "+ bestOff+"*****");
        System.out.println();
        System.out.println();
    }

    public void start() {
        System.out.println("Choose you'r hand");
        String hand = userInput();
        points(userHand(hand, compInput(rngNumber())));

        if (userPoints >= bestOff / 2 + 1) {
            System.out.println("*******Congratulation. You win!*******");
        } else if (computerPoints >= bestOff / 2 + 1) {
            System.out.println("*******To bad. You lost*******");
        } else {
            start();
        }
        boolean check = false;


        while (!check) {
            System.out.println("\n Want to play again? *** Y/N ***");
            String svar = input.nextLine().toLowerCase();


            if (svar.isEmpty()){

                continue;}

            else if (svar.equals("y")) {
                check = true;
                bestOff();
                start();

            } else if (svar.equals("n")) {
                System.out.println("*******Thanks for playing.*******");
                System.out.println("*******Have a nice day.*******");
                check = true;
                System.exit(0);

            }


        }
    }



}

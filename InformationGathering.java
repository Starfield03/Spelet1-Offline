import java.awt.Color;
import java.util.Scanner;

public class InformationGathering {
    
    public static int numberOfPlayers(){ //Decides number of players
        Scanner sc=new Scanner(System.in); //Imports scanner
        
        int numberOfPlayers = 0; //Puts numberOfPlayers = 0 to initiate the while loop
        
        while(numberOfPlayers < 2 || numberOfPlayers > 4){ //While loop that forces the number of players to be between 2 and 4
            System.out.println("Number of players, between 2 and 4:");
            
            numberOfPlayers = sc.nextInt(); //Number of players
            
            System.out.println(" ");
        }
        
        return numberOfPlayers; //Sends back the number of players
    }
    
    public static int sideLength(){ //Decides side length of gameboard
        Scanner sc=new Scanner(System.in); //Imports scanner
        
        int sideLength = 0; //Puts sideLength = 0 to initiate the while loop
        
        while(sideLength < 2 || sideLength % 2 == 1 || sideLength > 4){ //While loop that forces the side length to be an even number between 6 and 12
            System.out.println("Side length of gameboard, even numbers between 6 and 12:");
            
            sideLength = sc.nextInt(); //Side length
            
            System.out.println(" ");
        }
        
        return sideLength; //Sends back the side length
    }
}

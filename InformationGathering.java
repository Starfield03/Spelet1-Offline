import java.awt.Color;
import java.util.Scanner;

public class InformationGathering {
    
    public static int numberOfPlayers(){
        
        Scanner sc=new Scanner(System.in);
        
        int numberOfPlayers = 0;
        //Initiates while-loop
        
        while(numberOfPlayers < 2 || numberOfPlayers > 4){
            //Number of players must be between 2 and 4
            
            System.out.println("Number of players, between 2 and 4:");
            
            numberOfPlayers = sc.nextInt();
            
            System.out.println(" ");
        }
        
        return numberOfPlayers;
    } //Add try-catch
    
    public static int sideLength(){
        
        Scanner sc=new Scanner(System.in);
        
        int sideLength = 0;
        //Initiates while-loop
        
        while(sideLength < 6 || sideLength % 2 == 1 || sideLength > 12){
            //Side length must be even numbers between 6 and 12
            
            System.out.println("Side length of gameboard, even numbers between 6 and 12:");
            
            sideLength = sc.nextInt();
            
            System.out.println(" ");
        }
        
        return sideLength;
    } //Add try-catch
    
    public static int numberOfWeapons(){
        
        Scanner sc=new Scanner(System.in);
        
        int numberOfWeapons = 0;
        //Initiates while-loop
        
        while(numberOfWeapons < 1 || numberOfWeapons > 4){
            //Number of weapons must be between 1 and 4
            
            System.out.println("Number of weapons per player, between 1 and 4:");
            
            numberOfWeapons = sc.nextInt();
            
            System.out.println(" ");
        }
        
        return numberOfWeapons;
    } //Add try-catch
    
    
    
    public static int[][] chooseWeapons(int numberOfPlayers, int numberOfWeapons){
        
        Scanner sc=new Scanner(System.in);
        
        int chooseWeapons[][] = new int[numberOfPlayers][numberOfWeapons];
        
        int weapons[][] = new int[numberOfPlayers][4];
                
        for(int i = 0 ; i < numberOfPlayers ; i++){
            //Which player
            
            if(numberOfWeapons == 1){
                
                System.out.println("Player " + (i + 1) + ", choose 1 weapon by entering a number (bomb = 1, laser = 2, dash = 3, phase = 4):");
            }
            
            else{
                
                System.out.println("Player " + (i + 1) + ", choose " + numberOfWeapons + " weapons by entering numbers seperated by spaces (bomb = 1, laser = 2, dash = 3, phase = 4):");
            }
            
            for(int j = 0 ; j < numberOfWeapons ; j++){
                
                chooseWeapons[i][j] = 0;
                
                while(chooseWeapons[i][j] < 1 || chooseWeapons[i][j] > 4){
                    //Weapons must be a bomb, laser, dash or phase
                    
                    chooseWeapons[i][j] = sc.nextInt();
                }
            }
            
            System.out.println(" ");
            
            for(int k = 0 ; k < numberOfWeapons ; k++){
                
                if(chooseWeapons[i][k] == 1){
                    
                    weapons[i][0]++;
                }
                
                else if(chooseWeapons[i][k] == 2){
                    
                    weapons[i][1]++;
                }
                
                else if(chooseWeapons[i][k] == 3){
                    
                    weapons[i][2]++;
                }
                
                else{
                    
                    weapons[i][3]++;
                }
            }
        }
        
        
        
        return weapons;
    } //Add try-catch
    
    public static int visualRepresentationOfWeapons(int numberOfPlayers, int numberOfWeapons, int weapons[][]){
        
        for(int i = 0 ; i < numberOfPlayers ; i++){
            
            System.out.print((i + 1) + ": ");
            
            for(int j = 0 ; j < 4 ; j++){
                
                if(j + 1 == 4){
                    
                    System.out.println(weapons[i][j]);
                }
                
                else{
                    
                    System.out.print(weapons[i][j] + " ");
                }
            }
        }
        
        return 0;
    }
            
            
    
    public static int[] getCoordinates(SimpleWindow gameboard, int sideLength, int player, int weapons[][]){
        
        int nextCoordinates[] = {sideLength, sideLength, 0};
        
        while(nextCoordinates[0] >= sideLength || nextCoordinates[1] >= sideLength){
            
            gameboard.waitForMouseClick();
            
            nextCoordinates[0] = (int) Math.floor(gameboard.getMouseX() / 50);
            nextCoordinates[1] = (int) Math.floor(gameboard.getMouseY() / 50);
            //Coordinates of next move
            
            if(nextCoordinates[0] >= sideLength + 2 && nextCoordinates[0] <= sideLength + 5 && nextCoordinates[1] == 2 && weapons[player - 1][nextCoordinates[0] - sideLength - 2] >= 1){
                //If player presses a weapon which they posses
                
                if(nextCoordinates[2] == nextCoordinates[0] - sideLength - 1){
                    //If player presses the same weapon twice in a row it gets deselected
                    
                    Drawing.eraseWeaponMark(gameboard, sideLength, nextCoordinates[2]);
                    
                    nextCoordinates[2] = 0;
                }
                
                else{
                    //Weapon gets selected, old weapon gets deselected
                    
                    Drawing.eraseWeaponMark(gameboard, sideLength, nextCoordinates[2]);
                    
                    nextCoordinates[2] = nextCoordinates[0] - sideLength - 1;
                    
                    Drawing.drawWeaponMark(gameboard, sideLength, player, nextCoordinates[2]);
                }
            }
        }
        
        Drawing.eraseWeaponMark(gameboard, sideLength, nextCoordinates[2]);
        //Erases the mark so that the next player can have a clean slate
        
        return nextCoordinates;
        //nextCoordinates[0] = next x coordinate
        //nextCoordinates[1] = next y coordinate
        //nextCoordinates[2] = which weapon gets used, or if no weapon gets used
    }
}

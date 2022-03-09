public class Positions {
    
    public static boolean[] startPositions(int sideLength, int numberOfPlayers){ //Creates startpositions
        boolean takenPositions[] = new boolean[(sideLength + 2) * (sideLength + 2)]; //Creates boolean array
        
        for(int i = 0 ; i < (sideLength + 2) * (sideLength + 2) ; i++){ //Cycles through all positions in array
            
            if((i >= 0 && i < sideLength + 2) || ((i >= (sideLength + 2) * (sideLength + 1)) && (i < (sideLength + 2) * (sideLength + 2)))){ //If the position is somewhere along the outer walls, need to finish the left and right side
                takenPositions[i] = true; //Position is taken
            }
            
            else{ //If the position isn't along the outer walls
                takenPositions[i] = false; //Position not taken
            }
        }
        
        if(numberOfPlayers == 2){
            takenPositions[(sideLength + 2) * 2 + 2] = true; //Startposition of player 1 is taken
            
            takenPositions[(sideLength + 2) * 3 - 3] = true; //Startposition of player 2 is taken
        }
        
        else if(numberOfPlayers == 3){
            takenPositions[(sideLength + 2) * 2 + 2] = true; //Startposition of player 1 is taken
            
            takenPositions[(sideLength + 2) * 3 - 3] = true; //Startposition of player 2 is taken
            
            takenPositions[(sideLength + 2) * sideLength - 3] = true; //Startposition of player 2 is taken
        }
        
        else{
            takenPositions[(sideLength + 2) * 2 + 2] = true; //Startposition of player 1 is taken
            
            takenPositions[(sideLength + 2) * 3 - 3] = true; //Startposition of player 2 is taken
            
            takenPositions[(sideLength + 2) * sideLength - 3] = true; //Startposition of player 2 is taken
            
            takenPositions[(sideLength + 2) * (sideLength - 1) + 2] = true; //Startposition of player 2 is taken
        }
        
        for(int j = 0 ; j < ((sideLength + 2) * (sideLength + 2)) ; j++){ //Temporary
            if(j == 7 || j == 15 || j == 15 || j == 23 || j == 31 || j == 39 || j == 47 || j == 55){
                System.out.println(takenPositions[j]);
            }
            
            else{
                System.out.print(takenPositions[j]);
                System.out.print(" ");
            }
        }
        
        return takenPositions;
    }
}

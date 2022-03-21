public class Positions {
    
    public static boolean[] startPositions(int sideLength, int numberOfPlayers){ //Creates startpositions and an outer wall which players cannot get past through
        
        boolean takenPositions[] = new boolean[(sideLength + 2) * (sideLength + 2)]; //Creates boolean array
        
        for(int i = 0 ; i < (sideLength + 2) * (sideLength + 2) ; i++){ //Cycles through all positions in array
            
            if((i >= 0 && i < sideLength + 2) || ((i >= (sideLength + 2) * (sideLength + 1)) && (i < (sideLength + 2) * (sideLength + 2))) || (i + 1) % (sideLength + 2) == 0 || (i + 1) % (sideLength + 2) == 1){ //If the position is somewhere along the outer walls its taken
                
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
        
        //visualRepresentation(takenPositions, sideLength); //Not necessary, only for greater understanding of the positions
        
        return takenPositions;
    }
    
    public static boolean[] nextPosition(boolean takenPositions[], int sideLength, int nextPositionX, int nextPositionY){
        
        takenPositions[(sideLength + 2) * (nextPositionY + 1) + nextPositionX + 1] = true; //Position gets taken
        
        return takenPositions;
    }
    
    
    
    public static int visualRepresentation(boolean takenPositions[], int sideLength){ //Prints a visual representation of the taken positions, just for testing
        
        for(int j = 0 ; j < ((sideLength + 2) * (sideLength + 2)) ; j++){ //Goes through all the positions
            
            if((j + 1) % (sideLength + 2) == 0){
                
                System.out.println(takenPositions[j]);
            }
            
            else{
                
                System.out.print(takenPositions[j]);
                
                if(takenPositions[j] == true){
                    
                    System.out.print(" ");
                }
                
                System.out.print(" ");
            }
        }
        
        return 0;
    }
    
    
    
    public static int legalityOfMove(boolean takenPositions[], int sideLength, int activePositionX, int activePositionY, int nextPositionX, int nextPositionY){
        
        if(nextPositionX == activePositionX && nextPositionY == activePositionY){ //Player cant step on the same square that they are standing on
            
            return 1;
        }
        
        else if(nextPositionX < activePositionX - 1 || nextPositionX > activePositionX + 1 || nextPositionY < activePositionY - 1 || nextPositionY > activePositionY + 1){ //Cant move more than 1 step in each direction
            
            return 1;
        }
        
        else if(takenPositions[(sideLength + 2) * (nextPositionY + 1) + nextPositionX + 1] == true){ //Cant move to a taken position
            
            return 1;
        }
        
        else{ //Can move
            
            return 0;
        }
    }
    
    public static boolean canPlayerMove(boolean takenPositions[], int sideLength, int activePositionX, int activePositionY){
        
        boolean positions[] = new boolean[8];
        
        positions[0] = takenPositions[(sideLength + 2) * (activePositionY) + activePositionX]; //Above left
        positions[1] = takenPositions[(sideLength + 2) * (activePositionY) + activePositionX + 1]; //Above
        positions[2] = takenPositions[(sideLength + 2) * (activePositionY) + activePositionX + 2]; //Above right
        positions[3] = takenPositions[(sideLength + 2) * (activePositionY + 1) + activePositionX + 2]; //Right
        positions[4] = takenPositions[(sideLength + 2) * (activePositionY + 2) + activePositionX + 2]; //Below right
        positions[5] = takenPositions[(sideLength + 2) * (activePositionY + 2) + activePositionX + 1]; //Below 
        positions[6] = takenPositions[(sideLength + 2) * (activePositionY + 2) + activePositionX]; //Below left
        positions[7] = takenPositions[(sideLength + 2) * (activePositionY + 1) + activePositionX]; //Left
        
        int counter = 0;
        
        for(int i = 0 ; i < 8 ; i++){
            
            if(positions[i] == true){
                
                counter++;
            }
        }
        
        if(counter == 8){ //If every square around the player is taken the player loses
            
            return false;
        }
        
        else{
            
            return true;
        }
    }
    
    
    
    public static int[] activeStartPositions(int numberOfPlayers, int sideLength){
        
        int activePositions[] = new int[numberOfPlayers * 2];
        
        if(numberOfPlayers == 2){ //2 player game
            
            activePositions[0] = 1; //Player 1 X
            activePositions[1] = 1; //Player 1 Y
            
            activePositions[2] = sideLength - 2; //Player 2 X
            activePositions[3] = 1; //Player 2 Y
        }
        
        else if(numberOfPlayers == 3){ //3 player game
            
            activePositions[0] = 1; //Player 1 X
            activePositions[1] = 1; //Player 1 Y
            
            activePositions[2] = sideLength - 2; //Player 2 X
            activePositions[3] = 1; //Player 2 Y
            
            activePositions[4] = sideLength - 2; //Player 3 X
            activePositions[5] = sideLength - 2; //Player 3 Y
        }
        
        else{ //4 player game
            
            activePositions[0] = 1; //Player 1 X
            activePositions[1] = 1; //Player 1 Y
            
            activePositions[2] = sideLength - 2; //Player 2 X
            activePositions[3] = 1; //Player 2 Y
            
            activePositions[4] = sideLength - 2; //Player 3 X
            activePositions[5] = sideLength - 2; //Player 3 Y
            
            activePositions[6] = 1; //Player 3 X
            activePositions[7] = sideLength - 2; //Player 3 Y
        }
        
        return activePositions;
    }
}
public class Positions {
    
    public static boolean[][] boundaryPositions(int sideLength){
        
        boolean positions[][] = new boolean[sideLength + 2][sideLength + 2];
        
        for(int i = 0 ; i < sideLength + 2 ; i++){
            
            for(int j = 0 ; j < sideLength + 2 ; j++){
                
                if(i == 0 || j == 0 || i == sideLength + 1 || j == sideLength + 1){
                    
                    positions[i][j] = true;
                }
                
                else{
                    
                    positions[i][j] = false;
                }
            }
        }
        
        return positions;
    }
    
    public static boolean[][] playerStartPositions(boolean positions[][], int sideLength, int numberOfPlayers){
        
        positions[2][2] = true;
        
        positions[sideLength - 1][2] = true;
        
        if(numberOfPlayers > 2){
            
            positions[sideLength - 1][sideLength - 1] = true;
            
            if(numberOfPlayers == 4){
                
                positions[2][sideLength - 1] = true;
            }
        }
        
        return positions;
    }
    
    
    
    public static boolean[][] nextPosition(boolean positions[][], int sideLength, int nextX, int nextY){
        
        positions[nextX + 1][nextY + 1] = true;
        
        return positions;
    }
    
    
    
    public static int[][] plantBomb(SimpleWindow gameboard, int numberOfPlayers, int bombs[][], int activePositionX, int activePositionY){
        
        bombs[activePositionX][activePositionY] = 2 * numberOfPlayers;
        
        return bombs;
    }
    
    public static int[][] bombTimer(SimpleWindow gameboard, boolean positions[][], int numberOfPlayers, int bombs[][], int sideLength, int activePositions[]){
        
        for(int i = 0 ; i < sideLength ; i ++){
            
            for(int j = 0 ; j < sideLength ; j++){
                
                if(bombs[i][j] > 0){
                    
                Drawing.drawBombCounter(gameboard, numberOfPlayers, bombs[i][j], i, j);
                    
                    if(bombs[i][j] == 1){
                        
                        erasePositionBomb(gameboard, positions, bombs, numberOfPlayers, sideLength, i, j, activePositions);
                    }
                    
                    bombs[i][j]--;
                }
            }
        }
        
        return bombs;
    }
    
    public static boolean[][] erasePositionBomb(SimpleWindow gameboard, boolean positions[][], int bombs[][], int numberOfPlayers, int sideLength, int bombX, int bombY, int activePositions[]){
        
        for(int i = -1 ; i < 2; i++){
            
            for(int j = -1 ; j < 2 ; j++){
                
                if(bombX + 1 + j == 0 || bombX + 1 + j == sideLength + 1 || bombY + 1 + i == 0 || bombY + 1 + i == sideLength + 1){
                    //If the bomb is along a wall it wont destroy the wall
                }
                
                else{
                    //The bomb destroys squares
                    
                    int counter = 0;
                    
                    for(int k = 0 ; k < numberOfPlayers ; k++){
                        
                        int activePositionX = activePositions[(k + 1) * 2 - 2];
                        int activePositionY = activePositions[(k + 1) * 2 - 1];
                        
                        if(bombX + j == activePositionX && bombY + i == activePositionY){
                            
                            counter++;
                        }
                    }
                    
                    if(counter > 0){
                        //The bomb cannot remove players
                    }
                    
                    else if(bombs[bombX + j][bombY + i] > 1){
                        //The bomb cannot remove other bombs
                    }
                    
                    else{
                        
                        Drawing.eraseSquaresBomb(gameboard, numberOfPlayers, sideLength, bombX, bombY, activePositions, j, i);
                    
                        positions[bombX + 1 + j][bombY + 1 + i] = false;
                    }
                }
            }
        }
        
        return positions;
    } //Something wrong with "The bomb cannot remove other bombs" part
    
    
    
    public static boolean[][] erasePositionLaser(boolean positions[][], int sideLength, int numberOfPlayers, int activePositionX, int activePositionY, int nextPositionX, int nextPositionY, int activePositions[]){
        
        
        int slopeX = nextPositionX - activePositionX;
        int slopeY = nextPositionY - activePositionY;
        //The direction the laser is travelling 
        
        int positionX = nextPositionX + slopeX;
        int positionY = nextPositionY + slopeY;
        //The position of the tip of the laserbeam
        
        int counter = 0;
        
        while(true){
            
            for(int i = 0 ; i < numberOfPlayers ; i++){
                
                if(positionX == activePositions[(i + 1) * 2 - 2] && positionY == activePositions[(i + 1) * 2 - 1]){
                    //If the laser crosses paths with a player the counter will go up
                    
                    counter++;
                }
            }
            
            if(positionX + 1 == 0 || positionX + 1 == sideLength + 1 || positionY + 1 == 0 || positionY + 1 == sideLength + 1 || counter > 0){
                //If the laser reaches the edge of the gameboard, or a player, it will stop destroying stuff
                
                break;
            }
            
            else{
                //The laser gets fired and removes the squares in its way
                
                positions[positionX + 1][positionY + 1] = false;
                
                positionX = positionX + slopeX;
                positionY = positionY + slopeY;
                //The position of the tip of the laserbeam
            }
        }
        
        return positions;
    }
    
    
    
    public static int visualRepresentation(boolean positions[][], int sideLength){
        
        System.out.print("  ");
        
        for(int i = 0 ; i < sideLength + 2 ; i++){
            
            for(int j = 0 ; j < sideLength + 2 ; j++){
                
                if((j + 1) % (sideLength + 2) == 0){
                    
                    System.out.println(positions[j][i]);
                    
                    if(positions[j][i] == true){
                        
                        System.out.print("  ");
                    }
                    
                    else{
                        
                        System.out.print(" ");
                    }
                }
                
                else{
                    
                    System.out.print(positions[j][i]);
                    
                    if(positions[j][i] == true){
                        
                        System.out.print("  ");
                    }
                    
                    else{
                        
                        System.out.print(" ");
                    }
                }
            }
        }
        
        return 0;
    }
    
    
    
    public static boolean legalityOfMove(boolean positions[][], int sideLength, int numberOfPlayers, int players[], int activePositions[], int activePositionX, int activePositionY, int nextPositionX, int nextPositionY, int weapon, int bombs[][]){
        
        if(nextPositionX == activePositionX && nextPositionY == activePositionY){
            //The player cannot move to the same square they are standing on
            
            return false;
        }
        
        else if(bombs[nextPositionX][nextPositionY] > 0){
            //The player cannot move over a active bomb with any kind of ability
            
            return false;
        }
        
        else if(nextPositionX < activePositionX - 1 || nextPositionX > activePositionX + 1 || nextPositionY < activePositionY - 1 || nextPositionY > activePositionY + 1){
            //The player cannot move more than one square in each direction
            
            return false;
        }
        
        else if(positions[nextPositionX + 1][nextPositionY + 1] == true){
            //The player cannot move to a taken spot unless they use the laser or phase weapon
            
            if(weapon != 2 && weapon != 4){
                //They choose not to use the weapon which makes them unable to move to the taken position
                
                return false;
            }
            
            else{
                //They choose to use the weapon which makes them able to move to the taken position
                
                for(int i = 0 ; i < numberOfPlayers ; i++){
                    
                    if(nextPositionX == activePositions[(i + 1) * 2 - 2] && nextPositionY == activePositions[(i + 1) * 2 - 1] && players[i] != 0){
                        //If the next position is on any players, that is not dead, active position
                        
                        return false;
                    }
                }
                
                return true;
            }
        }
        
        else{
            //The player moves
            
            return true;
        }
    }
    
    public static boolean canPlayerMove(boolean positions[][], int sideLength, int activePositionX, int activePositionY, int weaponStatusLaser, int weaponStatusPhase){
        
        if(weaponStatusLaser > 0 || weaponStatusPhase > 0){
            
            return true;
        }
        
        else{
            
            boolean positionsAroundPlayer[] = new boolean[8];
        
            positionsAroundPlayer[0] = positions[activePositionX][activePositionY];
            //Left corner over player
            positionsAroundPlayer[1] = positions[activePositionX + 1][activePositionY];
            //Over player
            positionsAroundPlayer[2] = positions[activePositionX + 2][activePositionY];
            //Right corner over player
            positionsAroundPlayer[3] = positions[activePositionX][activePositionY + 1];
            //Left of player
            positionsAroundPlayer[4] = positions[activePositionX + 2][activePositionY + 1];
            //Right of player
            positionsAroundPlayer[5] = positions[activePositionX][activePositionY + 2];
            //Left corner below player
            positionsAroundPlayer[6] = positions[activePositionX + 1][activePositionY + 2];
            //Below player
            positionsAroundPlayer[7] = positions[activePositionX + 2][activePositionY + 2];
            //Right corner below player
            
            int counter = 0;
            
            for(int i = 0 ; i < 8 ; i++){
                
                if(positionsAroundPlayer[i] == true){
                    counter++;
                }
            }
            
            if(counter == 8){
                
                return false;
            }
            
            else{
                
                return true;
            }
        }
    }
    
    
    
    public static int[] activeStartPositions(int numberOfPlayers, int sideLength){
        int activePositions[] = new int[numberOfPlayers * 2];
        
        activePositions[0] = 1;
        activePositions[1] = 1;
        //Player 1 X & Y
            
        activePositions[2] = sideLength - 2;
        activePositions[3] = 1;
        //Player 2 X & Y
        
        if(numberOfPlayers > 2){
            
            activePositions[4] = sideLength - 2;
            activePositions[5] = sideLength - 2;
            //Player 3 X & Y
            
            if(numberOfPlayers == 4){
                
                activePositions[6] = 1;
                activePositions[7] = sideLength - 2;
                //Player 4 X & Y
            }
        }
        
        return activePositions;
    }
}

import java.awt.Color;

public class Phases {

    public static void main(String[] args) {
        
        informationPhase();
    }
    
    
    
    public static void informationPhase(){
        
        int numberOfPlayers = InformationGathering.numberOfPlayers();
        
        int sideLength = InformationGathering.sideLength();
        
        int numberOfWeapons = InformationGathering.numberOfWeapons();
                    
        int weapons[][] = InformationGathering.chooseWeapons(numberOfPlayers, numberOfWeapons);
        
        preGamePhase(numberOfPlayers, sideLength, numberOfWeapons, weapons);
    }
    
    
    
    public static void preGamePhase(int numberOfPlayers, int sideLength, int numberOfWeapons, int weapons[][]){
        
        boolean positions[][] = Positions.playerStartPositions(Positions.boundaryPositions(sideLength), sideLength, numberOfPlayers);
        
        int activePositions[] = Positions.activeStartPositions(numberOfPlayers, sideLength);
        
        int players[] = new int[numberOfPlayers];
        
        for(int i = 0 ; i < numberOfPlayers ; i++){
            
            players[i] = i + 1;
        }
        
        SimpleWindow gameboard = new SimpleWindow(sideLength * 50 + 350, sideLength * 50 + 1, "Gameboard");
        
        Drawing.drawGrid(gameboard, sideLength);
        Drawing.drawWeaponIcons(gameboard, sideLength, numberOfPlayers);
        Drawing.drawRound(gameboard, sideLength);
        Drawing.drawTurn(gameboard, sideLength);
        Drawing.drawWeaponMarkOutline(gameboard, sideLength);
        //Draws the static markings on the gameboard
        
        for(int i = 0 ; i < numberOfPlayers ; i++){
            
            Drawing.drawNewSquares(gameboard, (i + 1), activePositions[(i + 1) * 2 - 2], activePositions[(i + 1) * 2 - 1]);
        }
        
        boolean deadOrAlive[] = new boolean[numberOfPlayers];
        //The array that keeps track of who is dead and alive, "true" = alive, "false" = dead
        
        for(int i = 0 ; i < numberOfPlayers ; i++){
            
            deadOrAlive[i] = true;
            //Set all players to alive
        }
        
        int bombs[][] = new int[sideLength][sideLength];
        //Bombs location array
        
        gamePhase(gameboard, sideLength, numberOfPlayers, deadOrAlive, 1, 1, weapons, positions, players, activePositions, bombs);
    }
    
    
    
    public static void gamePhase(SimpleWindow gameboard, int sideLength, int numberOfPlayers, boolean deadOrAlive[], int turnCounter, int roundCounter, int weapons[][], boolean positions[][], int players[], int activePositions[], int bombs[][]){
        
        int player = players[(turnCounter + numberOfPlayers - 1) % numberOfPlayers];
        
        boolean canPlayerMove = Positions.canPlayerMove(positions, sideLength, activePositions[player * 2 - 2], activePositions[player * 2 - 1], weapons[player - 1][1], weapons[player - 1][3]);
        
        if(canPlayerMove == false){
                
            deadOrAlive[player - 1] = false;
            //Player dies
        }
        
        int counter = 0;
        
        for(int i = 0 ; i < numberOfPlayers; i++){
            //Counts how many players are dead
            
            if(deadOrAlive[i] == false){
                
                counter++;
            }
        }
        
        if(counter >= numberOfPlayers - 1){ 
            //If only one or no player lives
            
            if(player == numberOfPlayers){
                //If its the last players turn aka if the last round is completed
                
                int winner = 0;
                
                for(int i = 0 ; i < numberOfPlayers ; i++){
                    
                    if(deadOrAlive[i] == true){
                        
                        winner = players[i];
                    }
                }
            
                if(winner > 0){ 
                    //If the game got no winner it becomes a tie
                    
                    endPhase(gameboard, sideLength, winner, numberOfPlayers, false);
                }
            
                else{ 
                    //If the game got a winner that player wins
                    
                    endPhase(gameboard, sideLength, winner, numberOfPlayers, true);
                }
            }
            
            else{
                //If the last round is not completed the turn moves on to the next player

                turnCounter++;
                
                gamePhase(gameboard, sideLength, numberOfPlayers, deadOrAlive, turnCounter, roundCounter, weapons, positions, players, activePositions, bombs);
            }
        }
        
        else if(deadOrAlive[player - 1] == false){ 
            //If player is dead, skip to next person
            
            if(player == numberOfPlayers){ 
                //If it is the last players turn
                
                roundCounter++;
            }
            
            turnCounter++;
            
            bombs = Positions.bombTimer(gameboard, positions, numberOfPlayers, bombs, sideLength, activePositions);
            
            gamePhase(gameboard, sideLength, numberOfPlayers, deadOrAlive, turnCounter, roundCounter, weapons, positions, players, activePositions, bombs);
        }
        
        else{
            //If several players are alive
            
            Drawing.drawWeaponCounter(gameboard, sideLength, numberOfPlayers, weapons);
            Drawing.drawRoundNumber(gameboard, sideLength, roundCounter);
            Drawing.drawTurnSquareAndNumber(gameboard, sideLength, player);
            
            for(int i = 0 ; i < numberOfPlayers ; i++){
                
                if(deadOrAlive[i] == false){
                    
                    Drawing.drawOverDeadPlayer(gameboard, i + 1, sideLength);
                    //Draw over the dead players name and weapons to show that they are dead
                }
            }
            
            int coordinates[] = InformationGathering.getCoordinates(gameboard, sideLength, player, weapons); //Need player counter
           
            int nextPositionX = coordinates[0];
            int nextPositionY = coordinates[1];
            int weapon = coordinates[2];
            int activePositionX = activePositions[player * 2 - 2];
            int activePositionY = activePositions[player * 2 - 1];
            
            boolean legalityOfMove = Positions.legalityOfMove(positions, sideLength, numberOfPlayers, players, activePositions, activePositionX, activePositionY, nextPositionX, nextPositionY, weapon, bombs);
            
            if(legalityOfMove == true){
                //If the players move is legal, it gets executed
                
                Drawing.drawNewSquares(gameboard, player, nextPositionX, nextPositionY);
                Drawing.drawOldSquares(gameboard, player, activePositionX, activePositionY);
            
                activePositions[player * 2 - 2] = nextPositionX;
                activePositions[player * 2 - 1] = nextPositionY;
            
                positions = Positions.nextPosition(positions, sideLength, nextPositionX, nextPositionY);
                
                if((turnCounter + numberOfPlayers - 1) % numberOfPlayers + 1 == numberOfPlayers){ 
                    //If it is the last players turn the round counter goes up
                    
                    roundCounter++;
                }
                    
                if(weapon == 0){
                    //If the player does not use any weapons
                        
                    turnCounter++;
                    //Turn goes to the next player
                }
                    
                if(weapon == 1){
                    //If the player uses the bomb weapon
                    
                    //Write more code, not finished
                    
                    Drawing.drawBomb(gameboard, activePositionX, activePositionY);
                    
                    bombs = Positions.plantBomb(gameboard, numberOfPlayers, bombs, activePositionX, activePositionY);
                    
                    weapons[player - 1][0]--;
                    //Decreases the amount of this weapon in the players arsenal by one
                        
                    turnCounter++;
                    //Turn goes to the next player
                }
                    
                else if(weapon == 2){
                    //If the player uses the laser weapon
                        
                    Drawing.eraseSquaresLaser(gameboard, sideLength, numberOfPlayers, activePositionX, activePositionY, nextPositionX, nextPositionY, activePositions);
                        
                    positions = Positions.erasePositionLaser(positions, sideLength, numberOfPlayers, activePositionX, activePositionY, nextPositionX, nextPositionY, activePositions);
                        
                    weapons[player - 1][1]--;
                    //Decreases the amount of this weapon in the players arsenal by one
                        
                    turnCounter++;
                    //Turn goes to the next player
                }
                    
                else if(weapon == 3){
                    //If the player uses the dash weapon
                        
                    weapons[player - 1][2]--;
                    //Decreases the amount of this weapon in the players arsenal by one
                }
                    
                else if(weapon == 4){
                    //If the player uses the phase weapon
                        
                    weapons[player - 1][3]--;
                    //Decreases the amount of this weapon in the players arsenal by one
                        
                    turnCounter++;
                    //Turn goes to the next player
                }
                
                bombs = Positions.bombTimer(gameboard, positions, numberOfPlayers, bombs, sideLength, activePositions);
                    
                gamePhase(gameboard, sideLength, numberOfPlayers, deadOrAlive, turnCounter, roundCounter, weapons, positions, players, activePositions, bombs);
            }
                
            else{
                //The players move is illegal and they get to try again
                
                gamePhase(gameboard, sideLength, numberOfPlayers, deadOrAlive, turnCounter, roundCounter, weapons, positions, players, activePositions, bombs);
            }
        }
    }//Not finished
    
    
    
    public static void endPhase(SimpleWindow gameboard, int sideLength, int winningPlayer, int numberOfPlayers, boolean tie){
        
        gameboard.setLineColor(Color.black);
        
        if(tie == true){ 
            //If game ends in a tie
            
            gameboard.moveTo(sideLength * 50 + 50, 250);
            gameboard.writeText("Game ended in a tie.");
        }
        
        else{ 
            //If game doesn't end in a tie
            
            String colour;
        
            if(winningPlayer == 1){
            
                colour = "(Red)";
            }
        
            else if(winningPlayer == 2){
            
                colour = "(Blue)";
            }
        
            else if(winningPlayer == 3){
            
                colour = "(Green)";
            }
        
            else{
            
                colour = "(Yellow)";
            }
            
            gameboard.moveTo(sideLength * 50 + 50, 250);
            gameboard.writeText("Player " + winningPlayer + " " + colour + " won.");
        }
    }
}
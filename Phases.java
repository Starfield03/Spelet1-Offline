
import java.awt.Color;

public class Phases {

    public static void main(String[] args) {
        
        informationPhase();
    }
    
    public static void informationPhase(){ //Gathers information nad creates the gameboard
        
        int numberOfPlayers = InformationGathering.numberOfPlayers(); //Number of players
        
        int players[] = new int[numberOfPlayers]; //Array with number of players
        
        for(int i = 0 ; i < numberOfPlayers ; i++){ //Sets array numbers to player numbers
            
            players[i] = i + 1;
        }
        
        int sideLength = InformationGathering.sideLength(); //Side length of gameboard
        
        preGamePhase(numberOfPlayers, players, sideLength); //Starts the pre game phase
    }
    
    
    
    public static void preGamePhase(int numberOfPlayers, int players[], int sideLength){ //Sets up everything
        
        SimpleWindow gameboard = new SimpleWindow(sideLength * 50 + 101, sideLength * 50 + 1, "gameboard"); //Creates gameboard
        
        Drawing.drawGrid(gameboard, sideLength); //Draws grid
        Drawing.drawRound(gameboard, sideLength); //Draws "Round:"
        Drawing.drawTurn(gameboard, sideLength); //Draws "Turn:"
        
        boolean takenPositions[] = Positions.startPositions(sideLength, numberOfPlayers); //Creates startpositions and outer walls
        
        int activePositions[] = Positions.activeStartPositions(numberOfPlayers, sideLength); //Active positions
        
        for(int i = 1 ; i <= numberOfPlayers ; i++){
            
            Drawing.drawNewSquares(gameboard, i, activePositions[i * 2 - 2], activePositions[i * 2 - 1]); //Paints first active positions
        }
        
        boolean deadOrAlive[] = new boolean[numberOfPlayers];
        
        for(int i = 0 ; i < numberOfPlayers ; i++){
            
            deadOrAlive[i] = true;
        }
        
        gamePhase(gameboard, numberOfPlayers, players, deadOrAlive, sideLength, 1, 1, takenPositions, activePositions); //Starts the game phase
    }
    
    
    
    public static void gamePhase(SimpleWindow gameboard, int numberOfPlayers, int players[], boolean deadOrAlive[], int sideLength, int roundCounter, int turnCounter, boolean takenPositions[], int activePositions[]){
        
        int player = players[(turnCounter + numberOfPlayers - 1) % numberOfPlayers]; //Which players turn it is
        boolean playerStatus = deadOrAlive[player - 1];
        
        boolean canPlayerMove = Positions.canPlayerMove(takenPositions, sideLength, activePositions[player * 2 - 2], activePositions[player * 2 - 1]);
        
        if(canPlayerMove == false){
                
            deadOrAlive[player - 1] = false; //Player dies
        }
        
        int counter = 0;
        
        for(int i = 0 ; i < numberOfPlayers; i++){
            
            if(deadOrAlive[i] == false){
                
                counter++;
            }
        }
        
        if(counter >= numberOfPlayers - 1){ //If only one player lives
            
            if(player == numberOfPlayers){
                
                System.out.println("1");
                
                int winner = 0;
                
                for(int i = 0 ; i < numberOfPlayers ; i++){
                    
                    System.out.println(deadOrAlive[i]);
                    
                    if(deadOrAlive[i] == true){
                        
                        winner = players[i];
                    }
                }
            
                if(winner > 0){ //If the last player alive can move next round, that player wins
                    
                    System.out.println("1.1");
                    
                    endPhase(gameboard, sideLength, winner, numberOfPlayers, false);
                }
            
                else{ //If the last player alive can't move next round, the game becomes a tie
                    
                    System.out.println("1.2");
                    
                    endPhase(gameboard, sideLength, winner, numberOfPlayers, true);
                }
            }
            
            else{
                
                System.out.println("2");
                
                turnCounter++;
                
                gamePhase(gameboard, numberOfPlayers, players, deadOrAlive, sideLength, roundCounter, turnCounter, takenPositions, activePositions);
            }
        }
        
        else if(playerStatus == false){ //If player is dead, skip to next person
            
            System.out.println("3");
            
            if(player == numberOfPlayers){ //If it is the last players turn
                
                roundCounter++;
            }
            
            turnCounter++;
            
            gamePhase(gameboard, numberOfPlayers, players, deadOrAlive, sideLength, roundCounter, turnCounter, takenPositions, activePositions); //Repeats game phase process
        }
        
        else{
            
            Drawing.drawRoundNumber(gameboard, sideLength, roundCounter);
            Drawing.drawTurnCircle(gameboard, player, sideLength);
        
            int nextCoordinates[] = InformationGathering.getCoordinates(gameboard, sideLength); //Next move
        
            int nextPositionX = nextCoordinates[0]; //Next move X
            int nextPositionY = nextCoordinates[1]; //Next move Y
        
            int legality = Positions.legalityOfMove(takenPositions, sideLength, activePositions[player * 2 - 2], activePositions[player * 2 - 1], nextPositionX, nextPositionY);
        
            if(legality == 0){ //Players move is legal
                    
                Drawing.drawNewSquares(gameboard, player, nextPositionX, nextPositionY); //Draw new circles, needs restriction
                Drawing.drawOldSquares(gameboard, player, activePositions[player * 2 - 2], activePositions[player * 2 - 1]); //Redraws old circle with darker colour
       
                takenPositions = Positions.nextPosition(takenPositions, sideLength, nextPositionX, nextPositionY); //Temporary
       
                if((turnCounter + numberOfPlayers - 1) % numberOfPlayers + 1 == numberOfPlayers){ //If it is the last players turn
                        
                    roundCounter++;
                }
        
                turnCounter++;
        
                activePositions[player * 2 - 2] = nextPositionX;
                activePositions[player * 2 - 1] = nextPositionY;
        
                gamePhase(gameboard, numberOfPlayers, players, deadOrAlive, sideLength, roundCounter, turnCounter, takenPositions, activePositions); //Repeats game phase process
            }
        
            else if(legality == 1){ //Player can move but does so outside of boundaries
                    
                gamePhase(gameboard, numberOfPlayers, players, deadOrAlive, sideLength, roundCounter, turnCounter, takenPositions, activePositions); //Repeats game phase process
            }
        }
    }
    
    
    
    public static void endPhase(SimpleWindow gameboard, int sideLength, int player, int numberOfPlayers, boolean tie){
        
        gameboard.setLineColor(Color.black);
        
        if(tie == true){ //If game ends in a tie
            
            gameboard.moveTo(sideLength * 50 + 10, 250);
            gameboard.writeText("Game ended");
            
            gameboard.moveTo(sideLength * 50 + 10, 262);
            gameboard.writeText("in a tie.");
        }
        
        else{ //If game doesn't end in a tie
        
            String colour;
        
            if(player == 1){ //If winning player is 1
            
                colour = "(Red)";
            }
        
            else if(player == 2){ //If winning player is 2
            
                colour = "(Blue)";
            }
        
            else if(player == 3){ //If winning player is 3
            
                colour = "(Green)";
            }
        
            else{ //If winning player is 4
            
                colour = "(Yellow)";
            }
            
            gameboard.moveTo(sideLength * 50 + 10, 250);
            gameboard.writeText("Player " + player);
            
            gameboard.moveTo(sideLength * 50 + 10, 262);
            gameboard.writeText(colour + " won.");
        }
    }
}
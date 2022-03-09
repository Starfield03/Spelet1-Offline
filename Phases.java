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
        
        preGamePhase(numberOfPlayers, players, sideLength); //Calls preGamePhase
    }
    
    
    
    public static void preGamePhase(int numberOfPlayers, int players[], int sideLength){ //Sets up everything
        SimpleWindow gameboard = new SimpleWindow(sideLength * 50 + 101, sideLength * 50 + 1, "gameboard"); //Creates gameboard
        
        Drawing.drawGrid(gameboard, sideLength); //Draws grid
        Drawing.drawRound(gameboard, sideLength); //Draws "Round:"
        Drawing.drawTurn(gameboard, sideLength); //Draws "Turn:"
        
        int roundCounter = 1; //Roundcounter
        int turnCounter = 1; //Turncounter
        
        Positions.startPositions(sideLength, numberOfPlayers);
        
        gamePhase(gameboard, numberOfPlayers, players, sideLength);
    }
    
    
    
    public static void gamePhase(SimpleWindow gameboard, int numberOfPlayers, int players[], int sideLength){
        
        int nextCoordinates[] = InformationGathering.getCoordinates(gameboard, sideLength); //Next move
        
        int nextX = nextCoordinates[0]; //Next move X
        int nextY = nextCoordinates[1]; //Next move Y
        
        //Drawing.drawRoundNumber(gameboard, sideLength, 1);
        
        //Drawing.drawTurnCircle(gameboard, 1, sideLength);
        
        System.out.println(nextX + " " + nextY);
        
        gamePhase(gameboard, numberOfPlayers, players, sideLength);
    }
    
    
    
    public static void endPhase(){
        
    }
}
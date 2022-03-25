import java.awt.Color;

public class Drawing {
    
    public static int drawGrid(SimpleWindow gameboard, int sideLength){
        
        gameboard.setLineColor(Color.black);
        gameboard.setLineWidth(1);
        
        for(int i = 0 ; i < sideLength + 1 ; i++){
            
            gameboard.moveTo(0, i * 50);
            gameboard.lineTo(sideLength * 50, i * 50);
            //Draws horizontal lines
            
            gameboard.moveTo(i * 50, 0);
            gameboard.lineTo(i * 50, sideLength * 50);
            //Draws vertical lines
        }
        
        return 0;
    }
    
    
    
    public static int drawNewSquares(SimpleWindow gameboard, int player, int nextPositionX, int nextPositionY){
        
        
        if(player == 1){ 
            //Player 1
            
            Color player1 = new Color(255, 0, 0);
            gameboard.setLineColor(player1);
        }
        
        else if(player == 2){ 
            //Player 2
            
            Color player2 = new Color(0, 0, 255);
            gameboard.setLineColor(player2);
        }
        
        else if(player == 3){ 
            //Player 3
            
            Color player3 = new Color(0, 255, 0);
            gameboard.setLineColor(player3);
        }
        
        else{ 
            //Player 4
            
            Color player4 = new Color(255, 255, 0);
            gameboard.setLineColor(player4);
        }
        
        gameboard.square(nextPositionX * 50 + 1, nextPositionY * 50 + 1, 49);
        
        return 0;
    }
    
    public static int drawOldSquares(SimpleWindow gameboard, int player, int activePositionX, int activePositionY){
        
        if(player == 1){ 
            //Player 1
            
            Color player1 = new Color(180, 0, 0);
            gameboard.setLineColor(player1); 
        }
        
        else if(player == 2){ 
            //Player 2
            
            Color player2 = new Color(0, 0, 170);
            gameboard.setLineColor(player2);
        }
        
        else if(player == 3){ 
            //Player 3
            
            Color player3 = new Color(0, 180, 0);
            gameboard.setLineColor(player3);
        }
        
        else{ 
            //Player 4
            
            Color player4 = new Color(180, 180, 0);
            gameboard.setLineColor(player4);
        }
        
        gameboard.square(activePositionX * 50 + 1, activePositionY * 50 + 1, 49);
        
        return 0;
    }
    
    
    
    public static int drawBomb(SimpleWindow gameboard, int bombX, int bombY){
        
        gameboard.setLineColor(Color.black);
        
        gameboard.square(bombX * 50 + 4, bombY * 50 + 4, 43);
        
        return 0;
    }
    
    public static int drawBombCounter(SimpleWindow gameboard, int numberOfPlayers, int number, int bombX, int bombY){
        
        gameboard.setLineColor(Color.black);
        gameboard.square(bombX * 50 + 4, bombY * 50 + 4, 43);
        
        gameboard.setLineColor(Color.white);
        gameboard.moveTo(bombX * 50 + 21, bombY * 50 + 18);
        gameboard.writeText(((number + numberOfPlayers - 2) / numberOfPlayers) + " ");
        
        return 0;
    }
    
    public static int eraseSquaresBomb(SimpleWindow gameboard, int numberOfPlayers, int sideLength, int bombX, int bombY, int activePositions[], int j, int i){
        
        gameboard.setLineColor(Color.white);
        gameboard.square((bombX + j) * 50 + 1, (bombY + i) * 50 + 1, 49);
        
        gameboard.delay(100);
        
        return 0;
    }
    
    
    
    public static int eraseSquaresLaser(SimpleWindow gameboard, int sideLength, int numberOfPlayers, int activePositionX, int activePositionY, int nextPositionX, int nextPositionY, int activePositions[]){
        
        int slopeX = nextPositionX - activePositionX;
        int slopeY = nextPositionY - activePositionY;
        //The direction the laser is travelling 
        
        int positionX = nextPositionX + slopeX;
        int positionY = nextPositionY + slopeY;
        //The position of the tip of the laserbeam
        
        gameboard.setLineColor(Color.white);
        
        int counter = 0;
        
        while(true){
            
            for(int i = 0 ; i < numberOfPlayers ; i++){
                
                if(positionX == activePositions[(i + 1) * 2 - 2] && positionY == activePositions[(i + 1) * 2 - 1]){
                    //If the laser crosses paths with a player the counter will go up
                    
                    counter++;
                }
            }
            
            if(positionX == -1 || positionX == sideLength || positionY == -1 || positionY == sideLength || counter > 0){
                //If the laser reaches the edge of the gameboard, or a player, it will stop destroying stuff
                
                break;
            }
            
            else{
                //The laser gets fired and removes the squares in its way
                
                gameboard.square(positionX * 50 + 1, positionY * 50 + 1, 49);
                
                positionX = positionX + slopeX;
                positionY = positionY + slopeY;
                //The position of the tip of the laserbeam
                
                gameboard.delay(100);
                //Sets the delay between every block removal
            }
        }
        
        return 0;
    }
    
    
    
    public static int drawRound(SimpleWindow gameboard, int sideLength){
        
        gameboard.setLineColor(Color.black);
        gameboard.moveTo(sideLength * 50 + 50, 50);
        gameboard.writeText("Round:");
        
        return 0;
    }
    
    public static int drawRoundNumber(SimpleWindow gameboard, int sideLength, int roundCounter){
        
        gameboard.setLineColor(Color.white);
        gameboard.square(sideLength * 50 + 100, 40, 10);
        
        gameboard.setLineColor(Color.black);
        gameboard.moveTo(sideLength * 50 + 100, 50);
        gameboard.writeText(roundCounter + " ");
        
        return 0;
    }
    
    
    
    public static int drawTurn(SimpleWindow gameboard, int sideLength){
        
        gameboard.setLineColor(Color.black);
        gameboard.moveTo(sideLength * 50 + 200, 50);
        gameboard.writeText("Turn:");
        
        gameboard.setLineWidth(1);
        
        for(int i = 0 ; i < 2 ; i++){
            //Draws bounduary of turn square
            
            gameboard.moveTo(sideLength * 50 + 250, 28 + i * 50);
            gameboard.lineTo(sideLength * 50 + 300, 28 + i * 50);
            
            gameboard.moveTo(sideLength * 50 + 250 + i * 50, 28);
            gameboard.lineTo(sideLength * 50 + 250 + i * 50, 78);
        }
        
        gameboard.moveTo(sideLength * 50 + 253, 23);
        gameboard.writeText("Player");
        
        return 0;
    }
    
    public static int drawTurnSquareAndNumber(SimpleWindow gameboard, int sideLength, int player){
        
        gameboard.setLineColor(Color.white);
        gameboard.square(sideLength * 50 + 293, 13, 10);
        //Draws a white square over the previous turn number
        
        gameboard.setLineColor(Color.black);
        gameboard.moveTo(sideLength * 50 + 293, 23);
        gameboard.writeText(player + " ");
        //Writes which players turn it is
        
        Color turnSquare;
        
        if(player == 1){
            
            turnSquare = new Color(255, 0, 0);
        }
        
        else if(player == 2){
            
            turnSquare = new Color(0, 0, 255);
        }
        
        else if(player == 3){
            
            turnSquare = new Color(0, 255, 0);
        }
        
        else{
            
            turnSquare = new Color(255, 255, 0);
        }
        
        gameboard.setLineColor(turnSquare);
        gameboard.square(sideLength * 50 + 251, 29, 49);
        //Draws which players turn it is
        
        return 0;
    }
    
    
    
    public static int drawWeaponIcons(SimpleWindow gameboard, int sideLength, int numberOfPlayers){
        
        gameboard.setLineColor(Color.black);
        gameboard.setLineWidth(1);
        
        for(int i = 0 ; i < 2 ; i++){
            //Draws horizontal lines
            
            gameboard.moveTo(sideLength * 50 + 100, 100 + i * 50);
            gameboard.lineTo(sideLength * 50 + 300, 100 + i * 50);
        }
        
        for(int j = 0 ; j < 5 ; j++){
            //Draws vertical lines
            
            gameboard.moveTo(sideLength * 50 + 100 + 50 * j, 100);
            gameboard.lineTo(sideLength * 50 + 100 + 50 * j, 150);
        }
        
        gameboard.moveTo(sideLength * 50 + 109, 130);
        gameboard.writeText("BOMB");
        
        gameboard.moveTo(sideLength * 50 + 156, 130);
        gameboard.writeText("LASER");
        
        gameboard.moveTo(sideLength * 50 + 209, 130);
        gameboard.writeText("DASH");
        
        gameboard.moveTo(sideLength * 50 + 256, 130);
        gameboard.writeText("PHASE");
        
        for(int i = 0 ; i < numberOfPlayers ; i++){
            
            gameboard.moveTo(sideLength * 50 + 50, 150 + 15 + 20 * i);
            gameboard.writeText("Player " + (i + 1) + ":");
        }
        
        return 0;
    }
    
    public static int drawWeaponCounter(SimpleWindow gameboard, int sideLength, int numberOfPlayers, int weapons[][]){
        
        for(int i = 0 ; i < numberOfPlayers ; i++){
            
            for(int j = 0 ; j < 4 ; j++){
                
                gameboard.setLineColor(Color.white);
                gameboard.square(sideLength * 50 + 122 + j * 50,  156 + 20 * i, 9);
                //Draws over the old weapon numbers
                
                gameboard.setLineColor(Color.black);
                gameboard.moveTo(sideLength * 50 + 122 + j * 50, 150 + 15 + 20 * i);
                gameboard.writeText(weapons[i][j] + " ");
                //Draws the weapon numbers
            }
        }
        
        return 0;
    }
    
    
    
    public static int drawWeaponMarkOutline(SimpleWindow gameboard, int sideLength){
        
        gameboard.setLineColor(Color.black);
        gameboard.setLineWidth(1);
        
        for(int i = 0 ; i < 4 ; i++){
            
            gameboard.moveTo(sideLength * 50 + 120 + i * 50, 135);
            gameboard.lineTo(sideLength * 50 + 130 + i * 50, 135);
            gameboard.lineTo(sideLength * 50 + 130 + i * 50, 145);
            gameboard.lineTo(sideLength * 50 + 120 + i * 50, 145);
            gameboard.lineTo(sideLength * 50 + 120 + i * 50, 135);
            //Draws mark outlines
        }
        
        return 0;
    }
    
    public static int drawWeaponMark(SimpleWindow gameboard, int sideLength, int player, int weapon){
        
        Color weaponMark;
        
        if(player == 1){
            
            weaponMark = new Color(255, 0, 0);
        }
        
        else if(player == 2){
            
            weaponMark = new Color(0, 0, 255);
        }
        
        else if(player == 3){
            
            weaponMark = new Color(0, 255, 0);
        }
        
        else{
            
            weaponMark = new Color(255, 255, 0);
        }
        
        gameboard.setLineColor(weaponMark);
        
        gameboard.square(sideLength * 50 + 121 + (weapon - 1) * 50, 136, 9);
        
        return 0;
    }
    
    public static int eraseWeaponMark(SimpleWindow gameboard, int sideLength, int weapon){
        
        if(weapon == 0){
            //Nothing gets erased
        }
        
        else{
            //Previous mark gets erased
            
            gameboard.setLineColor(Color.white);
            
            gameboard.square(sideLength * 50 + 121 + (weapon - 1) * 50, 136, 9);
        }
        
        return 0;
    }
    
    
    
    public static int drawOverDeadPlayer(SimpleWindow gameboard, int player, int sideLength){
        
        gameboard.setLineColor(Color.black);
        gameboard.moveTo(sideLength * 50 + 50, 150 + 10 + 20 * (player - 1));
        gameboard.lineTo(sideLength * 50 + 300, 150 + 10 + 20 * (player - 1));
        
        return 0;
    }
}
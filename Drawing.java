import java.awt.Color;

public class Drawing {
    
    public static int drawGrid(SimpleWindow gameboard, int sideLength){ //Draws grid
        
        gameboard.setLineColor(Color.black); //Sets color to black
        
        for(int i = 0 ; i < sideLength + 1 ; i++){ //Loop
            
            gameboard.moveTo(i * 50, 0); //Moves cursor
            gameboard.lineTo(i * 50, sideLength * 50); //Draws line to other side of gameboard
            
            gameboard.moveTo(0, i * 50); //Moves cursor
            gameboard.lineTo(sideLength * 50, i * 50); //Draws line to other side of gameboard
        }
        
        return 0; //Returns
    }
    
    
    
    public static int drawNewSquares(SimpleWindow gameboard, int player, int nextPositionX, int nextPositionY){ //Draws new squares
        
        if(player == 1){ //Player 1
            
            Color player1 = new Color(255, 0, 0); //Custom color player 1
            gameboard.setLineColor(player1); //Sets color
        }
        
        else if(player == 2){ //Player 2
            
            Color player2 = new Color(0, 0, 255); //Custom color player 2
            gameboard.setLineColor(player2); //Sets color
        }
        
        else if(player == 3){ //Player 3
            
            Color player3 = new Color(0, 255, 0); //Custom color player 3
            gameboard.setLineColor(player3); //Sets color
        }
        
        else{ //Player 4
            
            Color player4 = new Color(255, 255, 0); //Custom color player 4
            gameboard.setLineColor(player4); //Sets color
        }
        
        gameboard.square(nextPositionX * 50 + 1, nextPositionY * 50 + 1, 49); //Creates square
        
        return 0; //Returns
    }
    
    public static int drawOldSquares(SimpleWindow gameboard, int player, int activePositionX, int activePositionY){ //Redraws old squares
        
        if(player == 1){ //Player 1
            
            Color player1 = new Color(180, 0, 0); //Custom color player 1
            gameboard.setLineColor(player1); //Sets color
        }
        
        else if(player == 2){ //Player 2
            
            Color player2 = new Color(0, 0, 170); //Custom color player 2
            gameboard.setLineColor(player2); //Sets color
        }
        
        else if(player == 3){ //Player 3
            
            Color player3 = new Color(0, 180, 0); //Custom color player 3
            gameboard.setLineColor(player3); //Sets color
        }
        
        else{ //Player 4
            
            Color player4 = new Color(180, 180, 0); //Custom color player 4
            gameboard.setLineColor(player4); //Sets color
        }
        
        gameboard.square(activePositionX * 50 + 1, activePositionY * 50 + 1, 49); //Creates square
        
        return 0; //Returns
    }
    
    
    
    public static int drawRound(SimpleWindow gameboard, int sideLength){ //Draws the text: "Round:"
        
        gameboard.setLineColor(Color.black); //Sets text color to black
        gameboard.moveTo(sideLength * 50 + 10, 50); //The position where the text is going to be at
        gameboard.writeText("Round:"); //The text
        
        return 0; //Returns
    }
    
    public static int drawRoundNumber(SimpleWindow gameboard, int sideLength, int roundCounter){ //Draws round number
        
        String round = Integer.toString(roundCounter); //Converts roundCounter to string
        
        gameboard.setLineColor(Color.white); //Sets square colour to white
        gameboard.square(sideLength * 50 + 60, 40, 20); //Draws square, its purpose is to cover the previous round number
        
        gameboard.setLineColor(Color.black); //Sets text colour to white
        gameboard.moveTo(sideLength * 50 + 60, 50); //The position where the text is going to be at
        gameboard.writeText(round); //The text, or number in this case
        
        return 0; //Returns
    }
    
    
    
    public static int drawTurn(SimpleWindow gameboard, int sideLength){ //Draws the text: "Turn:" and the black line around turn circle
        
        gameboard.setLineColor(Color.black); //Sets text color to black
        gameboard.moveTo(sideLength * 50 + 10, 150); //The position where the text is going to be at
        gameboard.writeText("Turn:"); //The text
        
        gameboard.circle(sideLength * 50 + 43, 118, 54); //Draws the black line around turn circle
        
        return 0; //Returns
    }
    
    public static int drawTurnCircle(SimpleWindow gameboard, int player, int sideLength){ //Draws turn circle
        
        if(player == 1){ //Player 1
            
            Color player1 = new Color(255, 0, 0); //Custom color player 1
            gameboard.setLineColor(player1); //Sets color
        }
        
        else if(player == 2){ //Player 1
            
            Color player2 = new Color(0, 0, 255); //Custom color player 2
            gameboard.setLineColor(player2); //Sets color
        }
        
        else if(player == 3){ //Player 3
            
            Color player3 = new Color(0, 255, 0); //Custom color player 3
            gameboard.setLineColor(player3); //Sets color
        }
        
        else{ //Player 4
            
            Color player4 = new Color(255, 255, 0); //Custom color player 4
            gameboard.setLineColor(player4); //Sets color
        }
        
        gameboard.circle(sideLength * 50 + 45, 120, 50); //Draws turn circle
        
        return 0; //Returns
    }
}
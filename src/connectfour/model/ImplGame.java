package connectfour.model;

public class ImplGame implements Game {
	private final Grid grid;
	private Tokens currentPlayer;
	private Tokens winnerPlayer;
	private boolean over;
	
	private static final Tokens[] TOKEN_VALUES = Tokens.values();

	public ImplGame(){
		grid = new ImplGrid(Game.COLUMNS,Game.ROWS);
		init();
	}
	
	@Override
	public Tokens getToken(int x, int y) {
		// TODO Auto-generated method stub
		return grid.getToken(x, y);
	}

	@Override
	public Tokens getCurrentPlayer() {
		// TODO Auto-generated method stub
		return currentPlayer;
	}
	
	private Tokens getNextPlayer() {
		return TOKEN_VALUES[(currentPlayer.ordinal() + 1) % TOKEN_VALUES.length];
		
	}

	@Override
	public boolean isOver() {
		return over;
	}
	
	private boolean calculateOver(int columnPlayer) {		
		//Test alignement
		if (inspectSouth(columnPlayer, grid.getRowOfLastPutToken()) >= 4 || inspectWestEast(columnPlayer, grid.getRowOfLastPutToken()) >= 4 || inspectNWSE(columnPlayer, grid.getRowOfLastPutToken()) >= 4 || inspectNESW(columnPlayer, grid.getRowOfLastPutToken()) >= 4) {
			winnerPlayer = getCurrentPlayer();
			return true; 			
		}
				
		//Test grille pleine
		if (grid.getRowOfLastPutToken() == 5) {
			//Boucle sur la dernière ligne
			for(int column = 0; column < Game.COLUMNS; column++) {
				if (grid.getToken(column, 5) == null) {
					return false;
				}
			}
					
			winnerPlayer = getCurrentPlayer();
			return true;
		}
		
		return false;
	}
	
	private int inspectSouth(int x, int y) {
		int foundInLine = 0;
		for (int i = 1; y - i >= 0 && getToken(x, y - i) == currentPlayer; i++) {
			foundInLine++;
		}
		
		
		return foundInLine + 1;
	}
	
	private int inspectWestEast(int x, int y) {
		int foundInLine = 0;
		for (int i = 1; x - i >= 0 && getToken(x - i, y) == currentPlayer; i++) {
			foundInLine++;
		}
		
		for (int i = 1; x + i < ROWS && getToken(x + i, y) == currentPlayer; i++) {
			foundInLine++;
		}
		
		
		return foundInLine + 1;
	}
	
	private int inspectNWSE(int x, int y) {
		int foundInLine = 0;
		for (int i = 1; x - i >= 0 && y + i < ROWS && getToken(x - i, y + i) == currentPlayer; i++) {
			foundInLine++;
		}
		
		for (int i = 1; x + i < COLUMNS && y - i >= 0 && getToken(x + i, y - i) == currentPlayer; i++) {
			foundInLine++;
		}
		
		
		return foundInLine + 1;
	}
	
	private int inspectNESW(int x, int y) {
		int foundInLine = 0;
		for (int i = 1; x + i < COLUMNS && y + i < ROWS && getToken(x + i, y + i) == currentPlayer; i++) {
			foundInLine++;
		}
		
		for (int i = 1; x - i >= 0 && y - i >= 0 && getToken(x - i, y - i) == currentPlayer; i++) {
			foundInLine++;
		}
		
		
		return foundInLine + 1;
	}
	

	@Override
	public Tokens getWinner() {
		// TODO Auto-generated method stub
		return winnerPlayer;
	}

	@Override
	public void putToken(int column) throws ConnectException {
		// TODO Auto-generated method stub
		grid.putToken(getCurrentPlayer(), column);
		over = calculateOver(column);
		currentPlayer = getNextPlayer();
		

	}

	@Override
	public void init() {
		double randDouble = Math.random();
		if (randDouble > 0.5) {
			currentPlayer = Tokens.BLUE;
		} else {
			currentPlayer = Tokens.RED;
		}
		
		over = false;
		winnerPlayer = null;
		
		grid.init();
		
	}

}

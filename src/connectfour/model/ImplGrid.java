package connectfour.model;

public class ImplGrid implements Grid {
	private final Tokens[][] grid;
	public Integer rowOfLastPutToken;
	
	public ImplGrid(int columns, int rows) {
		if (columns < 0 || rows < 0) {
			throw new IllegalArgumentException();
		}
		grid = new Tokens[columns][rows];
		
	}

	@Override
	public Tokens getToken(int x, int y) {
		if (x < 0 || x > Game.COLUMNS || y < 0 || y > Game.ROWS) {
			throw new IllegalArgumentException();
		}
		
		return grid[x][y];
	}

	@Override
	public void putToken(Tokens token, int x) throws ConnectException{
		
		if (x < 0 || x >= Game.COLUMNS) {
			throw new ConnectException("La colonne entrée n'est pas valide !");
		}else if (token == null) {
			throw new IllegalArgumentException();
		} else {
			int y = 0;
			while (y < grid[x].length && getToken(x, y) != null) {
				y++;
			}
			
			if (y >=6) {
				throw new ConnectException("Impossible de placer le jeton dans cette colonne, elle est pleine !");
			} else {
				grid[x][y] = token;
				rowOfLastPutToken = y;
			}
		}
		
	}

	@Override
	public Integer getRowOfLastPutToken() {
		return rowOfLastPutToken;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		rowOfLastPutToken = null;
		for(int column = 0; column < Game.COLUMNS; column++) {
			for(int row = 0; row < Game.ROWS; row++) {
				grid[column][row] = null;
			}
		}
	}

}

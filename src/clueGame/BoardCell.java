package clueGame;

public class BoardCell {
	private int row;
	private int col;
	private char initial;
	private DoorDirection direction;
	// TODO: we are going to need to add some sort of cell type variable
	
	public BoardCell(int y, int x, String cellName) {
		row = y;
		col = x;
		initial = cellName.charAt(0);
		direction = DoorDirection.NONE;
		if (cellName.length() == 2) {
			switch (cellName.charAt(1)) {
			case 'U':
				direction = DoorDirection.UP;
				break;
			case 'L':
				direction = DoorDirection.LEFT;
				break;
			case 'R':
				direction = DoorDirection.RIGHT;
				break;
			case 'D':
				direction = DoorDirection.DOWN;
				break;
			default:
				direction = DoorDirection.NONE;
			}
		}
	}
	
	public boolean isWalkway() {
		return false;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		return false;
	}

	public DoorDirection getDoorDirection() {
		return direction;
	}

	public char getInitial() {
		return initial;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + ", initial=" + initial + ", direction=" + direction + "]";
	}

}

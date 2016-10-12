package clueGame;

public class BoardCell {
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + ", initial=" + initial + ", direction=" + direction + "]";
	}

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
		if (initial == 'W' || initial == 'w'){
			return true;
		}
		return false;
	}
	
	public boolean isRoom() {
		// TODO: is this correct?
		// My logic is that if something is not a walkway then it must be a room
		return !this.isWalkway();
	}
	
	public boolean isDoorway() {
		if (direction != DoorDirection.NONE) {
			return true;
		}
		return false;
	}

	public DoorDirection getDoorDirection() {
		return direction;
	}

	public char getInitial() {
		return initial;
	}

	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

}

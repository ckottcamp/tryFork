package clueGame;

public class BoardCell {
	private int row;
	private int col;
	private char initial;
	private DoorDirection direction;
	// TODO: we are going to need to add some sort of cell type variable
	
	public BoardCell(int y, int x) {
		row = y;
		col = x;
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

}

package experiment;

import java.util.*;

public class IntBoard {
	private BoardCell[][] boardGrid;
	private Map<BoardCell, Set<BoardCell>> adjList;
	private Set<BoardCell> visitedList;
	private Set<BoardCell> targetList;
	
	public final static int BOARD_SIZE = 4;
	
	public IntBoard() {
		// TODO: put memory allocations here
	}
	
	public void calcAdjacencies() {
		return;
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		return;
	}
	
	public Set<BoardCell> getTargets() {
		return null;
	}
	
	public Set<BoardCell> getAdjList(BoardCell currentCell) {
		return null;
	}
	
	public BoardCell getCell(int x, int y) {
		return null;
	}
}

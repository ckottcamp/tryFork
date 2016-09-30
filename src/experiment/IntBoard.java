package experiment;

import java.util.*;

public class IntBoard {
	private BoardCell[][] boardGrid;
	private Map<BoardCell, Set<BoardCell>> adjList;
	private Set<BoardCell> visitedList;
	private Set<BoardCell> targetList;
	
	public IntBoard() {
		boardGrid = new BoardCell[21][21];
		adjList = new HashMap<BoardCell, Set<BoardCell>>();
		// TODO: Fix these errors:
		//visitedList = new Set<BoardCell>();
		//targetList = new Set<BoardCell>();
		
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
	
	public Set<BoardCell>getAdjList() {
		return null;
	}
}

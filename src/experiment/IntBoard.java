package experiment;

import java.util.Map;
import java.util.Set;

public class IntBoard {
	private BoardCell[][] boardGrid;
	private Map<BoardCell, Set<BoardCell>> adjencyList;
	private Set<BoardCell> visitedList;
	private Set<BoardCell> targetList;
	
	public IntBoard() {
		
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

package experiment;

import java.util.*;

public class IntBoard {
	private BoardCell[][] boardGrid;
	private Map<BoardCell, Set<BoardCell>> adjList;
	private Set<BoardCell> visitedList;
	private Set<BoardCell> targetList;
	
	public final static int BOARD_SIZE = 4;
	
	public IntBoard() {
		boardGrid = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		for (int x=0; x<BOARD_SIZE; x++) {
			for (int y=0; y<BOARD_SIZE; y++) {
				boardGrid[y][x] = new BoardCell(y,x);
			}
		}
		adjList = new HashMap<BoardCell, Set<BoardCell>>();
		visitedList = new HashSet<BoardCell>();
		targetList = new HashSet<BoardCell>();
	}
	
	public void calcAdjacencies() {
		/*	x->cols
		 *	y	 		2
		 *	| 		1	c	3
		 *	v 			4
		 *rows
		 */
		for (int x=0; x<BOARD_SIZE; x++) {
			for (int y=0; y<BOARD_SIZE; y++) {
				Set<BoardCell> tempAdj = new HashSet<BoardCell>();
				// add 1
				if (x>0){tempAdj.add(boardGrid[y][x-1]);}
				// add 2
				if (y>0){tempAdj.add(boardGrid[y-1][x]);}
				// add 3
				if (x<BOARD_SIZE - 1){tempAdj.add(boardGrid[y][x+1]);}
				// add 4
				if (y<BOARD_SIZE - 1){tempAdj.add(boardGrid[y+1][x]);}
				adjList.put(boardGrid[y][x], tempAdj);
			}
		}
		return;
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		return;
	}
	
	public Set<BoardCell> getTargets() {
		return targetList;
	}
	
	public Set<BoardCell> getAdjList(BoardCell currentCell) {
		return adjList.get(currentCell);
	}
	
	public BoardCell getCell(int row, int col) {
		return boardGrid[row][col];
	}
}

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1

        // If the current cell being analyzed falls outside the grid
        if (x > maze.getNCols() -1 || y > maze.getNRows() - 1 || y < 0 || x < 0) {
            return false;
        // If the current cell being analyzed is not NON_BACKGROUND
        } else if(maze.getColor(x,y) != Color.red){
            return false;
        // If the current cell being analyzed is the exit cell
        } else if(x == maze.getNCols() - 1 && y == maze.getNRows() - 1){
            maze.recolor(x, y, Color.green);
            return true;
        // Otherwise the current cell may be assumed to be part of the path
        } else {
            maze.recolor(x, y, Color.green);
            //explore neighbors
            return this.findMazePath(x - 1, y) || this.findMazePath(x+ 1, y) || this.findMazePath(x, y - 1) || this.findMazePath(x, y+ 1); 
        }

    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public void findMazePathStackBased(int x,int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> backtrack){
        if (x > maze.getNCols() - 1 || y > maze.getNRows() - 1 || y < 0 || x < 0) {
            return;
        } else if (maze.getColor(x, y) != Color.red) {
            return;
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            PairInt newPair = new PairInt(x, y);
            backtrack.push(newPair);
            ArrayList<PairInt> temp = new ArrayList<PairInt>();
            temp.addAll(backtrack);
            result.add(temp);
            backtrack.pop();
            maze.recolor(x, y, NON_BACKGROUND);
            return;
        } else {
            PairInt newPair = new PairInt(x, y);
            backtrack.push(newPair);
            maze.recolor(x, y, TEMPORARY);
            this.findMazePathStackBased(x - 1, y, result, backtrack); //left
            this.findMazePathStackBased(x + 1, y, result, backtrack); //right
            this.findMazePathStackBased(x, y - 1, result, backtrack); //top
            this.findMazePathStackBased(x, y + 1, result, backtrack); //bottom
            maze.recolor(x, y, NON_BACKGROUND);
            backtrack.pop();
            return;
        }
    }

    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> backtrack = new Stack<>();
        findMazePathStackBased(0, 0, result, backtrack);
        return result;
        
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        ArrayList<ArrayList<PairInt>> paths = findAllMazePaths(x, y);
        int shortest[] = new int[paths.size()];
        int max = shortest[0];
        int idx = 0;

        for (int i = 0; i < paths.size(); i++) {
            shortest[i] = paths.get(i).size();
        }

        for (int i = 1; i < shortest.length; i++) {
            if (shortest[i] > max) {
                max = shortest[i];
                idx = i;
            }
        }

        return paths.get(idx);
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/

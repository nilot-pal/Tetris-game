package edu.vt.cs5044;

import java.util.Set;

import edu.vt.cs5044.tetris.AI;
import edu.vt.cs5044.tetris.Board;
import edu.vt.cs5044.tetris.Placement;
import edu.vt.cs5044.tetris.Rotation;
import edu.vt.cs5044.tetris.Shape;

/**
 * Class that implements the methods defined by the interface the game engine uses to 
 * communicate with AI implementations.
 * One of the methods will be the AI system, responsible for choosing the best placement 
 * for each shape. The other methods will support this by performing necessary calculations.
 * @author nilotpal
 * @version Mar 5, 2023
 *
 */
public class TetrisAI implements AI {
    
    private int weightAverageColumnHeight = 1;
    private int weightColumnHeightRange = 12;
    private int weightColumnHeightVariance = 0;
    private int weightTotalGapCount = 12;
    
    /**
     * 
     * private method to calculate weighted cost of a board
     *
     * @param board current Board
     * @return cost of board
     */
    
    private int calculateCost(Board board) {
        return weightAverageColumnHeight * getAverageColumnHeight(board) +
            weightColumnHeightRange * getColumnHeightRange(board) +
            weightColumnHeightVariance * getColumnHeightVariance(board) +
            weightTotalGapCount * getTotalGapCount(board);
    }

    @Override
    public Placement findBestPlacement(Board currentBoard, Shape shape) {
        Placement temp = null;
        int minCost = Integer.MAX_VALUE;
        Set<Rotation> val = shape.getValidRotationSet();
        for (Rotation rot : val) {
            int maxCol = Board.WIDTH - shape.getWidth(rot);
            for (int col = 0; col <= maxCol; col++) {
                Placement pl = new Placement(rot, col);
                Board newBoard = currentBoard.getResultBoard(shape, pl);
                int cost = calculateCost(newBoard);
                if (minCost > cost) {
                    minCost = cost;
                    temp = pl;
                }
            }
        }
        return temp;
    }

    @Override
    public int getAverageColumnHeight(Board board) {
        boolean[][] blocksArray = board.getFixedBlocks();
        int avgColHeight = 0;
        for (int col = 0; col < Board.WIDTH; col++) {
            avgColHeight += columnHelper(blocksArray[col]);
        }
        avgColHeight /= Board.WIDTH;
        return avgColHeight;
    }
    

    @Override
    public int getColumnHeightRange(Board board) {
        boolean[][] blocksArray = board.getFixedBlocks();
        int tallest = Integer.MIN_VALUE;
        int shortest = Integer.MAX_VALUE;
        for (int col = 0; col < Board.WIDTH; col++) {
            int height = columnHelper(blocksArray[col]);
            if (height > tallest) {
                tallest = height;
            }
            if (height < shortest) {
                shortest = height;
            }
        }
        return tallest - shortest;
    }

    @Override
    public int getColumnHeightVariance(Board board) {
        boolean[][] blocksArray = board.getFixedBlocks();
        int varColHeight = 0;
        for (int col = 0; col < Board.WIDTH - 1; col++) {
            varColHeight += Math.abs(columnHelper(blocksArray[col]) - 
                columnHelper(blocksArray[col + 1]));
        }
        return varColHeight;
    }

    @Override
    public int getTotalGapCount(Board board) {
        boolean[][] blocksArray = board.getFixedBlocks();
        int totalGapCount = 0;
        for (int col = 0; col < Board.WIDTH; col++) {
            totalGapCount += gapHelper(blocksArray[col]);
        }
        return totalGapCount;
    }
    /**
     * 
     * Private helper method that calculates the height of a column
     * Used by the public methods getAverageColumnHeight, getColumnHeightRange
     * and getColumnHeightVariance
     * @param colArr 1D boolean array
     * @return height of column
     */
    
    private int columnHelper(boolean[] colArr) {
        for (int row = Board.HEIGHT - 1; row >= 0; row--) {
            if (colArr[row]) {
                return row + 1;
            }
        }
        return 0;
    }
    /**
     * 
     * Private helper method that calculates no. of gaps in a column
     * Used by the public method getTotalGapCount
     * @param colArr 1D boolean array
     * @return no. of gaps in a column
     */
    
    private int gapHelper(boolean[] colArr) {
        int gapCol = 0;
        for (int row = 0; row < Board.HEIGHT - 1; row++) {
            if (!colArr[row]) {
                gapCol += gapColumnHelper(colArr, row);
            }
            else {
                continue;
            }
        }
        return gapCol;
    }
    /**
     * 
     * Private helper method that helps to avoid nested loop
     * in the helper method gapHelper
     * @param colArr 1D boolean array
     * @param row row number
     * @return 1 if at least one row above the given row has a block,
     * otherwise 0
     */
    
    private int gapColumnHelper(boolean[] colArr, int row) {
        for (int j = row + 1; j < Board.HEIGHT; j++) {
            if (colArr[j]) {
                return 1;
            }
        }
        return 0;
    }

}

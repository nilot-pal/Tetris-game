package edu.vt.cs5044;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import edu.vt.cs5044.tetris.AI;
import edu.vt.cs5044.tetris.Board;
import edu.vt.cs5044.tetris.Placement;
import edu.vt.cs5044.tetris.Rotation;
import edu.vt.cs5044.tetris.Shape;

/**
 * 
 * Class for unit testing
 * Uses different boards to test the output of all 
 * five methods of the TetrisAi class.
 *
 * @author nilotpal
 * @version Mar 5, 2023
 *
 */

public class TetrisAITest {
    /**
     * Object of the AI interface
     */
    AI smartGamer;
    
    /**
     * Object of the Board class
     */
    Board emptyBoard;
    
    /**
     * Object of the Board class
     */
    Board oneBlockBoard;
    
    /**
     * Object of the Board class
     */
    Board twoLinesBoard;
    
    /**
     * Object of the Board class
     */
    Board apiBoard;
    
    /**
     * Object of the Board class
     */
    Board boardWc1;
    
    /**
     * Object of the Board class
     */
    Board boardWc2;
    
    /**
     * Object of the Board class
     */
    Board boardWc3;
    
    /**
     * Object of the Board class
     */
    Board boardWc4;
    /**
     * Object of the Board class
     */
    Board boardWc5;
    
    /**
     * My test board 1
     */
    Board testBoard1;
    
    /**
     * My test board 2
     */
    Board testBoard2;
    
    /**
     * My test board 3
     */
    Board testBoard3;
    
    /**
     * My test board 4
     */
    Board testBoard4;
    
    /**
     * My test board 5
     */
    Board testBoard5;
    
    /**
     * setup before running the tests
     */
    
    @Before
    /**
     * 
     * Create different boards and an object of TetrisAI class
     *
     */
    public void setUp() {
        smartGamer =  new TetrisAI();
        emptyBoard = new Board();
        oneBlockBoard = new Board("         #");
        twoLinesBoard = new Board("######## #",
                                 "######## #");
        apiBoard = new Board("## ##    #",
                             "# ##### ##",
                             "#### #####",
                             "# ##### ##",
                             "## #######",
                             "######### ",
                             " #########",
                             " #########",
                             "###  #####",
                             "####### ##",
                             "######## #",
                             " #### ####");
        boardWc1 = new Board(
            "     #  ##",
            " ##### ###",
            "##### ####"
            );
        boardWc2 = new Board(
            "   ###### ",
            " #### ### ",
            " # ###### "
            );
        boardWc3 = new Board(
            "   ##     ",
            " ####     ",
            "########  ",
            "######### ",
            "######### ",
            "## ###### "
            );
        boardWc4 = new Board(
            "##        ",
            "##        ",
            "###       ",
            "###       ",
            "###       ",
            "###       ",
            "### ###   ",
            "####### ##",
            "# ########"
            );
        boardWc5 = new Board(
            "   ##     ",
            "######    ",
            "# ####    ",
            "# ########",
            "# ########",
            "### ######"
            );
        // My five test boards
        testBoard1 = new Board(
            "   #      ",
            " #### #  #",
            " ###### ##",
            " #########");
        testBoard2 = new Board(
            "     #  # ",
            "   #######",
            " #########",
            " #########",
            " #########");
        testBoard3 = new Board(
            "     #    ",
            " ######## ",
            " ###### ##",
            " ######## ",
            " #########");
        testBoard4 = new Board(
            "  ##      ",
            "  ### # # ",
            " #########",
            " # ##### #",
            " #########",
            " #########",
            " ###### ##",
            " ######## ",
            " #########");
        testBoard5 = new Board(
            "###       ",
            "###   #   ",
            "### ######",
            "######## #",
            "####### ##",
            "## ##### #",
            "####### ##",
            "######### ");
    }
    
    /**
     * test getAverageColumnHeight method
     */
    @Test
    /**
     * 
     * Method to test output of getAverageColumnHeight
     *
     */
    public void testAverageColumnHeight() {
        assertEquals(0, smartGamer.getAverageColumnHeight(emptyBoard));
        assertEquals(0, smartGamer.getAverageColumnHeight(oneBlockBoard));
        assertEquals(1, smartGamer.getAverageColumnHeight(twoLinesBoard));
        assertEquals(11, smartGamer.getAverageColumnHeight(apiBoard));
        
        assertEquals(2, smartGamer.getAverageColumnHeight(boardWc1));
        assertEquals(2, smartGamer.getAverageColumnHeight(boardWc2));
        assertEquals(4, smartGamer.getAverageColumnHeight(boardWc3));
        assertEquals(4, smartGamer.getAverageColumnHeight(boardWc4));
        assertEquals(4, smartGamer.getAverageColumnHeight(boardWc5));
        
        assertEquals(2, smartGamer.getAverageColumnHeight(testBoard1));
        assertEquals(3, smartGamer.getAverageColumnHeight(testBoard2));
        assertEquals(3, smartGamer.getAverageColumnHeight(testBoard3));
        assertEquals(7, smartGamer.getAverageColumnHeight(testBoard4));
        assertEquals(6, smartGamer.getAverageColumnHeight(testBoard5));
    }
    
    /**
     * test getColumnHeightRange method
     */
    @Test
    /**
     * 
     * Method to test output of getColumnHeightRange
     *
     */
    public void testColumnHeightRange() {
        assertEquals(0, smartGamer.getColumnHeightRange(emptyBoard));
        assertEquals(1, smartGamer.getColumnHeightRange(oneBlockBoard));
        assertEquals(2, smartGamer.getColumnHeightRange(twoLinesBoard)); 
        assertEquals(2, smartGamer.getColumnHeightRange(apiBoard));
        
        assertEquals(2, smartGamer.getColumnHeightRange(boardWc1));
        assertEquals(3, smartGamer.getColumnHeightRange(boardWc2));
        assertEquals(6, smartGamer.getColumnHeightRange(boardWc3));
        assertEquals(8, smartGamer.getColumnHeightRange(boardWc4));
        assertEquals(3, smartGamer.getColumnHeightRange(boardWc5));
        
        assertEquals(4, smartGamer.getColumnHeightRange(testBoard1));
        assertEquals(5, smartGamer.getColumnHeightRange(testBoard2));
        assertEquals(5, smartGamer.getColumnHeightRange(testBoard3));
        assertEquals(9, smartGamer.getColumnHeightRange(testBoard4));
        assertEquals(3, smartGamer.getColumnHeightRange(testBoard5));
    }
    
    /**
     * test getColumnHeightVariance method
     */
    @Test
    /**
     * 
     * Method to test output of getColumnHeightVariance
     *
     */
    public void testColumnHeightVariance() {
        assertEquals(0, smartGamer.getColumnHeightVariance(emptyBoard));
        assertEquals(1, smartGamer.getColumnHeightVariance(oneBlockBoard));
        assertEquals(4, smartGamer.getColumnHeightVariance(twoLinesBoard)); 
        assertEquals(6, smartGamer.getColumnHeightVariance(apiBoard));
        
        assertEquals(6, smartGamer.getColumnHeightVariance(boardWc1));
        assertEquals(6, smartGamer.getColumnHeightVariance(boardWc2));
        assertEquals(8, smartGamer.getColumnHeightVariance(boardWc3));
        assertEquals(11, smartGamer.getColumnHeightVariance(boardWc4));
        assertEquals(4, smartGamer.getColumnHeightVariance(boardWc5));
        
        assertEquals(11, smartGamer.getColumnHeightVariance(testBoard1));
        assertEquals(8, smartGamer.getColumnHeightVariance(testBoard2));
        assertEquals(7, smartGamer.getColumnHeightVariance(testBoard3));
        assertEquals(15, smartGamer.getColumnHeightVariance(testBoard4));
        assertEquals(6, smartGamer.getColumnHeightVariance(testBoard5));
    }
    
    /**
     * test getTotalGapCount method
     */
    @Test
    /**
     * 
     * Method to test output of getTotalGapCount
     *
     */
    public void testTotalGapCount() {
        assertEquals(0, smartGamer.getTotalGapCount(emptyBoard));
        assertEquals(0, smartGamer.getTotalGapCount(oneBlockBoard));
        assertEquals(0, smartGamer.getTotalGapCount(twoLinesBoard)); 
        assertEquals(14, smartGamer.getTotalGapCount(apiBoard));
        
        assertEquals(1, smartGamer.getTotalGapCount(boardWc1));
        assertEquals(2, smartGamer.getTotalGapCount(boardWc2));
        assertEquals(1, smartGamer.getTotalGapCount(boardWc3));
        assertEquals(1, smartGamer.getTotalGapCount(boardWc4));
        assertEquals(4, smartGamer.getTotalGapCount(boardWc5));
        
        assertEquals(0, smartGamer.getTotalGapCount(testBoard1));
        assertEquals(0, smartGamer.getTotalGapCount(testBoard2));
        assertEquals(2, smartGamer.getTotalGapCount(testBoard3));
        assertEquals(4, smartGamer.getTotalGapCount(testBoard4));
        assertEquals(6, smartGamer.getTotalGapCount(testBoard5));
    }
    
    /**
     * test findBestPlacement method
     */
    @Test
    /**
     * 
     * Method to test output of findBestPlacement
     *
     */
    
    public void testfindBestPlacement() {
        assertEquals(new Placement(Rotation.NONE, 0),
            smartGamer.findBestPlacement(emptyBoard, Shape.O));
        assertEquals(new Placement(Rotation.NONE, 0),
            smartGamer.findBestPlacement(oneBlockBoard, Shape.T));
        assertEquals(new Placement(Rotation.NONE, 8),
            smartGamer.findBestPlacement(twoLinesBoard, Shape.I)); 
        assertEquals(new Placement(Rotation.CCW_90, 5), 
            smartGamer.findBestPlacement(apiBoard, Shape.I));
        
        assertEquals(new Placement(Rotation.CCW_180, 6), 
            smartGamer.findBestPlacement(testBoard1, Shape.L));
        assertEquals(new Placement(Rotation.NONE, 0), 
            smartGamer.findBestPlacement(testBoard2, Shape.T));
        assertEquals(new Placement(Rotation.CCW_270, 0), 
            smartGamer.findBestPlacement(testBoard3, Shape.L));
        assertEquals(new Placement(Rotation.CCW_270, 0), 
            smartGamer.findBestPlacement(testBoard4, Shape.T));
        assertEquals(new Placement(Rotation.CCW_90, 3), 
            smartGamer.findBestPlacement(testBoard5, Shape.Z));
    }
    
}

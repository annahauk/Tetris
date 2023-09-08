/**
 * Tetrad class to be completed for Tetris project
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.lang.Math;

public class Tetrad
{
    private static int I_Tetrad = 0;
    private static int T_Tetrad = 1;
    private static int O_Tetrad = 2;
    private static int L_Tetrad = 3;
    private static int J_Tetrad = 4;
    private static int S_Tetrad = 5;
    private static int Z_Tetrad = 6;
    private int shape;
    private Block[] blocks;

    public Tetrad(BoundedGrid<Block> grid)
    {
        
        //Exercise 1.2  Insert code here to
        //                  instantiate blocks Block array (length 4)
        //                  initialize blocks array with new Block objects
        //                  declare color variable
        //                  declare and instantiate locs Location array (length 4)
        //                  declare shape variable and set equal to zero
        
        blocks = new Block[4];
        for(int i=0;i<4;i++)
        {
            blocks[i]=new Block();
        }
        Color c = Color.BLACK;
        Location[] locs = new Location[4];
        shape=0;
        
        //Exercise 2.0  Insert code here to
        //                  choose a random integer from 0 to 6
        shape = (int)(Math.random()*7);

        //Exercise 1.2  Insert code here to
        //                  branch (if statements) based on each shape number, to then
        //                      set the color variable for that shape
        //                      set the block locations for that shape
        int gridWidth = grid.getNumCols();
        int startingColumn = 0;
        if (shape == I_Tetrad) {
            c=Color.RED;
            startingColumn = gridWidth/2-2;
            locs[0]=new Location(0,startingColumn+1);
            locs[1]=new Location(0,startingColumn);
            locs[2]=new Location(0,startingColumn+2);
            locs[3]=new Location(0,startingColumn+3);
        } else if (shape == T_Tetrad) {
            c=Color.GRAY;
            startingColumn = gridWidth/2-2;
            locs[0]=new Location(0,startingColumn+1);
            locs[1]=new Location(0,startingColumn);
            locs[2]=new Location(0,startingColumn+2);
            locs[3]=new Location(1,startingColumn+1);
        } else if (shape == O_Tetrad) {
            c=Color.CYAN;
            startingColumn = gridWidth/2-1;
            locs[0]=new Location(0,startingColumn);
            locs[1]=new Location(0,startingColumn+1);
            locs[2]=new Location(1,startingColumn);
            locs[3]=new Location(1,startingColumn+1);
        } else if (shape == L_Tetrad) {
            c=Color.YELLOW;
            startingColumn = gridWidth/2-2;
            locs[0]=new Location(0,startingColumn+1);
            locs[1]=new Location(0,startingColumn);
            locs[2]=new Location(0,startingColumn+2);
            locs[3]=new Location(1,startingColumn);
        } else if (shape == J_Tetrad) {
            c=Color.MAGENTA;
            startingColumn = gridWidth/2-2;
            locs[0]=new Location(0,startingColumn+1);
            locs[1]=new Location(0,startingColumn);
            locs[2]=new Location(0,startingColumn+2);
            locs[3]=new Location(1,startingColumn+2);
        } else if (shape == S_Tetrad) {
            c=Color.BLUE;
            startingColumn = gridWidth/2-2;
            locs[0]=new Location(0,startingColumn+1);
            locs[1]=new Location(0,startingColumn+2);
            locs[2]=new Location(1,startingColumn);
            locs[3]=new Location(1,startingColumn+1);
        } else if (shape == Z_Tetrad) {
            c=Color.GREEN;
            startingColumn = gridWidth/2-2;
            locs[0]=new Location(0,startingColumn+1);
            locs[1]=new Location(0,startingColumn);
            locs[2]=new Location(1,startingColumn+1);
            locs[3]=new Location(1,startingColumn+2);
        }
        //Exercise 1.2  Insert code here (after the above if statements) to
        //                  loop through the blocks array to
        //                  set the color of each block
        for (int i=0;i<blocks.length;++i)
        {
            blocks[i].setColor(c);
        }
        //
        //  call addToLocations
        addToLocations(grid, locs);
    }

    //precondition:  blocks are not in any grid;
    //               blocks.length = locs.length = 4.
    //postcondition: The locations of blocks match locs,
    //               and blocks have been put in the grid.
    private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
    {
        for(int i=0;i<locs.length;++i)
        {
            blocks[i].putSelfInGrid(grid,locs[i]);
        }
    }

    //precondition:  Blocks are in the grid.
    //postcondition: Returns old locations of blocks;
    //               blocks have been removed from grid.
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[4];
        for(int i=0;i<blocks.length;++i)
        {
            locs[i]=blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return locs;
    }

    //postcondition: Returns true if each of locs is
    //               valid (on the board) AND empty in
    //               grid; false otherwise.
    private boolean areEmpty(BoundedGrid<Block> grid,
                             Location[] locs)
    {
        for(int i=0;i<locs.length;++i)
        {
            if(!grid.isValid(locs[i]))
            {
                return false;
            }
            if(grid.get(locs[i])!=null)
            {
                return false;
            }
        }
        return true;
    }

    //postcondition: Attempts to move this tetrad deltaRow
    //               rows down and deltaCol columns to the
    //               right, if those positions are valid
    //               and empty; returns true if successful
    //               and false otherwise.
    public boolean translate(int deltaRow, int deltaCol)
    {
        //Exercise 2.2    Insert code here to
        //              ask any block for its grid and store value
        //              remove the blocks (but save the locations)
        //              create an array of the new (possible) locations
        //              check if the new locations are empty
        //              replace the tetrad in the proper place (translated)
        //              return true if moved, false if not moved
        BoundedGrid<Block> grid = blocks[0].getGrid();
        Location[] oldLocations = removeBlocks();
        Location[] newLocations = new Location[4];
        for(int i=0; i<newLocations.length; i++)
        {
            newLocations[i] = new Location(oldLocations[i].getRow()+deltaRow,oldLocations[i].getCol()+deltaCol);
        }
        if (areEmpty(grid, newLocations))
        {
            addToLocations(grid, newLocations);
            return true;
        }
        else
        {
            addToLocations(grid,oldLocations);
            return false;    
        }
    }

    //postcondition: Attempts to rotate this tetrad
    //               clockwise by 90 degrees about its
    //               center, if the necessary positions
    //               are empty; returns true if successful
    //               and false otherwise.
    public boolean rotate()
    {
        //Exercise 3.0  Insert code here to
        //              ask any block for its grid and store value
        //              remove the blocks (but save the locations)
        //              check if the new locations are empty
        //              replace the tetrad in the proper place (rotated)

        BoundedGrid<Block> grid = blocks[0].getGrid();
        Location[] oldLocations = removeBlocks();
        Location[] newLocations = new Location[4];
        for(int i=0; i<newLocations.length; ++i)
        {
            newLocations[i] = new Location(oldLocations[0].getRow() - oldLocations[0].getCol() + oldLocations[i].getCol(),
            oldLocations[0].getRow()+oldLocations[0].getCol()-oldLocations[i].getRow());
        }
        if (areEmpty(grid, newLocations))
        {
            addToLocations(grid, newLocations);
            return true;
        }
        else
        {
            addToLocations(grid,oldLocations);
            return false;    
        }
    }
}
/**
 * Tetris class to be completed for Tetris project
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tetris implements ArrowListener
{
    public static void main(String[] args)
    {
        Tetris tetris = new Tetris();
        tetris.play();
    }

    private BoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad activeTetrad;
    private int score = 0;

    public Tetris()
    {
        grid = new BoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        display.setTitle("Tetris --- Score: " + score);
        display.setArrowListener(this);
        activeTetrad = new Tetrad(grid);
        display.showBlocks();
    }

    public void upPressed()
    {
        activeTetrad.rotate();
        //activeTetrad.translate(-1,0);
        display.showBlocks();
    }

    public void downPressed()
    {
        activeTetrad.translate(1,0);
        display.showBlocks();
    }

    public void leftPressed()
    {
        activeTetrad.translate(0,-1);
        display.showBlocks();
    }

    public void rightPressed()
    {
        activeTetrad.translate(0,1);
        display.showBlocks();
    }

    public void spacePressed()
    {
        while(activeTetrad.translate(1,0))
        {
            ;
        }
        display.showBlocks();
    }

    public void play()
    {
        int delay = 1000;
        int countOfNewTetrads = 1;
        
        while (true)
        {
            try { Thread.sleep(delay); } catch(Exception e) {}

            //Insert Exercise 3.2 code here
            boolean atBottom = !activeTetrad.translate(1,0);
            display.setTitle("Tetris --- Score: " + score);
            display.showBlocks();
            //Insert Exercise 3.3 code here
            if (atBottom)
            {
                clearCompletedRows();
                if (!topRowsEmpty())
                {
                    break;
                }
                activeTetrad = new Tetrad(grid);
                countOfNewTetrads++;
                if (countOfNewTetrads % 10 == 0)
                {
                    delay -= 100;
                }
            }
        }
    }

    //precondition:  0 <= row < number of rows
    //postcondition: Returns true if every cell in the
    //               given row is occupied;
    //               returns false otherwise.
    private boolean isCompletedRow(int row)
    {
        for(int c=0;c<grid.getNumCols();++c)
        {
            if(grid.get(new Location(row,c))==null)
            {
                return false;
            }
        }
        return true;
    }

    //precondition:  0 <= row < number of rows;
    //               given row is full of blocks
    //postcondition: Every block in the given row has been
    //               removed, and every block above row
    //               has been moved down one row.
    private void clearRow(int row)
    {
        for(int c=0;c<grid.getNumCols();++c)
        {
            Block b = grid.get(new Location(row,c));
            b.removeSelfFromGrid();
        }
        for(int r=row-1;r>=0;r--)
        {
            for(int c=0;c<grid.getNumCols();++c)
            {
                Block b = grid.get(new Location(r,c));
                if(b!=null)
                {
                    b.moveTo(new Location(r+1,c));
                }
            }
        }
        score++;
    }

    //postcondition: All completed rows have been cleared.
    private void clearCompletedRows()
    {
        int currentRow = grid.getNumRows()-1;
        while(true)
        {
            if(isCompletedRow(currentRow))
            {
                clearRow(currentRow);   
            }
            else
            {
                currentRow--;
            }
            if (currentRow == -1)
                break;
        }
    }

    //returns true if top two rows of the grid are empty (no blocks), false otherwise
    private boolean topRowsEmpty()
    {
        for(int c=0;c<grid.getNumCols();++c)
        {
            Block b = grid.get(new Location(0,c));
            if (b!=null)
            {
                return false;
            }
            b = grid.get(new Location(1,c));
            if (b!=null)
            {
                return false;
            }
        }
        return true;
    }

}
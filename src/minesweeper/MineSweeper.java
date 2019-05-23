package minesweeper;

/***
 * OzU CS 222 Note:
 * This code is taken from
 * http://www.planet-source-code.com/vb/scripts/ShowCode.asp?txtCodeId=4853&lngWId=2
 *
 ***/

// The "MineSweeper" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeper extends Applet implements MouseListener
{
    Button buttons[] [];
    boolean isBomb[] [];
    boolean isFlag[] [];
    boolean isExposed[] [];   //used for eposing 0's. If true then a 0 has been isExposed
    boolean checkButtonType[] [];  // if [x][y] = true then the button has a number on it or it is a isBomb (used for checking if game is over)
    int bombCount = 0, bombsRemaining;          //counting the number of bombs placed
    String surroundingBombsCount;        //number of bombs surrounding button [x][y] (is a string so that we can setLabel for the button)
    int locationX, locationY;       //random ints for bombs
    int rowCount = 16, columnCount = 30, totalBombCount = 99, Setup = 3; //number of rows columns, and bombs
    int sizeX = 780, sizeY = 492;
    Font font;
    Panel centerPanel, northPanel, buttonPanel, newGamePanel;
    Label bombs, timeRemaning, newGameLabel;
    Button easyRestart, mediumRestart, hardRestart;
    GridLayout layout;
    public void init ()
    {
        setLayout (new BorderLayout ());
        layout      = new GridLayout (rowCount, columnCount);
        centerPanel = new Panel (layout);
        font        = new Font ("ComicSans", Font.BOLD, 17);
        setFont (font);
        northPanel   = new Panel (new BorderLayout ());
        newGamePanel = new Panel ();
        timeRemaning = new Label ("");
        newGameLabel = new Label ("New Game");
        easyRestart  = new Button ("Easy");
        easyRestart.addMouseListener (this);
        mediumRestart = new Button ("Medium");
        mediumRestart.addMouseListener (this);
        hardRestart = new Button ("Hard");
        hardRestart.addMouseListener (this);
        buttonPanel = new Panel ();
        bombsRemaining = totalBombCount;
        bombs   = new Label (Integer.toString (bombsRemaining));
        buttons = new Button  [rowCount] [columnCount];
        isBomb  = new boolean [rowCount] [columnCount];
        isFlag  = new boolean [rowCount] [columnCount];
        isExposed = new boolean [rowCount] [columnCount];
        checkButtonType = new boolean [rowCount] [columnCount];

        for (int x = 0; x < rowCount; x++)
        {
            for (int y = 0; y < columnCount; y++)
            {
                buttons[x] [y] = new Button ();
                buttons[x] [y].addMouseListener (this);
                centerPanel.add (buttons[x] [y]);
            }
        }
        //these for loops set up the buttons and sets all the boolean arrays to = false
        add (northPanel, "North");
        add (centerPanel, "Center");
        northPanel.add (bombs, "West");
        northPanel.add (newGamePanel, "North");
        newGamePanel.add (newGameLabel);
        northPanel.add (buttonPanel, "Center");
        buttonPanel.add (easyRestart);
        buttonPanel.add (mediumRestart);
        buttonPanel.add (hardRestart);
        northPanel.setBackground(Color.lightGray);
        newGameLabel.setBackground(Color.lightGray);
        newGameLabel.setForeground(Color.black);
        bombs.setBackground(Color.lightGray);
        bombs.setForeground(Color.white);
        restartGame(rowCount, columnCount, totalBombCount, rowCount, columnCount, sizeX, sizeY);
    }


    public void restartGame(int row, int column, int bombCount, int prow, int pcol, int sizex, int sizey)
    {
        //mBar.Restart (buttons, rowCount, columnCount);
        //mBar.SetupMenu ();
        setSize (sizex, sizey);
        invalidate();
        validate();
        layout.setRows (row);
        layout.setColumns (column);
        int count = 0;
        bombsRemaining = bombCount;
        bombs.setText (Integer.toString (bombsRemaining));
        for (int x = 0 ; x < prow ; x++)
        {
            for (int y = 0 ; y < pcol ; y++)
            {
                centerPanel.remove (buttons[x] [y]);
            }
        }
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < column ; y++)
            {

                buttons[x] [y].setEnabled (true);
                buttons[x] [y].setLabel ("");
                buttons[x] [y].setBackground (Color.gray);
                buttons[x] [y].setForeground (Color.white);
                centerPanel.add (buttons[x] [y]);
                isBomb[x] [y] = false;
                isFlag[x] [y] = false;
                isExposed[x] [y] = false;
                checkButtonType[x] [y] = false;
            }
        }
        setSize (sizex, sizey);
        invalidate();
        validate();
        //adds the bombs to random places on the grid
        while (count < bombCount)
        {
            locationX = (int) (Math.random () * (row));
            locationY = (int) (Math.random () * (column));
            if (isBomb[locationX] [locationY] == false)
            {
                isBomb[locationX] [locationY] = true;
                checkButtonType[locationX] [locationY] = true;
                count++;
            }
        }
    }


    public void mouseClicked (MouseEvent e)
    {
        int prow = 0, pcol = 0;
        if (e.getSource () == easyRestart)
        {
            rowCount = 10;
            columnCount = 10;
            totalBombCount = 10;
            sizeX = 300;
            sizeY = 346;

            if (Setup == 2)
            {
                prow = 16;
                pcol = 16;
                Setup = 1;
            }
            else if (Setup == 3)
            {
                prow = 16;
                pcol = 30;
                Setup = 1;
            }
        }
        if (e.getSource () == mediumRestart)
        {
            rowCount = 16;
            columnCount = 16;
            totalBombCount = 40;
            sizeX = 496;
            sizeY = 540;
            if (Setup == 1)
            {
                prow = 10;
                pcol = 10;
                Setup = 2;
            }
            else if (Setup == 3)
            {
                prow = 16;
                pcol = 30;
                Setup = 2;
            }
        }
        if (e.getSource () == hardRestart)
        {
            rowCount = 16;
            columnCount = 30;
            totalBombCount = 99;
            sizeX = 780;
            sizeY = 492;
            if (Setup == 1)
            {
                prow = 10;
                pcol = 10;
                Setup = 3;
            }
            else if (Setup == 2)
            {
                prow = 16;
                pcol = 16;
                Setup = 3;
            }
        }
        if (e.getSource () == easyRestart || e.getSource () == mediumRestart || e.getSource () == hardRestart)
            restartGame(rowCount, columnCount, totalBombCount, prow, pcol, sizeX, sizeY);

        boolean gameover = false;

        for (int x = 0; x < rowCount; x++)
        {
            for (int y = 0; y < columnCount; y++)
            {
                if (e.getSource () == buttons[x] [y])
                {
                    if (e.getButton () == e.BUTTON1 && isFlag[x] [y] == false) //if left click, and there is no isFlag on the button
                    {
                        if (isBomb[x] [y] == true)  // if you you click on a isBomb, results in game over
                        {
                            buttons[x] [y].setLabel ("*");
                            gameOver();
                            buttons[x] [y].setBackground (Color.black);
                            gameover = true;
                            break;

                        }
                        isExposed[x] [y] = true;
                        checkButtonType[x] [y] = true; // these set to true mean that the button has been changeButtonColor
                        surroundingBombsCount = Integer.toString (checkSurrBombs(x, y)); //gets the number of surrounding buttons with bombs and sets it to a string so that it is possible to setLabel
                        buttons[x] [y].setLabel (surroundingBombsCount); // sets the label to be the number of bombs in the 8 surrounding buttons
                        if (checkSurrBombs(x, y) == 0)
                        {
                            checkAndExposeButtons(x, y);          //calls a recursive method so that if a "0" is there the surrounding 8 buttins must be isExposed and if one of those is "0" it too must be isExposed and so on
                        }
                    }
                    else if (e.getButton () == e.BUTTON3)  // if it is a right click
                    {
                        if (isFlag[x] [y] == true) //if there is a isFlag already present set it so that there is no isFlag
                        {
                            buttons[x] [y].setLabel ("");
                            buttons[x] [y].setForeground (Color.white);
                            isFlag[x] [y] = false;
                            checkButtonType[x] [y] = false;
                            bombsRemaining++;
                        }
                        else if (checkButtonType[x] [y] == false || isBomb[x] [y] == true) //if there is no isFlag, set it so there is a isFlag
                        {
                            buttons[x] [y].setLabel ("|>");
                            buttons[x] [y].setForeground (Color.red);
                            isFlag[x] [y] = true;
                            checkButtonType[x] [y] = true;
                            bombsRemaining--;
                        }
                        bombs.setText (Integer.toString (bombsRemaining));

                    }
                    else if (e.getButton () == e.BUTTON2 && isFlag[x] [y] == false && checkButtonType[x] [y] == true && isBomb[x] [y] == false) //if both left and right click at the same time
                    { //only executes if there is no isFlag, it has been isExposed, and no isBomb
                        if (getSurrFlagCount(x, y) == checkSurrBombs(x, y)) //checks that the number of getSurrFlagCount around [x][y] = the number of bombs around [x][y]
                        {
                            for (int q = x - 1 ; q <= x + 1 ; q++)
                            {
                                for (int w = y - 1 ; w <= y + 1 ; w++)
                                {
                                    if (q < 0 || w < 0 || q >= rowCount || w >= columnCount) // makes sure that it wont have an error for buttons next to the wall
                                        break;
                                    if (isBomb[q] [w] == false && isFlag[q] [w] == true) //if there is no isBomb, but there is a isFlag its game over
                                    {
                                        gameOver();
                                        gameover = true;
                                        break;
                                    }
                                }
                            }
                            if (gameover == false)
                            {
                                expose (x, y);     //eposes the surrounding 8 buttons
                                checkAndExposeButtons(x, y);      //checks if any of those are "0" and calls the recursive method
                            }
                        }
                    }
                    if (gameover == false) //this just calls the method for changing the colours of the buttons if they have been changeButtonColor
                        changeButtonColor();
                }

            }
        }
        checkWinStatus();
    }


    public void changeButtonColor()     //changes the color of the buttons and if [x][y] is "0" set the label to nothing
    {
        for (int x = 0; x < rowCount; x++)
        {
            for (int y = 0; y < columnCount; y++)
            {
                if (checkButtonType[x] [y] == true && isFlag[x] [y] == false && isBomb[x] [y] == false)
                    buttons[x] [y].setBackground (Color.darkGray);
                if (isFlag[x] [y] == false && checkSurrBombs(x, y) == 0)
                    buttons[x] [y].setLabel ("");
            }
        }
    }


    public int getSurrFlagCount(int x, int y)  // get the number of surrounding getSurrFlagCount
    {
        int surrFlagCount = 0;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= rowCount || w >= columnCount) // makes sure that it wont have an error for buttons next to the wall
                        break;

                    if (isFlag[q] [w] == true)
                    {
                        surrFlagCount++;
                    }
                    break;
                }
            }
        }
        return surrFlagCount;
    }


    public int checkSurrBombs(int x, int y)  // checks surrounding 8 squares for number of bombs (it does include itself, but has already been checked for a isBomb so it won't matter)
    {
        int surBombs = 0;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= rowCount || w >= columnCount) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (isBomb[q] [w] == true)
                        surBombs++;
                    break;
                }
            }
        }
        return surBombs;
    }


    public void expose (int x, int y)  // exposes the surrounding 8 buttons
    {
        String surrBombs;
        isExposed[x] [y] = true;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= rowCount || w >= columnCount) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (isFlag[q] [w] == true)
                        break;

                    checkButtonType[q] [w] = true;
                    surrBombs = Integer.toString (checkSurrBombs(q, w));
                    buttons[q] [w].setLabel (surrBombs);
                    break;

                }
            }
        }
    }


    public void checkSurrButton(int x, int y)  //this is what checks if a surrounding button has "0" is so expose it and checkAndExposeButtons around the isExposed buttons until there is no more "0"'s
    {
        String surrbombs;
        for (int i = x - 1 ; i <= x + 1 ; i++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (i < 0 || w < 0 || i >= rowCount || w >= columnCount) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (isFlag[i] [w] == true)
                        break;
                    if (isExposed[i] [w] == false && checkSurrBombs(i, w) == 0)
                    {
                        expose (i, w);
                        checkAndExposeButtons(i, w);
                    }
                    break;
                }
            }
        }
    }


    public void checkAndExposeButtons(int x, int y)  //calls the checkSurrButton method but is neccesary because of the expose first
    {
        expose (x, y);
        checkSurrButton(x, y);
    }


    public void checkWinStatus()    //checks if all the button without bombs have been pressed
    {
        boolean isAllExposed = true;
        for (int x = 0; x < rowCount; x++)
        {
            for (int y = 0; y < columnCount; y++)
            {
                if (isFlag[x] [y] == true && isBomb[x] [y] == false)
                    isAllExposed = false;
                if (checkButtonType[x] [y] == false)
                {
                    isAllExposed = false;
                    break;
                }
            }
        }
        if (isAllExposed != false)
        {
            gameOver();
            int x2 = (int) columnCount / 2;
            int y2 = (int) rowCount / 2;
            buttons[y2] [x2 - 4].setLabel ("Y");
            buttons[y2] [x2 - 3].setLabel ("O");
            buttons[y2] [x2 - 2].setLabel ("U");
            buttons[y2] [x2 - 1].setLabel ("");
            buttons[y2] [x2    ].setLabel ("W");
            buttons[y2] [x2 + 1].setLabel ("I");
            buttons[y2] [x2 + 2].setLabel ("N");
            buttons[y2] [x2 + 3].setLabel ("!");
            buttons[y2] [x2 + 4].setLabel ("!");

            for (int i = -4 ; i < 5 ; i++)
            {
                buttons[y2] [x2 + i].setBackground (Color.black);
                buttons[y2] [x2 + i].setForeground (Color.white);
            }
        }
    }


    public void gameOver()  // is called if isBomb is changeButtonColor or on the double click if isFlag is not on a isBomb
    {
        for (int x = 0; x < rowCount; x++)
        {
            for (int y = 0; y < columnCount; y++)
            {
                if (isBomb[x] [y] == true)
                {
                    buttons[x] [y].setLabel ("*"); //exposes all bombs
                    buttons[x] [y].setBackground (Color.red);
                }
                buttons[x] [y].setEnabled (false); //disable all buttons
            }
        }
        int x2 = (int) columnCount / 2;
        int y2 = (int) rowCount / 2;
        buttons[y2] [x2 - 4].setLabel ("Y");
        buttons[y2] [x2 - 3].setLabel ("O");
        buttons[y2] [x2 - 2].setLabel ("U");
        buttons[y2] [x2 - 1].setLabel ("");
        buttons[y2] [x2    ].setLabel ("L");
        buttons[y2] [x2 + 1].setLabel ("O");
        buttons[y2] [x2 + 2].setLabel ("S");
        buttons[y2] [x2 + 3].setLabel ("E");
        buttons[y2] [x2 + 4].setLabel ("!");

        for (int i = -4 ; i < 5 ; i++)
        {
            buttons[y2] [x2 + i].setBackground (Color.black);
            buttons[y2] [x2 + i].setForeground (Color.white);
        }
    }


    /////////////////////////////////////////////////////////////////
    //These are not used but are necessary for mouseListener
    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }
}



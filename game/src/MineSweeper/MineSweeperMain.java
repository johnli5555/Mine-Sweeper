package MineSweeper;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/*
 * The MineSweeperMain class runs the game. It implements and updates the game board, the mine board, and the 
 * num-of-surrounding-mines board. Minesweeper rules and algorithms are implemented here
 */

public class MineSweeperMain extends JFrame implements ActionListener, MouseListener
{
	// Initializing the minesweeper grids and the graphic user interface for the game
    static int x;
    static int y;
    static int numBomb;
    static int numBombTemp;
    int spaceLeft;
    static ArrayList<ArrayList<Integer>> grid;
    static ArrayList<ArrayList<Boolean>> checked;
    static ArrayList<ArrayList<Boolean>> mined;
    static ArrayList<ArrayList<JButton>> gridB;
    JPanel buttonPan;
    JPanel textPan;
    JPanel statPan;
    JLabel MineCounter;
    JLabel message;
    boolean unclicked;
    boolean lost;
    int firstClickX;
    int firstClickY;
    boolean rightClick;
    
    static {
        MineSweeperMain.x = 6;
        MineSweeperMain.y = 6;
        MineSweeperMain.numBomb = 5;
        MineSweeperMain.grid = new ArrayList<ArrayList<Integer>>();
        MineSweeperMain.checked = new ArrayList<ArrayList<Boolean>>();
        MineSweeperMain.mined = new ArrayList<ArrayList<Boolean>>();
        MineSweeperMain.gridB = new ArrayList<ArrayList<JButton>>();
    }
    
    // Once the settings have been set, this main bloc will be called and the game will be executed
    public MineSweeperMain(final int xSet, final int ySet, final int mines) {
        this.buttonPan = new JPanel();
        this.textPan = new JPanel();
        this.statPan = new JPanel();
        this.MineCounter = new JLabel();
        this.message = new JLabel();
        this.unclicked = true;
        this.lost = false;
        this.firstClickX = 0;
        this.firstClickY = 0;
        this.rightClick = false;
        MineSweeperMain.x = xSet;
        MineSweeperMain.y = ySet;
        MineSweeperMain.numBomb = mines;
        MineSweeperMain.numBombTemp = MineSweeperMain.numBomb;
        this.spaceLeft = MineSweeperMain.x * MineSweeperMain.y - MineSweeperMain.numBomb;
        this.setTitle("MINESWEEPER");
        this.setSize(600, 480);
        final GridLayout buttonLay = new GridLayout(MineSweeperMain.x, MineSweeperMain.y);
        final FlowLayout general = new FlowLayout();
        final FlowLayout mess = new FlowLayout();
        final FlowLayout statLayout = new FlowLayout();
        this.setLayout(general);
        this.buttonPan.setLayout(buttonLay);
        this.textPan.setLayout(mess);
        this.statPan.setLayout(statLayout);
        this.MineCounter.setText(String.valueOf(MineSweeperMain.numBombTemp));
        this.statPan.add(this.MineCounter);
        this.textPan.add(this.message);
        for (int i = 0; i < MineSweeperMain.x; ++i) {
            MineSweeperMain.gridB.add(new ArrayList<JButton>(MineSweeperMain.y));
            MineSweeperMain.checked.add(new ArrayList<Boolean>(MineSweeperMain.y));
            MineSweeperMain.mined.add(new ArrayList<Boolean>(MineSweeperMain.y));
            for (int j = 0; j < MineSweeperMain.y; ++j) {
                MineSweeperMain.gridB.get(i).add(new JButton(""));
                MineSweeperMain.gridB.get(i).get(j).addMouseListener(this);
                MineSweeperMain.gridB.get(i).get(j).addActionListener(this);
                this.buttonPan.add(MineSweeperMain.gridB.get(i).get(j));
                MineSweeperMain.checked.get(i).add(false);
                MineSweeperMain.mined.get(i).add(false);
            }
        }
        for (int i = 0; i < MineSweeperMain.x; ++i) {
            MineSweeperMain.grid.add(new ArrayList<Integer>(MineSweeperMain.y));
            for (int j = 0; j < MineSweeperMain.y; ++j) {
                MineSweeperMain.grid.get(i).add(0);
            }
        }
        this.buttonPan.setVisible(true);
        this.textPan.setVisible(true);
        this.statPan.setVisible(true);
        this.add(this.statPan);
        this.add(this.buttonPan);
        this.add(this.textPan);
        this.setResizable(true);
        this.setVisible(true);
    }
    
    // Processing and differentiating right and left clicks on the game board
    public void mousePressed(final MouseEvent event) {
        if (event.getButton() == 3) {
            this.rightClick = true;
            for (int i = 0; i < MineSweeperMain.gridB.size(); ++i) {
                for (int j = 0; j < MineSweeperMain.gridB.get(i).size(); ++j) {
                    if (MineSweeperMain.gridB.get(i).get(j) == event.getSource()) {
                        this.firstClickX = i;
                        this.firstClickY = j;
                    }
                }
            }
            if (!MineSweeperMain.checked.get(this.firstClickX).get(this.firstClickY)) {
                MineSweeperMain.checked.get(this.firstClickX).set(this.firstClickY, true);
                MineSweeperMain.mined.get(this.firstClickX).set(this.firstClickY, true);
                MineSweeperMain.gridB.get(this.firstClickX).get(this.firstClickY).setText("M");
                --MineSweeperMain.numBombTemp;
                this.MineCounter.setText(String.valueOf(MineSweeperMain.numBombTemp));
            }
            else if (MineSweeperMain.mined.get(this.firstClickX).get(this.firstClickY)) {
                MineSweeperMain.checked.get(this.firstClickX).set(this.firstClickY, false);
                MineSweeperMain.mined.get(this.firstClickX).set(this.firstClickY, false);
                MineSweeperMain.gridB.get(this.firstClickX).get(this.firstClickY).setText(" ");
                ++MineSweeperMain.numBombTemp;
                this.MineCounter.setText(String.valueOf(MineSweeperMain.numBombTemp));
            }
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        
        // Obtaining the clicked coordinates
        if (!this.lost) {
            for (int i = 0; i < MineSweeperMain.gridB.size(); ++i) {
                for (int j = 0; j < MineSweeperMain.gridB.get(i).size(); ++j) {
                    if (MineSweeperMain.gridB.get(i).get(j) == event.getSource()) {
                        this.firstClickX = i;
                        this.firstClickY = j;
                    }
                }
            }
            
            // If the board has not been clicked, generate random mines based on the settings
            if (this.unclicked) {
                for (int i = 0; i < MineSweeperMain.grid.size(); ++i) {
                    for (int j = 0; j < MineSweeperMain.grid.get(i).size(); ++j) {
                        if (this.unclicked) {
                            for (int k = 0; k < MineSweeperMain.numBomb; ++k) {
                                final int randX = (int)(Math.random() * MineSweeperMain.grid.size());
                                final int randY = (int)(Math.random() * MineSweeperMain.grid.get(i).size());
                                if (randX == this.firstClickX && randY == this.firstClickY) {
                                    --k;
                                }
                                else if (MineSweeperMain.grid.get(randX).get(randY) == -1) {
                                    --k;
                                }
                                else {
                                    MineSweeperMain.grid.get(randX).set(randY, -1);
                                }
                            }
                            this.initializeNum();
                            this.unclicked = false;
                        }
                    }
                }
            }
            
            // If there are no mines left on the board the game is won
            this.click(this.firstClickX, this.firstClickY);
            if (this.spaceLeft == 0) {
                this.message.setText("you won!");
                for (int a = 0; a < MineSweeperMain.grid.size(); ++a) {
                    for (int b = 0; b < MineSweeperMain.grid.get(this.firstClickX).size(); ++b) {
                        if (MineSweeperMain.grid.get(a).get(b) == -1) {
                            MineSweeperMain.gridB.get(a).get(b).setText(String.valueOf(MineSweeperMain.grid.get(a).get(b)));
                        }
                    }
                }
            }
        }
    }
    
    // Initializing the numbers on the minesweeper grid. They represent the surrounding number of mines
    public void initializeNum() {
        int tempNumMine = 0;
        for (int i = 0; i < MineSweeperMain.grid.size(); ++i) {
            for (int j = 0; j < MineSweeperMain.grid.get(i).size(); ++j) {
                if (MineSweeperMain.grid.get(i).get(j) != -1) {
                    tempNumMine = this.getSurMine(i, j);
                    MineSweeperMain.grid.get(i).set(j, tempNumMine);
                }
            }
        }
    }
    
    // Finds the number of surrounding mines next to a specific grid
    public int getSurMine(final int i, final int j) {
        int numMine = 0;
        if (MineSweeperMain.grid.get(i).get(j) != -1) {
            if (i < MineSweeperMain.x - 1 && MineSweeperMain.grid.get(i + 1).get(j) == -1) {
                ++numMine;
            }
            if (i > 0 && MineSweeperMain.grid.get(i - 1).get(j) == -1) {
                ++numMine;
            }
            if (j < MineSweeperMain.y - 1 && MineSweeperMain.grid.get(i).get(j + 1) == -1) {
                ++numMine;
            }
            if (j > 0 && MineSweeperMain.grid.get(i).get(j - 1) == -1) {
                ++numMine;
            }
            if (i < MineSweeperMain.x - 1 && j < MineSweeperMain.y - 1 && MineSweeperMain.grid.get(i + 1).get(j + 1) == -1) {
                ++numMine;
            }
            if (i < MineSweeperMain.x - 1 && j > 0 && MineSweeperMain.grid.get(i + 1).get(j - 1) == -1) {
                ++numMine;
            }
            if (i > 0 && j < MineSweeperMain.y - 1 && MineSweeperMain.grid.get(i - 1).get(j + 1) == -1) {
                ++numMine;
            }
            if (i > 0 && j > 0 && MineSweeperMain.grid.get(i - 1).get(j - 1) == -1) {
                ++numMine;
            }
        }
        return numMine;
    }
    
    // Processes a left mouse click. If it is a mine the game is lost and all mines and false-mines(NM) are shown
    // If the grid has mines surrounding it, it displays that number Otherwise 0 is shown and the grids around that 
    // one is clicked (this is to prevent repetitive and useless clicks)
    public void click(final int i, final int j) {
        if (MineSweeperMain.grid.get(i).get(j) == -1 && !MineSweeperMain.mined.get(this.firstClickX).get(this.firstClickY)) {
            this.lost = true;
            this.message.setText("You lost");
            for (int a = 0; a < MineSweeperMain.grid.size(); ++a) {
                for (int b = 0; b < MineSweeperMain.grid.get(this.firstClickX).size(); ++b) {
                    if (MineSweeperMain.grid.get(a).get(b) == -1) {
                        MineSweeperMain.gridB.get(a).get(b).setText(String.valueOf(MineSweeperMain.grid.get(a).get(b)));
                    }
                    else if (MineSweeperMain.mined.get(a).set(b, true)) {
                        MineSweeperMain.gridB.get(a).get(b).setText("NM");
                    }
                }
            }
        }
        else if (MineSweeperMain.grid.get(i).get(j) == 0 && !MineSweeperMain.checked.get(i).get(j)) {
            MineSweeperMain.checked.get(i).set(j, true);
            --this.spaceLeft;
            MineSweeperMain.gridB.get(i).get(j).setText(String.valueOf(MineSweeperMain.grid.get(i).get(j)));
            if (i < MineSweeperMain.x - 1) {
                this.click(i + 1, j);
            }
            if (i > 0) {
                this.click(i - 1, j);
            }
            if (j < MineSweeperMain.y - 1) {
                this.click(i, j + 1);
            }
            if (j > 0) {
                this.click(i, j - 1);
            }
            if (i < MineSweeperMain.x - 1 && j < MineSweeperMain.y - 1) {
                this.click(i + 1, j + 1);
            }
            if (i < MineSweeperMain.x - 1 && j > 0) {
                this.click(i + 1, j - 1);
            }
            if (i > 0 && j < MineSweeperMain.y - 1) {
                this.click(i - 1, j + 1);
            }
            if (i > 0 && j > 0) {
                this.click(i - 1, j - 1);
            }
        }
        else if (!MineSweeperMain.checked.get(i).get(j)) {
            --this.spaceLeft;
            MineSweeperMain.checked.get(i).set(j, true);
            MineSweeperMain.gridB.get(i).get(j).setText(String.valueOf(MineSweeperMain.grid.get(i).get(j)));
        }
    }
}
package MineSweeper;

import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
/*
 * The settings class initializes and executes the graphical user interface of the 
 * Settings. It also determines the mode in which you play and initializes the settings
 * for the modes
 */
public class Settings extends JFrame implements ActionListener
{
	//initializing buttons, labels, textfields, and panels 
    protected int x;
    protected int y;
    protected int mineNum;
    protected String saveFile;
    protected boolean terminated;
    static JButton startGame;
    static JButton loadGame;
    static JButton beginner;
    static JButton intermediate;
    static JButton expert;
    static JButton custom;
    static JButton okCustom;
    static JButton backLoad;
    static JButton backNew;
    static JButton backCustom;
    static ArrayList<JButton> saves;
    static JLabel gridWidth;
    static JLabel gridLength;
    static JLabel numMine;
    static JLabel chooseSaves;
    static JTextField width;
    static JTextField length;
    static JTextField mine;
    static JPanel panStart;
    static JPanel panDiff;
    static JPanel panCustom;
    static JPanel panLoad;
    static JPanel panGeneral;
    
    static {
    	//initializing the properties of buttons, labels, textfields, and panels
        Settings.startGame = new JButton("NEW GAME");
        Settings.loadGame = new JButton("LOAD GAME");
        Settings.beginner = new JButton("BEGINNER");
        Settings.intermediate = new JButton("INTERMEDIATE");
        Settings.expert = new JButton("EXPERT");
        Settings.custom = new JButton("CUSTOM");
        Settings.okCustom = new JButton("OK");
        Settings.backLoad = new JButton("QUIT LOAD");
        Settings.backNew = new JButton("QUIT NEW GAME");
        Settings.backCustom = new JButton("QUIT CUSTOM");
        Settings.saves = new ArrayList<JButton>();
        Settings.gridWidth = new JLabel("Enter width of game grid");
        Settings.gridLength = new JLabel("Enter length of game grid");
        Settings.numMine = new JLabel("enter number of mines");
        Settings.chooseSaves = new JLabel("click on the following save file to load");
        Settings.width = new JTextField("0");
        Settings.length = new JTextField("0");
        Settings.mine = new JTextField("0");
        Settings.panStart = new JPanel();
        Settings.panDiff = new JPanel();
        Settings.panCustom = new JPanel();
        Settings.panLoad = new JPanel();
        Settings.panGeneral = new JPanel();
    }
    
    // The settings function is called when the program is run and the start game panel is set and made visible
    public Settings() {
        this.terminated = false;
        this.setTitle("MINESWEEPER");
        this.setSize(320, 240);
        this.setResizable(true);
        this.setVisible(true);
        Settings.saves.add(new JButton("mockSave"));
        
        // Action listeners are added to the buttons
        Settings.startGame.addActionListener(this);
        Settings.loadGame.addActionListener(this);
        Settings.beginner.addActionListener(this);
        Settings.intermediate.addActionListener(this);
        Settings.expert.addActionListener(this);
        Settings.custom.addActionListener(this);
        Settings.backNew.addActionListener(this);
        Settings.backLoad.addActionListener(this);
        Settings.backCustom.addActionListener(this);
        Settings.okCustom.addActionListener(this);
        
        // Generation of different grids based on different modes of the game
        final FlowLayout general = new FlowLayout();
        this.setLayout(general);
        final BoxLayout gen = new BoxLayout(Settings.panGeneral, 1);
        Settings.panGeneral.setLayout(gen);
        final BoxLayout startLay = new BoxLayout(Settings.panStart, 1);
        Settings.panStart.setLayout(startLay);
        final BoxLayout diffLay = new BoxLayout(Settings.panDiff, 1);
        Settings.panDiff.setLayout(diffLay);
        final BoxLayout loadLay = new BoxLayout(Settings.panLoad, 1);
        Settings.panLoad.setLayout(loadLay);
        final BoxLayout customLay = new BoxLayout(Settings.panCustom, 1);
        
        // Initializing information depending on modes. Custom mode also takes in parameters
        Settings.panCustom.setLayout(customLay);
        Settings.panStart.add(Settings.startGame);
        Settings.panStart.add(Settings.loadGame);
        Settings.panDiff.add(Settings.beginner);
        Settings.panDiff.add(Settings.intermediate);
        Settings.panDiff.add(Settings.expert);
        Settings.panDiff.add(Settings.custom);
        Settings.panDiff.add(Settings.backNew);
        Settings.panDiff.setVisible(false);
        Settings.panLoad.add(Settings.chooseSaves);
        for (int i = 0; i < Settings.saves.size(); ++i) {
            Settings.saves.get(i).addActionListener(this);
            Settings.panLoad.add(Settings.saves.get(i));
        }
        Settings.panLoad.add(Settings.backLoad);
        Settings.panLoad.setVisible(false);
        Settings.panCustom.add(Settings.gridLength);
        Settings.panCustom.add(Settings.length);
        Settings.panCustom.add(Settings.gridWidth);
        Settings.panCustom.add(Settings.width);
        Settings.panCustom.add(Settings.numMine);
        Settings.panCustom.add(Settings.mine);
        Settings.panCustom.add(Settings.okCustom);
        Settings.panCustom.add(Settings.backCustom);
        Settings.panCustom.setVisible(false);
        Settings.panGeneral.add(Settings.panStart);
        Settings.panGeneral.add(Settings.panDiff);
        Settings.panGeneral.add(Settings.panLoad);
        Settings.panGeneral.add(Settings.panCustom);
        this.add(Settings.panGeneral);
    }
    
 // Determining the results of action listeners, i.e what happens when buttons are clicked
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        if (command.equalsIgnoreCase("NEW GAME")) {
            Settings.panStart.setVisible(false);
            Settings.panDiff.setVisible(true);
        }
        else if (command.equalsIgnoreCase("LOAD GAME")) {
            Settings.panStart.setVisible(false);
            Settings.panLoad.setVisible(true);
        }
        else if (command.equalsIgnoreCase("BEGINNER")) {
            final MineSweeperMain mineSweep = new MineSweeperMain(9, 9, 10);
            this.dispose();
        }
        else if (command.equalsIgnoreCase("INTERMEDIATE")) {
            final MineSweeperMain mineSweep = new MineSweeperMain(16, 16, 40);
            this.dispose();
        }
        else if (command.equalsIgnoreCase("EXPERT")) {
            final MineSweeperMain mineSweep = new MineSweeperMain(16, 30, 99);
            this.dispose();
        }
        else if (command.equalsIgnoreCase("CUSTOM")) {
            Settings.panDiff.setVisible(false);
            Settings.panCustom.setVisible(true);
        }
        else if (command.equalsIgnoreCase("QUIT NEW GAME")) {
            Settings.panDiff.setVisible(false);
            Settings.panStart.setVisible(true);
        }
        else if (command.equalsIgnoreCase("QUIT LOAD")) {
            Settings.panLoad.setVisible(false);
            Settings.panStart.setVisible(true);
        }
        else if (Settings.saves.contains(command)) {
            this.saveFile = command;
            this.dispose();
        }
        else if (command.equalsIgnoreCase("OK")) {
            this.x = Integer.parseInt(Settings.length.getText());
            this.y = Integer.parseInt(Settings.width.getText());
            this.mineNum = Integer.parseInt(Settings.mine.getText());
            final MineSweeperMain mineSweep = new MineSweeperMain(this.x, this.y, this.mineNum);
            this.dispose();
        }
        else if (command.equalsIgnoreCase("QUIT CUSTOM")) {
            Settings.panCustom.setVisible(false);
            Settings.panDiff.setVisible(true);
        }
    }
}
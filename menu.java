import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.lang.module.ModuleDescriptor;


public class menu extends JFrame{
    private JFrame frame;
    //private JButton start;
    //private JButton Rules;
    //private JButton Options;
   // private JButton Stats;
    private String gameMode;
    private String playerOption;
    private static int playCount;
    private static int gamesWon;
    private static double winPercentage;
    private int colorChooser;
    private Color colorNames[] = { Color.BLACK, Color.BLUE, Color.CYAN,
            Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE,
            Color.PINK, Color.RED, Color.YELLOW };
    ButtonGroup gamemodeGroup;
    ButtonGroup playerGroup;

    public menu() {
        createAndShowGUI("Menu");

        //createAndShowGUI("Rules");
    }

    public static void main(String[] args) {
        menu men = new menu();

//        createAndShowGUIMenu();
//        createAndShowGUISetup();
//        createAndShowGUIStats();
//        createAndShowGUIRules();
    }

    private void addAButton(JButton buttName, Container container) {
        buttName.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttName.setFont(new Font("Verdana", Font.PLAIN, 50));
        buttName.setPreferredSize(new Dimension(400, 75));
        container.add(buttName);
    }

    private static void addRadioButton(JRadioButton radio, Container container) {
        radio.setAlignmentX(Component.CENTER_ALIGNMENT);
        radio.setFont(new Font("Verdana", Font.PLAIN, 20));
        radio.setPreferredSize(new Dimension(50, 75));
        container.add(radio);
    }

    private static void addlabel(String text, Container container, int size, Color col) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Verdana", Font.BOLD, size));
        label.setForeground(col);
        container.add(label);
    }

    private static void addlabelforStats(String text, double measure, Container container, int size, Color col) {
        JLabel labelFStats = new JLabel(text + measure);
        labelFStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelFStats.setFont(new Font("Verdana", Font.BOLD, size));
        labelFStats.setForeground(col);
        container.add(labelFStats);
    }

    public void Menu(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        ButtonHandler buttonHandle = new ButtonHandler();

        addlabel("MANCALA", pane, 100, colorNames[colorChooser]);
        JButton playGame = new JButton("Play Game");
        addAButton(playGame, pane);
        playGame.addActionListener(buttonHandle);
        JButton stats = new JButton("Statistics");
        addAButton(stats, pane);
        stats.addActionListener(buttonHandle);
        JButton DoNotPress = new JButton("DO NOT PRESS!");
        addAButton(DoNotPress, pane);
        DoNotPress.addActionListener(buttonHandle);

    }

    public void Setup(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        CheckBoxHandler Players = new CheckBoxHandler();
        playerGroup = new ButtonGroup();

        addlabel("Choose Players", pane, 50, Color.BLACK);
        JRadioButton PVP = new JRadioButton("Player vs Player");
        addRadioButton(PVP, pane);
        PVP.addActionListener(Players);
        playerGroup.add(PVP);

        JRadioButton PVC = new JRadioButton("Player vs Computer");
        addRadioButton(PVC, pane);
        PVC.addActionListener(Players);
        playerGroup.add(PVC);

        JRadioButton CVC = new JRadioButton("Computer vs Computer");
        addRadioButton(CVC, pane);
        CVC.addActionListener(Players);
        playerGroup.add(CVC);

        CheckBoxHandler gameMode = new CheckBoxHandler();
        addlabel("Game Mode", pane, 50, Color.BLACK);
        gamemodeGroup = new ButtonGroup();

        JRadioButton capture = new JRadioButton("Capture");
        addRadioButton(capture, pane);
        capture.addActionListener(gameMode);
        gamemodeGroup.add(capture);

        JRadioButton avalanche = new JRadioButton("Avalanche");
        addRadioButton(avalanche, pane);
        capture.addActionListener(gameMode);
        gamemodeGroup.add(avalanche);

        ButtonHandler buttonHandle = new ButtonHandler();
        JButton startGame = new JButton("Start Game");
        addAButton(startGame, pane);
        startGame.addActionListener(buttonHandle);

    }

    public static void Statistics(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addlabel("Statistics", pane, 80, Color.RED);
        addlabelforStats("Games Played: ", playCount, pane, 40, Color.RED);
        addlabelforStats("Games Won: ", gamesWon, pane, 40, Color.ORANGE);
        addlabelforStats("Winning Percentage: ", winPercentage, pane, 40, Color.RED);

    }

    public void captureRules(Container pane) {

            String str = ("<html><span>1. Each Player has a store on one side of the board.<br><br>" +
                    "2.Players take turns choosing a pile from one of the holes. Moving<br>" +
                    "counter-clockwise, stones from the selected pile are deposited in each<br>" +
                    "of the following hole until you run out of stones.<br><br>" +
                    "3. If you drop the last stone into your store - you get a free turn.<br><br>" +
                    "4. If you drop the last stone into an empty hole on your side of the board" +
                    "- you can capture stones from the hole on the opposite side.<br><br>" +
                    "5. The game ends when all six holes on either side of the board are empty." +
                    "If a player has any stones on their side of the board when the game ends -" +
                    "he will capture all of those stones.<br><br><br>" +
                    "GOAL: PLAYER WITH THE MOST STONES IN THEIR STORE WINS<br></span></html>");



        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addlabel("Rules of the Game", pane, 50, Color.RED);
        addlabel(str, pane, 15, Color.BLACK);

        ButtonHandler buttonHandle = new ButtonHandler();
        JButton Begin = new JButton("Begin!");
        addAButton(Begin, pane);
        Begin.addActionListener(buttonHandle);

    }

    public void avalancheRules(Container pane) {

        String str = ("<html><span>Rules:<br> 1. Each Player has a store on one side of the board.<br><br>" +
                "2. Players take turns choosing a pile from one of the holes. Moving counter-clockwise, stones from " +
                "the selected pile are deposited in each of the following hole.<br><br>" +
                "3. If you drop the last stone into an unempty hole, you will pick up the stones from that hole and " +
                "continue depositing them counter-clockwise.<br><br>" +
                "4. Your turn is over when you drop the last stone into an empty hole. <br><br>" +
                "5. If you drop the last stone into your store - you get a free turn.<br><br>" +
                "6. The game ends when all six holes on either side of the board are empty.<br><br>" +
                "Goal: Player with most stones in their stones wins.</span></html>");



        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addlabel("Rules of the Game", pane, 50, Color.RED);
        addlabel(str, pane, 15, Color.BLACK);

        ButtonHandler buttonHandle = new ButtonHandler();
        JButton Begin = new JButton("Begin!");
        addAButton(Begin, pane);
        Begin.addActionListener(buttonHandle);

    }

    private void createAndShowGUI(String frameName) {
        frame = new JFrame(frameName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (frameName.equals("Menu"))
            Menu(frame.getContentPane());
        else if (frameName.equals("Setup"))
            Setup(frame.getContentPane());
        else if (frameName.equals("Statistics"))
            Statistics(frame.getContentPane());
        else if(frameName.equals("captureRules"))
            captureRules(frame.getContentPane());
        else if(frameName.equals("avalancheRules"))
            avalancheRules(frame.getContentPane());


        frame.pack();
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private class CheckBoxHandler implements ActionListener{    // respond to checkbox events
        public void actionPerformed(ActionEvent event) {
            String str = event.getActionCommand();
            // process bold checkbox events
            if (str.equals("Player vs Player")) {
                playerOption = "PVP";

            } else if (str.equals("Player vs Computer")) {
                playerOption = "PVC";

            } else if (str.equals("Computer vs Computer")) {
                playerOption = "CVC";

            }

            if (str.equals("capture")) {
                gameMode = "capture";

            } else if (str.equals("avalanche")) {
                gameMode = "avalanche";

            }

        } // end method itemStateChanged
    }
    // end private inner class CheckBoxHandler

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String str = event.getActionCommand();
            if (str.equals("Play Game")) {
                frame.setVisible(false);
                createAndShowGUI("Setup");
            } else if (str.equals("Statistics")) {
                frame.setVisible(false);
                createAndShowGUI("Statistics");
            } else if (str.equals("DO NOT PRESS!")) {
                frame.setVisible(false);
                if (colorChooser == 9)
                    colorChooser = 0;
                else
                    colorChooser++;

                createAndShowGUI("Menu");
            }else if(str.equals("Start Game")){
                frame.setVisible(false);
                if(gameMode.equals("capture"))
                    createAndShowGUI("captureRules");
                else
                    createAndShowGUI("avalancheRules");

            }else if(str.equals("Begin!")){
                frame.setVisible(false);
                createAndShowGUI("GAME"); //This is where the game will begin. We will need input
            }
        } // end method itemStateChanged

    } // end private inner class CheckBoxHandler
}
//
//    public class Butt extends JFrame implements ActionListener{
//        private Button button1;
//        public Butt(){
//            super();
//            this.setSize(100,100);
//            this.setVisible(true);
//
//            this.button1 = newJButton("1");
//            this.button1.addActionListener(this);
//            this.add(button1);
//        }
//        public static void main(String[] args){
//            Butt but = new Butt();
//            but.setVisible(true);
//        }
//        public void actionPerformed(ActionEvent e){
//            if(e.getSource() == button1){
//
//            }else if(e.getSource() == button2){
//
//            }else if(e.getSource() == button3){
//
//            }
//        }
//    }

//    public menu(){
//        setLayout(new FlowLayout());
//
//        label1 = new JLabel("MANCALA");
//        label1.setFont(new Font("Verdana", Font.BOLD, 90));
//        label1.setForeground(Color.blue);
//        add(label1);
//
//        start = new JButton("Play Game");
//        start.setFont(new Font("Verdana", Font.PLAIN, 50));
//        start.setPreferredSize(new Dimension(400, 75));
//        add(start);
//
//        Rules = new JButton("Rules");
//        Rules.setFont(new Font("Verdana", Font.PLAIN, 50));
//        Rules.setPreferredSize(new Dimension(400, 75));
//        add(Rules);
//
//        Options = new JButton("Statistics");
//        Options.setFont(new Font("Verdana", Font.PLAIN, 50));
//        Options.setPreferredSize(new Dimension(400, 75));
//        add(Options);
//
//        Stats = new JButton("Settings");
//        Stats.setFont(new Font("Verdana", Font.PLAIN, 50));
//        Stats.setPreferredSize(new Dimension(400, 75));
//        add(Stats);
//    }

//
//        public void paintComponent(Graphics2D g) {
//            super.paintComponent(g);
//            int startX = getWidth();
//            int startY = getHeight();
//
//
//            g.setFont(new Font("TimesRoman", Font.BOLD, 100));
//            g.drawString("MANCALA", startX, startY);
//            g.setColor(Color.CYAN);
//            g.fill3DRect(startX / 200, startY / 7, startX, startY, true);
//            g.setColor(Color.BLACK);
//
//
//        }




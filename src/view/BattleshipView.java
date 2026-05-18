package view;
import model.Board;
import javax.swing.*; import java.awt.*; import java.awt.event.*;
public class BattleshipView extends JFrame {
    private static final Color WATER=new Color(203,232,245), DOT=new Color(28,28,28), SHIP=new Color(170,176,176), HIT=new Color(255,89,48), MISS=new Color(75,210,230);
    private final JButton[][] playerButtons=new JButton[Board.SIZE][Board.SIZE];
    private final JButton[][] computerButtons=new JButton[Board.SIZE][Board.SIZE];
    private final JLabel statusLabel=new JLabel("Place your ships on the left board.",JLabel.CENTER);
    private final JLabel currentShipLabel=new JLabel("Current ship: Carrier (5)",JLabel.CENTER);
    private final JToggleButton orientationButton=new JToggleButton("Orientation: Horizontal");
    private final JButton startBattleButton=new JButton("Start Battle"), restartButton=new JButton("Restart Game");
    public BattleshipView(){
        setTitle("Battleship Game - Manual Placement MVC"); setSize(1050,690); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLayout(new BorderLayout(10,10));
        JLabel title=new JLabel("BATTLESHIP GAME",JLabel.CENTER); title.setFont(new Font("Serif",Font.BOLD,30)); statusLabel.setFont(new Font("Arial",Font.BOLD,16));
        JPanel top=new JPanel(new GridLayout(2,1)); top.add(title); top.add(statusLabel);
        JPanel boards=new JPanel(new GridLayout(1,2,20,0)); boards.setBorder(BorderFactory.createEmptyBorder(10,20,10,20)); boards.add(boardPanel("Your Board - Place Ships Here",playerButtons)); boards.add(boardPanel("Computer Board - Attack Here",computerButtons));
        currentShipLabel.setFont(new Font("Arial",Font.BOLD,15)); JPanel bottom=new JPanel(new GridLayout(1,4,10,0)); bottom.setBorder(BorderFactory.createEmptyBorder(0,20,15,20)); bottom.add(currentShipLabel); bottom.add(orientationButton); bottom.add(startBattleButton); bottom.add(restartButton);
        add(top,BorderLayout.NORTH); add(boards,BorderLayout.CENTER); add(bottom,BorderLayout.SOUTH); startBattleButton.setEnabled(false); setComputerBoardEnabled(false);
    }
    private JPanel boardPanel(String labelText,JButton[][] buttons){
        JPanel panel=new JPanel(new BorderLayout()); JLabel label=new JLabel(labelText,JLabel.CENTER); label.setFont(new Font("Arial",Font.BOLD,16));
        JPanel grid=new JPanel(new GridLayout(Board.SIZE,Board.SIZE)); grid.setBorder(BorderFactory.createLineBorder(new Color(120,95,55),4));
        for(int r=0;r<Board.SIZE;r++) for(int c=0;c<Board.SIZE;c++){ JButton b=waterButton(); buttons[r][c]=b; grid.add(b); }
        panel.add(label,BorderLayout.NORTH); panel.add(grid,BorderLayout.CENTER); return panel;
    }
    private JButton waterButton(){ JButton b=new JButton("●"); b.setPreferredSize(new Dimension(46,46)); b.setFont(new Font("Arial",Font.BOLD,16)); b.setForeground(DOT); b.setBackground(WATER); b.setFocusPainted(false); b.setBorder(BorderFactory.createLineBorder(Color.GRAY)); b.setCursor(new Cursor(Cursor.HAND_CURSOR)); return b; }
    public void addPlayerBoardListener(int r,int c,ActionListener l){playerButtons[r][c].addActionListener(l);} public void addComputerBoardListener(int r,int c,ActionListener l){computerButtons[r][c].addActionListener(l);}
    public void addOrientationListener(ActionListener l){orientationButton.addActionListener(l);} public void addStartBattleListener(ActionListener l){startBattleButton.addActionListener(l);} public void addRestartListener(ActionListener l){restartButton.addActionListener(l);}
    public void updateOrientationText(boolean h){orientationButton.setText(h?"Orientation: Horizontal":"Orientation: Vertical");}
    public void setCurrentShipText(String t){currentShipLabel.setText(t);} public void setStatus(String m){statusLabel.setText(m);} public void setStartBattleEnabled(boolean e){startBattleButton.setEnabled(e);}
    public void setPlayerPlacementEnabled(boolean e){for(int r=0;r<Board.SIZE;r++)for(int c=0;c<Board.SIZE;c++)playerButtons[r][c].setEnabled(e);} public void setComputerBoardEnabled(boolean e){for(int r=0;r<Board.SIZE;r++)for(int c=0;c<Board.SIZE;c++)computerButtons[r][c].setEnabled(e);}
    public void showPlayerShip(int r,int c){JButton b=playerButtons[r][c]; b.setText("■"); b.setForeground(Color.DARK_GRAY); b.setBackground(SHIP);} 
    public void markPlayerHit(int r,int c){mark(playerButtons[r][c],HIT);} public void markPlayerMiss(int r,int c){mark(playerButtons[r][c],MISS);} public void markComputerHit(int r,int c){mark(computerButtons[r][c],HIT); computerButtons[r][c].setEnabled(false);} public void markComputerMiss(int r,int c){mark(computerButtons[r][c],MISS); computerButtons[r][c].setEnabled(false);} public void disableComputerBoard(){setComputerBoardEnabled(false);} 
    private void mark(JButton b,Color color){b.setText("●"); b.setForeground(Color.WHITE); b.setBackground(color);} 
}

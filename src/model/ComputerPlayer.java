package model;
import java.awt.Point; import java.util.*;
public class ComputerPlayer extends Player {
    private final Random random=new Random(); private final List<Point> targets=new ArrayList<>();
    public ComputerPlayer(String name){super(name);}
    public Point chooseMove(Board enemyBoard){
        while(!targets.isEmpty()){ Point p=targets.remove(0); if(isValid(enemyBoard,p.x,p.y)) return p; }
        int r,c; do{r=random.nextInt(Board.SIZE); c=random.nextInt(Board.SIZE);}while(!isValid(enemyBoard,r,c));
        return new Point(r,c);
    }
    public void addSmartTargets(int r,int c,Board b){ add(r-1,c,b); add(r+1,c,b); add(r,c-1,b); add(r,c+1,b); }
    private void add(int r,int c,Board b){ if(isValid(b,r,c)) targets.add(new Point(r,c)); }
    private boolean isValid(Board b,int r,int c){ return r>=0&&r<Board.SIZE&&c>=0&&c<Board.SIZE&&!b.getCell(r,c).isAttacked(); }
}

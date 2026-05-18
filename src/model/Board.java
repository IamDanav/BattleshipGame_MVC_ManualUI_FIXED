package model;
import java.util.*;
public class Board {
    public static final int SIZE=10;
    private final Cell[][] grid=new Cell[SIZE][SIZE];
    private final List<Ship> ships=new ArrayList<>();
    public Board(){ for(int r=0;r<SIZE;r++) for(int c=0;c<SIZE;c++) grid[r][c]=new Cell(r,c); }
    public Cell getCell(int r,int c){return grid[r][c];}
    public List<int[]> previewShipCells(int row,int col,int size,boolean horizontal){
        List<int[]> cells=new ArrayList<>();
        for(int i=0;i<size;i++) cells.add(horizontal?new int[]{row,col+i}:new int[]{row+i,col});
        return cells;
    }
    public boolean canPlaceShip(int row,int col,int size,boolean horizontal){
        if(row<0||row>=SIZE||col<0||col>=SIZE) return false;
        if(horizontal){ if(col+size>SIZE) return false; for(int i=0;i<size;i++) if(grid[row][col+i].hasShip()) return false; }
        else { if(row+size>SIZE) return false; for(int i=0;i<size;i++) if(grid[row+i][col].hasShip()) return false; }
        return true;
    }
    public boolean placeShip(Ship ship,int row,int col,boolean horizontal){
        if(!canPlaceShip(row,col,ship.getSize(),horizontal)) return false;
        for(int i=0;i<ship.getSize();i++) if(horizontal) grid[row][col+i].setShip(ship); else grid[row+i][col].setShip(ship);
        ships.add(ship); return true;
    }
    public AttackResult attack(int row,int col){
        if(row<0||row>=SIZE||col<0||col>=SIZE) return new AttackResult(false,false,false,null,"Invalid attack position.");
        Cell cell=grid[row][col];
        if(cell.isAttacked()) return new AttackResult(false,false,false,null,"This cell was already attacked.");
        cell.setAttacked(true);
        if(cell.hasShip()){
            Ship ship=cell.getShip(); ship.hit();
            if(ship.isSunk()) return new AttackResult(true,true,true,ship.getName(),"Hit and sunk "+ship.getName()+"!");
            return new AttackResult(true,true,false,ship.getName(),"Hit!");
        }
        return new AttackResult(true,false,false,null,"Miss!");
    }
    public boolean allShipsSunk(){ if(ships.isEmpty()) return false; for(Ship s:ships) if(!s.isSunk()) return false; return true; }
    public void placeShipsRandomly(){ Random random=new Random(); for(ShipType t:ShipType.values()){ boolean ok=false; while(!ok){ ok=placeShip(new Ship(t.getName(),t.getSize()),random.nextInt(SIZE),random.nextInt(SIZE),random.nextBoolean()); } } }
}

package model;
public class Cell {
    private final int row, col;
    private boolean attacked;
    private Ship ship;
    public Cell(int row, int col) { this.row=row; this.col=col; }
    public int getRow(){return row;} public int getCol(){return col;}
    public boolean hasShip(){return ship!=null;} public Ship getShip(){return ship;}
    public void setShip(Ship ship){this.ship=ship;}
    public boolean isAttacked(){return attacked;} public void setAttacked(boolean attacked){this.attacked=attacked;}
}

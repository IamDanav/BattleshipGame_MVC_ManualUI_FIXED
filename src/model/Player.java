package model;
public abstract class Player {
    protected final String name; protected final Board board;
    public Player(String name){this.name=name;this.board=new Board();}
    public String getName(){return name;} public Board getBoard(){return board;}
}

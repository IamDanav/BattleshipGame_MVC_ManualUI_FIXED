package model;
public class Ship {
    private final String name; private final int size; private int hitCount;
    public Ship(String name, int size){this.name=name; this.size=size;}
    public void hit(){ if(hitCount<size) hitCount++; }
    public boolean isSunk(){ return hitCount>=size; }
    public String getName(){return name;} public int getSize(){return size;}
}

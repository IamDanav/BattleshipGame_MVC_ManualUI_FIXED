package model;
public class AttackResult {
    private final boolean valid, hit, sunk; private final String shipName, message;
    public AttackResult(boolean valid, boolean hit, boolean sunk, String shipName, String message){this.valid=valid;this.hit=hit;this.sunk=sunk;this.shipName=shipName;this.message=message;}
    public boolean isValid(){return valid;} public boolean isHit(){return hit;} public boolean isSunk(){return sunk;}
    public String getShipName(){return shipName;} public String getMessage(){return message;}
}

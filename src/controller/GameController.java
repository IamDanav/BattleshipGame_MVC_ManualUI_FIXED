package controller;
import model.*; import view.BattleshipView; import java.awt.Point; import java.util.List;
public class GameController {
    private HumanPlayer human; private ComputerPlayer computer; private BattleshipView view; private boolean horizontal=true, battleStarted=false; private int shipIndex=0;
    public void startGame(){
        horizontal = true;
        battleStarted = false;
        shipIndex = 0;

        human = new HumanPlayer("Player");
        computer = new ComputerPlayer("Computer");
        computer.getBoard().placeShipsRandomly();

        view = new BattleshipView();
        setupListeners();
        updatePlacementMessage();
        view.updateOrientationText(horizontal);
        view.setVisible(true);
    }
    private void setupListeners(){
        for(int r=0;r<Board.SIZE;r++) for(int c=0;c<Board.SIZE;c++){ final int row=r,col=c; view.addPlayerBoardListener(row,col,e->placeHumanShip(row,col)); view.addComputerBoardListener(row,col,e->playerAttack(row,col)); }
        view.addOrientationListener(e->{horizontal=!horizontal; view.updateOrientationText(horizontal);}); view.addStartBattleListener(e->startBattle()); view.addRestartListener(e->restartGame());
    }
    private void placeHumanShip(int row,int col){
        if(battleStarted||shipIndex>=ShipType.values().length) return; ShipType type=ShipType.values()[shipIndex];
        if(!human.getBoard().canPlaceShip(row,col,type.getSize(),horizontal)){ view.setStatus("Invalid position for "+type.getName()+". Try another cell or change orientation."); return; }
        human.getBoard().placeShip(new Ship(type.getName(),type.getSize()),row,col,horizontal);
        List<int[]> cells=human.getBoard().previewShipCells(row,col,type.getSize(),horizontal); for(int[] cell:cells) view.showPlayerShip(cell[0],cell[1]);
        shipIndex++; if(shipIndex>=ShipType.values().length){ view.setCurrentShipText("All ships placed."); view.setStatus("All ships placed. Click Start Battle."); view.setStartBattleEnabled(true); view.setPlayerPlacementEnabled(false); } else updatePlacementMessage();
    }
    private void updatePlacementMessage(){ ShipType current=ShipType.values()[shipIndex]; view.setCurrentShipText("Current ship: "+current.getName()+" ("+current.getSize()+")"); view.setStatus("Place your "+current.getName()+" of size "+current.getSize()+"."); }
    private void startBattle(){ if(shipIndex<ShipType.values().length){view.setStatus("Place all ships before starting the battle."); return;} battleStarted=true; view.setStatus("Battle started. Attack the computer board!"); view.setComputerBoardEnabled(true); view.setStartBattleEnabled(false); }
    private void playerAttack(int row,int col){
        if(!battleStarted){view.setStatus("Place your ships and click Start Battle first."); return;} AttackResult result=computer.getBoard().attack(row,col); if(!result.isValid()){view.setStatus(result.getMessage()); return;}
        if(result.isHit()){view.markComputerHit(row,col); view.setStatus("Player: "+result.getMessage());} else {view.markComputerMiss(row,col); view.setStatus("Player missed.");}
        if(computer.getBoard().allShipsSunk()){view.setStatus("You win! All computer ships are sunk."); view.disableComputerBoard(); return;} computerTurn();
    }
    private void computerTurn(){ Point move=computer.chooseMove(human.getBoard()); AttackResult result=human.getBoard().attack(move.x,move.y); if(result.isHit()){view.markPlayerHit(move.x,move.y); computer.addSmartTargets(move.x,move.y,human.getBoard()); view.setStatus("Computer hit your ship!");} else {view.markPlayerMiss(move.x,move.y); view.setStatus("Computer missed. Your turn.");} if(human.getBoard().allShipsSunk()){view.setStatus("Computer wins! All your ships are sunk."); view.disableComputerBoard();} }
    private void restartGame(){
        view.dispose();
        startGame();
    }
}

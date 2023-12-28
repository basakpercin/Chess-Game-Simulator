import java.util.*;

public class Pawn extends Piece {
    public Pawn(Color c) { super(c); }
    // implement appropriate methods

    public String toString() {
        Color colored = this.color();
        if (colored == Color.BLACK){
            return "bp";
        } else {
            return "wp";
        }
    }

    public List<String> moves(Board b, String loc) {
        List<String> allMoves = new ArrayList<>();

        int Direction; //Direction changes dRow

        if(isWhite()){
            Direction = 1;
        } else {
            Direction = -1;
        }

        int[] dColumn = {0, -1, 1};
        int[] dRow = {Direction, Direction, Direction};
        int[] homeRow = {Direction, Direction*2};

        if(isHome(loc)) for (int j : homeRow) {
            String newLoc = changeLoc(loc, 0, j);
            if (newLoc == null) {
                continue;
            }

            Piece pieceNewLoc = b.getPiece(newLoc);

            if (pieceNewLoc == null && !allMoves.contains(newLoc)) {
                allMoves.add(newLoc);
            }
        }

        for(int i = 0; i < dColumn.length; i++){
            String newLoc = changeLoc(loc, dColumn[i], dRow[i]);
            if (newLoc == null){
                continue;
            }

            Piece pieceNewLoc = b.getPiece(newLoc);

            if(pieceNewLoc == null && dColumn[i] == 0 && !allMoves.contains(newLoc)){
                allMoves.add(newLoc);
            } else if (pieceNewLoc != null) {
                if ((pieceNewLoc.color() != this.color()) && (dColumn[i] != 0) && !allMoves.contains(newLoc)) {
                    allMoves.add(newLoc);
                }
            }
        }
        return allMoves;
    }

    private boolean isHome(String loc){
        if (loc.endsWith("7") && this.color() == Color.BLACK){
            return true;
        } else if (loc.endsWith("2") && this.color() == Color.WHITE) {
            return true;
        }
        return false;
    }

    private boolean isWhite(){
        return (this.color() == Color.WHITE);
    }
}
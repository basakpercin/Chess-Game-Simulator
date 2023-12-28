import java.util.*;

public class King extends Piece {
    public King(Color c) {
        super(c);
    }
    // implement appropriate methods

    public String toString() {
        Color colored = this.color();
        if (colored == Color.BLACK){
            return "bk";
        } else {
            return "wk";
        }
    }

    public List<String> moves(Board b, String loc) {
        List<String> allMoves = new ArrayList<>();

        int[] dColumn = {-1, 1, 0, 0, -1, 1, -1, 1};
        int[] dRow = {0, 0, 1, -1, 1, 1, -1, -1};

        for (int i = 0; i < dColumn.length; i++){
            String newLoc = changeLoc(loc, dColumn[i], dRow[i]);
            if (newLoc == null){
                continue;
            }

            Piece pieceNewLoc = b.getPiece(newLoc);

            if(pieceNewLoc == null){
                allMoves.add(newLoc);
            } else if (pieceNewLoc.color() != this.color()){
                allMoves.add(newLoc);
            }
        }
        return allMoves;
    }
}
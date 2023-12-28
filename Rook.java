import java.util.*;

public class Rook extends Piece {
    public Rook(Color c) { super(c); }
    // implement appropriate methods

    public String toString() {
        Color colored = this.color();
        if (colored == Color.BLACK){
            return "br";
        } else {
            return "wr";
        }
    }

    public List<String> moves(Board b, String loc) {
        List<String> allMoves = new ArrayList<>();

        int[] dColumn = {-1, 1, 0, 0};
        int[] dRow = {0, 0, 1, -1};

        for (int i = 0; i < dColumn.length; i++){
            for (int j = 1; j <= CELLS_IN_CHESS_BOARD; j++){
                String newLoc = changeLoc(loc, dColumn[i]*j, dRow[i]*j);
                if (newLoc == null){
                    continue;
                }
                Piece pieceNewLoc = b.getPiece(newLoc);

                if(pieceNewLoc == null){
                    allMoves.add(newLoc);
                } else if (pieceNewLoc.color() != this.color()){
                    allMoves.add(newLoc);
                }

                if (pieceNewLoc != null && pieceNewLoc.color() == this.color()){
                    break;
                }
            }
        }
        return allMoves;
    }
}
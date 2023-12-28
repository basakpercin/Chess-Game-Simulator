import java.util.*;

public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private static HashSet<BoardListener> LISTENER_HASH_SET = new HashSet<>();

    private Board() {  }

    private static final Board myBoard = new Board();
    public static Board theBoard() {
        return myBoard;
    }

    // Returns piece at given loc or null if no such piece
    // exists
    public Piece getPiece(String loc) {

        if(!validLocation(loc)){
            throw new NoSuchElementException("Invalid Location");
        }

        int columnNumber = loc.charAt(0) - 'a'; //0-based
        int rowNumber = Integer.parseInt(loc.substring(1)) - 1; //1-based

        return pieces[rowNumber][columnNumber];
    }

    public void addPiece(Piece p, String loc) {
        int columnNumber = loc.charAt(0) - 'a'; //0-based
        int rowNumber = Integer.parseInt(loc.substring(1)) - 1; //0-based

        Piece pieceAtLoc = getPiece(loc);
        if (pieceAtLoc != null){
            throw new NoSuchElementException("Location Already Occupied");
        }

        pieces[rowNumber][columnNumber] = p;
    }

    public void movePiece(String from, String to) {

        if(!validLocation(from)){
            throw new NoSuchElementException("Invalid Location From");
        }
        Piece pieceAtFrom = getPiece(from);

        if(!validLocation(to)){
            throw new NoSuchElementException("Invalid Location To");
        }     //also checks if 'to' position is valid.

        int columnNumberFrom = from.charAt(0) - 'a'; //0-based
        int rowNumberFrom = Integer.parseInt(from.substring(1)) - 1; //0-based

        int columnNumberTo = to.charAt(0) - 'a'; //0-based
        int rowNumberTo = Integer.parseInt(to.substring(1)) - 1; //0-based

        if (pieceAtFrom == null){
            throw new NoSuchElementException("From Location is Empty");
        }

        List<String> possibleMovesTo = pieceAtFrom.moves(Board.theBoard(), from);
        if (possibleMovesTo.contains(to)){
            pieces[rowNumberFrom][columnNumberFrom] = null;
            pieces[rowNumberTo][columnNumberTo] = pieceAtFrom;
        } else {
            throw new NoSuchElementException("Move invalid");
        }
    }

    public boolean validLocation(String loc){
        if(loc.length() != 2){
            return false;
        }

        int columnLetter = loc.charAt(0); //letter based
        int rowNumber = Integer.parseInt(loc.substring(1)); //1-based


        if (!(('a' <= columnLetter) && (columnLetter <= 'h') && (1 <= rowNumber) && (rowNumber <= 8))){
            return false;
        }

        return true;
    }

    public void clear() {

        for(int i = 0; i < pieces.length; i++){
            for(int j = 0; j < pieces.length; j++){
                pieces[i][j] = null;
            }
        }
    }

    public void registerListener(BoardListener bl) {
        LISTENER_HASH_SET.add(bl);
    }
//hashset
    public void removeListener(BoardListener bl) {
	    LISTENER_HASH_SET.remove(bl);
    }

    public void removeAllListeners() {
        LISTENER_HASH_SET.clear();
    }

    public void iterate(BoardInternalIterator bi) {
        for(int i = 0; i < pieces.length; i++){
            for(int j = 0; j < pieces.length; j++){
                Piece currentPiece = pieces[i][j];
                int currentRow = i+1;
                char currentColumn = (char) (j + 'a');
                String loc = currentColumn + "" + currentRow;
                bi.visit(loc, currentPiece);
            }
        }
    }
}
import java.util.*;

abstract public class Piece {

    public static final int CELLS_IN_CHESS_BOARD = 8;
    private static final Map<String, PieceFactory> FACTORY_MAP = new HashMap<>();

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }


    public static void registerPiece(PieceFactory pf) {
        FACTORY_MAP.put(Character.toString(pf.symbol()), pf);
    }

    public static Piece createPiece(String name) {
        String givenSymbol = name.substring(1,2);
        String colorString = name.substring(0,1);
        
        Color color1;
        
        if(colorString.equals("b")){
            color1 = Color.BLACK;
        } else if(colorString.equals("w")) {
            color1 = Color.WHITE;
        } else {
            throw new NoSuchElementException("Unknown Color: " + colorString);
        }

        if (!FACTORY_MAP.containsKey(givenSymbol)){
            throw new NoSuchElementException("No such key/symbol: " + givenSymbol);
        }

        PieceFactory mappedValue = FACTORY_MAP.get(givenSymbol);

        return mappedValue.create(color1);
    }

    public Color color() {
	// You should write code here and just inherit it in
	// subclasses. For this to work, you should know
	// that subclasses can access superclass fields.
        return color;
    }

    public static String changeLoc(String loc, int dColumn, int dRow){
        int column = loc.charAt(0) - 'a'; // 0-based
        int row = Integer.parseInt(loc.substring(1)) - 1; // 0-based

        int newColumn = column + dColumn;
        int newRow = row + dRow;

        char columnLetter = (char) ('a' + newColumn); //back to letter
        int rowString = newRow + 1; // 1-based

        String returnString = columnLetter + "" + rowString;

        if ((0 <= newColumn) && (newColumn <= 7) && (0 <= newRow) && (newRow <= 7)){
            return returnString;
        }
        return null;
    }

    abstract public String toString();

    abstract public List<String> moves(Board b, String loc);
}
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Chess {
    public static void main(String[] args) throws FileNotFoundException {
	if (args.length != 2) {
	    System.out.println("Usage: java Chess layout moves");
		throw new NoSuchElementException();
	}
	Piece.registerPiece(new KingFactory());
	Piece.registerPiece(new QueenFactory());
	Piece.registerPiece(new KnightFactory());
	Piece.registerPiece(new BishopFactory());
	Piece.registerPiece(new RookFactory());
	Piece.registerPiece(new PawnFactory());
	Board.theBoard().registerListener(new Logger());
	// args[0] is the layout file name
	// args[1] is the moves file name
	// Put your code to read the layout file and moves files
	// here.
	Scanner layout = new Scanner(new FileReader(args[0]));
	Scanner moves = new Scanner(new FileReader(args[1]));


	while(layout.hasNextLine()){
		String line = layout.nextLine();

		if(line.startsWith("#") ){
			continue;
		}

		String newPieceString = line.substring(3);
		Piece newPiece = Piece.createPiece(newPieceString);

		String newLoc = line.substring(0,2);

		if(Board.theBoard().getPiece(newLoc) == null){
			Board.theBoard().addPiece(newPiece, newLoc);
		}
	}

	while(moves.hasNextLine()){
		String line = moves.nextLine();

		if(line.startsWith("#") ){
			continue;
		}

		String fromPosition = line.substring(0,2);
		String toPosition = line.substring(3);
		Board.theBoard().movePiece(fromPosition, toPosition);
	}

	// Leave the following code at the end of the simulation:
	System.out.println("Final board:");
	Board.theBoard().iterate(new BoardPrinter());
    }
}

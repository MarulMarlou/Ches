public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        int direction = getColor().equals("White") ? 1 : -1;

        if (toLine == line + direction && column == toColumn) {
            if (chessBoard.board[toLine][toColumn] == null) {
                return true;
            }
        }

        if (getColor().equals("White") && line == 6 && toLine == 4 && column == toColumn) {
            if (chessBoard.board[5][column] == null && chessBoard.board[4][column] == null) {
                return true;
            }
        }

        if (getColor().equals("Black") && line == 1 && toLine == 3 && column == toColumn) {
            if (chessBoard.board[2][column] == null && chessBoard.board[3][column] == null) {
                return true;
            }
        }

        if (Math.abs(toColumn - column) == 1 && toLine == line + direction) {
            if (chessBoard.board[toLine][toColumn] != null && !chessBoard.board[toLine][toColumn].getColor().equals(getColor())) {
                return true;
            }
        }

        return false;
    }


    @Override
    public String getSymbol() {
        return "P";
    }
}

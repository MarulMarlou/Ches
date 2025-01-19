public class Bishop extends ChessPiece {
    public Bishop(String color) {
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

        if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            int lineDirection = (toLine > line) ? 1 : -1;
            int columnDirection = (toColumn > column) ? 1 : -1;

            int currentLine = line + lineDirection;
            int currentColumn = column + columnDirection;

            while (currentLine != toLine && currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false; // На пути есть фигура
                }
                currentLine += lineDirection;
                currentColumn += columnDirection;
            }

            if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getColor().equals(getColor())) {
                return false;
            }

            return true;
        }

        return false;
    }


    @Override
    public String getSymbol() {
        return "B";
    }
}

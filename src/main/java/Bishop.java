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
        // Проверка выхода за доску
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Проверка того, что фигура не ходит в точку, в которой она сейчас находится
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка хода по диагонали
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

            // Проверка того, что на конечной позиции нет фигуры того же цвета
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

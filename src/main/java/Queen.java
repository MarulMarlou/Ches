public class Queen extends ChessPiece {
    public Queen(String color) {
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

        // Проверка хода по прямой или диагонали
        if (line == toLine || column == toColumn || Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            // Проверка того, что на пути нет других фигур
            if (line == toLine) { // Ход по горизонтали
                int minColumn = Math.min(column, toColumn);
                int maxColumn = Math.max(column, toColumn);
                for (int i = minColumn + 1; i < maxColumn; i++) {
                    if (chessBoard.board[line][i] != null) {
                        return false;
                    }
                }
            } else if (column == toColumn) { // Ход по вертикали
                int minLine = Math.min(line, toLine);
                int maxLine = Math.max(line, toLine);
                for (int i = minLine + 1; i < maxLine; i++) {
                    if (chessBoard.board[i][column] != null) {
                        return false;
                    }
                }
            } else { // Ход по диагонали
                int lineDirection = (toLine > line) ? 1 : -1;
                int columnDirection = (toColumn > column) ? 1 : -1;

                int currentLine = line + lineDirection;
                int currentColumn = column + columnDirection;

                while (currentLine != toLine) {
                    if (chessBoard.board[currentLine][currentColumn] != null) {
                        return false;
                    }
                    currentLine += lineDirection;
                    currentColumn += columnDirection;
                }
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
        return "Q";
    }
}

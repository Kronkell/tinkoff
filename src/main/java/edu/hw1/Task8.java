package edu.hw1;

public class Task8 {
    private static final int[][] VARIANTS = {
        {1, 2},
        {2, 1},
        {-1, 2},
        {2, -1},
        {1, -2},
        {-2, 1},
        {-1, -2},
        {-2, -1}};

    private Task8() {
    }

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] board) {
        if (board == null || board.length != 8 || board[0].length != 8) {
            throw new IllegalArgumentException("This is not a chess board!");
        }

        int[][] extendedBoard = new int[board.length + 4][];
        for (int i = 0; i < extendedBoard.length; ++i) {
            extendedBoard[i] = new int[board.length + 4];
            if (i >= 2 && i < board.length + 2) {
                System.arraycopy(board[i - 2], 0, extendedBoard[i], 2, board.length);
            }
        }

        boolean answer = true;
        for (int i = 2; i < board.length + 2; ++i) {
            for (int j = 2; j < board.length + 2; ++j) {
                if (extendedBoard[i][j] == 1) {
                    for (int[] variant : VARIANTS) {
                        if (extendedBoard[i + variant[0]][j + variant[1]] == 1) {
                            answer = false;
                            break;
                        }
                    }
                }
            }
        }

        return answer;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sudokusolver;
/**
 *
 * @author RIBU
 */
import java.util.*;
public class SudoKuSoLvEr {

    /**
     * @param args the command line arguments
     */
private static final int GRID_SIZE=9;
private static Scanner sc = new Scanner(System.in);    
    public static void main(String args[]) {
        
        int[][] board ={
            {7,0,2,0,5,0,6,0,0}, 
            {0,0,0,0,0,3,0,0,0},
            {1,0,0,0,0,9,5,0,0},
            {8,0,0,0,0,0,0,9,0},
            {0,4,3,0,0,0,7,5,0},
            {0,9,0,0,0,0,0,0,8},
            {0,0,9,7,0,0,0,0,5},
            {0,0,0,2,0,0,0,0,0},
            {0,0,7,0,4,0,2,0,3}
        };
        
        board = saveBoard(board);
        
        if(solveBoard(board))
            System.out.println("Solve Successful");
        else
            System.out.println("Unsolvable board");
        
        printBoard(board);
    }
    
    private static int[][] saveBoard(int[][] board) {
        
        System.out.println("Enter the values of the sudoku in row wise manner "
                + "and enter 0 for empty spaces");
        for(int row=0;row< GRID_SIZE;row++){
            for(int col=0;col<GRID_SIZE;col++){
                board[row][col]=sc.nextInt();
            }
        }
        return board;
    }
    
    private static boolean isNumberInRow(int[][] board, int number,int row) {
        
        for(int i=0;i<GRID_SIZE;i++)
            if(board[row][i]==number)
                return true;
        return false;
    }
    
    private static boolean isNumberInColumn(int[][] board, int number,int col) {
        
        for(int i=0;i<GRID_SIZE;i++)
            if(board[i][col]==number)
                return true;
        return false;
    }
    
    private static boolean isNumberInBox(int[][] board, int number, int row, int col) {
        
        int localrow = row - row % 3;
        int localcol = col - col % 3;
        
        for(int i = localrow; i < localrow + 3; i++)
            for(int j = localcol; j < localcol + 3; j++)
                if(board[i][j] == number)
                    return true;
        
        
        return false;
    }
    
    private static boolean isValidPlacement(int[][] board, int number, int row, int col) {
        
        return !isNumberInColumn(board, number, col)&&
                !isNumberInRow(board, number, row)&&
                !isNumberInBox(board, number, row, col);
    }
    
    public static boolean solveBoard(int[][] board) {
        
        for(int row = 0; row < GRID_SIZE; row++) {
            for(int col = 0; col < GRID_SIZE; col++) {
                if(board[row][col] == 0) {
                    for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if(isValidPlacement(board,numberToTry,row,col)) {
                            board[row][col] = numberToTry;
                            
                            if(solveBoard(board))
                                return true;
                            else
                                board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard(int[][] board) {
        
        for(int row = 0; row < GRID_SIZE; row ++) {
            if(row%3==0 && row!=0)
                System.out.println("------------");
            for(int col = 0; col < GRID_SIZE; col ++) {
                if(col%3==0 && col!=0)
                    System.out.print("|");
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }
}
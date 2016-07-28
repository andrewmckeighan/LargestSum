/**
 * @author Andrew McKeighan
 */
import java.util.Scanner;

public class Main {
	/**
	 *Given a triangle of positive integers, we want to find a largest possible sum along a path from top
	 *row to bottom row. A path can go down either directly south or directly south-east (Note, you can
     *NEVER go EAST, WEST, or NORTH etc). For example, in the triangle
     *
     *1
     *2 3
     *1 5 7
     *9 1 1 2
     *8 5 4 13 2
     *
     *the path 1→3→7→2→13 is a legal path with sum 26. This path is “best” because 26 is the largest
     *such sum. Generally, suppose that the triangle is stored in an array A[1..n ,1..n] with the relevant
     *entries being on the diagonal and below. For 1≤ ≤r n and 1≤ ≤c r let B[r,c] be the sum along the
     *best path that ends in row r and column c of the matrix A.
     *Example: B[1,1] = 1, B[2,1] = 3, B[2,2] = 4
	 */
    public static void main(String[] args) {

        int numRows = 0;
        String read = "";
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            numRows = Integer.parseInt(scan.next());
            //scan.nextLine();
            makeArray(numRows, scan);
            if(!scan.hasNext()){
                break;
            }
        }

	}
    /**
     * This method creates the triangle. It initializes everything as -1 to keep track of the empty spaces.
     * @param rows the length of the rows and columns of the array.
     * @param scan scans the input for the array
     */
    public static void makeArray(int rows, Scanner scan){
        int triangle[][] = new int[rows][rows];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < rows; j++){
                triangle[i][j] = -1;
            }
        }
        for(int i = 0; i < rows; i++){
            scan.nextLine();
            for(int j = 0; j < rows; j++){
                if(j <= i){
                    triangle[i][j] = scan.nextInt();
                }
            }
        }
        findBestPath(triangle, rows);
        
        
    }
    
    public static void findBestPath(int triangle[][], int size){
        int max = 0;
        int maxArr[] = new int[size];
        
        for(int i = size-2; i >= 0; i--){
            for(int j = 0; j <= i; j++){
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }
        System.out.println(triangle[0][0]);
    }
    
    
}

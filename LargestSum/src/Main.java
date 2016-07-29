/**
 * @author Andrew McKeighan
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
    
    public static ArrayList<String> FinalAnswer = new ArrayList<String>();
    
    
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
        
        while(true){
            numRows = Integer.parseInt(scan.next());
            //scan.nextLine();
            makeArray(numRows, scan);
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
        int temp[][] = new int[size][];
        for(int i = 0; i< size; i++){
            temp[i] = triangle[i].clone();
        }
        int maxTri = 0;
        Stack st = new Stack();
        Stack fin = new Stack();
        for(int i = size-2; i >= 0; i--){
            for(int j = 0; j <= i; j++){
                maxTri = Math.max(triangle[i+1][j], triangle[i+1][j+1]);
                if(maxTri == triangle[i+1][j]){
                    st.push(temp[i+1][j]);
                }else{
                    st.push(temp[i+1][j+1]);
                }
                triangle[i][j] += maxTri;
                
            }
            int large = 0;
            while(!st.isEmpty()){
                if(large < (int) st.peek()){
                    large = (int) st.pop();
                }else{
                    st.pop();
                }
            }
            fin.push(large);
            
        }
        fin.push(temp[0][0]);
        //System.out.println(triangle[0][0]);
        FinalAnswer.add("Max is " + triangle [0][0]);
        String sentence = "";
        while(!fin.isEmpty()){
            sentence += fin.pop()+"-->";
        }
        sentence = sentence.substring(0, sentence.length()-3);
        FinalAnswer.add(sentence);
    }
    
    
    
}

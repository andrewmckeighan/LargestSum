/**
 * @author Andrew McKeighan
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
    
    public static ArrayList<String> FinalAnswer = new ArrayList<String>();
    
    //Finds the best path for the largest sum in a pyramid of values.
    //It's important to note that if you're using eclipse, you need to press CTRL+Z when you have
    //finished inputing values.
    public static void main(String[] args) {

        int numRows = 0;
        String read = "";
        Scanner scan = new Scanner(System.in);
        
        while(scan.hasNext()){
            numRows = Integer.parseInt(scan.next());
            makeArray(numRows, scan);
        }
        scan.close();
        for(int i = 0; i < FinalAnswer.size(); i++){
        	System.out.println(FinalAnswer.get(i)); 
        }

	}
    /**
     * This creates the array of values for the triangle.
     * @param rows The width and height of the 2D array.
     * @param scan A Scanner(System.In) passed from the main method.
     */
    public static void makeArray(int rows, Scanner scan){
        int triangle[][] = new int[rows][rows];
        /**
         * Marks everything as -1 to keep track of the
         * unused parts outside the triangle
         */
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < rows; j++){
                triangle[i][j] = -1;
            }
        }
        /**
         * Fills in the triangle.
         */
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
    /**
     * Calculates the largest sum and the best path for the triangle.
     * @param triangle a 2D array that is used to store the values for the triangle.
     * @param size the length of the rows and columns.
     * Unused parts of the array have the value -1.
     */
    public static void findBestPath(int triangle[][], int size){
        int max = 0;
        int maxArr[] = new int[size];
        int temp[][] = new int[size][];
        for(int i = 0; i< size; i++){
            temp[i] = triangle[i].clone();
        }
        int maxTri = 0;
        Stack<Integer> st = new Stack<Integer>();
        Stack<Integer> fin = new Stack<Integer>();
        /**
         * calculates the max sum by working from the bottom up.
         */
        for(int i = size-2; i >= 0; i--){
            for(int j = 0; j <= i; j++){
                maxTri = Math.max(triangle[i+1][j], triangle[i+1][j+1]);
                triangle[i][j] += maxTri;
            }
           
        }
        int x = 0;
        int j = 0;
        fin.push(temp[0][0]);
        /**
         * A clever way to find the path by working backwards using the triangle array
         * that has been modified to include the max path with it.
         * It works by subtracting the two values in the original triangle array and compares
         * them with the values of the modified array. If they match, that is your path!
         */
        for(int i = 0; i < size-1; i++){
            if((triangle[i][j] - temp[i][j] == triangle[i+1][j+1])){
                j++;
            }
            fin.push(temp[i+1][j]);
        }
        FinalAnswer.add("Max is " + triangle [0][0]);
        String sentence = "";
        
        /**
         * This was needed to flip the stack so output would be correct.
         * If I had more time, I would make it a queue instead.
         */
        while(!fin.isEmpty()){
        	st.push(fin.pop());
        }
        while(!st.isEmpty()){
            sentence += st.pop()+"-->";
        }
        sentence = sentence.substring(0, sentence.length()-3);
        FinalAnswer.add(sentence);
    }
    
    
    
}

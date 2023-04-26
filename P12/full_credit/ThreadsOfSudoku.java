import java.util.HashSet;
import java.util.Scanner;
import java.io.File;

public class ThreadsOfSudoku {
    public static void main(String[] args) {
        
        try {
            if(args.length < 2) {
                System.err.println("usage: java ThreadsOfSudoku threads puzzleFilename [puzzleName]");
                System.exit(-1);
            }
            
            Scanner in = new Scanner(new File(args[1]));
            String name = (args.length > 1) ? args[2] : null;
            Sudoku sud = new Sudoku(in, name);
            System.out.println(sud + "\n\n");
            
            int numThreads = Integer.parseInt(args[0]);
            
            int index = 0;
            for(int y=1; y<=9; ++y) {
                for(int x=1; x<=9; ++x) {
                    suds[index++] = new Sudoku(sud, new int[]{x, y});
                }
            }
            
            Thread[] threads = new Thread[numThreads];
            int puzzlesPerThread = suds.length / numThreads;
            int startIndex = 0;
            int endIndex = puzzlesPerThread - 1;
            for (int i = 0; i < numThreads; i++) {
                final int start = startIndex;
                final int end = endIndex;
                final int id = i;
                threads[i] = new Thread(() -> solveSuds(start, end, id));
                threads[i].start();
                startIndex = endIndex + 1;
                endIndex += puzzlesPerThread;
            }
            
            for (Thread thread : threads) {
                thread.join();
            }
                        
            System.out.println("Found " + solutions.size() + " solutions");
            for(var s : solutions) System.out.println(s);
        } catch(Exception e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }
    
    private static void solveSuds(int first, int last, int id) {
        System.out.println("Thread " + id + " will solve " + first + " to " + last);
        for(int i=first; i<=last; ++i) {
            System.out.println("Thread " + id + " solving " + i);
            if(suds[i].solve()) synchronized (solutions) {
                solutions.add(suds[i]);
                try {
                    Thread.sleep(30000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private static Sudoku[] suds = new Sudoku[81];
    private static HashSet<Sudoku> solutions = new HashSet<>();
}

import java.io.*;

public class EC
{
    public static void main(String[] args){
        try{
            FileWriter fw = new FileWriter("output.csv");
            fw.write("# runs, Condensation\n");
            SizedCollisionSpace s = new SizedCollisionSpace(50, 200);
            
            System.out.println("Initial State");
            s.printGrid();
            
            int condensation = s.getCondensation();
            System.out.println("Condensation: " + condensation);
                
            //Running simulation 50 times
            for(int i = 0; i < 50; i++){
                //Running and printing state to console
                s.run();
                System.out.println("After " + (i + 1) + " runs");
                s.printGrid();
                
                //Writing to csv file
                condensation = s.getCondensation();
                fw.write((i + 1) + ", " + condensation + "\n");
            }
            
            fw.close();
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
        
    }
}

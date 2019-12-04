import java.util.*;
    
public class CollisionSpace{
    ArrayList<Particle>[][] grid = new ArrayList[50][50];
        
    CollisionSpace(){
        initializeGrid();
        initializeParticles();
    }
        
    public void run(){
        ArrayList<Particle>[][] newGrid = new ArrayList[50][50];
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                newGrid[i][j] = new ArrayList<Particle>();
            }
        }
            
        //Handling change of direction
        for(int i = 0; i < 50; i++){
            for(int j = 1; j < 50; j++){
                if(this.grid[i][j].size() == 2){
                    //If multiple particles exist in the same space
                    this.grid[i][j].get(0).collide(this.grid[i][j].get(1));
                }else if(this.grid[i][j].size() == 3){
                    //If multiple particles exist in the same space
                    this.grid[i][j].get(0).collide(this.grid[i][j].get(1), this.grid[i][j].get(2));
                }
                for(Particle p : this.grid[i][j]){
                    int d = p.direction;
                    if((d == 0 && j == 49) || (d == 1 && i == 49) || (d == 3 && i == 0)){
                        p.direction = (p.direction + 2) % 4;
                    }
                }
            }
        }
           
        //Handling movement
        /**
        * 0 = North
        * 1 = East
        * 2 = South
        * 3 = West
        */
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                for(Particle p : this.grid[i][j]){
                    if(j == 0){
                        newGrid[i][j].add(p);
                        continue;
                    }
                    int d = p.direction;
                    if(d == 0){
                        newGrid[i][j+1].add(p);
                    }else if(d == 1){
                        newGrid[i+1][j].add(p);
                    }else if(d == 2){
                        newGrid[i][j-1].add(p);
                    }else if(d == 3){
                        newGrid[i-1][j].add(p);
                    }
                }
            }
        }
        this.grid = newGrid;
    }
    
        public void initializeParticles(){
        Random r = new Random();
        for(int i = 0; i < 50; i++){
              
            int x = r.nextInt(50);
            int y = r.nextInt(50);
                
            int direction = r.nextInt(4);
            
            this.grid[x][y].add(new Particle(direction));
        }
    }
    
    public void initializeGrid(){
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                this.grid[i][j] = new ArrayList<Particle>();
            }
        }
    }
    
    public void printGrid(){
        //Printing starting line
        for(int i = 0; i < 50; i++){
            System.out.print("--");
        }
        System.out.println("");
        
        //Printing grid
        for(int i = 49; i >= 0; i--){
            System.out.print('|');
            for(int j = 49; j >= 0; j--){
                if(this.grid[j][i].size() > 0){
                    System.out.print(this.grid[j][i].size());
                }else{
                    System.out.print("  ");
                }
            }
            System.out.print('|');
            System.out.println("");
        }
        
        printCondensation();
        //Printing ending line
        for(int i = 0; i < 50; i++){
            System.out.print("--");
        }
        System.out.println("");
    }
    
    public void printCondensation(){
        int count = 0;
        for(int i = 0; i < 50; i++){
            count += this.grid[i][0].size();
        }
        System.out.println("Condenstaion: " + count);
    }
}

import java.util.*;

public class Particle
{
    /**
     * 0 = North
     * 1 = East
     * 2 = South
     * 3 = West
     */
    int direction;
    
    Particle(int d){
        direction = d;
    }
    
    public void collide(Particle p){
        Random rand = new Random();
        int newDirection = rand.nextInt(4);
        this.direction = newDirection;
        p.direction = (newDirection + 2) % 4; //Reverses the direction
    }
    
    public void collide(Particle p1, Particle p2){
        Random rand = new Random();
        int newDirection = rand.nextInt(4);
        this.direction = newDirection;
        p1.direction = (newDirection + 2) % 4; //Reverses the direction
        p2.direction = newDirection + 1; //Chooses an adjacent direction
    }
    
    public void changeDirection(){
        this.direction = (this.direction + 2) % 4;
    }
}

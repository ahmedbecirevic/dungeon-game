package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dungeon {

    private int length,
                height,
                vampires, 
                moves;
    
    private boolean vampireMove;

    private List<Vampires> vampiresPositions;
    private Random random;
    private Player player;
    private Scanner reader;

    public Dungeon ( int length, int height, int vampires, int moves, boolean vampireMove ) {

        this.length = length;
        this.height = height;
        this.vampires = vampires;
        this.moves = moves;
        this.vampireMove = vampireMove;

        vampiresPositions = new ArrayList<Vampires>();
        random = new Random();
        player = new Player();

        for ( int i = 1; i <= this.vampires; i++ ) {
            
            vampiresPositions.add( new Vampires( createVampirePositionX(), createVampirePositionY() ) );

        }

    }



    public void printLayout () {

        System.out.println( moves );
        System.out.println( "   " );
        System.out.println( "@ " + player.getX() + " " + player.getY() );
        

        for ( Vampires v : vampiresPositions ) {

            System.out.println( "V " + v.getX() + " " + v.getY() );
            
        }

        System.out.println( "   " );

        for ( int i = 0; i < height; i++ ) {

            for ( int j = 0; j < length; j++ ) {

                if ( !checkPosVampire( j, i ) ) {

                    System.out.print( "V" );

                } else if ( player.getX() == j && player.getY() == i ) {

                    System.out.print( "@" );

                } else {

                    System.out.print( "." );

                }
            }
        System.out.println( "" );
        } 
        System.out.println( "   " );
    }

    public void run () {

     

        reader = new Scanner ( System.in );

        while ( true ) {

            printLayout();
            String input = reader.nextLine();
            inputMovement(input);

            if ( win() ) {
                break;
            }
            if ( lose() ) {
                break;
            }
        }

    }

    public void inputMovement ( String input ) {
        
        moves--;
        inputMessageReaction( input );
        

    }

    public void vampiresNewOrientation () {
        if ( vampireMove ) {

            for ( Vampires v : vampiresPositions ) {

                int rand = random.nextInt(4);

                if ( rand == 3 && v.getX() < length - 1 && checkPosVampire( v.getX() + 1, v.getY()) ) {
                    v.rightX();

                } else if ( rand == 2 && v.getY() > 0 && checkPosVampire( v.getX(), v.getY() - 1 ) ) {
                    v.upY();

                } else if ( rand == 1 && v.getX() > 0 && checkPosVampire( v.getX() - 1, v.getY()) ) {
                    v.leftX();

                } else if ( rand == 0 && v.getY() < height - 1 && checkPosVampire( v.getX(), v.getY() + 1 ) ) {
                    v.downY();

                }
            }
            kill();
        }
    }

    public void kill () {

        ArrayList<Vampires> remove = new ArrayList<Vampires>();
        
        for ( Vampires v : vampiresPositions ) {
            if ( v.getX() == player.getX() && v.getY() == player.getY() ) {

                remove.add(v);

            }
        }
        vampiresPositions.removeAll(remove);
    }


    public void inputMessageReaction ( String input ) {

        for ( int i = 0; i < input.length(); i++ ) {

            String letter = Character.toString( input.charAt( i ) );

            if ( letter.equals( "d" ) && player.getX() < length - 1 ) {

                player.rightX();
                kill();
                vampiresNewOrientation();
            
            } else if ( letter.equals( "a" ) && player.getX() > 0 ) {

                player.leftX();
                kill();
                vampiresNewOrientation();
                
            } else if ( letter.equals( "w" ) && player.getY() > 0 ) {

                player.upY();
                kill();
                vampiresNewOrientation();
            
            } else if ( letter.equals( "s" ) && player.getY() < height - 1 ) {

                player.downY();
                kill();
                vampiresNewOrientation();

            }
            

        }


    }



    public int createVampirePositionX () {

        int coordinate = random.nextInt( length - 1 ) + 1;
        return coordinate;

    }

    public int createVampirePositionY () {

        int coordinate = random.nextInt( height - 1 ) + 1;
        return coordinate;

    }


    public boolean checkPosVampire ( int x, int y ) {

        for ( Vampires v : vampiresPositions ) {

            if ( v.getX() == x && v.getY() == y ) {

                return false;

            }  
        }
        return true;
    }

   /* public void fromScratch () {

        player.resetValues();
        
        for ( Vampires v : vampiresPositions ) {
            v.resetValues();     
        }
    }*/

    public boolean win () {

        if ( vampiresPositions.isEmpty() ) {

            System.out.println("YOU WON!!!!!!!!");
            return true;

        }
        return false;

    }

    public boolean lose() {

        if ( moves == 0 ) {

                System.out.println("YOU LOST!!!!!!!!");
                return true;

        }

        return false;
    }

    














   /* public int getHeight () {
        return height;
    }
    public int getLength () {
        return length;
    }

    public void setLength ( int length ) {
        this.length = length;
    }
    public void setHeight ( int height ) {
        this.height = height;
    }

    public int getVampires () {
        return vampires;
    }
    */
}

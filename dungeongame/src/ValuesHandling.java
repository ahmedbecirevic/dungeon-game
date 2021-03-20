package src;

public abstract class ValuesHandling {
    int y;
    int x;

    public ValuesHandling (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ValuesHandling () {
        x = 0;
        y = 0;
    }

    
    public int getX () {
        return this.x;
    }

    public int getY () {
        return this.y;
    }


    public void rightX () {
        x++;
    }
    public void downY () {
        y++;
    }

    public void leftX () {
        x--;
    }
    public void upY () {
        y--;
    }
}

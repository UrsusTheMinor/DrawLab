import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Point implements Serializable {

    private int x,y;
    private boolean isSelected;
    public static final int SIZE = 50;
    public static Color borderColor = new Color(0,0,0);
    public static Color backColor = new Color(236,236,236);

    public Point(int x, int y) {
        set(x,y);
        isSelected = false;
    }

    public Point(MouseEvent e) {
        set(e.getX(), e.getY());
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void toggleSelected() {
        isSelected = !isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void move(int dx, int dy) {
        set(getX() + dx, getY() + dy);
    }

    public boolean inPoint(int x, int y) {
        return  diff(x, y) <= SIZE/2;
    }

    public boolean nearPoint(int x, int y) {
        return  diff(x, y) <= SIZE;
    }

    public int diff(int x, int y) {
        int dx = getX() - x;
        int dy = getY() - y;
        return (int)Math.round(Math.sqrt(dx*dx + dy*dy));
    }

    public void paint(Graphics g) {

        if(isSelected) {
            g.setColor(Color.BLACK);
            g.drawRect(getX() - SIZE / 2, getY() - SIZE /2, SIZE, SIZE);
        }

        g.setColor(backColor);
        g.drawOval(getX() - SIZE / 2, getY() - SIZE /2, SIZE, SIZE );
        g.setColor(Color.BLACK);
        g.fillOval(getX() - SIZE / 2, getY() - SIZE /2, SIZE, SIZE );
    }

    public void move(MouseEvent lastE, MouseEvent currentE) {
        moveX(lastE, currentE);
        moveY(lastE, currentE);
    }

    public void moveX(MouseEvent lastE, MouseEvent currentE) {
        setX(getX() + currentE.getX() - lastE.getX());
    }

    public void moveY(MouseEvent lastE, MouseEvent currentE) {
        setY(getY() + currentE.getY() - lastE.getY());
    }

    public boolean inPoint(MouseEvent e) {
        return inPoint(e.getX(), e.getY());
    }

    public double getDistance(MouseEvent e) {
        return Math.sqrt(
                Math.pow(getX() - e.getX(), 2)
                +
                Math.pow(getY() - e.getY(), 2)
        );
    }

    public void set(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBorderColor(Color c) {
        borderColor = c;
    }
}

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Figure implements Serializable {

    protected ArrayList<Point> points;

    public static final int SIZE = 10;

    public static final int CIRCLE = 0;
    public static final int RECTANGLE = 1;
    public static final int LINE = 2;

    public Figure(){
        points = new ArrayList<>();
    }

    public Figure(MouseEvent e){
        super();
    }
    public abstract void move(MouseEvent lastE, MouseEvent currentE) ;

    public abstract void paint(Graphics g);

    public void toggleSelectPoints() {
        for (Point p : points) {
            p.toggleSelected();
        }
    }

    public void setSelectPoints(boolean selected) {
        for (Point p : points) {
            p.setSelected(selected);
        }
    }

//    public void move(int dx, int dy) {
//        for (Point p : points) {
//            p.move(dx, dy);
//        }
//    }

    public Point inPoint(int x, int y) {
        for (Point p : points)
            if (p.inPoint(x, y))
                return p;
        return null;
    }

    public Point inPoint(MouseEvent e) {
        for (Point p : points)
            if (p.inPoint(e.getX(), e.getY()))
                return p;
        return null;
    }

    public void paintPoints(Graphics g) {
        for (Point p : points) {
            p.paint(g);
        }
    }

    public abstract boolean inFigure(MouseEvent e);

    public void set(Point p) {
        this.points.add((new Point(p.getX(), p.getY())));
    }


}

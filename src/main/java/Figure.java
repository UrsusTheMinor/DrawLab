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
    public static final int POINT = 3;


    private boolean isSelected = false;

    public Color borderColor = new Color(0,0,0);
    public Color fillColor = null;

    public Figure(){
        points = new ArrayList<>();
    }

    public Figure(MouseEvent e){
        this();
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

    public Point containsPoint(int x, int y) {
        for (Point p : points)
            if (p.inPoint(x, y))
                return p;
        return null;
    }

    public boolean containsPoint(MouseEvent e) {
        for (Point p : points)
            if (p.inPoint(e))
                return true;
        return false;
    }

    public void paintPoints(Graphics g) {
        for (Point p : points) {
            p.paint(g);
        }
    }

    public abstract boolean inFigure(MouseEvent e);

    protected void add(Point p) {
        this.points.add(p);
    }


    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        for(Point p : points){
            p.setSelected(selected);
        }
    }

    public Point inPoint(MouseEvent e) {
        for (Point p : points)
            if (p.inPoint(e))
                return p;
        return null;
    }
}

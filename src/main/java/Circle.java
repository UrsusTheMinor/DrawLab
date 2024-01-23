import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Circle extends Figure implements Serializable {

    private Point center, radius;

    public Circle(MouseEvent e){
        super(e);
        center = new Point(e);
        radius = new Point(e);
        points.add(center);
        points.add(radius);
    }

    public void move(MouseEvent last_e, MouseEvent current_e){
        if(radius.inPoint(last_e)){
            radius.move(last_e, current_e);
        } else if(center.inPoint(last_e)){
            center.move(last_e, current_e);
        } if(inFigure(last_e)) {
            points.get(0).move(last_e, current_e);
            points.get(1).move(last_e, current_e);
        }
    }

    public void paint(Graphics g) {
        int r = getRadius();

        g.drawOval(center.getX() - r, center.getY() - r, 2 * r, 2 * r);
        center.paint(g);
        radius.paint(g);
    }

    public int getRadius() {
        int dx = center.getX() - center.getY();
        int dy = radius.getX() - radius.getY();

        return (int) Math.round(Math.sqrt(dx * dx + dy * dy));
    }

    public boolean inFigure(MouseEvent e){
        return inPoint(e) != null || center.getDistance(e) < getRadius();
    }

}

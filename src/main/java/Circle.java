import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Circle extends Figure implements Serializable {

    private Point center, radius;

    public Circle(MouseEvent e){
        super(e);
        center = new Point(e);
        radius = new Point(e);
        add(center);
        add(radius);
    }

    public void move(MouseEvent last_e, MouseEvent current_e){
        if(radius.inPoint(last_e)){
            radius.move(last_e, current_e);
        } else if(center.inPoint(last_e)){
            center.move(last_e, current_e);
        } else if(inFigure(last_e)) {
            center.move(last_e, current_e);
            radius.move(last_e, current_e);
        }
    }

    public void paint(Graphics g) {
        int r = getRadius();


        if(fillColor != null) {
            g.setColor(fillColor);
            g.fillOval(center.getX() - r, center.getY() - r, 2 * r, 2 * r);
        }

        g.setColor(borderColor);
        g.drawOval(center.getX() - r, center.getY() - r, 2 * r, 2 * r);
        center.paint(g);
        radius.paint(g);
    }

    public boolean inFigure(MouseEvent e){
        return containsPoint(e) || center.getDistance(e) < getRadius();
    }

    public int getRadius() {
        int dx = center.getX() - radius.getX();
        int dy = center.getY() - radius.getY();

        return (int) Math.round(Math.sqrt(dx * dx + dy * dy));
    }
}

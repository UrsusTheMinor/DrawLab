import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Line extends Figure implements Serializable {

    Point start, center, end;

    Line(MouseEvent e){
        super();
        start = new Point(e);
        center = new Point(e);
        end = new Point(e);
        points.add(start);
        points.add(center);
        points.add(end);
    }

    @Override
    public void move(MouseEvent lastE, MouseEvent currentE) {
        if(end.inPoint(lastE)){
            end.move(lastE, currentE);
            calculateCenter();
        } else if (start.inPoint(lastE)) {
            start.move(lastE, currentE);
            calculateCenter();
        } else if (center.inPoint(lastE)){
            end.move(lastE, currentE);
            start.move(lastE, currentE);
            center.move(lastE, currentE);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(borderColor);
        g.drawLine(
                start.getX(),
                start.getY(),
                end.getX(),
                end.getY()
        );
        for(Point p : points){
            p.paint(g);
        }
    }

    private void calculateCenter(){
        center.set(
                (start.getX() + end.getX())/2,
                (start.getY() + end.getY())/2
        );
    }


    @Override
    public boolean inFigure(MouseEvent e){
        return containsPoint(e);
    }
}

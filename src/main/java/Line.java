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
        } else if (start.inPoint(lastE)) {
            start.move(lastE, currentE);
        } else if (center.inPoint(lastE)){
            center.move(La);
        }
    }
}

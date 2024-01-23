import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class FigurePoint extends Figure implements Serializable {

    public FigurePoint() {
        points = new ArrayList<Point>();
    }

    public FigurePoint(MouseEvent e) {
        points.add(new Point(e));
    }

    @Override
    public void move(MouseEvent lastE, MouseEvent currentE) {
        points.get(0).move(lastE, currentE);
    }

    @Override
    public void paint(Graphics g) {
        points.get(0).paint(g);
    }

    @Override
    public boolean inFigure(MouseEvent e) {
        return points.get(0).inPoint(e.getX(), e.getY());
    }
}

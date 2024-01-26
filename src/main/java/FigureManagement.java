import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class FigureManagement implements Serializable {

    ArrayList<Figure> figures, selected_figures;

    FigureManagement() {
        figures = new ArrayList<>();
        selected_figures = new ArrayList<>();
    }

    public Figure getFigure(MouseEvent e) {
        for (Figure f : figures)
            if (f.inFigure(e))
                return f;
        return null;
    }

    public Point getPoint(MouseEvent e) {
        for (Figure f : figures)
            if (f.inPoint(e) != null)
                return f.inPoint(e);
        return null;
    }

    public void movePoint(Point p, MouseEvent last_e, MouseEvent current_e) {
        for (Figure f : figures)
            if (f.inPoint(last_e) != null)
                f.move(last_e, current_e);
    }

    public void moveSelected(MouseEvent last_e, MouseEvent current_e) {
        for (Figure f : selected_figures) {
            f.move(last_e, current_e);
        }
    }

    public void moveAll(MouseEvent last_e, MouseEvent current_e) {
        for (Figure f : figures) {
            f.move(last_e, current_e);
        }
    }

    public void paint(Graphics g) {
        for (Figure f : figures) {
            f.paint(g);
        }
    }

    public void clear() {
        figures.clear();
        clearSelected();
    }

    public void clearSelected() {
        for (Figure f : selected_figures) {
            f.setSelected(false);
        }
        selected_figures.clear();
    }

    public void selectFigures(MouseEvent e) {
        for (Figure f : figures) {
            if (f.inFigure(e)) {
                selected_figures.add(f);
            }
        }
    }

    public void addFigure(Figure f) {
        figures.add(f);
    }

    public void setBorderColor(Color color) {
        if(!selected_figures.isEmpty()) {
            for(Figure f : selected_figures)
                f.setBorderColor(color);
            return;
        } else {
            for (Figure f : figures) {
                f.setBorderColor(color);
            }
        }
    }

    public void setFillColor(Color color) {
        if(!selected_figures.isEmpty()) {
            for(Figure f : selected_figures)
                f.setFillColor(color);
        } else {
            for (Figure f : figures) {
                f.setFillColor(color);
            }
        }

    }

    public void addSelected(Figure f) {
        selected_figures.add(f);
        f.setSelected(true);
    }

    public void toggleSelected(Figure f) {
        if(selected_figures.contains(f)) {
            selected_figures.remove(f);
            f.setSelected(false);
        } else {
            selected_figures.add(f);
            f.setSelected(true);
        }
    }


}

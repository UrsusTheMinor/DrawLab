import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FigureManagement {
    ArrayList<Figure> figures, selected_figures;

    FigureManagement(){
        figures = new ArrayList<>();
        selected_figures = new ArrayList<>();
    }

    Figure getFigure(MouseEvent e){
        for (Figure f : figures){
            if(f.inFigure(e)){
                return f;
            }
        }
        return null;
    }

    void moveSelected(MouseEvent last_e, MouseEvent current_e){
        for (Figure f : selected_figures) {
            f.move(last_e, current_e);
        }
    }

    void moveAll(MouseEvent last_e, MouseEvent current_e){
        for (Figure f : figures) {
            f.move(last_e, current_e);
        }
    }

    void paint(Graphics g){
        for (Figure f : figures) {
            f.paint(g);
        }
    }

    void clear(){
        figures.clear();
        clearSelected();
    }

    void clearSelected(){
        selected_figures.clear();
    }

    void selectFigures(MouseEvent e) {
        for (Figure f : figures) {
            if(f.inFigure(e)) {
                selected_figures.add(f);
            }
        }
    }

    void addFigure(Figure f){
        figures.add(f);
    }
}

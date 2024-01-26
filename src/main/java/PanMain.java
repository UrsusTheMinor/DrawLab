import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class PanMain extends JPanel {

    private FigureManagement figures;
    private Figure current_figure;
    private int figure_type;
    MouseEvent last_e;

    public PanMain() {
        figures = new FigureManagement();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(e.isControlDown())
                    return;
                last_e = e;
                current_figure = figures.getFigure(e);
                if(current_figure == null){
                    switch (figure_type){
                        case Figure.CIRCLE:
                            current_figure = new Circle(e);
                            break;
                        case Figure.RECTANGLE:
                            current_figure = new Rectangle(e);
                            break;
                        case Figure.LINE:
                            current_figure = new Line(e);
                            break;
                        case Figure.POINT:
                            current_figure = new FigurePoint(e);
                            break;
                        default:
                            throw new RuntimeException("Figure not found");
                    }
                    figures.addFigure(current_figure);
                }
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!e.isControlDown())
                    return;
                current_figure = figures.getFigure(e);
                if(current_figure != null) {
                    figures.toggleSelected(current_figure);
                } else {
                    figures.clearSelected();
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                current_figure.move(last_e, e);
                last_e = e;
                repaint();
            }
        });


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (figures != null)
            figures.paint(g);
    }

    public void doMenuNew() {
        figures = new FigureManagement();
        repaint();
    }

    public void miOpen(String filename) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            figures = (FigureManagement) ois.readObject();
            repaint();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        repaint();
    }

    public void miSave(String filename, int type) {
        if (type == 0 && !filename.endsWith(".bin"))
            filename += ".bin";
        else if (type == 1 && !filename.endsWith(".txt"))
            filename += ".txt";

        /*
         heheheha I love ternary operator
         above could be:
         filename += (type == 0 && !filename.endsWith(".bin")) ? ".bin" :
                     (type == 1 && !filename.endsWith(".txt")) ? ".txt" : "";
        */

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(figures);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        repaint();
    }

    public void setFigureType(int figure_type) {
        this.figure_type = figure_type;
    }

    public void setBorderColor(Color color){
        if(current_figure != null){
            figures.setBorderColor(color);
            repaint();
        }
    }

    public void setFillColor(Color color){
        if(current_figure != null){
            figures.setFillColor(color);
            repaint();
        }
    }
}

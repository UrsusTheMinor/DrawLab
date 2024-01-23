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
                        default:
                            throw new RuntimeException("Figure not found");
                    }
                    figures.addFigure(current_figure);
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                current_figure.move(last_e, e);
                last_e = e;
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

    public void miSave(String filename) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(figures);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        repaint();
    }
}
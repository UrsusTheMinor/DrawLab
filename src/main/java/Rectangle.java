import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Rectangle extends  Figure implements Serializable {

    private Point tl, tr, bl, br;

    public Rectangle(MouseEvent e) {
        super(e);
        tl = new Point(e);
        tr = new Point(e);
        bl = new Point(e);
        br = new Point(e);
        add(tl);
        add(tr);
        add(bl);
        add(br);
    }

    public void paint(Graphics g) {

    }

    public boolean inFigure(MouseEvent e){
        return inPoint(e) != null;
    }

    public void move(MouseEvent last_e, MouseEvent current_e) {
        if (tl.inPoint(last_e)){
            tl.move(last_e, current_e);
            bl.moveX(last_e, current_e);
            tl.moveY(last_e, current_e);
        }
        else if (tr.inPoint(last_e)){
            tr.move(last_e, current_e);
            br.moveX(last_e, current_e);
            tl.moveY(last_e, current_e);
        }
        else if (bl.inPoint(last_e)){
            bl.move(last_e, current_e);
            tl.moveX(last_e, current_e);
            br.moveY(last_e, current_e);
        }
        else if (br.inPoint(last_e)){
            br.move(last_e, current_e);
            tr.moveX(last_e, current_e);
            bl.moveY(last_e, current_e);
        }
        else if (inFigure(last_e)){
            tl.move(last_e, current_e);
            tr.move(last_e, current_e);
            bl.move(last_e, current_e);
            br.move(last_e, current_e);
        }
    }
}

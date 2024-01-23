import javax.swing.*;
import java.awt.*;

public class ColorDialog extends JDialog {

    private PanColorPicker panColorPicker = new PanColorPicker();

    public ColorDialog(JFrame parent) {
        super(parent, "Color", false);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setContentPane(panColorPicker);
    }

    public void toggleVisibility() {
        setVisible(!isVisible());
    }
}

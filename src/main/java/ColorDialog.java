import javax.swing.*;
import java.awt.*;

public class ColorDialog extends JDialog {

    private PanColorPicker panColorPicker = new PanColorPicker();

    public ColorDialog(JFrame parent) {
        super(parent, "Color", false);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.MODELESS);


        setContentPane(panColorPicker);
    }

    public void toggleVisibility() {
        setVisible(!isVisible());
    }

    public static void main(String[] args) {
        ColorDialog colorDialog = new ColorDialog(null);
        colorDialog.toggleVisibility();
    }
}

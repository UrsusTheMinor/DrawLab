import javax.swing.*;
import java.awt.*;

public class ColorDialog extends JDialog {

    private PanColorPicker panColorPicker = new PanColorPicker();

    public ColorDialog(JFrame parent) {
        super(parent, "Color", false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.MODELESS);

        setContentPane(panColorPicker);
        setSize(1154, 270);
        System.out.println(getSize());
        setResizable(false);
    }

    public void toggleVisibility() {
        setVisible(!isVisible());
    }

    public static void main(String[] args) {
        ColorDialog colorDialog = new ColorDialog(null);
        colorDialog.toggleVisibility();
    }
}

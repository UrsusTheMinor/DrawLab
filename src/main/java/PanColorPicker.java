import javax.swing.*;

public class PanColorPicker extends JPanel {

    public PanColorPicker() {
        JColorChooser colorChooser = new JColorChooser();

        colorChooser.setPreviewPanel(this);
    }
}

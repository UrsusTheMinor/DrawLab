import javax.swing.*;

public class PanColorPicker extends JPanel {

    public PanColorPicker() {

        // display JColorChooser in Panel
        JColorChooser colorChooserBorder = new JColorChooser();
        add(colorChooserBorder);
        JColorChooser colorChooserFill = new JColorChooser();
        add(colorChooserFill);

        colorChooserBorder.getSelectionModel().addChangeListener(e -> {
            FrmMain.setBorderColor(colorChooserBorder.getColor());
        });

        colorChooserFill.getSelectionModel().addChangeListener(e -> {
            FrmMain.setFillColor(colorChooserFill.getColor());
        });

        repaint();
    }
}

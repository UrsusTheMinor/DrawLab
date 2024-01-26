import javax.swing.*;
import java.awt.*;

public class PanColorPicker extends JPanel {

    public static final int RGB = 3;

    public PanColorPicker() {

        setLayout(new GridBagLayout());
        var constraints = new GridBagConstraints();

        //Label for Border
        JLabel borderLabel = new JLabel("Border", SwingConstants.CENTER);

        constraints.gridx = 0;
        constraints.gridy = 0;

        add(borderLabel, constraints);

        //Label for Fill
        JLabel fillLabel = new JLabel("Fill", SwingConstants.CENTER);

        constraints.gridx = 1;
        constraints.gridy = 0;

        add(fillLabel, constraints);

        //Color Chooser for Border
        JColorChooser colorChooserBorder = new JColorChooser();
        JComponent hsvPanelBorder = getDesiredColorPanel(colorChooserBorder, RGB);

        constraints.gridx = 0;
        constraints.gridy = 1;

        add(hsvPanelBorder, constraints);

        //Color Chooser for Fill
        JColorChooser colorChooserFill = new JColorChooser();
        JComponent hsvPanelFill = getDesiredColorPanel(colorChooserFill, RGB);

        constraints.gridx = 1;
        constraints.gridy = 1;

        add(hsvPanelFill, constraints);

        colorChooserBorder.getSelectionModel().addChangeListener(e -> {
            FrmMain.setBorderColor(colorChooserBorder.getColor());
        });

        colorChooserFill.getSelectionModel().addChangeListener(e -> {
            FrmMain.setFillColor(colorChooserFill.getColor());
        });

        repaint();
    }


    private JComponent getDesiredColorPanel(JColorChooser colorChooser, int type) {
        return colorChooser.getChooserPanels()[type];
    }

}

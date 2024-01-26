import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panTest extends JPanel {

    private JColorChooser colorChooser;
    private JTextField rgbTextField;

    public panTest() {
        initUI();
    }

    private void initUI() {
        // Create a color chooser with HSV panel
        colorChooser = new JColorChooser();
        colorChooser.setPreviewPanel(new JPanel()); // Disable the default preview panel

        // Create a custom panel for HSV representation
        JComponent hsvPanel = createHSVPanel();

        // Create a text field for entering RGB code
        rgbTextField = new JTextField(8);
        rgbTextField.setToolTipText("Enter RGB code (e.g., 255,0,0)");

        // Add a listener to update color when RGB code is entered
        rgbTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String rgbCode = rgbTextField.getText();
                    String[] rgbValues = rgbCode.split(",");
                    int red = Integer.parseInt(rgbValues[0]);
                    int green = Integer.parseInt(rgbValues[1]);
                    int blue = Integer.parseInt(rgbValues[2]);

                    Color newColor = new Color(red, green, blue);
                    colorChooser.setColor(newColor);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panTest.this, "Invalid RGB format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Layout components
        setLayout(new BorderLayout());
        add(hsvPanel, BorderLayout.CENTER);
        add(rgbTextField, BorderLayout.SOUTH);
    }

    private JComponent createHSVPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Add the default HSV panel from the color chooser
        JComponent defaultHSVPanel = colorChooser.getChooserPanels()[1];
        panel.add(defaultHSVPanel, BorderLayout.CENTER);

        return panel;
    }

    public Color getSelectedColor() {
        return colorChooser.getColor();
    }

    public void setSelectedColor(Color color) {
        colorChooser.setColor(color);
    }

    public void setRGBTextField(String rgbCode) {
        rgbTextField.setText(rgbCode);
    }

    public static void main(String[] args) {
        // Example of using panTest in another class
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                panTest colorChooserPanel = new panTest();
                frame.add(colorChooserPanel);

                JButton showColorButton = new JButton("Show Selected Color");
                showColorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color selectedColor = colorChooserPanel.getSelectedColor();
                        JOptionPane.showMessageDialog(frame, "Selected Color: " + selectedColor);
                    }
                });

                JButton setRGBButton = new JButton("Set RGB Code");
                setRGBButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        colorChooserPanel.setRGBTextField("255,0,0");
                    }
                });

                JPanel buttonPanel = new JPanel();
                buttonPanel.add(showColorButton);
                buttonPanel.add(setRGBButton);

                frame.add(buttonPanel, BorderLayout.SOUTH);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

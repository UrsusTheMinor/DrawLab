import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.awt.event.*;

public class FrmMain extends JFrame {

    private static final int BIN = 0;
    private static final int TXT = 1;

    private static PanMain panMain = new PanMain();

    private ColorDialog colorDialog = new ColorDialog(this);
    private final FileFilter txtFileFilter = new FileFilter() {
        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().endsWith(".txt");
        }

        @Override
        public String getDescription() {
            return "Text file (*.txt)";
        }
    };

    private final FileFilter binFileFilter = new FileFilter() {
        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().endsWith(".bin");
        }

        @Override
        public String getDescription() {
            return "Binary file (*.bin)";
        }
    };

    public FrmMain() {
        setSize(1154,800);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DrawLab - Alam | Papp | Behr");


        JMenuItem miNew = new JMenuItem("New", KeyEvent.VK_N);
        miNew.setAccelerator(KeyStroke.getKeyStroke("control N"));
        miNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panMain.doMenuNew();
            }
        });

        JMenu miOpen = new JMenu("Open");

        JMenuItem openBin = new JMenuItem("Open Binary");
        openBin.setAccelerator(KeyStroke.getKeyStroke("control O"));
        openBin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open(BIN);
            }
        });

        JMenuItem openTxt = new JMenuItem("Open Text");
        openTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open(TXT);
            }
        });

        miOpen.add(openBin);
        miOpen.add(openTxt);

        JMenu miSave = new JMenu("Save");

        JMenuItem saveBin = new JMenuItem("Save Binary", KeyEvent.VK_S);
        saveBin.setAccelerator(KeyStroke.getKeyStroke("control S"));
        saveBin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save(BIN);
            }
        });

        JMenuItem saveTxt = new JMenuItem("Save Text", KeyEvent.VK_S);
        saveTxt.addActionListener(new ActionListener() {  // minor bug fix: opened two file choosers at once
            @Override
            public void actionPerformed(ActionEvent e) {
                save(TXT);
            }
        });

        miSave.add(saveBin);
        miSave.add(saveTxt);

        JMenuItem miExit = new JMenuItem("Exit", KeyEvent.VK_X);
        miExit.setAccelerator(KeyStroke.getKeyStroke("control X"));
        miExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu menFile = new JMenu("File");

        menFile.add(miNew);
        menFile.addSeparator();
        menFile.add(miOpen);
        menFile.add(miSave);
        menFile.addSeparator();
        menFile.add(miExit);

        JMenu menOptions = new JMenu("Options");
        JMenu menType = new JMenu("Type");

        JRadioButton miPoint = new JRadioButton("Point");
        JRadioButton miLine = new JRadioButton("Line");
        JRadioButton miCircle = new JRadioButton("Circle");
        JRadioButton miRectangle = new JRadioButton("Rectangle");

        ButtonGroup bgType = new ButtonGroup();
        bgType.add(miPoint);
        bgType.add(miLine);
        bgType.add(miCircle);
        bgType.add(miRectangle);
        miRectangle.setSelected(true);
        panMain.setFigureType(Figure.RECTANGLE);

        menType.add(miPoint);
        menType.add(miLine);
        menType.add(miCircle);
        menType.add(miRectangle);

        miPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panMain.setFigureType(Figure.POINT);
            }
        });

        miLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panMain.setFigureType(Figure.LINE);
            }
        });

        miCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panMain.setFigureType(Figure.CIRCLE);
            }
        });

        miRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panMain.setFigureType(Figure.RECTANGLE);
            }
        });

        JMenuItem miColor = new JMenuItem("Color");
        miColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorDialog.toggleVisibility();
            }
        });
        menOptions.add(menType);
        menOptions.add(miColor);

        JMenuBar mbMain = new JMenuBar();
        mbMain.add(menFile);
        mbMain.add(menOptions);

        setJMenuBar(mbMain);
        setContentPane(panMain);
        setVisible(true);
    }

    public void save(int type) {
        FileFilter filter = (type == 1) ? txtFileFilter : binFileFilter;

        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(filter);
        fc.setFileFilter(filter);
        if (fc.showSaveDialog(FrmMain.this) == JFileChooser.APPROVE_OPTION) {
            panMain.miSave(fc.getSelectedFile().getAbsolutePath(), type);
        }
    }

    public void open(int type) {
        FileFilter filter = (type == 0) ? binFileFilter: txtFileFilter;

        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(filter);
        fc.setFileFilter(filter);
        if (fc.showOpenDialog(FrmMain.this) == JFileChooser.APPROVE_OPTION) {
            panMain.miOpen(fc.getSelectedFile().getAbsolutePath());
        }
    }

    public static void setBorderColor(Color c) {
        panMain.setBorderColor(c);
    }

    public static void setFillColor(Color c) {
        panMain.setFillColor(c);
    }


    public static void main(String[] args) {
        FrmMain frmMain = new FrmMain();
    }
}

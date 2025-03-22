import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    JLabel statusLabel;
    JPanel statusBar;

    JFrame frame;
    //Text Area Components
    JTextArea textArea;
    JScrollPane scrollPane;
    Boolean wordWrapOn = false;
    //Top menu
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuBackground, menuAbout;
    JMenuItem menuFileNew, menuFileOpen, menuFileSave, menuFileSaveAs, menuFileExit;
    //Color Menu
    JMenuItem color1, color2, color3;
    //Format
    JMenuItem menuFormatWrap, FontArial, FontCSMS, FontTNR, FontSize8, FontSize12, FontSize16, FontSize20, FontSize24, FontSize28;
    JMenu menuFont, menuFontSize;


    JMenuItem menuEditUndo, menuEditRedo;


    //Initializing function classes
    Functions file = new Functions(this);
    Functions_Format format = new Functions_Format(this);
    Function_Color color = new Function_Color(this);
    Function_Edit edit = new Function_Edit(this);
    Key_Handler kh = new Key_Handler(this);
    Function_About ab=new Function_About(this);


    UndoManager um = new UndoManager();


    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        createWindow();
        createStatusBar();
        createTextArea();
        createMenuBar();
        createMenuFile();
        createFormatMenu();
        createColorMenu();
        createAbout();
        editMenu();
        color.setColor("White");
        format.selectedFont = "Arial";//default font
        format.createFont(16);//default font size
        format.wordWrap();


        frame.setVisible(true);
    }
    public void createAbout(){
            JMenuItem aboutItem = new JMenuItem("About");
            aboutItem.addActionListener(this);
            aboutItem.setActionCommand("About");
            menuAbout.add(aboutItem);



    }

    public void editMenu() {
        menuEditUndo = new JMenuItem("Undo");
        menuEditUndo.addActionListener(this);
        menuEditUndo.setActionCommand("Undo");
        menuEdit.add(menuEditUndo);

        menuEditRedo = new JMenuItem("Redo");
        menuEditRedo.addActionListener(this);
        menuEditRedo.setActionCommand("Redo");
        menuEdit.add(menuEditRedo);
    }

    public void createColorMenu() {
        color1 = new JMenuItem("White");
        color1.addActionListener(this);
        color1.setActionCommand("White");
        menuBackground.add(color1);

        color2 = new JMenuItem("Black");
        color2.addActionListener(this);
        color2.setActionCommand("Black");

        menuBackground.add(color2);
        color3 = new JMenuItem("Cyan");
        color3.addActionListener(this);
        color3.setActionCommand("Cyan");
        menuBackground.add(color3);
    }

    public void createWindow() {
        frame = new JFrame("NotepadB");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea() {
        textArea = new JTextArea();
        //add keyListener for keyboard shortcuts
        textArea.addKeyListener(kh);
        //undo redo function to text area
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane);
        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateStatus();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateStatus();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateStatus();
            }
        });


    }
    public void updateStatus() {
        String text = textArea.getText();

        // Count words & characters
        int charCount = text.length();
        int wordCount = text.isBlank() ? 0 : text.trim().split("\\s+").length;

        // Update status bar text
        statusLabel.setText("Words: " + wordCount + "  Characters: " + charCount);
    }

    public void createStatusBar() {
        statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEtchedBorder());

        // Label to display word & character count
        statusLabel = new JLabel("Words: 0  Characters: 0");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));

        // Add label to the status bar
        statusBar.add(statusLabel, BorderLayout.WEST);

        // Add status bar to the frame
        frame.add(statusBar, BorderLayout.SOUTH);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuFormat = new JMenu("Format");
        menuBackground = new JMenu("Theme");
        menuAbout = new JMenu("About");
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFormat);
        menuBar.add(menuBackground);
        menuBar.add(menuAbout);
    }

    public void createFormatMenu() {
        menuFormatWrap = new JMenuItem("Word Wrap : OFF");
        menuFormatWrap.addActionListener(this);
        menuFormatWrap.setActionCommand("Word Wrap");
        menuFormat.add(menuFormatWrap);

        //Font menu
        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        FontArial = new JMenuItem("Arial");
        FontArial.addActionListener(this);
        FontArial.setActionCommand("Arial");
        menuFont.add(FontArial);

        FontCSMS = new JMenuItem("Comic Sans MS");
        FontCSMS.setActionCommand("Comic Sans MS");
        FontCSMS.addActionListener(this);
        menuFont.add(FontCSMS);

        FontTNR = new JMenuItem("Times New Roman");
        FontTNR.addActionListener(this);
        FontTNR.setActionCommand("Times New Roman");
        menuFont.add(FontTNR);


        //Font size menu
        menuFontSize = new JMenu("FontSize");
        menuFormat.add(menuFontSize);

        FontSize8 = new JMenuItem("8");
        FontSize8.addActionListener(this);
        FontSize8.setActionCommand("8");
        menuFontSize.add(FontSize8);

        FontSize12 = new JMenuItem("12");
        FontSize12.addActionListener(this);
        FontSize12.setActionCommand("12");
        menuFontSize.add(FontSize12);

        FontSize16 = new JMenuItem("16");
        FontSize16.addActionListener(this);
        FontSize16.setActionCommand("16");
        menuFontSize.add(FontSize16);

        FontSize20 = new JMenuItem("20");
        FontSize20.addActionListener(this);
        FontSize20.setActionCommand("20");
        menuFontSize.add(FontSize20);

        FontSize24 = new JMenuItem("24");
        FontSize24.addActionListener(this);
        FontSize24.setActionCommand("24");
        menuFontSize.add(FontSize24);

        FontSize28 = new JMenuItem("28");
        FontSize28.addActionListener(this);
        FontSize28.setActionCommand("28");
        menuFontSize.add(FontSize28);


    }

    public void createMenuFile() {
        menuFileNew = new JMenuItem("New");
        menuFileNew.addActionListener(this);
        menuFileNew.setActionCommand("New");
        menuFile.add(menuFileNew);

        menuFileOpen = new JMenuItem("Open");
        menuFileOpen.addActionListener(this);
        menuFileOpen.setActionCommand("Open");
        menuFile.add(menuFileOpen);

        menuFileSave = new JMenuItem("Save");
        menuFileSave.addActionListener(this);
        menuFileSave.setActionCommand("Save");
        menuFile.add(menuFileSave);

        menuFileSaveAs = new JMenuItem("Save As");
        menuFileSaveAs.addActionListener(this);
        menuFileSaveAs.setActionCommand("SaveAs");
        menuFile.add(menuFileSaveAs);

        menuFileExit = new JMenuItem("Exit");
        menuFileExit.addActionListener(this);
        menuFileExit.setActionCommand("Exit");
        menuFile.add(menuFileExit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New":
                file.newFile();
                break;
            case "Open":
                file.openFile();
                break;
            case "Save":
                file.saveFile();
                break;
            case "SaveAs":
                file.saveAsFile();
                break;
            case "Word Wrap":
                format.wordWrap();
                break;
            case "8":
                format.createFont(8);
                break;

            case "12":
                format.createFont(12);
                break;

            case "16":
                format.createFont(16);
                break;

            case "20":
                format.createFont(20);
                break;

            case "24":
                format.createFont(24);
                break;

            case "28":
                format.createFont(28);
                break;

            case "Arial":
                format.setFont(command);
                break;

            case "Comic Sans MS":
                format.setFont("Comic Sans MS");
                break;
            case "White", "Black", "Cyan":
                color.setColor(command);
                break;
            case "Times New Roman":
                format.setFont("Times New Roman");
                break;
            case "Undo":
                edit.undo();
                break;
            case "Redo":
                edit.redo();
                break;
            case "About":
                ab.createAbout();
                break;
            case "Exit":
                file.exit();
                break;
        }
    }


}

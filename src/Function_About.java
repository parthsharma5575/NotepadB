import javax.swing.*;

public class Function_About {
    GUI gui;
    public Function_About(GUI gui) {
        this.gui = gui;
    }
    public void createAbout(){
        JFrame frame = new JFrame("About");
        frame.setSize(400,200);
        frame.setLocationRelativeTo(null);//centers
        JTextArea textArea = new JTextArea();
        textArea.setText("NotepadB is functional notepad created by Parth Sharma");
        textArea.setEditable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(textArea);
        frame.setVisible(true);
    }
}

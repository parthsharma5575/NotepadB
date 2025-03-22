import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Functions {
    GUI gui;
    String fileName;
    String fileAdd;

    public Functions(GUI gui) {
        this.gui = gui;
    }

    public void newFile() {
        gui.textArea.setText("");//erase current text
        gui.frame.setTitle("New File");//changes title to new file
        fileName=null;  //when you start application file name and file add has nothing
        fileAdd=null;
    }

    public void openFile() {
        FileDialog fd = new FileDialog(gui.frame, "Open File", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileName = fd.getFile();//gets file name of currently opened file
            fileAdd = fd.getDirectory(); // gets file address
            //display file name on window frame
            gui.frame.setTitle(fileName);
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileAdd + fileName));//need the address to read the file
            gui.textArea.setText("");
            String line;
            while ((line = br.readLine()) != null) {
                gui.textArea.append(line+"\n");//reads line by line and appends to textArea
                //Adds new line after reading
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveFile() {
        if(fileName == null || fileAdd==null) {//means new file not saved yet
            saveAsFile();//calls save as
        }
        //working on saved file
        try{
            FileWriter fw = new FileWriter(fileAdd + fileName);
            fw.write(gui.textArea.getText());
            gui.frame.setTitle(fileName);
            fw.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void saveAsFile() {
        FileDialog fd = new FileDialog(gui.frame, "Save File", FileDialog.SAVE);//implements filedialog which is a part of awt
        fd.setVisible(true);//shows dialog
        if(fd.getFile() != null) {
            fileName = fd.getFile();//get file name
            fileAdd = fd.getDirectory();//get file path
            gui.frame.setTitle(fileName);
        }
        if (fd.getFile() == null || fd.getDirectory() == null) {
            return; // User canceled the dialog
        }
        try {
            FileWriter fw = new FileWriter(fileAdd + fileName);
            fw.write(gui.textArea.getText());
            fw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void exit(){
        System.exit(0);
    }
}

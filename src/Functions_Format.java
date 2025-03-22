import java.awt.*;

public class Functions_Format {
    GUI gui;
    Font arial, comicSansMs, timesNewRoman;
    String selectedFont;

    public Functions_Format(GUI gui) {
        this.gui = gui;
    }

    public void wordWrap() {
        if (!gui.wordWrapOn) {//word wrap is off turn it on
            gui.wordWrapOn = true;
            gui.textArea.setLineWrap(true);//line break anywhere
            gui.textArea.setWrapStyleWord(true);//line break doesn't in between word after or before word
            gui.menuFormatWrap.setText("Word Wrap : ON");
        } else {//word wrap is on turn it off
            gui.wordWrapOn = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.menuFormatWrap.setText("Word Wrap : OFF");
        }
    }

    public void createFont(int fontSize) {
        arial = new Font("Arial", Font.PLAIN, fontSize);
        comicSansMs = new Font("Comic Sans MS", Font.PLAIN, fontSize);
        timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);
        setFont(selectedFont);
    }

    public void setFont(String font) {
        selectedFont = font;
        switch (selectedFont) {
            case "Arial":
                gui.textArea.setFont(arial);
                break;
            case "Comic Sans MS":
                gui.textArea.setFont(comicSansMs);
                break;
            case "Times New Roman":
                gui.textArea.setFont(timesNewRoman);
                break;
        }
    }


}

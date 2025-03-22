import java.awt.*;

public class Function_Color {
    GUI gui;

    public Function_Color(GUI gui) {
        this.gui = gui;
    }

    public void setColor(String color) {
        Color bgColor;
        Color fgColor;

        switch (color) {
            case "Cyan":
                bgColor = new Color(0, 255, 255);
                fgColor = Color.BLACK;
                break;
            case "White":
                bgColor = Color.WHITE;
                fgColor = Color.BLACK;
                break;
            case "Black":
                bgColor = Color.BLACK;
                fgColor = Color.WHITE;
                break;
            default:
                System.out.println("Invalid color selection.");
                return; // Exit if the color is invalid
        }

        // Apply color changes to the text area (main UI component)
        gui.textArea.setBackground(bgColor);
        gui.textArea.setForeground(fgColor);

        // Apply color to the frame's content pane
        gui.frame.getContentPane().setBackground(bgColor);


    }
}

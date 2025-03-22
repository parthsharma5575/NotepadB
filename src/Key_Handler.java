import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key_Handler implements KeyListener {
    GUI gui;
    public Key_Handler(GUI gui){
        this.gui = gui;

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isControlDown() &&  e.getKeyCode() == KeyEvent.VK_S){
            gui.file.saveFile();
        }
        if(e.isShiftDown() && e.isControlDown() &&  e.getKeyCode() == KeyEvent.VK_S){
            gui.file.saveAsFile();
        }
        if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F){
            gui.menuFile.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

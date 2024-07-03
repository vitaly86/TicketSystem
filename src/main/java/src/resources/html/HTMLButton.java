package src.resources.html;

import javax.swing.*;
import java.awt.*;

public class HTMLButton extends JButton {

    public HTMLButton(String action, Color btnColor) {
        this.setText(action);
        this.setFocusable(false);
        this.setBackground(btnColor);
        this.setForeground(Color.WHITE);
    }
}

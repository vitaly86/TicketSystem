package mvc.gui.admin;

import javax.swing.*;
import java.awt.*;

public class MainGUIContainer extends JPanel {

    protected JPanel contentPanel;

    public MainGUIContainer(String cardLabel) {
        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.setPreferredSize(new Dimension(AdminMainTemplate.adminPanelOptionsWidth,
                (int)(AdminMainTemplate.screenHeight * 0.1)));
        upperPanel.setBackground(Color.WHITE);

        JLabel currentCard = new JLabel(cardLabel);
        currentCard.setBounds(50, 50, 200, 100);
        currentCard.setForeground(Color.BLACK);
        currentCard.setFont(new Font(null, Font.BOLD, 30));

        JPanel resultContentPanel = createUserBox();
        resultContentPanel.add(currentCard);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(upperPanel);
        add(resultContentPanel);
        setPreferredSize(new Dimension(AdminMainTemplate.adminPanelOptionsWidth,
                AdminMainTemplate.screenHeight));
    }

    protected JPanel createUserBox() {

        contentPanel = new JPanel();
        contentPanel.setLayout(null);

        contentPanel.setPreferredSize(new Dimension(AdminMainTemplate.adminPanelOptionsWidth,
                (int)(AdminMainTemplate.screenHeight * 0.9)));
        contentPanel.setBackground(Color.LIGHT_GRAY);
        return contentPanel;
    }
}

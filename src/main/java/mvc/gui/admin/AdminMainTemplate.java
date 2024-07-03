package mvc.gui.admin;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class AdminMainTemplate extends JFrame{

    protected JPanel sidePanel;

    protected static int screenWidth;
    protected static int screenHeight;
    protected static int sidePanelOptionsWidth;
    protected static int adminPanelOptionsWidth;

    public AdminMainTemplate(){

        /*
            Define Local Variables
        */

        JLabel appLabel, generalLabel, administrationLabel;
        JPanel tamponPanel, endPanel, containerPanel;

        /*
          Find the current Window Size
         */

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) (screenSize.width * 0.8);
        screenHeight = (int) (screenSize.height * 0.8);
        sidePanelOptionsWidth = (int)(screenWidth * 0.20);
        adminPanelOptionsWidth = (int) (screenWidth * 0.80);

        /*
          Create Label for Admin Window Options
         */

        appLabel = new JLabel("TICKET SYSTEM APP");
        appLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        appLabel.setForeground(Color.WHITE);
        appLabel.setFont(new Font(null, Font.BOLD, 20));
        appLabel.setBorder(new EmptyBorder(50, 20, 50, 20));

         /*
          Create General Label
         */

        generalLabel = new JLabel("GENERAL");
        generalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        generalLabel.setForeground(Color.WHITE);
        generalLabel.setFont(new Font(null, Font.ITALIC, 15));
        generalLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        /*
          Create Administration Label
         */

        administrationLabel = new JLabel("ADMINISTRATION");
        administrationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        administrationLabel.setForeground(Color.WHITE);
        administrationLabel.setFont(new Font(null, Font.ITALIC, 15));
        administrationLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        /*
           Create Tampon Panel for Components
        */

        tamponPanel = new JPanel();
        tamponPanel.setPreferredSize(new Dimension(sidePanelOptionsWidth, 100));
        tamponPanel.setBackground(Color.GRAY);

        /*
           Create Tampon Panel 2 for Components
        */

        endPanel = new JPanel();
        endPanel.setPreferredSize(new Dimension(sidePanelOptionsWidth, 100));
        endPanel.setBackground(Color.BLACK);

        /*
           Create Container Panel for Admin Options
        */

        containerPanel = new JPanel();
        String[] generalList = {"Dashboard", "Tickets"};
        String[] adminOptionsList = {"Statuses", "Priorities", "Users", "User roles"};
        containerPanel.setBackground(Color.GRAY);
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.add(generalLabel);
        containerPanel.add(new FixedPanel(generalList));
        containerPanel.add(administrationLabel);
        containerPanel.add(new FixedPanel(adminOptionsList));
        containerPanel.add(tamponPanel);
        containerPanel.add(endPanel);


        /*
           Create Side Panel for Components
        */

        sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(sidePanelOptionsWidth, screenHeight));
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(appLabel);
        sidePanel.add(containerPanel);

        /*
            Add Side Panel to the Window Frame
         */

    }

    private static class FixedPanel extends JPanel{

        public FixedPanel(String @NotNull [] options) throws HeadlessException {

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            for (String option : options) {
                this.add(createCard(option));
            }
        }

        private @NotNull JPanel createCard(String text){
            JPanel card = new JPanel(new BorderLayout());
            card.setPreferredSize(new Dimension(sidePanelOptionsWidth, 100));
            card.setMaximumSize(new Dimension(sidePanelOptionsWidth, 100));
            card.setMinimumSize(new Dimension(sidePanelOptionsWidth, 100));
            card.setBackground(Color.GRAY);

            JLabel label = new JLabel(text);
            label.setForeground(Color.WHITE);
            label.setFont(new Font(null, Font.PLAIN, 15));
            label.setBorder(new EmptyBorder(10, 10, 10, 0));

            card.add(label, BorderLayout.WEST);

            card.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    card.setBackground(Color.BLACK);
                    card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    card.setBackground(Color.GRAY);
                    card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            });
            return card;
        }
    }
    protected abstract static class UserCardContainer extends JPanel{

        protected JPanel contentPanel;

        public UserCardContainer(String cardName) {
            JPanel upperPanel = new JPanel();
            upperPanel.setPreferredSize(new Dimension(adminPanelOptionsWidth, (int)(screenHeight * 0.1)));
            upperPanel.setBackground(Color.WHITE);

            JPanel resultContentPanel = createUserBox(cardName);

            setLayout(new FlowLayout(FlowLayout.CENTER));
            add(upperPanel);
            add(resultContentPanel);
            setPreferredSize(new Dimension(adminPanelOptionsWidth, screenHeight));
        }

        protected JPanel createUserBox(String cardName) {

            JLabel currentCard = new JLabel(cardName);
            currentCard.setBounds(50, 50, 100, 100);
            currentCard.setForeground(Color.BLACK);
            currentCard.setFont(new Font(null, Font.BOLD, 30));

            contentPanel = new JPanel();
            contentPanel.setLayout(null);
            contentPanel.add(currentCard);

            contentPanel.setPreferredSize(new Dimension(adminPanelOptionsWidth, (int)(screenHeight * 0.9)));
            contentPanel.setBackground(Color.LIGHT_GRAY);
            return contentPanel;
        }
    }
}


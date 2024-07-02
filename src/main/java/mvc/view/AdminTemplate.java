package mvc.view;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AdminTemplate extends JFrame{

    private static int sidePanelOptionsWidth;

    public AdminTemplate(){

        /*
          Find the current Window Size
         */

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) (screenSize.width * 0.8);
        int screenHeight = (int) (screenSize.height * 0.8);
        sidePanelOptionsWidth = (int)(screenWidth * 0.20);
        int adminPanelOptionsWidth = (int) (screenWidth * 0.80);

        /*
          Create Label for Admin Window Options
         */

        JLabel appLabel = new JLabel("TICKET SYSTEM APP");
        appLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        appLabel.setForeground(Color.WHITE);
        appLabel.setFont(new Font(null, Font.BOLD, 20));
        appLabel.setBorder(new EmptyBorder(50, 20, 50, 20));

         /*
          Create General Label
         */

        JLabel generalLabel = new JLabel("GENERAL");
        generalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        generalLabel.setForeground(Color.WHITE);
        generalLabel.setFont(new Font(null, Font.ITALIC, 15));
        generalLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        /*
          Create Administration Label
         */

        JLabel administrationLabel = new JLabel("ADMINISTRATION");
        administrationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        administrationLabel.setForeground(Color.WHITE);
        administrationLabel.setFont(new Font(null, Font.ITALIC, 15));
        administrationLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        /*
           Create Tampon Panel for Components
        */

        JPanel tamponPanel = new JPanel();
        tamponPanel.setPreferredSize(new Dimension(sidePanelOptionsWidth, 100));
        tamponPanel.setBackground(Color.GRAY);

        /*
           Create Tampon Panel 2 for Components
        */

        JPanel endPanel = new JPanel();
        endPanel.setPreferredSize(new Dimension(sidePanelOptionsWidth, 100));
        endPanel.setBackground(Color.BLACK);

        /*
           Create Container Panel for Admin Options
        */

        JPanel containerPanel = new JPanel();
        String[] generalList = {"Dashboard", "Tickets"};
        String[] adminOptionsList = {"Statuses", "Priorities", "Users", "User roles"};
        containerPanel.setBackground(Color.GRAY);
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.add(generalLabel);
        containerPanel.add(new GeneralPanel(generalList));
        containerPanel.add(administrationLabel);
        containerPanel.add(new GeneralPanel(adminOptionsList));
        containerPanel.add(tamponPanel);
        containerPanel.add(endPanel);


        /*
           Create Side Panel for Components
        */

        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(sidePanelOptionsWidth, screenHeight));
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(appLabel);
        sidePanel.add(containerPanel);

        /*
           Create Admin Panel for Content
        */

        JPanel adminPanel = new JPanel();
        adminPanel.setPreferredSize(new Dimension(adminPanelOptionsWidth, screenHeight));

        /*
            Add Side Panel to the Window Frame
         */

        this.setLayout(new BorderLayout());
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new FlowLayout());
        mainContainer.add(sidePanel);
        mainContainer.add(adminPanel);
        this.setTitle("Welcome");
        this.setSize(screenWidth, screenHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private static class GeneralPanel extends JPanel{

        public GeneralPanel(String @NotNull [] options) throws HeadlessException {

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
}


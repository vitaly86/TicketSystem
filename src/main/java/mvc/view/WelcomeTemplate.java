package mvc.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WelcomeTemplate extends  JFrame{

    private static int panelOptionsWidth;

    public WelcomeTemplate(){

        /*
          Find the current Window Size
         */

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) (screenSize.width * 0.8);
        int screenHeight = (int) (screenSize.height * 0.8);
        panelOptionsWidth = (int)(screenWidth * 0.20);

        /*
          Create Label for Admin Window Options
         */

        JLabel appLabel = new JLabel("TICKET SYSTEM APP", SwingConstants.CENTER);
        appLabel.setForeground(Color.WHITE);
        appLabel.setFont(new Font(null, Font.BOLD, 15));
        appLabel.setBorder(new EmptyBorder(10, 20, 10, 20));

         /*
          Create General Label
         */

        JLabel generalLabel = new JLabel("GENERAL");
        generalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        generalLabel.setForeground(Color.WHITE);
        generalLabel.setFont(new Font(null, Font.ITALIC, 12));
        generalLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        /*
           Create Container Panel for Admin Options
        */

        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(Color.GRAY);
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.add(generalLabel);
        containerPanel.add(new GeneralPanel());

        /*
           Create Side Panel for Components
        */

        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(panelOptionsWidth, screenHeight));
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(appLabel);
        sidePanel.add(containerPanel);


        /*
            Add Side Panel to the Window Frame
         */

        this.setLayout(new BorderLayout());
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.add(sidePanel, BorderLayout.WEST);
        this.setTitle("Welcome");
        this.setSize(screenWidth, screenHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private static class GeneralPanel extends JPanel{

        public GeneralPanel() throws HeadlessException {

            this.setLayout(new GridLayout(3, 1));

            JPanel cardDashboard = createCard("Dashboard");
            JPanel cardTicket = createCard("Tickets");
            JPanel cardExample = createCard("Example");

            this.add(cardDashboard);
            this.add(cardTicket);
            this.add(cardExample);
        }

        private JPanel createCard(String text){
            JPanel card = new JPanel(new BorderLayout());
            card.setPreferredSize(new Dimension(panelOptionsWidth, 10));
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
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    card.setBackground(Color.GRAY);
                }
            });
            return card;
        }
    }
}


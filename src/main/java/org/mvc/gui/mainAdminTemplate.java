package org.mvc.gui;

import org.jetbrains.annotations.NotNull;
import org.resources.css.ShadowPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class mainAdminTemplate extends JFrame{

    private JPanel MainPanel;
    private static int screenHeight;
    private static int sidePanelOptionsWidth;
    private static int adminPanelOptionsWidth;

    public mainAdminTemplate() throws IOException {

        /*
          Find the current Window Size
         */

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) (screenSize.width * 0.8);
        screenHeight = (int) (screenSize.height * 0.8);
        sidePanelOptionsWidth = (int)(screenWidth * 0.20);
        adminPanelOptionsWidth = (int) (screenWidth * 0.80);

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
        containerPanel.add(new FixedPanel(generalList));
        containerPanel.add(administrationLabel);
        containerPanel.add(new FixedPanel(adminOptionsList));
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
            Add Side Panel to the Window Frame
         */

        this.setLayout(new BorderLayout());
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new FlowLayout());
        mainContainer.add(sidePanel);
        mainContainer.add(new UserCardContainer());
        this.setTitle("Welcome");
        this.setSize(screenWidth, screenHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    private static class FixedPanel extends JPanel{

        public FixedPanel(String @NotNull [] options) throws HeadlessException {

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            for (String option : options) {
                JPanel card = createCard(option);
                this.add(card);
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

    private static class UserCardContainer extends JPanel{

        public UserCardContainer() {
            JPanel upperPanel = new JPanel();
            upperPanel.setPreferredSize(new Dimension(adminPanelOptionsWidth, (int)(screenHeight * 0.1)));
            upperPanel.setBackground(Color.WHITE);

            JPanel contentPanel = createUserBox();

            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.add(upperPanel);
            this.add(contentPanel);
            this.setPreferredSize(new Dimension(adminPanelOptionsWidth, screenHeight));
        }

        private static @NotNull JPanel createUserBox() {
            JLabel currentCard = new JLabel("Users");
            currentCard.setBounds(50, 50, 100, 100);
            currentCard.setForeground(Color.BLACK);
            currentCard.setFont(new Font(null, Font.BOLD, 30));

            JButton createUserBTN = new JButton("Create User");
            createUserBTN.setBounds(adminPanelOptionsWidth - 170, 50, 120, 50);
            createUserBTN.setFocusable(false);
            createUserBTN.setBackground(Color.GRAY);
            createUserBTN.setForeground(Color.WHITE);

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(null);
            contentPanel.add(currentCard);
            contentPanel.add(createUserBTN);

            JPanel userContainer = userContainer();

            for(int i = 0; i < 20; i++){

                /*
                  adds vertical spacing between components in userContainer
                 */

                userContainer.add(Box.createRigidArea(new Dimension(0, 10)));
                userContainer.add(userCardContainer(i + 1 + ". " + "Admin" + i,"Admin"));
            }

            JScrollPane scrollPane = new JScrollPane(userContainer);
            scrollPane.setBounds(50, 200, adminPanelOptionsWidth - 100, (int)(screenHeight * 0.3));
            contentPanel.add(scrollPane);

            contentPanel.setPreferredSize(new Dimension(adminPanelOptionsWidth, (int)(screenHeight * 0.9)));
            contentPanel.setBackground(Color.LIGHT_GRAY);
            return contentPanel;
        }
    }

    private static @NotNull JPanel userContainer(){
        JPanel userInfos = new JPanel();
        userInfos.setLayout(new BoxLayout(userInfos, BoxLayout.Y_AXIS));
        userInfos.setBackground(Color.WHITE);
        return userInfos;
    }

    private static @NotNull ShadowPanel userCardContainer(String userID, String userRole) {
        ShadowPanel userCard = new ShadowPanel();
        userCard.setLayout(new GridLayout(1, 2));
        userCard.setPreferredSize(new Dimension(adminPanelOptionsWidth - 120, 100));
        userCard.setBorder(BorderFactory.createLineBorder(Color.WHITE));


        JLabel idLabel = new JLabel(userID);
        idLabel.setFont(new Font(null, Font.BOLD, 17));
        idLabel.setBorder(new EmptyBorder(0, 25, 0, 0));

        String modifiedUserRole = "<html>"
                + "The user has the role " +
                "<span style='font-size:15px; color: blue; font-style:italic'>"
                + userRole +
                "</span>" +
                "</html>";

        JLabel contentRoleLabel = new JLabel(modifiedUserRole);

        JLabel editText = new JLabel("Edit User");
//        editText.setBorder(new EmptyBorder(0, 0, 0, 50));

        ImageIcon editUserIcon = null;
        String editIconPath = "org.src\\main\\java\\org.src\\resources\\icons\\edit-user.jpg";
        File imageFile = new File(editIconPath);

        if(imageFile.exists()){
            try {
                Image icon = ImageIO.read(imageFile);
                Image resizedIcon = icon.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                editUserIcon = new ImageIcon(resizedIcon);
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
                System.out.println("Image file not found at: " + editIconPath);
        }


        JButton editUserBTN = getBorderButton(editUserIcon);

        JPanel editBTNContainer = new JPanel();
        editBTNContainer.setLayout(new BoxLayout(editBTNContainer, BoxLayout.X_AXIS));
        editBTNContainer.add(Box.createHorizontalGlue());
        editBTNContainer.add(editText);
        editBTNContainer.add(Box.createRigidArea(new Dimension(15, 0)));
        editBTNContainer.add(editUserBTN);
        editBTNContainer.add(Box.createHorizontalGlue());

        userCard.add(idLabel);
        userCard.add(contentRoleLabel);
        userCard.add(editBTNContainer);

        return userCard;
    }

    private static @NotNull JButton getBorderButton(ImageIcon editUserIcon) {
        JButton editUserBTN = new JButton(editUserIcon);
        editUserBTN.setPreferredSize(new Dimension(60, 60));
//        editUserBTN.setBorder(new LineBorder(Color.GRAY, 2));
        editUserBTN.setMargin(new Insets(0,0,0,0));

        Border paddingBorder = BorderFactory.createEmptyBorder(0,0,0,0);
        Border currentBTNBorder = editUserBTN.getBorder();

        if(currentBTNBorder != null){
            editUserBTN.setBorder(BorderFactory.createCompoundBorder(currentBTNBorder, paddingBorder));
        }else{
            editUserBTN.setBorder(paddingBorder);
        }

        editUserBTN.setBorderPainted(true);
        editUserBTN.setFocusPainted(false);
        editUserBTN.setContentAreaFilled(false);
        return editUserBTN;
    }

    public static void main(String[] args) throws IOException {
        new mainAdminTemplate();
    }
}




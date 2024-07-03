package mvc.gui.admin;

import org.jetbrains.annotations.NotNull;
import src.resources.css.ShadowPanel;
import src.resources.html.HTMLButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.GRAY;

public class UserCardDetails extends MainGUIContainer{

    public UserCardDetails(String cardLabel) {
        super(cardLabel);
    }

    public @NotNull JPanel createUserBox() {

        super.createUserBox();

        JButton createUserBTN = new HTMLButton("Create User", GRAY);
        createUserBTN.setBounds(AdminMainTemplate.adminPanelOptionsWidth - 170, 50, 120, 50);

        JPanel userContainer = userContainer();

        for(int i = 0; i < 20; i++){

            /*
              Box.createRigidArea(): adds vertical spacing between components in userContainer
             */

            userContainer.add(Box.createRigidArea(new Dimension(0, 10)));
            userContainer.add(userCardContainer(i + 1 + ". " + "Admin" + i,"Admin"));
        }

        JScrollPane scrollPane = getFastScrollPane(userContainer);

        contentPanel.add(createUserBTN);
        contentPanel.add(scrollPane);

        return contentPanel;
    }

    private static @NotNull JScrollPane getFastScrollPane(JPanel userContainer) {
        JScrollPane scrollPane = new JScrollPane(userContainer);

        MouseWheelListener fastScrollListener = e -> {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            int rotation = e.getWheelRotation();
            int unitToScroll = e.getScrollAmount() * 5;
            int newValue = verticalScrollBar.getValue() + (rotation * unitToScroll);
            verticalScrollBar.setValue(newValue);
        };
        scrollPane.addMouseWheelListener(fastScrollListener);

        scrollPane.setBounds(50, 200, AdminMainTemplate.adminPanelOptionsWidth - 100,
                (int)(AdminMainTemplate.screenHeight * 0.3));
        return scrollPane;
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
        userCard.setPreferredSize(new Dimension(AdminMainTemplate.adminPanelOptionsWidth - 120, 100));
        userCard.setBorder(BorderFactory.createLineBorder(Color.WHITE));


        JLabel idLabel = new JLabel(userID);
        idLabel.setFont(new Font(null, Font.BOLD, 17));
        idLabel.setBorder(new EmptyBorder(0, 25, 0, 0));

        String modifiedUserRole = "<html>"
                + "The user has the role: " +
                "<span style='font-size:15px; color: blue; font-style:italic'>"
                + userRole +
                "</span>" +
                "</html>";

        JLabel contentRoleLabel = new JLabel(modifiedUserRole);

        JLabel editText = new JLabel("Edit User");
//        editText.setBorder(new EmptyBorder(0, 0, 0, 50));

        ImageIcon editUserIcon = null;
        String editIconPath = "src\\main\\java\\src\\resources\\icons\\edit-user.jpg";
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
}

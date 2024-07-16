package org.mvc.gui.admin;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class AdminMainTemplate extends JFrame{

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenWidth = (int) (screenSize.width * 0.8);
    public static int screenHeight = (int) (screenSize.height * 0.8);
    public static int sidePanelOptionsWidth = (int)(screenWidth * 0.20);
    public static int adminPanelOptionsWidth = (int) (screenWidth * 0.80);

    private static final ArrayList<JPanel> availableCards = new ArrayList<>();
    private static MainGUIContainer currentCardState;

    static {
        try {
            currentCardState = new MainGUIContainer("Overview");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Container mainContainer;

    public AdminMainTemplate(){

        /*
            Define Local Variables
        */

        JLabel appLabel, generalLabel, administrationLabel;
        JPanel tamponPanel, endPanel;

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

        JPanel containerPanel = new JPanel();
        String[] generalOptionsList = {"Dashboard", "Tickets"};
        String[] adminOptionsList = {"Statuses", "Priorities", "Users", "User roles"};
        containerPanel.setBackground(Color.GRAY);
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.add(generalLabel);
        containerPanel.add(new MenuPanel(generalOptionsList));
        containerPanel.add(administrationLabel);
        containerPanel.add(new MenuPanel(adminOptionsList));
        containerPanel.add(tamponPanel);
        containerPanel.add(endPanel);
        getHoverEffects();


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

        this.setLayout(new FlowLayout());
        mainContainer = this.getContentPane();
        mainContainer.add(sidePanel);
        mainContainer.add(currentCardState);

        this.setTitle("Welcome");
        this.setSize(screenWidth, screenHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    private static class MenuPanel extends JPanel{

        public MenuPanel(String @NotNull [] options) throws HeadlessException {

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            for (String option : options) {
                JPanel actuallyCard = createCard(option);
                availableCards.add(actuallyCard);
                this.add(actuallyCard);
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
            label.setOpaque(false);
            label.setFont(new Font(null, Font.PLAIN, 15));
            label.setBorder(new EmptyBorder(10, 10, 10, 0));
            card.add(label, BorderLayout.WEST);

//            card.addMouseListener(Listener.addListeners(card));

            return card;
        }
    }

    private static class Listener {

        @Contract(value = "_ -> new", pure = true)
        public static @NotNull MouseAdapter addListeners(JPanel menuCard){

            return new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    String[] optionsList = {"Dashboard", "Tickets", "Statuses", "Priorities", "Users", "User roles"};

                    for(String option : optionsList){
                        switch (option){
                            case "Dashboard" -> new Thread(() -> backThread(menuCard, "Dashboard", MainGUIContainer.class)).start();
                            case "Tickets" -> new Thread(() -> backThread(menuCard, "Tickets", MainGUIContainer.class)).start();
                            case "Statuses" -> new Thread(() -> backThread(menuCard, "Statuses", MainGUIContainer.class)).start();
                            case "Priorities" -> new Thread(() -> backThread(menuCard, "Priorities", MainGUIContainer.class)).start();
                            case "Users" -> new Thread(() -> backThread(menuCard, "Users", UserCardDetails.class)).start();
                            case "User roles" -> new Thread(() -> backThread(menuCard, "User roles", MainGUIContainer.class)).start();
                        }
                    }
                }

                private <T> void backThread(@NotNull JPanel myCard, Object option, Class<T> clazz){
                    if (((JLabel)myCard.getComponent(0)).getText().equals(option)) {
                        mainContainer.remove(currentCardState);
                        try {
                            Constructor<T> constructor = clazz.getConstructor(option.getClass());
                            currentCardState = (MainGUIContainer) constructor.newInstance(option);
                        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                                 NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                        SwingUtilities.invokeLater(() ->{
                            mainContainer.add(currentCardState);
                            mainContainer.revalidate();
                            mainContainer.repaint();
                        });

                    }
                }



                @Override
                public void mouseEntered(MouseEvent e) {
                    menuCard.setBackground(Color.BLACK);
                    menuCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    menuCard.setBackground(Color.GRAY);
                    menuCard.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            };
        }
    }

    private static void getHoverEffects(){

        for (JPanel availableCard : availableCards) {
            availableCard.addMouseListener(Listener.addListeners(availableCard));
        }
    }
}


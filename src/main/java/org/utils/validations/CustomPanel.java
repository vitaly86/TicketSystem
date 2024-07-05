package org.utils.validations;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

    public class CustomPanel extends JPanel {

        private static final EventListenerList listenerList = new EventListenerList();

        public CustomPanel() {
            super(new BorderLayout());
            this.addMouseListener(new MouseAdapter());
        }

        public void addCustomMouseListener(MouseListener listener){
            listenerList.add(MouseListener.class, listener);
        }

        public void removeCustomMouseListener(MouseListener listener){
            listenerList.remove(MouseListener.class, listener);
        }

        private static void fireMouseEvent(MouseEvent event){
            MouseListener[] listeners = listenerList.getListeners(MouseListener.class);
            for(MouseListener listener: listeners){
                switch(event.getID()){
                    case MouseEvent.MOUSE_CLICKED -> listener.mouseClicked(event);
                    case MouseEvent.MOUSE_PRESSED -> listener.mousePressed(event);
                    case MouseEvent.MOUSE_RELEASED -> listener.mouseReleased(event);
                    case MouseEvent.MOUSE_ENTERED -> listener.mouseEntered(event);
                    case MouseEvent.MOUSE_EXITED -> listener.mouseExited(event);
                }
            }
        }

        private static class MouseAdapter implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {
                fireMouseEvent(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                fireMouseEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                fireMouseEvent(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                fireMouseEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fireMouseEvent(e);
            }
        }
    }


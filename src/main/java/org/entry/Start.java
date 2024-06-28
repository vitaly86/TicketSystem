package org.entry;

import mvc.controller.LogInController;
import org.database.Schema;

public class Start {
    public static void main(String[] args) {
        new Schema();
        new LogInController();
    }
}
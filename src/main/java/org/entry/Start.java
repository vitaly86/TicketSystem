package org.entry;

import mvc.controller.LogInController;
import org.database.CreateTables;

public class Start {
    public static void main(String[] args) {

        new CreateTables();
        new LogInController();
    }
}
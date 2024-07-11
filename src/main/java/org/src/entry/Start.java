package org.src.entry;

import org.mvc.controller.LogInController;
import org.mvc.model.UsersModel;
import org.mvc.gui.LogInTemplate;
import org.src.database.Schema;


public class Start {
    public static void main(String[] args) {

        /*
        Make visible to VisualVM
         */

        CustomClassLoader customLoader = new CustomClassLoader(Start.class.getClassLoader());

        try {
            // Load a class using NewClassLoader
            Class<?> schemaVM = customLoader.loadClass("org.src.database.Schema");
            Class<?> logInModelVM = customLoader.loadClass("org.mvc.model.UsersModel");
            Class<?> logInTemplateVM = customLoader.loadClass("org.mvc.gui.LogInTemplate");

            /*
          Create Database
         */
            new Schema();

        /*
          User Anmeldung Fenster
         */
            UsersModel logInModel = new UsersModel();
            LogInTemplate logInView = new LogInTemplate();
            new LogInController(logInModel, logInView);

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }


    }
}
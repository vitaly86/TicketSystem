
package org.app.entry;

import org.mvc.controller.LogInController;

import java.lang.*;
import org.mvc.model.UsersModel;
import org.mvc.gui.LogInTemplate;
import org.app.database.Schema;
import org.app.utils.profiler.CustomClassLoader;


public class Start {

    public static void main(String[] args) {

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

        // Create an instance of your custom class loader
        CustomClassLoader customClassLoader = new CustomClassLoader(ClassLoader.getSystemClassLoader());

        // Use the custom class loader to find a loaded class
        customClassLoader.findLoadedClassReflectively("org.src.database.Schema");
        customClassLoader.findLoadedClassReflectively("org.mvc.model.UsersModel");
        customClassLoader.findLoadedClassReflectively("org.mvc.gui.LogInTemplate");
        customClassLoader.findLoadedClassReflectively("org.mvc.controller.LogInController");
    }
}

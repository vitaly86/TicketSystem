//module org.src.entry {
//    requires java.base; // Explicitly requires the java.base module
//
//    // Other module declarations
//
//    // Example of opening java.lang for reflective access
//    opens java.lang;
//}

package org.src.entry;

import org.mvc.controller.LogInController;
import java.lang.reflect.Method;
import java.security.PrivilegedAction;

import org.mvc.model.UsersModel;
import org.mvc.gui.LogInTemplate;
import org.src.database.Schema;


public class Start extends ClassLoader {

    public Start(ClassLoader parent) {
        super(parent);
    }

//    // Public method to call the protected findLoadedClass method using reflection
//    public Class<?> findLoadedClass(String name) {
//        try {
//            // Get the findLoadedClass method from the ClassLoader class
//            Method method = ClassLoader.class.getDeclaredMethod("findLoadedClass", String.class);
//
//            // Make the method accessible
//            method.setAccessible(true);
//
//            // Invoke the method on the current instance with the given class name
//            return (Class<?>) method.invoke(this, name);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static void main(String[] args) {

//        Start customClassLoader = new Start(ClassLoader.getSystemClassLoader());
//
//        try {
//            // Load a class using the custom class loader
//            Class<?> loadedClass = Class.forName("java.lang.String", true, customClassLoader);
//
//            // Check if the class is loaded using the custom findLoadedClass method
//            Class<?> foundClass = customClassLoader.findLoadedClass("java.lang.String");
//
//            if (foundClass != null) {
//                System.out.println("Class found: " + foundClass.getName());
//            } else {
//                System.out.println("Class not found.");
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        /*
        Make visible to VisualVM
         */

//        CustomClassLoaderTry customLoader = new CustomClassLoaderTry(Start.class.getClassLoader());
//
//        try {
//            // Load a class using NewClassLoader
//            Class<?> schemaVM = customLoader.loadClass("org.src.database.Schema");
//            Class<?> logInModelVM = customLoader.loadClass("org.mvc.model.UsersModel");
//            Class<?> logInTemplateVM = customLoader.loadClass("org.mvc.gui.LogInTemplate");
//
//            /*
//          Create Database
//         */
//            new Schema();
//
//        /*
//          User Anmeldung Fenster
//         */
//            UsersModel logInModel = new UsersModel();
//            LogInTemplate logInView = new LogInTemplate();
//            new LogInController(logInModel, logInView);
//
//        } catch (ClassNotFoundException e) {
//            System.err.println("Class not found: " + e.getMessage());
//        }

//        try {
//            // Load the class that contains the protected final method (assuming it's loaded by system class loader)
//            Class<?> clazz = Class.forName("java.lang.ClassLoader");
//
//            // Get the declared method named "myProtectedFinalMethod" with appropriate parameter types
//            Method method = clazz.getDeclaredMethod("findLoadedClass", String.class);
//
//            // Ensure the method is accessible (bypass access checks)
//            method.setAccessible(true);
//
//        } catch (ClassNotFoundException | NoSuchMethodException e) {
//            e.printStackTrace();
//        }

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
    }
}

//module org.src.entry {
//    requires java.base; // Explicitly requires the java.base module
//
//    // Other module declarations
//
//    // Example of opening java.lang for reflective access
//    opens java.lang;
//}

package org.src.entry;

import org.jetbrains.annotations.NotNull;
import org.mvc.controller.LogInController;

import java.lang.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.mvc.model.UsersModel;
import org.mvc.gui.LogInTemplate;
import org.src.database.Schema;


public class Start {

//    public Start(ClassLoader parent) {
//        super(parent);
//    }

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

//        /*
//        Make findLoadedClass() method visible to VisualVM
//         */
//
//        try {
//            // Step 1: Get the Class object for the abstract class
//            Class<?> loaderClass = ClassLoader.class;
//
//            // Step 2: Retrieve the Method object for the static final method
//            Method loaderMethod = loaderClass.getDeclaredMethod("findLoadedClass", String.class);
//
//            // Step 3: Make the method accessible if necessary
//            loaderMethod.setAccessible(true);
//
//            // Step 4: Invoke the method (since it's static, no instance is needed)
//            loaderMethod.invoke(null, "Hello World!");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Start customLoader = new Start(ClassLoader.getSystemClassLoader());
//
//        try {
//
//            // Load a class using NewClassLoader
//            Class<?> clazzSchema = Class.forName("org.src.database.Schema", true, customLoader);
//            Class<?> clazzLogInModel = Class.forName("org.mvc.model.UsersModel", true, customLoader);
//            Class<?> clazzLogInView = Class.forName("org.mvc.gui.LogInTemplate", true, customLoader);
//            Class<?> clazzLogInController = Class.forName("org.mvc.controller.LogInController", true, customLoader);
//
//            Class<?> foundClazzSchema = customLoader.findLoadedClass("org.src.database.Schema");
//
//            // Get Constructors
//
//            // Create Database
//
//            Constructor<?> schemaConstructor = clazzSchema.getDeclaredConstructor();
//            Object schemaInstance = schemaConstructor.newInstance();
//            Schema schema = (Schema) schemaInstance;
//
//            //User Anmeldung Fenster
//
//            final Object logInControllerInstance = getObject(clazzLogInModel, clazzLogInView, clazzLogInController);
//            LogInController logInController = (LogInController) logInControllerInstance;
//
//        } catch (ClassNotFoundException e) {
//            System.err.println("Class not found: " + e.getMessage());
//        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
//            throw new RuntimeException(e);
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

    private static @NotNull Object getObject(@NotNull Class<?> clazzLogInModel, @NotNull Class<?> clazzLogInView, @NotNull Class<?> clazzLogInController)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> logInModelConstructor = clazzLogInModel.getDeclaredConstructor();
        Object logInModelInstance = logInModelConstructor.newInstance();
        UsersModel logInModel = (UsersModel) logInModelInstance;

        Constructor<?> logInViewConstructor = clazzLogInView.getDeclaredConstructor();
        Object logInViewInstance = logInViewConstructor.newInstance();
        LogInTemplate logInView = (LogInTemplate) logInViewInstance;


        Constructor<?> logInControllerConstructor = clazzLogInController.getConstructor(UsersModel.class, LogInTemplate.class);
        return logInControllerConstructor.newInstance(logInModel, logInView);
    }
}

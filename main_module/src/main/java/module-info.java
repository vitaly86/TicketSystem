module main_module {
    requires org.jetbrains.annotations;
    requires java.datatransfer;
    requires java.desktop;
    requires java.sql;
    requires java.base;
    requires mysql.connector.j;

    // Open packages to yourModule
    opens org.app.entry to java.base;
    opens org.app.database to java.base;
    opens org.mvc.gui to java.base;
    opens org.mvc.model to java.base;
    opens org.mvc.controller to java.base;
    opens org.internal.css to java.base;
    opens org.internal.html to java.base;

    // Open java.lang to yourModule
//    opens org.app.entry to java.base;

    // Exports
    exports org.app.entry;
}
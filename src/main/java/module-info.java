module org {
    requires java.datatransfer;
    requires java.desktop;
    requires java.sql;
    requires org.jetbrains.annotations;
    requires java.base;

    // Open java.lang to yourModule
    opens org.src.entry;
    opens org.mvc.gui;
    opens org.mvc.model;
    opens org.mvc.controller;
    opens org.resources.css;
    opens org.resources.html;
}
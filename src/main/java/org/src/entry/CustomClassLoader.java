package org.src.entry;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {
    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> findLoadedClass(String name) {
        return super.findLoadedClass(name);
    }
}




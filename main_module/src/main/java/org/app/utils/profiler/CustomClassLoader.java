package org.app.utils.profiler;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class CustomClassLoader extends ClassLoader{

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    public void findLoadedClassReflectively(String className) {
        try {
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(ClassLoader.class, MethodHandles.lookup());
            MethodHandle findLoadedClassHandle = lookup.findVirtual(ClassLoader.class, "findLoadedClass",
                    MethodType.methodType(Class.class, String.class));
            findLoadedClassHandle.invoke(this, className);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

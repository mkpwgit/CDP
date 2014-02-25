package com.epam.cdp.lesson1.part2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mikalai on 2/25/14.
 */
public class DynamicClassOverLoader extends ClassLoader {

    //every time jvm creates new classesHash
    private Map<String, Class> classesHash = new HashMap<String, Class>();

    public final String[] classPath;

    public DynamicClassOverLoader(String[] classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = findClass(name);
        if (resolve)
            resolveClass(result);
        return result;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class result = classesHash.get(name);

        if (result != null) {
            System.out.println("% Class " + name + " found in cache");
            return result;
        }

        File f = findFile(name.replace('.', '/'), ".class");
        System.out.println("% Class " + name + (f == null ? "" : " found in " + f));

        if (f == null) {
            //call system find class
            return findSystemClass(name);
        }

        try {
            byte[] classBytes = loadFileAsBytes(f);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Cannot load class " + name + ": " + e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Format of class file incorrect for class " + name + ": " + e);
        }
        classesHash.put(name, result);
        return result;
    }

    @Override
    protected URL findResource(String name) {
        File f = findFile(name, "");
        if (f == null)
            return null;
        try {
            return f.toURI().toURL();
        } catch (java.net.MalformedURLException e) {
            return null;
        }
    }

    private File findFile(String name, String extension) {
        for (int k=0; k<classPath.length; k++) {
            File f= new File((new File(classPath[k])).getPath()+File.separatorChar+name.replace('/',File.separatorChar)+extension);
            if (f.exists())
                return f;
        }
        return null;
    }

    public static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result= new byte[(int)file.length()];
        FileInputStream f= new FileInputStream(file);
        try {
            f.read(result,0,result.length);
        } finally {
            try {
                f.close();
            } catch (Exception e) {

            };
        }
        return result;
    }
}

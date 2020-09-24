package Helpers;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

public interface InstanceCreating {
    public <T extends CreatableFromList> T getMe(int id, String filename, int attributes, Class<T> tClass)
            throws FileNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException ;
    }

package Helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public final class FindSth implements InstanceCreating {
    public FindSth(){
    }
    public <T extends CreatableFromList> T getMe(int id, String filename, int attributes, Class<T> tClass)
            throws FileNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        BufferedReader fIS = new BufferedReader(new FileReader(filename));
        List<String> lines = fIS.lines().skip((attributes+1)*id+1).limit(attributes).collect(Collectors.toList());
        if (lines.size()<attributes)
            return null;
        Constructor<T> constructor = tClass.getConstructor(List.class);
        return constructor.newInstance(lines);
    }
}

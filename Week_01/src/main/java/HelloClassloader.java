import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;


public class HelloClassloader extends ClassLoader {


    public static void main(String[] args) {
        try {
            Class<?> helloClass = new HelloClassloader().findClass("Hello");
            Method method = helloClass.getDeclaredMethod("hello");
            method.invoke(helloClass.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = toByteArray();
        return defineClass(name, bytes, 0, bytes.length);
    }


    private byte[] toByteArray() {
        String path = this.getClass().getResource("/Hello.xlass").getPath();
        URI uri = new File(path).toURI();
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(uri));
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }


}

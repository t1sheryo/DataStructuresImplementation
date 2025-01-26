import java.lang.reflect.Array;
import java.util.Arrays;

public class Test {
    public static void main(String[] args){

        StringBuilder[] array = {new StringBuilder("adaf"), new StringBuilder("afa")};
        StringBuilder[] copy = Arrays.copyOf(array, 2);
        copy[0].insert(0, new StringBuilder("pisya"));

        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(copy));
    }
}

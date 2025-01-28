import java.lang.reflect.Array;
import java.util.Arrays;
import ArrayListImpl.*;

public class Test {
    public static void main(String[] args){

        ArrayListClass<Integer> list = new ArrayListClass<>(5);
        list.add(0, 2);
        //System.out.println(list.get(1));
        System.out.println(list.get(0));
    }
}

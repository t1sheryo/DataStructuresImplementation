import java.util.Arrays;

public class Test {
    public static void main(String[] args){

        int[] array = {1, 2, 3};
        int[] array2 = {4, 5, 6, 7, 8};
        System.arraycopy(array, 0, array2, 2, 2);
//        array2[3] = 16;
//        array2[0] = 99;

        array2[2] = 9;
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(array2));
    }
}

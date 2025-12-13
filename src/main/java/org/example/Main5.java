/**
 * Что выведется на экран?
 */

package org.example;

public class Main5 {
    public static void main(String[] args) {
        exceptionTest();
    }

    private static int exceptionTest() {
        int count = 1000;
        try {
            while (true) {
                Object[][] array = new Object[count][count];
                count *= 100;
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemory");
        } catch (Exception ex) {
            System.out.println("Exception");
        }
        count /= 100;
        System.out.println("MaxCommandHash passMap MACCUBB " + count);
        try {
            RuntimeException[][] array = new RuntimeException[count][count];
            array[0][1] = new NullPointerException();
        } catch (Exception ex) {
            System.out.println("Exception 2");
        } catch (Error e) {
            System.out.println("Error");
        }
        return count;
    }
}

package org.example.junit;

/**
 *
 */
public class SomeClass {
    public static int add(int x, int y) {
        if (Configuration.isEnabled()) {
            return x + y;
        }
        return 0;
    }


    public int add(int x, int y, int z) {
        int tempTotal = add(x, y);
        if (Configuration.isEnabled()) {
            return tempTotal + z;
        }
        return 0;
    }
}

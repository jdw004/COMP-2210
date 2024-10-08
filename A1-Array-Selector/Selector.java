import java.util.ArrayList;
import java.util.Arrays;

/**
 * Defines a library of selection methods
 * on arrays of ints.
 *
 * @author   YOUR NAME (jdw0156@auburn.edu)
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  TODAY
 *
 */
public final class Selector {

    /**
     * Can't instantiate this class.
     * <p>
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     */
    private Selector() {
    }


    /**
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int min(int[] a) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must have elements.");
        }
        int min = a[0];
        for (int x : a) {
            if (x < min) {
                min = x;
            }
        }
        return min;
    }


    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int max(int[] a) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must have elements.");
        }
        int max = a[0];
        for (int x : a) {
            if (x > max) {
                max = x;
            }
        }
        return max;
    }


    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmin(int[] a, int k) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must have elements.");
        }

        int[] copyRay = Arrays.copyOf(a, a.length);
        Arrays.sort(copyRay);

        int z = 1;
        int i = 0;

        if (k == 1) {
            return copyRay[0];
        }

        while (i < copyRay.length - 1 && z < k) {
            if (copyRay[i] != copyRay[++i]) {
                z++;
            }
        }

        if (z != k) {
            throw new IllegalArgumentException(k + "th min does not exist.");
        }

        return copyRay[i];
    }



    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmax(int[] a, int k) {
        if (a == null || a.length == 0 || k <= 0 || k > a.length) {
            throw new IllegalArgumentException("Invalid input.");
        }

        int[] copyRay = Arrays.copyOf(a, a.length);
        Arrays.sort(copyRay);

        int z = 1;
        int i = copyRay.length - 1;

        if (k == 1) {
            return copyRay[i];
        }

        while (i > 0) {
            if (copyRay[i] != copyRay[i - 1]) {
                z++;
                if (z == k) {
                    return copyRay[i - 1];
                }
            }
            i--;
        }

        throw new IllegalArgumentException(k + "th max does not exist.");
    }



    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     */
    public static int[] range(int[] a, int low, int high) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must have elements.");
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < a.length; i++) {
            if ((a[i] >= low) && (a[i] <= high)) {
                result.add(a[i]);
            }
        }

        int[] realResult = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            realResult[i] = result.get(i);
        }
        return realResult;
    }


    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int ceiling(int[] a, int key) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must have elements.");
        }

        int min = Integer.MAX_VALUE;

        for (int x : a) {
            if ((x >= key) && (x < min)) {
                min = x;
            }
        }
        if (min == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("No value");
        }
        return min;
    }


    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int floor(int[] a, int key) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must have elements.");
        }

        int max = Integer.MIN_VALUE;

        for (int x : a) {
            if ((x <= key) && (x > max)) {
                max = x;
            }
        }
        if (max == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("No value");
        }
        return max;
    }
}

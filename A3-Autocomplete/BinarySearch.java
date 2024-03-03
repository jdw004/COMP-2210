import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null) {
            throw new NullPointerException("Key[] must not be null");
        }
        if(key == null) {
            throw new NullPointerException("Key must not be null");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator must not be null");
        }

        int left = 0;
        int right = a.length;
        int mid;
        int firstIndex = -1;

        while(left <= right) {
            mid = (right + left) / 2;

            if (comparator.compare(a[mid], key) > 0) {

                    right = mid - 1;
                }
            else if (comparator.compare(a[mid], key) < 0) {
                    left = mid + 1;
                } else {
                    firstIndex = mid;
                    left = mid - 1;
                }
        }
        return firstIndex;
    }

    /**
     * Returns the index of the last key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
            if (a == null) {
                throw new NullPointerException("Key[] must not be null");
            }
            if(key == null) {
                throw new NullPointerException("Key must not be null");
            }
            if (comparator == null) {
                throw new NullPointerException("comparator must not be null");
            }

            int left = 0;
            int right = a.length;
            int mid;
            int lastIndex = -1;

            while(left <= right) {
                mid = (right + left) / 2;

                if (comparator.compare(a[mid], key) > 0) {

                        right = mid - 1;
                    }
                else if (comparator.compare(a[mid], key) < 0) {
                        left = mid + 1;
                    }
                else {
                        lastIndex = mid;
                        left = mid + 1;
                    }
                }

                return lastIndex;
        }


}

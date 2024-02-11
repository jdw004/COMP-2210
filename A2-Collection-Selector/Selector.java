import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  John Welch (jdw0156@auburn.edu)
 *
 */
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
    private Selector() { }


    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the minimum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T min(Collection<T> c, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException("Array must have elements.");
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException("Collection must have elements");
        }

        Iterator<T> itr = c.iterator();
        T min = itr.next();

        while (itr.hasNext()) {
            T x = itr.next();

            if (comp.compare(x, min) < 0) {
                min = x;
            }
        }
        return min;
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the maximum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T max(Collection<T> c, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException("Array must have elements.");
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException("Collection must have elements");
        }

        Iterator<T> itr = c.iterator();
        T max = itr.next();

        while (itr.hasNext()) {
            T x = itr.next();
            if (comp.compare(x, max) > 0) {
                max = x;
            }
        }
        return max;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth minimum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmin(Collection<T> c, int k, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException("Array must have elements.");
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException("Collection must have elements");
        }
        if (k < 1 || k > c.size()) {
            throw new NoSuchElementException("K is not valid");
        }

        ArrayList<T> copyRay = new ArrayList<T>();
        Iterator<T> itr = c.iterator();
        while (itr.hasNext()) {
            copyRay.add(itr.next());
        }
        java.util.Collections.sort(copyRay, comp);

        if (k == 1) {
            return copyRay.get(0);
        }

        T x = copyRay.get(0);
        T kmin = null;
        int count = 1;

        for (int i = 1; i < copyRay.size(); i++) {
            if (!copyRay.get(i).equals(x)) {
                count++;
                if (k == count) {
                    kmin = copyRay.get(i);
                }
            }
            x = copyRay.get(i);
        }

        if (count < k) {
            throw new NoSuchElementException(k + "th min is not valid.");
        }
        return kmin;

    }



    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth maximum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmax(Collection<T> c, int k, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException("Array must have elements.");
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException("Collection must have elements");
        }
        if (k < 1 || k > c.size()) {
            throw new NoSuchElementException("K is not valid");
        }

        ArrayList<T> copyRay = new ArrayList<T>();
        Iterator<T> itr = c.iterator();
        while (itr.hasNext()) {
            copyRay.add(itr.next());
        }
        java.util.Collections.sort(copyRay, comp);

        if (k == 1) {
            return copyRay.get(copyRay.size()-1);
        }

        T x = copyRay.get(copyRay.size()-1);
        T kmax = null;
        int count = 1;

        for (int i = copyRay.size()-1; i >= 0; i--) {
            if (!copyRay.get(i).equals(x)) {
                count++;
                if (k == count) {
                    kmax = copyRay.get(i);
                }
            }
            x = copyRay.get(i);
        }

        if (count < k) {
            throw new NoSuchElementException(k + "th max is not valid.");
        }
        return kmax;

    }



    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the range values are selected
     * @param low     the lower bound of the range
     * @param high    the upper bound of the range
     * @param comp    the Comparator that defines the total order on T
     * @return        a Collection of values between low and high
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> Collection<T> range(Collection<T> c, T low, T high, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException("Array must have elements.");
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException("Collection must have elements");
        }

        ArrayList<T> result = new ArrayList<T>();
        Iterator<T> itr = c.iterator();

        while(itr.hasNext()) {
            T x = itr.next();
            if ((comp.compare(x,low) >= 0) && (comp.compare(x,high) <= 0)) {
                result.add(x);
            }
        }
        if (result.isEmpty()) {
            throw new NoSuchElementException("None of the values are within the specified range");
        }
        return result;
    }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the ceiling value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the ceiling value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T ceiling(Collection<T> c, T key, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException("Array must have elements.");
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException("Collection must have elements");
        }

        T min = null;
        Iterator<T> itr = c.iterator();
        boolean hasFound = false;

        while(itr.hasNext()) {
            T x = itr.next();
            if ((comp.compare(x, key) >= 0) && !(hasFound)) {
                min = x;
                hasFound = true;
            }
            else if ((comp.compare(x, key) >= 0) && (comp.compare(x,min) < 0)) {
                min = x;
            }
        }
        if (min == null) {
            throw new NoSuchElementException("No value");
        }
        return min;
    }

    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the floor value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the floor value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T floor(Collection<T> c, T key, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException("Array must have elements.");
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException("Collection must have elements");
        }

        T max = null;
        Iterator<T> itr = c.iterator();
        boolean hasFound = false;

        while(itr.hasNext()) {
            T x = itr.next();
            if ((comp.compare(x, key) <= 0) && !(hasFound)) {
                max = x;
                hasFound = true;
            }
            else if ((comp.compare(x, key) <= 0) && (comp.compare(x,max) > 0)) {
                max = x;
            }
        }
        if (max == null) {
            throw new NoSuchElementException("No value");
        }
        return max;
    }

}

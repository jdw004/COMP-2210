import java.util.Arrays;
import java.util.Comparator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Autocomplete.
 */
public class Autocomplete {

    private Term[] termArray;

    /**
     * Initializes a data structure from the given array of terms.
     * This method throws a NullPointerException if terms is null.
     */
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new NullPointerException("Terms cannot be null");
        }
        Arrays.sort(terms);
        termArray = terms;
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * This method throws a NullPointerException if prefix is null.
     */
    public Term[] allMatches(String prefix) {
        if(prefix == null) {
            throw new NullPointerException("prefix must not be null");
        }

        Term temp = new Term(prefix, 0);

        int i = BinarySearch.firstIndexOf(termArray, temp, Term.byPrefixOrder(prefix.length()));
        int j = BinarySearch.lastIndexOf(termArray, temp, Term.byPrefixOrder(prefix.length()));
        if (i == - 1 || j == - 1) {
            throw new java.lang.NullPointerException();
        }

        int length = prefix.length();
        Term term = new Term(prefix, 0);
        Comparator<Term> comp = termArray[0].byPrefixOrder(length);
        int firstIndex = BinarySearch.<Term>firstIndexOf(termArray, term, comp);
        int lastIndex = BinarySearch.<Term>lastIndexOf(termArray, term, comp);
        if (firstIndex == -1 && lastIndex == -1) {
            return new Term[0];
        }
        int matchesSize = (lastIndex - firstIndex) + 1;
        Term[] allMatches = new Term[matchesSize];

        allMatches = Arrays.copyOfRange(termArray, firstIndex, lastIndex+1);
        Comparator<Term> comp2 = allMatches[0].byDescendingWeightOrder();
        Arrays.sort(allMatches, comp2);
        return allMatches;
    }


}

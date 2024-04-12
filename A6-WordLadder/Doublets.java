import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface.
 *
 * @author John Welch (jdw0156@auburn.edu)
 */
public class Doublets implements WordLadderGame {

    TreeSet<String> lexicon;
    List<String> NADA_LADDER = new ArrayList<>();

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
    // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {
        try {
            lexicon = new TreeSet<String>();
            Scanner s =
                    new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                lexicon.add(str.toLowerCase());
                s.nextLine();
            }
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }


    //////////////////////////////////////////////////////////////
    // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
    //////////////////////////////////////////////////////////////
    private class Node {
        String position;
        Node prev;

        public Node(String x, Node prev) {
            position = x;
            this.prev = prev;
        }
    }
    public int getWordCount() {
        return lexicon.size();
    }

    public boolean isWord(String str) {
        return lexicon.contains(str);
    }

    public int getHammingDistance(String str1, String str2) {
        int i = 0;
        int count = 0;
        int size1 = str1.length();
        int size2 = str2.length();

        if (size1 != size2) {
            return -1;
        }

        while (i < size1) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count += 1;
            }
            i += 1;
        }
        return count;
    }

    public List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<String>();

        if (word == null) {
            return NADA_LADDER;
        }

        for(String x: lexicon) {
            if(getHammingDistance(x, word) == 1) {
                neighbors.add(x);
            }
        }
        return neighbors;

    }

    public boolean isWordLadder(List<String> sequence) {
        if (sequence == null || sequence.isEmpty()) {
            return false;
        }
        for(int i = 0; i < sequence.size()-1; i++) {
            if(!isWord(sequence.get(i))){
                return false;
            } else if (!isWord(sequence.get(i+1))) {
                return false;
            }
            if (getHammingDistance(sequence.get(i), sequence.get(i+1)) != 1) {
                return false;
            }
        }
        return true;
    }



    /**
     * Returns a minimum-length word ladder from start to end. If multiple
     * minimum-length word ladders exist, no guarantee is made regarding which
     * one is returned. If no word ladder exists, this method returns an empty
     * list.
     *
     * Breadth-first search must be used in all implementing classes.
     *
     * @param  start  the starting word
     * @param  end    the ending word
     * @return        a minimum length word ladder from start to end
     */
    public List<String> getMinLadder(String start, String end) {
        List<String> ladder = new ArrayList<String>();
        if (start.equals(end)) {
            ladder.add(start);
            return ladder;
        }
        else if (start.length() != end.length()) {
            return NADA_LADDER;
        }
        else if (!isWord(start) || !isWord(end)) {
            return NADA_LADDER;
        }

        Deque<Node> queue = new ArrayDeque<>();
        TreeSet<String> myTree = new TreeSet<>();

        myTree.add(start);
        queue.addLast(new Node(start, null));
        while(!queue.isEmpty()) {
            Node x = queue.removeFirst();
            String position = x.position;

            for (String neighbor1 : getNeighbors(position)) {
                if (!myTree.contains(neighbor1)) {
                    myTree.add(neighbor1);
                    queue.addLast(new Node(neighbor1, x));
                }
                if (neighbor1.equals(end)) {

                    Node m = queue.removeLast();

                    while (m != null) {
                        ladder.add(0, m.position);
                        m = m.prev;
                    }
                    return ladder;
                }
            }
        }
        return NADA_LADDER;
    }
}

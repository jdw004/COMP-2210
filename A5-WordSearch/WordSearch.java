import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class WordSearch implements WordSearchGame{
    private TreeSet<String> lexicon = new TreeSet<String>();
    private String[][] board = new String[][] {
            {"E", "E", "C", "A"},
            {"A", "L", "E", "P"},
            {"H","N", "B", "O"},
            {"Q", "T", "T", "Y"}
    };
    private int squareSize = 4;
    private boolean lexiconAdded = false;

    @Override
    public void loadLexicon(String fileName) {
        if(fileName == null) {
            throw new IllegalArgumentException();
        }
        File file = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(file);
            while(s.hasNext()) {
                lexicon.add(s.next().toLowerCase());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        } finally {
            if(s != null) {
                s.close();
            }
        }
        lexiconAdded = true;
    }


    @Override
    public void setBoard(String[] letterArray) {
        if (letterArray == null) {
            throw new IllegalArgumentException("LetterArray must not be null");
        }

        double boardLen = Math.sqrt((double) letterArray.length);
        if (boardLen == Math.round(boardLen)) {
            int boardLenInt = (int) boardLen;
            board = new String[boardLenInt][boardLenInt];
            for (int i = 0; i < boardLenInt; i++) {
                for (int j = 0; j < boardLenInt; j++) {
                    board[i][j] = letterArray[i*boardLenInt + j];
                }
            }
            squareSize = boardLenInt;
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public String getBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < squareSize; i++) {
            for(int x = 0; x < squareSize; x++) {
                sb.append(board[i][x] + " ");
                if(x == squareSize - 1) {
                    sb.append("br ");
                }
            }
        }
        String s = sb.toString();
        return s;
    }

    /**
     * Retrieves all scorable words on the game board, according to the stated game
     * rules.
     *
     * @param minimumWordLength The minimum allowed length (i.e., number of
     *     characters) for any word found on the board.
     * @return java.util.SortedSet which contains all the words of minimum length
     *     found on the game board and in the lexicon.
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException if loadLexicon has not been called.
     */

    public SortedSet<String> getAllValidWords(int minimumWordLength) {
        if(!lexiconAdded) {
            throw new IllegalStateException("Lex must be added");
        }
        if(minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        boolean[][] visited = new boolean[squareSize][squareSize];
        TreeSet<String> words = new TreeSet<String>();
        for(int i = 0; i < squareSize; i++) {
            for(int j = 0; j < squareSize; j++) {
                getWords(minimumWordLength,i,j,"",visited,words);
            }
        }
        return words;
    }
    @Override
    public SortedSet<String> getAllScorableWords(int minimumWordLength) {
        if(!lexiconAdded) {
            throw new IllegalStateException("Lex must be added");
        }
        if(minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        boolean[][] visited = new boolean[squareSize][squareSize];
        TreeSet<String> words = new TreeSet<String>();
        for(int i = 0; i < squareSize; i++) {
            for(int j = 0; j < squareSize; j++) {
                getWords(minimumWordLength,i,j,"",visited,words);
            }
        }
        return words;
    }
    private void getWords(int minLength, int i, int x, String str, boolean[][] vis, TreeSet<String> word) {
        vis[i][x] = true;
        str = str + board[i][x];
        if(isValidWord(str) && str.length() >= minLength) {
            word.add(str);
        }
        if(isValidPrefix(str)) {
            for (int r = i-1; r <= i+1 && r < squareSize; r++) {
                for (int c = x-1; c <= x+1 && c < squareSize; c++) {
                    if (r >= 0 && c >= 0 && !vis[r][c]) {
                        getWords(minLength, r, c, str, vis, word);
                    }
                }
            }
        }
        str = str.substring(0, str.length()-1);
        vis[i][x] = false;
    }

    /**
     * Computes the cummulative score for the scorable words in the given set.
     * To be scorable, a word must (1) have at least the minimum number of characters,
     * (2) be in the lexicon, and (3) be on the board. Each scorable word is
     * awarded one point for the minimum number of characters, and one point for
     * each character beyond the minimum number.
     *
     * @param words The set of words that are to be scored.
     * @param minimumWordLength The minimum number of characters required per word
     * @return the cummulative score of all scorable words in the set
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
        if(!lexiconAdded) {
            throw new IllegalStateException();
        }
        if(minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        Iterator<String> itr = words.iterator();
        int total = 0;
        while(itr.hasNext()) {
            String s = itr.next();
            if(s.length() >= minimumWordLength && isValidWord(s) && !isOnBoard(s).isEmpty()) {
                total += 1;
                if(s.length() > minimumWordLength) {
                    total += s.length() - minimumWordLength;
                }
            }
        }
        return total;
    }

    /**
     * Determines if the given word is in the lexicon.
     *
     * @param wordToCheck The word to validate
     * @return true if wordToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public boolean isValidWord(String wordToCheck) {
        wordToCheck = wordToCheck.toLowerCase();
        if(wordToCheck == null) {
            throw new IllegalArgumentException();
        }
        else if(!lexiconAdded) {
            throw new IllegalStateException();
        }
        else if(lexicon.contains(wordToCheck)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Determines if there is at least one word in the lexicon with the
     * given prefix.
     *
     * @param prefixToCheck The prefix to validate
     * @return true if prefixToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if prefixToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public boolean isValidPrefix(String prefixToCheck){
        prefixToCheck = prefixToCheck.toLowerCase();

        if(prefixToCheck.equals(null)) {
            throw new IllegalArgumentException();
        }

        if(!lexiconAdded) {
            throw new IllegalStateException();
        }

        String s = lexicon.ceiling(prefixToCheck);
        if(s.equals(null) || !s.startsWith(prefixToCheck)) {
            return false;
        }
        else if(s.startsWith(prefixToCheck) || s.equals(prefixToCheck)) {
            return true;
        }
        return false;
    }

    /**
     * Determines if the given word is in on the game board. If so, it returns
     * the path that makes up the word.
     * @param wordToCheck The word to validate
     * @return java.util.List containing java.lang.Integer objects with  the path
     *     that makes up the word on the game board. If word is not on the game
     *     board, return an empty list. Positions on the board are numbered from zero
     *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
     *     board, the upper left position is numbered 0 and the lower right position
     *     is numbered N^2 - 1.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public List<Integer> isOnBoard(String wordToCheck){
        if(wordToCheck.equals(null)) {
            throw new IllegalArgumentException();
        }
        if(!lexiconAdded) {
            throw new IllegalStateException();
        }
        boolean[][] visited = new boolean[squareSize][squareSize];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int a = 0; a < squareSize; a++) {
            for(int b = 0; b < squareSize; b++) {
                wordOnBoard(a,b,"",wordToCheck,visited,list);
            }
        }

        return list;
    }
    private void wordOnBoard(int i, int x, String str, String found, boolean[][] vis, ArrayList<Integer> word) {
        vis[i][x] = true;
        str = str + board[i][x];
        if(str.equals(found)) {
            for(int j = 0; j < squareSize; j++) {
                for(int k = 0; k < squareSize; k++) {
                    if(vis[j][k] == true) {
                        word.add(j * squareSize + k);
                    }
                }
            }
        }
        if(found.startsWith(str)) {
            for (int r = i-1; r <= i+1 && r < squareSize; r++) {
                for (int c = x-1; c <= x+1 && c < squareSize; c++) {
                    if (r >= 0 && c >= 0 && !vis[r][c]) {
                        wordOnBoard(r, c, str, found, vis, word);
                    }
                }
            }
        }
// Erase current character from string and mark visited
// of current cell as false
        str = str.substring(0, str.length()-1);
        vis[i][x] = false;
    }
    private class ComparePrefix implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return 0;
        }

    }

}

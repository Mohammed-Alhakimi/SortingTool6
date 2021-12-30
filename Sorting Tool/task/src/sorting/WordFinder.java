package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class WordFinder extends Finder<String> {
    private String longestWord;

    @Override
    public String findHighestValue() {
        String longestWord = listOfItems.get(0);
        for (String s : listOfItems) {
            if (s.length() > longestWord.length()) {
                longestWord = s;
            }
        }
        this.longestWord = longestWord;
        return longestWord;
    }

    @Override
    public int howManyTimeOccurred() {
        int counter = 0;
        for (String s : listOfItems
        ) {
            if (s.equals(longestWord)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String sortByCount() {
        for (String s : listOfItems
        ) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        ArrayList<String[]> list = new ArrayList<>();

        int index = 0;
        for (String s : map.keySet()
        ) {
            list.add(index, new String[]{s, map.get(s).toString()});
        }
        list.sort(Comparator.comparing(o -> o[0]));

        list.sort((o1, o2) -> {
            if (Integer.parseInt(o1[1]) > Integer.parseInt(o2[1]))
                return 1;
            else if (Integer.parseInt(o1[1]) == Integer.parseInt(o2[1]))
                return 0;
            else return -1;
        });
        StringBuilder sb = new StringBuilder();
        for (String[] array : list
        ) {
            sb.append(array[0] + ": " + array[1] + " time(s), " + getPercentage(Integer.parseInt(array[1])) + "%" + "\n");
        }
        return sb.toString();
    }

    public WordFinder(ArrayList<String> listOfItems, TreeMap<String, Integer> map) {
        super(listOfItems, map);
    }

    @Override
    public void scanAndAdd(boolean importFromFile, String path) throws FileNotFoundException {
        if (importFromFile) {
            Scanner s = new Scanner(new File(path));
            while (s.hasNext()) {
                String word = s.next();
                super.listOfItems.add(word);
            }
            s.close();
        }
    }

    @Override
    public void scanAndAdd() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String word = scanner.next();
            super.listOfItems.add(word);
        }
    }

    @Override
    public String displayInfo() {
        return String.format("Total words: %d."
                , listOfItems.size());
    }

    @Override
    public String displayInfoSorting(String sortingType) {
        sort();
        StringBuilder sb = new StringBuilder();
        for (String s : listOfItems
        ) {
            sb.append(s).append(" ");
        }
        return "Sorted data: " + sb;
    }

    @Override
    public void sort() {
        Collections.sort(this.listOfItems);
    }

    @Override
    public void sort(String type) {

    }
}
package sorting;

import java.util.ArrayList;

import java.util.TreeMap;

public class FinderFactory {

    public Finder<?> makeFinder(String type) {
        if (type.equalsIgnoreCase("long")) {
            TreeMap<Long, Integer> map = new TreeMap<>();
            ArrayList<Long> list = new ArrayList<>();
            return new HighestNumberFinder(list, map);
        } else if (type.equalsIgnoreCase("word")) {
            TreeMap<String, Integer> map = new TreeMap<>();
            ArrayList<String> list = new ArrayList<>();
            return new WordFinder(list, map);
        } else if (type.equalsIgnoreCase("line")) {
            TreeMap<String, Integer> map = new TreeMap<>();
            ArrayList<String> list = new ArrayList<>();
            return new LineFinder(list, map);
        } else return null;
    }
}
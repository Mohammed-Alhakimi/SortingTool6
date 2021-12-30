package sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class LineFinder extends WordFinder {

    public LineFinder(ArrayList<String> listOfItems, TreeMap<String, Integer> map) {
        super(listOfItems, map);
    }

    @Override
    public void scanAndAdd() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            super.listOfItems.add(line);
        }
    }

    @Override
    public String displayInfoSorting(String sortingType) {
        sort();
        StringBuilder sb = new StringBuilder();
        for (String s : listOfItems
        ) {
            sb.append(s).append("\n");
        }
        return "Sorted data:\n" + sb;
    }

    @Override
    public String displayInfo() {
        return String.format("Total lines: %d."
                , listOfItems.size());
    }

    @Override
    public void scanAndAdd(boolean importFromFile, String path) throws FileNotFoundException {
        if (importFromFile) {
            Scanner s = new Scanner(new File(path));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                super.listOfItems.add(line);
            }
            s.close();
        }
    }
}
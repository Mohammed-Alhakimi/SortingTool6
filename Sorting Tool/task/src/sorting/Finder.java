package sorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public abstract class Finder<T> {

    public ArrayList<T> listOfItems;
    public TreeMap<T, Integer> map;

    public Finder(ArrayList<T> listOfItems, TreeMap<T, Integer> map) {
        this.listOfItems = listOfItems;
        this.map = map;
    }

    public Finder(ArrayList<T> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public abstract void scanAndAdd(boolean importFromFile, String path) throws IOException;

    public abstract void scanAndAdd();


    public abstract T findHighestValue();

    public abstract int howManyTimeOccurred();

    public abstract String sortByCount();

    public abstract String displayInfo();

    public abstract String displayInfoSorting(String sortingType);

    public int getPercentage(Integer i) {
        int percentage;
        percentage = (i * 100) / listOfItems.size();
        return percentage;
    }

    public abstract void sort();

    public abstract void sort(String type);
}
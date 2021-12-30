package sorting;

import java.io.*;
import java.util.*;

public class HighestNumberFinder extends Finder<Long> {
    private Long highestNumber;

    public HighestNumberFinder(ArrayList<Long> listOfItems, TreeMap<Long, Integer> map) {
        super(listOfItems, map);
    }

    @Override
    public void scanAndAdd(boolean importFromFile, String path) throws FileNotFoundException {
        if (importFromFile) {
            Scanner s = new Scanner(new File(path));
            while (s.hasNextLine()) {
                try {
                    long number = s.nextLong();
                    super.listOfItems.add(number);
                } catch (InputMismatchException e) {
                    String error = s.next();
                    System.out.println("\"" + error + "\" is not a number! it will be skipped!");
                }
                s.close();
            }
        }
    }

    @Override
    public void scanAndAdd() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLong()) {
            try {
                long number = scanner.nextLong();
                super.listOfItems.add(number);
            } catch (InputMismatchException e) {
                String error = scanner.next();
                System.out.println("\"" + error + "\" is not a number! it will be skipped!");
            }
        }
    }

    @Override
    public int howManyTimeOccurred() {
        int counter = 0;
        for (Long l : listOfItems
        ) {
            if (l.equals(this.highestNumber)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String sortByCount() {
        for (Long l : listOfItems
        ) {
            map.put(l, map.getOrDefault(l, 0) + 1);
        }


        ArrayList<String[]> list = new ArrayList<>();
        int index = 0;
        for (Long l : map.keySet()
        ) {
            list.add(index, new String[]{l.toString(), map.get(l).toString()});
        }
        list.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (Integer.parseInt(o1[0]) > Integer.parseInt(o2[0]))
                    return 1;
                else if (Integer.parseInt(o1[0]) == Integer.parseInt(o2[0]))
                    return 0;
                else return -1;
            }
        });
        list.sort((o1, o2) -> {
            if (Integer.parseInt(o1[1]) > Integer.parseInt(o2[1]))
                return 1;
            else if (Integer.parseInt(o1[1]) == Integer.parseInt(o2[1]))
                return 0;
            else return -1;
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                sb.append(list.get(i)[0] + ": " + list.get(i)[1] + " time(s), " + getPercentage(Integer.parseInt(list.get(i)[1])) + "%\n");
            } else {
                sb.append(list.get(i)[0] + ": " + list.get(i)[1] + " time(s), " + getPercentage(Integer.parseInt(list.get(i)[1])) + "%");
            }
        }
        return sb.toString();
    }

    @Override
    public String displayInfo() {
        return String.format("Total numbers: %d.",
                listOfItems.size());
    }

    @Override
    public String displayInfoSorting(String sortingType) {
        sort();
        StringBuilder sb = new StringBuilder();
        for (Long l : listOfItems
        ) {
            sb.append(l).append(" ");
        }
        return "Sorted data: " + sb;
    }


    @Override
    public void sort() {
        Collections.sort(this.listOfItems);
    }


    @Override
    public Long findHighestValue() {
        Long highestNumber = listOfItems.get(0);
        for (Long l : listOfItems) {
            if (l > highestNumber) {
                highestNumber = l;
            }
        }
        this.highestNumber = highestNumber;
        return highestNumber;
    }

    public void sort(String typeOfSort) {
        if (typeOfSort.equalsIgnoreCase("insertion")) {
            for (int i = 1; i < listOfItems.size(); i++) {
                long currentValue = listOfItems.get(i);
                int j = i - 1;
                while (j >= 0 && listOfItems.get(j) > currentValue) {
                    listOfItems.set(j + 1, listOfItems.get(j));
                    j--;
                }
                listOfItems.set(j + 1, currentValue);
            }
        } else if (typeOfSort.equalsIgnoreCase("mergesort")) {
            long[] array = new long[listOfItems.size()];

            for (int i = 0; i < listOfItems.size(); i++) {
                array[i] = listOfItems.get(i);
            }
            mergeSort(array);
            int x = 0;
            for (long l : array
            ) {
                listOfItems.set(x, l);
                x++;
            }
        } else {
            sort();
        }
    }

    private void mergeSort(long[] array) {
        int listSize = array.length;
        if (listSize < 2) {
            return;
        }
        int middleIndex = listSize / 2;

        long[] leftArray = new long[middleIndex];
        long[] rightArray = new long[listSize - middleIndex];

        for (int i = 0; i < middleIndex; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middleIndex; i < listSize; i++) {
            rightArray[i - middleIndex] = array[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(array, leftArray, rightArray);
    }

    private void merge(long[] original, long[] leftArray, long[] rightArray) {
        int leftArraySize = leftArray.length;
        int rightArraySize = rightArray.length;
        int i = 0, j = 0, k = 0;
        while (i < leftArraySize && j < rightArraySize) {
            if (leftArray[i] <= rightArray[j]) {
                original[k] = leftArray[i];
                i++;
            } else {
                original[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < leftArraySize) {
            original[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < rightArraySize) {
            original[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
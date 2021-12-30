package sorting;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        FinderFactory factory = new FinderFactory();
        Finder finder = null;
        boolean argsPassed = args.length > 0;
        if (argsPassed) {
            String[] arrayOfArgs = new String[args.length];
            for (int i = 0; i < args.length; i++) {
                arrayOfArgs[i] = args[i];
            }
            List<String> listOfArgs = List.of(arrayOfArgs);
            checkValidParams(listOfArgs);
            if (listOfArgs.contains("-dataType")) {
                try {
                    if (arrayOfArgs[listOfArgs.indexOf("-dataType") + 1].matches("long||word||line")) {
                        finder = factory.makeFinder(arrayOfArgs[listOfArgs.indexOf("-dataType") + 1]);
                    } else {
                        System.out.println("No data type defined!");
                        System.exit(0);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No data type defined!");
                    System.exit(0);
                }
            }
            if (finder == null) {
                finder = factory.makeFinder("word");
            }
            if (listOfArgs.contains("-inputFile")) {
                try {
                    finder.scanAndAdd(true, "./" + listOfArgs.get(listOfArgs.indexOf("-inputFile")) + 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No Input file specified");
                    System.exit(0);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                    finder.scanAndAdd();
                }
            } else {
                finder.scanAndAdd();
            }
            if (listOfArgs.contains("-sortingType")) {
                try {
                    if (arrayOfArgs[listOfArgs.indexOf("-sortingType") + 1].equalsIgnoreCase("byCount")) {
                        System.out.println(finder.displayInfo());
                        System.out.println(finder.sortByCount());
                    } else if (arrayOfArgs[listOfArgs.indexOf("-sortingType") + 1].equalsIgnoreCase("natural")) {
                        System.out.println(finder.displayInfo());
                        System.out.println(finder.displayInfoSorting(""));
                    } else {
                        System.out.println("Wrong sorting type defined!");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No sorting type defined!");
                }
            } else {
                System.out.println(finder.displayInfo());
                System.out.println(finder.displayInfoSorting(""));
            }
            if (listOfArgs.contains("-outputFile")) {
                try {
                    if (listOfArgs.contains("byCount")) {

                        outPutToFile(("./" + listOfArgs.get(listOfArgs.indexOf("-outputFile") + 1)), finder.displayInfo());
                        outPutToFile("./" + listOfArgs.get(listOfArgs.indexOf("-outputFile") + 1), finder.sortByCount());
                    } else {
                        outPutToFile(listOfArgs.get(listOfArgs.indexOf("-outputFile") + 1), finder.displayInfo());
                        outPutToFile(listOfArgs.get(listOfArgs.indexOf("-outputFile") + 1), finder.displayInfoSorting(""));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("No file path entered");
                    System.exit(0);
                } catch (FileNotFoundException e) {
                    System.out.println("Output file not found");
                }
            }
        } else {
            finder = factory.makeFinder("word");
            finder.scanAndAdd();
            System.out.println(finder.displayInfo());
            System.out.println(finder.displayInfoSorting(""));
        }
    }

    private static void outPutToFile(String path, String whatToWrite) throws IOException {
        OutputStream stream = new BufferedOutputStream
                (new FileOutputStream(path, true));
        byte[] array = whatToWrite.getBytes();
        stream.write(array);
        stream.close();
    }

    private static void checkValidParams(List<String> l) {
        for (String s : l
        ) {
            if (!s.matches("-dataType||-sortingType||natural||byCount||long||-inputFile||-outputFile||word||line||.*/*.*")) {
                System.out.println("\"" + s + "\" is not a valid parameter. It will be skipped.");
            }
        }
    }
}
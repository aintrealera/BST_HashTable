import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int REPETITIONS = 5;

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000};

        try (PrintWriter writer = new PrintWriter(
                new FileWriter("results.csv"))) {

            writer.println(
                    "Elements," +
                            "BST Insert (ms)," +
                            "Hash Insert (ms)," +
                            "BST Search (ms)," +
                            "Hash Search (ms)," +
                            "BST Delete (ms)," +
                            "Hash Delete (ms)"
            );

            System.out.println(
                    "BST vs Hash Table Performance Analysis"
            );

            for (int size : sizes) {
                double bstInsert = 0;
                double hashInsert = 0;

                double bstSearch = 0;
                double hashSearch = 0;

                double bstDelete = 0;
                double hashDelete = 0;

                for (int r = 0; r < REPETITIONS; r++) {
                    List<Integer> values = createValues(size);

                    BST bst = new BST();
                    HashTable hashTable = new HashTable();

                    long start = System.nanoTime();

                    for (int value : values) {
                        bst.insert(value);
                    }

                    long end = System.nanoTime();
                    bstInsert += toMilliseconds(end - start);

                    start = System.nanoTime();

                    for (int value : values) {
                        hashTable.insert(value);
                    }

                    end = System.nanoTime();
                    hashInsert += toMilliseconds(end - start);

                    List<Integer> searchValues =
                            new ArrayList<>(values);

                    Collections.shuffle(searchValues);

                    start = System.nanoTime();

                    for (int value : searchValues) {
                        bst.search(value);
                    }

                    end = System.nanoTime();
                    bstSearch += toMilliseconds(end - start);

                    start = System.nanoTime();

                    for (int value : searchValues) {
                        hashTable.search(value);
                    }

                    end = System.nanoTime();
                    hashSearch += toMilliseconds(end - start);

                    BST bstDeleteTree = new BST();
                    HashTable hashDeleteTable = new HashTable();

                    for (int value : values) {
                        bstDeleteTree.insert(value);
                        hashDeleteTable.insert(value);
                    }

                    List<Integer> deleteValues =
                            new ArrayList<>(values);

                    Collections.shuffle(deleteValues);

                    start = System.nanoTime();

                    for (int value : deleteValues) {
                        bstDeleteTree.delete(value);
                    }

                    end = System.nanoTime();
                    bstDelete += toMilliseconds(end - start);

                    start = System.nanoTime();

                    for (int value : deleteValues) {
                        hashDeleteTable.delete(value);
                    }

                    end = System.nanoTime();
                    hashDelete += toMilliseconds(end - start);
                }
                bstInsert /= REPETITIONS;
                hashInsert /= REPETITIONS;

                bstSearch /= REPETITIONS;
                hashSearch /= REPETITIONS;

                bstDelete /= REPETITIONS;
                hashDelete /= REPETITIONS;

                System.out.println(
                        "\nElements: " + size
                );

                printResult("BST Insert", bstInsert);
                printResult("Hash Insert", hashInsert);

                printResult("BST Search", bstSearch);
                printResult("Hash Search", hashSearch);

                printResult("BST Delete", bstDelete);
                printResult("Hash Delete", hashDelete);

                writer.printf(
                        java.util.Locale.US,
                        "%d,%.6f,%.6f,%.6f,%.6f,%.6f,%.6f%n",
                        size,
                        bstInsert,
                        hashInsert,
                        bstSearch,
                        hashSearch,
                        bstDelete,
                        hashDelete
                );
            }

            System.out.println(
                    "\nResults saved to results.csv"
            );

        } catch (IOException e) {
            System.out.println(
                    "Error writing results: " + e.getMessage()
            );
        }
    }

    private static List<Integer> createValues(int size) {
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            values.add(i);
        }

        Collections.shuffle(
                values, new Random()
        );

        return values;
    }

    private static double toMilliseconds(long nanoseconds) {
        return nanoseconds / 1_000_000.0;
    }

    private static void printResult(
            String operation, double time) {

        System.out.printf(
                java.util.Locale.US,
                "%-15s: %.6f ms%n",
                operation,
                time
        );
    }
}
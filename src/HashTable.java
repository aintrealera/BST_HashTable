import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private List<Integer>[] buckets;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable() {
        capacity = 8;
        size = 0;

        buckets = (List<Integer>[]) new List[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

        private int hash(int key) {
        return Math.floorMod(Integer.hashCode(key), capacity);
    }

    public void insert(int key) {
        int index = hash(key);
        List<Integer> bucket = buckets[index];

        if (bucket.contains(key)) {
            return;
        }

        bucket.add(key);
        size++;

        if ((double) size / capacity > 0.75) {
            resize();
        }
    }

        public boolean search(int key) {
        int index = hash(key);

        return buckets[index].contains(key);
    }

    public boolean delete(int key) {
        int index = hash(key);

        boolean removed = buckets[index].remove(
                Integer.valueOf(key)
        );

        if (removed) {
            size--;
        }

        return removed;
    }

     @SuppressWarnings("unchecked")
    private void resize() {
        List<Integer>[] oldBuckets = buckets;

        capacity *= 2;
        size = 0;

        buckets = (List<Integer>[]) new List[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (List<Integer> bucket : oldBuckets) {
            for (int key : bucket) {
                int index = hash(key);

                buckets[index].add(key);
                size++;
            }
        }
    }
}
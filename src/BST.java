public class BST {
    private Node root;

    public BST() {
        root = null;
    }

    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    private Node insertRecursive(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insertRecursive(node.left, key);
        } else if (key > node.key) {
            node.right = insertRecursive(node.right, key);
        }

        return node;
    }

    public boolean search(int key) {
        Node current = root;

        while (current != null) {
            if (current.key == key) {
                return true;
            }

            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    private Node deleteRecursive(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = deleteRecursive(node.left, key);
        } else if (key > node.key) {
            node.right = deleteRecursive(node.right, key);
        } else {

            if (node.left == null && node.right == null) {
                return null;
            }

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            node.key = findMin(node.right);

            node.right = deleteRecursive(
                    node.right, node.key
            );
        }

        return node;
    }

    private int findMin(Node node) {
        int min = node.key;

        while (node.left != null) {
            node = node.left;
            min = node.key;
        }

        return min;
    }
}
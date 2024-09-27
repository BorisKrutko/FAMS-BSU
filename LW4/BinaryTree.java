package LW4;

public class BinaryTree<T extends Comparable<T>> {
    public static void main(String[] args) {
        BinaryTree<Integer> temp = new BinaryTree<>(12);
        temp.push(11);
        temp.push(10);
        temp.push(2);
        temp.push(30);
        System.err.println("\nLRN:");
        temp.bypassLRN();
        System.err.println("\nLRN:");
        temp.delete(12);
        temp.bypassLRN();
        System.err.println("\nNLR:");
        temp.delete(30);
        temp.bypassNLR();
        System.err.println("\nLNR:");
        temp.delete(10);
        temp.bypassLNR();
    }

    class Node {
        T value;
        Node left;
        Node right;
        
        Node(T value) {
            left = right = null;
            this.value = value;
        }
    }

    Node root; 
    public BinaryTree(T value) {
        root = new Node(value);
    }

    public boolean find(T value) {
        Node current = root;
        while (current != null) {
            int compare = value.compareTo(current.value);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    private Node findParentNode(T value) { 
        Node current = root;
        while (current != null) {
            int compare = value.compareTo(current.value);
            if (current.left != null && value.compareTo(current.left.value) == 0) {
                return current;
            } else if (current.right != null && value.compareTo(current.right.value) == 0) {
                return current;
            } else if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public void push(T value) {
        if (root == null) {
            root = new Node(value);
        } else {
            recursivePush(root, value);
        }
    }
    
    private void recursivePush(Node current, T value) {
        int compare = value.compareTo(current.value);
        if (compare > 0) {
            if (current.right == null) current.right = new Node(value); 
            else {
                recursivePush(current.right, value);                
            }  
        }
        else if (compare < 0) {
            if (current.left == null) current.left = new Node(value);
            else {
                recursivePush(current.left, value);                
            }  
        }
    }

    public void bypassNLR() {
        recursiveBypassNLR(root);
    }

    private void recursiveBypassNLR(Node current) {
        if (current != null) {
            System.out.print(current.value + " "); 
            recursiveBypassNLR(current.left);
            recursiveBypassNLR(current.right);   
        }
    }

    public void bypassLRN() {
        recursiveBypassLRN(root);
    }

    private void recursiveBypassLRN(Node current){
        if (current != null) {
            recursiveBypassLRN(current.left);
            recursiveBypassLRN(current.right);
            System.err.print(current.value + " ");
        }
    }

    public void bypassLNR() {
        recursiveBypassLNR(root);
    }

    private void recursiveBypassLNR(Node current){
        if (current != null) {
            recursiveBypassLRN(current.left);
            System.err.print(current.value + " ");
            recursiveBypassLRN(current.right);
        }
    }

    private T findMinValue(Node current) {
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public void delete(T value) {
        // new parentNodeToDelene and NodeToDelete
        Node parentNodeToDelete, nodeToDelete;
        // if root need to del
        if (value.compareTo(root.value) == 0) {
            parentNodeToDelete = null;
            nodeToDelete = root;
        } else {
            parentNodeToDelete = findParentNode(value);
            if (parentNodeToDelete.left != null && value.compareTo(parentNodeToDelete.left.value) == 0) {
                nodeToDelete = parentNodeToDelete.left;
            } else if (parentNodeToDelete.right != null && value.compareTo(parentNodeToDelete.right.value) == 0) {
                nodeToDelete = parentNodeToDelete.right;
            } else {
                return;
            }
        }

        // 3 ways to delete
        // # 1 plate
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (parentNodeToDelete.left == nodeToDelete) {
                parentNodeToDelete.left = null;
            } else {
                parentNodeToDelete.right = null;
            }
        // # 2 1_child
        } else if (nodeToDelete.left == null || nodeToDelete.right == null) {
            Node child = (nodeToDelete.left != null) ? nodeToDelete.left : nodeToDelete.right;
            if (nodeToDelete == root) {
                root = child;
                nodeToDelete = null;
            }
            else if (parentNodeToDelete.left == nodeToDelete) {
                parentNodeToDelete.left = child;
            } else {
                parentNodeToDelete.right = child;
            }
        // # 3 2_childes
        } else {
            T minValue = findMinValue(nodeToDelete.right);
            delete(minValue);
            nodeToDelete.value = minValue;
        }
    }
}
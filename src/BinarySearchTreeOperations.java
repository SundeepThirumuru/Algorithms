import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 6/1/16
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class BinarySearchTreeOperations {

    private int curNodeNum = 0;
    private List<Node<Integer>> endNodesOfDiameter = new ArrayList<>();
    private int maxDiameter = 0;
    private int curDiameter = 0;
    private Node<Integer> maxDepthNode = null;
    private int maxDepth = 0;
    private int currentDepth = 0;


    public int findKthSmallestElement(int k, Node<Integer> root) {
        if (root == null) {
            throw new NullPointerException();
        }
        curNodeNum = -1;
        return findElement(k, root);
    }

    private int findElement(int k, Node<Integer> node) {
        if (node.left != null) {
            int element = findElement(k, node.left);
            if (k == curNodeNum) {
                return element;
            }
        }
        curNodeNum++;
        if (k == curNodeNum) {
            return node.value;
        }
        if (node.right != null) {
            int element = findElement(k, node.right);
            if (k == curNodeNum) {
                return element;
            }
        }
        return node.value;
    }

    public int getMinTreeDepth(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        int minLeftTreeDepth = getMinTreeDepth(node.left);
        int minRightTreeDepth = getMinTreeDepth(node.right);
        return Math.min(minLeftTreeDepth, minRightTreeDepth) + 1;
    }

    public int getMaxTreeDepth(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        int maxLeftTreeDepth = getMaxTreeDepth(node.left);
        int maxRightTreeDepth = getMaxTreeDepth(node.right);
        return Math.max(maxLeftTreeDepth, maxRightTreeDepth) + 1;
    }

    public int getMaxDiameter(Node<Integer> root) {
        if (root == null) {
            return 0;
        }
        int leftMaxDepth = getMaxTreeDepth(root.left);
        int rightMaxDepth = getMaxTreeDepth(root.right);
        getMaxDiameter(root.left);
        getMaxDiameter(root.right);

        if (leftMaxDepth + rightMaxDepth + 1 > maxDiameter) {
            maxDiameter = leftMaxDepth + rightMaxDepth + 1;
            endNodesOfDiameter.clear();
            endNodesOfDiameter.add(root.left != null ? findDeepestNode(root.left) : root);
            endNodesOfDiameter.add(root.right != null ? findDeepestNode(root.right) : root);
        }

        return maxDiameter;
    }


    public Node<Integer> findDeepestNodeHelper(Node<Integer> root) {
        if (root == null) {
            return maxDepthNode;
        }

        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepthNode = root;
            maxDepth = currentDepth;
        }
        findDeepestNodeHelper(root.left);
        findDeepestNodeHelper(root.right);
        currentDepth--;
        return maxDepthNode;
    }

    public Node<Integer> findDeepestNode(Node<Integer> root) {
        if (root == null) {
            return root;
        }

        maxDepth = 0;
        currentDepth = 0;
        maxDepthNode = null;
        return findDeepestNodeHelper(root);
    }


    public int findLCA(int a, int b, Node<Integer> root) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        if (root == null) {
            throw new RuntimeException();
        }

        if (b < root.value) {
            return findLCA(a, b, root.left);
        } else if (a > root.value) {
            return findLCA(a, b, root.right);
        }

        return root.value;
    }

    public Node<Integer> buildTree(int input, Node<Integer> root) {
        if (root == null) {
            root = new Node<>();
            root.value = input;
            return root;
        }
        Node<Integer> curNode = root;
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        Node<Integer> potentialParent = null;
        while (!queue.isEmpty()) {
            curNode = queue.remove();
            if (input == curNode.value) {
                int count = 1;
                if (curNode.properties.contains("count")) {
                    count = (Integer) curNode.properties.get("count");
                }
                count++;
                curNode.properties.put("count", count);
                return root;
            } else {
                if (curNode.left != null) {
                    queue.add(curNode.left);
                } else if (potentialParent == null) {
                    potentialParent = curNode;
                }

                if (curNode.right != null) {
                    queue.add(curNode.right);
                } else if(potentialParent == null){
                    potentialParent = curNode;
                }
            }
        }

        Node<Integer> newNode = new Node<>();
        newNode.value = input;
        if (potentialParent.left == null) {
            potentialParent.left = newNode;
        } else {
            assert (potentialParent.right == null);
            potentialParent.right = newNode;
        }

        return root;
    }

    public String getValuesAsString(Node<Integer> root) {
        StringBuilder stringBuilder = new StringBuilder();
        if (root == null) {
            return stringBuilder.toString();
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        Node<Integer> curNode = null;
        while (!queue.isEmpty()) {
            curNode = queue.remove();
            stringBuilder.append(curNode.value).append(curNode.properties.containsKey("count") ? ";" + curNode.properties.get("count") : "").append(" ");
            if (curNode.left != null) {
                queue.add(curNode.left);
            }

            if (curNode.right != null) {
                queue.add(curNode.right);
            }
        }
        return stringBuilder.toString();
    }




    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Node<Integer> root = BSTFromSortedArray.formBST(a);

        BSTFromSortedArray.printBST(root);

        BinarySearchTreeOperations BSTOperations = new BinarySearchTreeOperations();
        for (int i = 0; i < a.length; i++) {
            System.out.println(BSTOperations.findKthSmallestElement(i, root));
        }

        System.out.println("Min depth: " + BSTOperations.getMinTreeDepth(root));
        System.out.println("Max depth: " + BSTOperations.getMaxTreeDepth(root));
        System.out.println("Max Diameter: " + BSTOperations.getMaxDiameter(root));
        System.out.println("End Nodes of max diameter: " + BSTOperations.endNodesOfDiameter);
        System.out.println("Deepest Node: " + BSTOperations.findDeepestNode(root));
        System.out.println("Least Common Ancestor: " + BSTOperations.findLCA(3, 6, root));

        a = new int[]{1, 2, 2, 3, 4, 1, 5, 6, 6, 7, 9, 8, 2};
        System.out.println("Input Array: " + Arrays.toString(a));
        Node<Integer> root2 = null;
        for (int value : a) {
            root2 = BSTOperations.buildTree(value, root2);
        }
        BSTFromSortedArray.printBST(root2);
        System.out.println("Stringified Input Stream: " + BSTOperations.getValuesAsString(root2));
    }
}

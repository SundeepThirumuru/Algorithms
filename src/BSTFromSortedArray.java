import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 3/1/16
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BSTFromSortedArray {

    public static Node<Integer> formBST(int[] sortedArray)
    {
        if(sortedArray == null || sortedArray.length == 0)
        {
            return null;
        }
        return formSubTree(sortedArray, 0, sortedArray.length);
    }

    private static Node<Integer> formSubTree(int[] sortedArray, int startIndex, int endIndex)
    {
        if(startIndex >= endIndex)
        {
            return null;
        }
        else
        {
            int middleIndex = (startIndex + endIndex)/2;
            Node<Integer> node = new Node<>();
            node.value = sortedArray[middleIndex];
            node.left = formSubTree(sortedArray, startIndex, middleIndex);
            node.right = formSubTree(sortedArray, middleIndex + 1, endIndex);
            return node;
        }
    }

    public static void printBST(Node<Integer> node)
    {
        LinkedList<Node<Integer>> queue = new LinkedList<>();
        queue.add(node);
        LinkedList<Node<Integer>> stagingQueue = new LinkedList<>();
        while(!queue.isEmpty())
        {
            Node<Integer> tempNode = queue.remove();
            System.out.print(tempNode.value + " ");
            if(tempNode.left != null)
            {
                stagingQueue.add(tempNode.left);
            }
            if(tempNode.right != null)
            {
                stagingQueue.add(tempNode.right);
            }
            if(queue.isEmpty())
            {
                System.out.println();
                System.out.println();
                LinkedList<Node<Integer>> tempQueue = queue;
                queue = stagingQueue;
                stagingQueue = tempQueue;
            }
        }
    }

    public static void main(String[] args) {
        int inputSize = 100000;
        int[] sortedArray = new int[inputSize];
        for(int i=0; i<inputSize; i++)
        {
            sortedArray[i] = i;
        }

        long startTime = System.currentTimeMillis();
        Node<Integer> root = formBST(sortedArray);
        long endTime = System.currentTimeMillis();
        System.out.printf("Formed BST in %d ms", (endTime - startTime));
        System.out.println();

        TreeSet<Integer> javaTreeSet = new TreeSet<>();
        startTime = System.currentTimeMillis();
        for(int value : sortedArray)
        {
            javaTreeSet.add(value);
        }
        endTime = System.currentTimeMillis();
        System.out.printf("Time taken by java tree set to form bst is %d ms", (endTime - startTime));
        //printBST(root);
    }
}

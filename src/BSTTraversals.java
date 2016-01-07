import javax.xml.transform.Source;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 7/1/16
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class BSTTraversals {
    public static void printBSTZigZag(Node<Integer> root)
    {
        if(root == null)
        {
            return;
        }

        Queue<Node<Integer>> queue = new LinkedList<>();
        List<Node<Integer>> stagingQueue = new LinkedList<>();
        queue.add(root);
        Node<Integer> curNode = null;
        System.out.println(root.value);
        boolean alternate = true;
        while(!queue.isEmpty())
        {
            curNode = queue.remove();
            if(curNode.left != null)
            {
                stagingQueue.add(curNode.left);
            }
            if(curNode.right != null)
            {
                stagingQueue.add(curNode.right);
            }
            if(queue.isEmpty() && !stagingQueue.isEmpty())
            {
                System.out.println();
                StringBuilder stringBuilder = new StringBuilder();
                Iterator<Node<Integer>> iterator = stagingQueue.listIterator();
                while (iterator.hasNext())
                {
                    if(alternate)
                    {
                        stringBuilder.insert(0, " ").insert(0, iterator.next().value);
                    }
                    else
                    {
                        stringBuilder.append(iterator.next().value).append(" ");
                    }
                }
                System.out.println(stringBuilder.toString());
                queue.addAll(stagingQueue);
                stagingQueue.clear();
                alternate = !alternate;
            }
        }
    }

    public static void printBSTAlternateRows(Node<Integer> root)
    {
        if(root == null)
        {
            return;
        }

        Queue<Node<Integer>> queue = new LinkedList<>();
        List<Node<Integer>> stagingQueue = new LinkedList<>();
        queue.add(root);
        Node<Integer> curNode = null;
        int curLevel = 0;
        while(!queue.isEmpty())
        {
            curNode = queue.remove();
            if(curLevel % 2 == 0)
            {
                System.out.print(curNode.value + " ");
            }
            if(curNode.left != null)
            {
                stagingQueue.add(curNode.left);
            }
            if(curNode.right != null)
            {
                stagingQueue.add(curNode.right);
            }
            if(queue.isEmpty() && !stagingQueue.isEmpty())
            {
                System.out.println();
                queue.addAll(stagingQueue);
                stagingQueue.clear();
                curLevel ++;
            }
        }
    }



    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Node<Integer> root = BSTFromSortedArray.formBST(a);
        System.out.println("BST: ");
        BSTFromSortedArray.printBST(root);
        System.out.println("Print BST ZigZag: ");
        printBSTZigZag(root);
        System.out.println("Print Even levels of BST : ");
        printBSTAlternateRows(root);
    }
}

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 6/1/16
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
    public class Node<T>
    {
        public T value;
        public Node<T> left, right, parent;
        public Properties properties = new Properties();
        public String toString()
        {
            return value != null ? value.toString() : "";
        }
    }
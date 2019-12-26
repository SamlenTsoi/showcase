package samlen.tsoi.showcase.web.test;

/**
 * @author samlen_tsoi
 * @date 2019/12/25
 **/
public class Node {
    /**
     * 数据
     */
    private Object data;

    /**
     * 下个节点
     */
    private Node next;

    public Node() {
    }

    public Node(Object data) {
        this.data = data;
    }

    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }
}

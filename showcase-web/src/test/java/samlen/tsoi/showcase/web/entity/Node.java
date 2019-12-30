package samlen.tsoi.showcase.web.entity;

import lombok.Data;

/**
 * @author samlen_tsoi
 * @date 2019/12/27
 **/
@Data
public class Node {
    private String data;

    private Node next;

    public Node(String data) {
        this.data = data;
    }
}

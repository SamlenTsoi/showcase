package samlen.tsoi.showcase.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务端发送消息实体
 *
 * @author samlen_tsoi
 * @date 2018/4/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerMessage {

    private String responseMessage;
}

package org.zerhusen.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/login/{userId}")
@Component
public class WebSocketService {

   private final Logger logger = LoggerFactory.getLogger(WebSocketService.class);
   /**
    * 静态变量，用来记录当前在线连接数，应把它设计成线程安全的
    */
   private static int onlineCount = 0;

   /**
    * concurrent包的线程安全的map，用来存放客户端对应的WebSocket对象
    *
    */
   private static ConcurrentHashMap<String, WebSocketService> webSocketMap = new ConcurrentHashMap<>();

   /**
    * 与某个客户端的连接对话，需要通过它来给客户端发数据
    */
   private Session session;

   private  String userId = "";
   private static final String ADMIN_USER = "admin";

   /**
    * 连接成功
    * @param session
    */
   @OnOpen
   public void onOpen(Session session,  @PathParam("userId")String userId) {
      logger.info("web socket 连接成功:  " + userId);
      this.session = session;
      this.userId = userId;
      countOnline(1);
   }

   /**
    * 连接关闭调用的方法
    */
   @OnClose
   public void onClose() {
      logger.info("web socket 关闭 : " + userId);
      countOnline(-1);
   }
   /**
    * 收到客户端消息后调用的方法
    *
    * @param message 客户端发送过来的消息
    */
   @OnMessage
   public void onMessage(String message, Session session) {
      logger.info("用户：" + userId + " 消息：" + message);

//      if (!StringUtils.isEmpty(message)) {
//         try {
//            JSONObject json = JSONUtil.parseObj(message);
//            String cmdStr = json.getStr("cmd");
//            //check command
//            if (!SocketCMD.isValidCommand(cmdStr)) {
//               sendMessage(SocketCMD.err_invalid_command);
//            } else {
//               handleCommand(cmdStr, message);
//            }
//         } catch (Exception e) {
//            sendMessage(SocketCMD.err_json_format);
//         }

   }

   private void closeByReason(String reason){
      try {
         session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, reason));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   public static synchronized void countOnline(int n) {
      WebSocketService.onlineCount += n;
   }
   /**
    * @param session
    * @param error
    */
   @OnError
   public void onError(Session session, Throwable error) {
      String msg = error.getMessage() != null? error.getMessage():"未知錯誤";
      logger.error("web socket 连接错误:" + msg);
   }

   /**
    * 实现服务器主动推送
    */
   public void sendMessage(String message) {
      try {
         this.session.getBasicRemote().sendText(message);
      } catch (IOException e) {
         e.printStackTrace();
         logger.error(String.format("发送消息错误:%s, 原因:%s", e.getMessage()));
      }
   }

}

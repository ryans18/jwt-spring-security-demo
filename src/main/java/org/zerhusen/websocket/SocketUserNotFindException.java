package org.zerhusen.websocket;

/**
 * Author : Ryans
 * Date : 2021/8/13
 * Introduction :
 */
public class SocketUserNotFindException extends RuntimeException{

   private static final long serialVersionUID = -5561223394807456347L;

   public SocketUserNotFindException(String message) {
      super(message);
   }
}

package org.zerhusen.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.zerhusen.rest.dto.StatusDto;
import org.zerhusen.websocket.SocketUserNotFindException;
import org.zerhusen.websocket.WebSocketService;

/**
 * Author : Ryans
 * Date : 2021/8/2
 * Introduction :
 */
@RestController
@RequestMapping("/api")
public class QRRestController {

   private final Logger logger = LoggerFactory.getLogger(QRRestController.class);

   @GetMapping("/qr")
   public ResponseEntity<?> getActualUser(@RequestParam(value = "msg", required = true) String msg) {
      logger.info("Qr msg: " +  msg);
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      try {
         WebSocketService.sendInfo(msg, authentication.getCredentials().toString());
      } catch (SocketUserNotFindException e) {
         e.printStackTrace();
         return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
      }
      return new ResponseEntity<>(HttpStatus.OK);
   }
}

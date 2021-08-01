package org.zerhusen.security.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.security.model.User;
import org.zerhusen.security.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

   private final Logger logger = LoggerFactory.getLogger(UserRestController.class);

   private final UserService userService;

   public UserRestController(UserService userService) {
      this.userService = userService;
   }

   @GetMapping("/user")
   public ResponseEntity<User> getActualUser() {
      return ResponseEntity.ok(userService.getUserWithAuthorities().get());
   }

   @GetMapping("/qr")
   public ResponseEntity.BodyBuilder getActualUser( @RequestParam(value = "msg", required = true) String msg) {
      logger.info("Qr msg: " +  msg);
      ResponseEntity.ok()
      return ResponseEntity.ok();
   }


}

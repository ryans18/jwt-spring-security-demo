package org.zerhusen.security.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

}

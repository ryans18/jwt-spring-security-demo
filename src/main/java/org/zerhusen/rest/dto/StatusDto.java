package org.zerhusen.rest.dto;

/**
 * Author : Ryans
 * Date : 2021/8/2
 * Introduction :
 */
public class StatusDto {

   private int code;
   private boolean success;

   public StatusDto(int code, boolean success) {
      this.code = code;
      this.success = success;
   }

   public int getCode() {
      return code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public boolean isSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }
}

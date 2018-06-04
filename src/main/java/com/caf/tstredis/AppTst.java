package com.caf.tstredis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTst {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(Application.class);
      
      DealerService dealerService1 = context.getBean(DealerService.class);
      dealerService1.getAllUsers();

      DealerService dealerService2 = context.getBean(DealerService.class);
      dealerService2.getAllUsers();

      context.close();
   }
}

package com.async.async;

import org.springframework.scheduling.annotation.Async;

@org.springframework.stereotype.Service
public class Service {


    @Async
    public void addEnti() {
        Entity a=new Entity("Ananth","22");


        System.out.println("HIIII");
        System.out.println(a.getName()+ "  :" +" "+a.getAge());
        int i=1;
        int h=1;
      while (i!=0){
          h++;
          System.out.println(h);
          try {
              Thread.sleep(1000);

          }catch (Exception e){
              System.out.println(e);
          }
          if(h==1000){

              i=0;
          }

      }


    }
}

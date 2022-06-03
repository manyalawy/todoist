package com.example.firebase.springbootfirebasedemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.transaction.Transactional;
import java.util.List;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path="api/")
public class NotificationsController {

    @Autowired
    private NotificationsService notificationsService;


    @PostMapping(value = "Notifications", consumes = "application/json", produces = "application/json")
    public void createNotification (@RequestBody Notifications n) throws ExecutionException, InterruptedException {

//    String t = body.getString("title");
//    int ID = body.getInt("id");
//    String b = body.getString("body");
//	Chats c = new Chats(t, ID, b);S
        try {

            notificationsService.createNotification(n);
        } catch (Exception e) {
//		// TODO Auto-generated catch block
//        System.out.println("here");
            System.out.println("error in notificationsController");
            e.printStackTrace();
        }
//	return c;
//	System.out.println("here is the chat"+ c);
//	return "Success";
    }

//Response StartNewChat(@RequestBody Chats c) {
//    System.out.println("StartNewChat" + c.toString());
//    return new Response("Success","200");
//}




//@Value

//public Chats getChatByName(String title) throws ExecutionException, InterruptedException{
//	Chats c1 = new Chats(title, 113, "Ahlan wasahlan");
//	chatsService.getChatByName(title);
//	return c1;
//
//}


    @GetMapping("Notifications/{title}")
    public Notifications getNotificationDetailsByName (@PathVariable String title)  {

        try {
            return notificationsService.getNotificationDetailsByName(title);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("Notifications/{title}")
    public String deleteNotification(@PathVariable String title){
        try {
            return notificationsService.deleteNotification(title);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

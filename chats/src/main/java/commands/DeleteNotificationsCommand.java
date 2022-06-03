package commands;

import com.example.firebase.springbootfirebasedemo.NotificationsService;

import java.util.concurrent.ExecutionException;

public class DeleteNotificationsCommand {

    String title;

    public void execute() throws ExecutionException, InterruptedException {
        NotificationsService.deleteNotification(title);

    }
		
}

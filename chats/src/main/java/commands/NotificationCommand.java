package commands;

import com.example.firebase.springbootfirebasedemo.NotificationsService;

import java.util.concurrent.ExecutionException;

public abstract class NotificationCommand extends Command {

    String title;

    public void execute() throws ExecutionException, InterruptedException {
        NotificationsService.getNotificationDetailsByName(title);

    }
	
 
}

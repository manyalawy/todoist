package commands;

import com.example.firebase.springbootfirebasedemo.Notifications;
import com.example.firebase.springbootfirebasedemo.NotificationsService;

import java.util.concurrent.ExecutionException;

public class UpdateNotificationsCommand {

    Notifications n;

    public void execute() throws ExecutionException, InterruptedException {
        NotificationsService.updateNotification(n);

    }

}

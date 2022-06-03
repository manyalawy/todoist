package commands;

import com.example.firebase.springbootfirebasedemo.ChatsService;
import com.example.firebase.springbootfirebasedemo.NotificationsService;

import java.util.concurrent.ExecutionException;

public class DeleteChatCommand {


    String title;

    public void execute() throws ExecutionException, InterruptedException {
        ChatsService.deleteChat(title);

    }

}

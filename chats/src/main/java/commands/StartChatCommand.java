package commands;

import com.example.firebase.springbootfirebasedemo.Chats;
import com.example.firebase.springbootfirebasedemo.ChatsService;

import java.util.concurrent.ExecutionException;

public class StartChatCommand {

    Chats c;

    public void execute() throws ExecutionException, InterruptedException {
        ChatsService.StartNewChat(c);

    }

}

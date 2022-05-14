import config.Consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class List {


    public static void main(String[] args) throws IOException, TimeoutException {
        Consumer consumer = new Consumer();
        consumer.consume();
    }

}

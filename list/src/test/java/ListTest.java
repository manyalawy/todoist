import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import java.io.IOException;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ListTest {

    @Test
    public void createTodolistSuccess() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8080/api/todolist/create");

        String json = "{'user_id':17,'name':todolist444}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(request);
        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(200));

    }

    @Test
    public void createTaskSuccess() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8080/api/todolist/create");

        String json = "{'user_id':17,'name':todolist444}";
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(request);
        assertThat(
                response.getStatusLine().getStatusCode(),
                equalTo(200));

    }
}

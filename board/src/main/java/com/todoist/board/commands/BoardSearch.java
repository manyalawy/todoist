
package main.java.com.todoist.board.commands;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.conversions.Bson;

public class BoardSearch implements Command{

    String boardName;


    public ListSearch(String boardName) {
        this.boardName = boardName;

    }




    @Override
    public void execute() {

        MongoDB db = new MongoDB();
        MongoCollection boardCollection =  db.dbInit(CollectionNames.BOARD.get());


        //Inserting task in todolist
        Bson filter = Filters.eq("name", boardName);

        Bson projection = Projections.include("name");
//       boardCollection.find(filter).forEach(doc -> System.out.println(doc));



    }


}


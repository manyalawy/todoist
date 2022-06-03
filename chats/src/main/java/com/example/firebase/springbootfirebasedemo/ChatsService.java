package com.example.firebase.springbootfirebasedemo;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

//import org.springframework.boot.SpringApplication;
import com.google.firebase.FirebaseApp;
import firebase.FirebaseInitialization;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
//import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
//import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ChatsService {
//	List<Chats> chatslist= null;
	private static final String COLLECTION_NAME = "Chats";

	public static String StartNewChat(Chats c ) throws ExecutionException,InterruptedException{
		System.out.println(c);
		FirebaseInitialization myApp = new FirebaseInitialization();
		FirebaseApp dBFireApp = myApp.initialization();

		Firestore dbFirestore= FirestoreClient.getFirestore(dBFireApp);
		ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("Chats").document(Chats.getTitle()).set(c);
//		System.out.println("Agmal RR2aaH");
//		chatslist.add(c);
		return collectionApiFuture.get().getUpdateTime().toString();
	}


//public String StartNewChat(String title, int id, String body) throws ExecutionException,InterruptedException{
//		Chats c = new Chats(title,id,body);
//		Firestore dbFirestore= FirestoreClient.getFirestore();
//		ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(Chats.getTitle()).set(c);
////		System.out.println("Agmal RR2aaH");
//		return collectionApiFuture.get().getUpdateTime().toString();
//	}


	public static List<Chats> getAllChats() throws ExecutionException,InterruptedException{

		FirebaseInitialization myApp = new FirebaseInitialization();
		FirebaseApp dBFireApp = myApp.initialization();
		Firestore dbFirestore= FirestoreClient.getFirestore(dBFireApp);

		Iterable<DocumentReference> documentReference=dbFirestore.collection("Chats").listDocuments();
		Iterator<DocumentReference> iterator=documentReference.iterator();

		List<Chats> chatslist= null;
		Chats chats=null;

		while(iterator.hasNext()) {
			DocumentReference documentReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future=documentReference1.get();
			DocumentSnapshot document=future.get();

			chats=	document.toObject(Chats.class);
			chatslist.add(chats);
		}

			return chatslist;
}

public static String deleteChat(String title) throws ExecutionException,InterruptedException{

	FirebaseInitialization myApp = new FirebaseInitialization();
	FirebaseApp dBFireApp = myApp.initialization();
	Firestore dbFirestore= FirestoreClient.getFirestore(dBFireApp);
	ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection("Chats").document(title).delete();

	return "Chat titled "+title+" has been deleted successfully";
}
public static Chats getChatByName(String title) throws ExecutionException, InterruptedException {

	FirebaseInitialization myApp = new FirebaseInitialization();
	FirebaseApp dBFireApp = myApp.initialization();
	Firestore dbFirestore= FirestoreClient.getFirestore(dBFireApp);

	DocumentReference documentReference=dbFirestore.collection("Chats").document(title);
	ApiFuture<DocumentSnapshot> future=documentReference.get();
	DocumentSnapshot document=future.get();
	Chats chats=null;
	if(document.exists()) {
	 chats=	document.toObject(Chats.class);
	return chats;
	}
	else {
		return null;
	}

}
}

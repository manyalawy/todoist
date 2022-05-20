package service;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import entity.Chats;
public class ChatsService {

	private static final String COLLECTION_NAME = "noticitaions";
	public String StartNewChat(Chats c ) throws ExecutionException,InterruptedException{
		
		Firestore dbFirestore= FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(Chats.getTitle()).set(c);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	
	
	public List<Chats> getAllChats() throws ExecutionException,InterruptedException{
		Firestore dbFirestore= FirestoreClient.getFirestore();
		Iterable<DocumentReference> documentReference=dbFirestore.collection(COLLECTION_NAME).listDocuments();
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
//public String updateNotifcation(Chats n ) throws ExecutionException,InterruptedException{
//		
//		Firestore dbFirestore= FirestoreClient.getFirestore();
//		ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(Notifications.getTitle()).set(n);
//		
//		return collectionApiFuture.get().getUpdateTime().toString();
//	}
public String deleteChat(String title) throws ExecutionException,InterruptedException{
	
	Firestore dbFirestore= FirestoreClient.getFirestore();
	ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(title).delete();
	
	return "Chat titled "+title+" has been deleted successfully";
}
public Chats getChatByName(String title) throws ExecutionException,InterruptedException{
	Firestore dbFirestore= FirestoreClient.getFirestore();
	DocumentReference documentReference=dbFirestore.collection(COLLECTION_NAME).document(title);
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

package com.example.firebase.springbootfirebasedemo;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import firebase.FirebaseInitialization;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;

@Service
//@AutoWired

public class NotificationsService {

	private static final String COLLECTION_NAME = "notifications";
	public static String createNotification(Notifications n ) throws ExecutionException,InterruptedException{

		FirebaseInitialization myApp = new FirebaseInitialization();
		FirebaseApp dBFireApp = myApp.initialization();
		Firestore dbFirestore= FirestoreClient.getFirestore(dBFireApp);
		ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(Notifications.getTitle()).set(n);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
//	public static List<Notifications> getNotificationDetails() throws ExecutionException,InterruptedException{
//		FirebaseInitialization myApp = new FirebaseInitialization();
//		FirebaseApp dBFireApp = myApp.initialization();
//		Firestore dbFirestore= FirestoreClient.getFirestore(dBFireApp);
//		Iterable<DocumentReference> documentReference=dbFirestore.collection(COLLECTION_NAME).listDocuments();
//		Iterator<DocumentReference> iterator=documentReference.iterator();
//
//		List<Notifications> notificationslist= null;
//		Notifications notifications=null;
//
//		while(iterator.hasNext()) {
//			DocumentReference documentReference1 = iterator.next();
//			ApiFuture<DocumentSnapshot> future=documentReference1.get();
//			DocumentSnapshot document=future.get();
//
//			notifications=	document.toObject(Notifications.class);
//			notificationslist.add(notifications);
//		}
//
//
//			return notificationslist;
//
//}
public static String updateNotification(Notifications n ) throws ExecutionException,InterruptedException{
		
		Firestore dbFirestore= FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(Notifications.getTitle()).set(n);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
public static String deleteNotification(String title) throws ExecutionException,InterruptedException{
	
	Firestore dbFirestore= FirestoreClient.getFirestore();
	ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(title).delete();
	
	return "Notification titled "+title+" has been deleted successfully";
}
public static Notifications getNotificationDetailsByName(String title) throws ExecutionException,InterruptedException{
	Firestore dbFirestore= FirestoreClient.getFirestore();
	DocumentReference documentReference=dbFirestore.collection(COLLECTION_NAME).document(title);
	ApiFuture<DocumentSnapshot> future=documentReference.get();
	DocumentSnapshot document=future.get();
	Notifications notifications=null;
	if(document.exists()) {
	 notifications=	document.toObject(Notifications.class);
	return notifications;
	}
	else {
		return null;
	}
	

}

}

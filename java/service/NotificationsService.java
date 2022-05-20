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

import entity.Notifications;

@Service

public class NotificationsService {

	private static final String COLLECTION_NAME = "noticitaions";
	public String createNotifcation(Notifications n ) throws ExecutionException,InterruptedException{
		
		Firestore dbFirestore= FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(Notifications.getTitle()).set(n);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	public List<Notifications> getNotificationdetails() throws ExecutionException,InterruptedException{
		Firestore dbFirestore= FirestoreClient.getFirestore();
		Iterable<DocumentReference> documentReference=dbFirestore.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator=documentReference.iterator();
		
		List<Notifications> notificationslist= null;
		Notifications notifications=null;
		
		while(iterator.hasNext()) {
			DocumentReference documentReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future=documentReference1.get();
			DocumentSnapshot document=future.get();
			
			notifications=	document.toObject(Notifications.class);
			notificationslist.add(notifications);
		}

	
			return notificationslist;

}
public String updateNotifcation(Notifications n ) throws ExecutionException,InterruptedException{
		
		Firestore dbFirestore= FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(Notifications.getTitle()).set(n);
		
		return collectionApiFuture.get().getUpdateTime().toString();
	}
public String deleteNotifcation(String title) throws ExecutionException,InterruptedException{
	
	Firestore dbFirestore= FirestoreClient.getFirestore();
	ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(title).delete();
	
	return "Notication titled "+title+" has been deleted successfully";
}
public Notifications getNotificationdetailsByName(String title) throws ExecutionException,InterruptedException{
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

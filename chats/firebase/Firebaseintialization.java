package firebase;
//package scalable;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class Firebaseintialization {
	
@PostConstruct
	public void intialization() {

		FileInputStream serviceAccount =null;
		try {
			serviceAccount = new FileInputStream("./serviceAccountKey.json");

FirebaseOptions options = new FirebaseOptions.Builder()
  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
  .build();

FirebaseApp.initializeApp(options);}
		
		catch(Exception e) {
			e.printStackTrace();}
	}
}

	


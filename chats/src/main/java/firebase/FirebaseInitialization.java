package firebase;
//package scalable;

import java.io.FileInputStream; //done

import javax.annotation.PostConstruct;// done

//import org.springframework.boot.SpringApplication;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service; //done
import java.io.FileNotFoundException; //done
//
//import com.google.auth.oauth2.GoogleCredentials;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitialization {
	static FirebaseApp App;

	@PostConstruct
	public FirebaseApp initialization() {
	if (App != null){
		return App;
		}

		FileInputStream serviceAccount =
				null;

		try {
			serviceAccount = new FileInputStream("/Users/rawanashraf/Downloads/To Rawan 3/springboot-firebase-demo/src/main/java/firebase/serviceAccountKey.json");
			System.out.println("here in initialize");
			System.out.println(serviceAccount);
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			App = FirebaseApp.initializeApp(options);


		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return App;

	}

}
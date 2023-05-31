/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;

/**
 *
 * @author nguye
 */
public class FirebaseContext {
    
    public static Firestore getFirestoreInstance() {
        // Define the path to the Google Services JSON file
        String serviceAccountFile = "D:\\FER201m\\CineMagic\\cinemagic\\service-account.json";

        try {
            // Create the FirebaseOptions object
            FileInputStream serviceAccount = new FileInputStream(serviceAccountFile);
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://cinemagic-388114.firebaseio.com/")
                .build();

            // Initialize Firebase
            FirebaseApp.initializeApp(options);

            System.out.println("Firebase Cloud Firestore connection established successfully.");

        } catch (Exception e) {
            System.err.println("Error establishing Firebase Cloud Firestore connection: " + e.getMessage());
        }
        return FirestoreClient.getFirestore();
    }
}

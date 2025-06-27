package mantenimiento.Cibertec.Proyecto.Security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@Configuration
public class FirebaseConfig {
    @PostConstruct
    public void init() {
        try {
            String base64Creds = System.getenv("FIREBASE_CONFIG_BASE64");
            if (base64Creds == null) {
                throw new RuntimeException("⚠️ Variable de entorno FIREBASE_CONFIG_BASE64 no encontrada.");
            }

            byte[] decoded = Base64.getDecoder().decode(base64Creds);
            ByteArrayInputStream serviceAccount = new ByteArrayInputStream(decoded);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase inicializado desde entorno");
            }

        } catch (IOException e) {
            System.err.println("❌ Error inicializando Firebase: " + e.getMessage());
        }
    }
}

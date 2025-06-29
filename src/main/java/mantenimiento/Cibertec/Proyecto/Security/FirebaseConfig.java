package mantenimiento.Cibertec.Proyecto.Security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class FirebaseConfig {
    @PostConstruct
    public void init() {
        try {
            String escapedJson = System.getenv("git af");
            if (escapedJson == null || escapedJson.trim().isEmpty()) {
                throw new RuntimeException("⚠️ Variable de entorno FIREBASE_CONFIG_JSON no encontrada o vacía.");
            }

            ByteArrayInputStream serviceAccount = new ByteArrayInputStream(
                    escapedJson.getBytes(StandardCharsets.UTF_8)
            );

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase inicializado desde variable de entorno (JSON escapado)");
            }

        } catch (IOException e) {
            System.err.println("❌ Error inicializando Firebase: " + e.getMessage());
        }
    }
}

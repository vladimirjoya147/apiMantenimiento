package mantenimiento.Cibertec.Proyecto.Security;

import com.google.common.net.HttpHeaders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirebaseAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(header);

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println("segundo"+ header);
            response.getWriter().write(header +" Falta el token o formato incorrecto");
            return;
        }

        String token = header.substring(7);
        System.out.println("tercero"+token);

        try {
            System.out.println("🔍 Verificando token...");
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            System.out.println("✅ Token verificado: " + decodedToken.getEmail());


            List<GrantedAuthority> authorities = new ArrayList<>();
            Authentication authentication = new UsernamePasswordAuthenticationToken(decodedToken, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            request.setAttribute("firebaseUser", decodedToken);

            filterChain.doFilter(request, response);

        } catch (FirebaseAuthException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido: " + e.getMessage());
        }

    }
}

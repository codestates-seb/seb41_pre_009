package stackoverflow.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import stackoverflow.auth.util.ErrorResponder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
        Exception exception = (Exception) request.getAttribute("exception");
        ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);

        log.warn("Unauthorized error happened: {}",
                exception != null ? exception.getMessage() : authException.getMessage());
    }
}

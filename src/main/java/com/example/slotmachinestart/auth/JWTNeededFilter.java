package com.example.slotmachinestart.auth;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.MACVerifier;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class JWTNeededFilter implements ContainerRequestFilter {

    public static final String JWT_SECRET = "this_secret_is_not_looong_enough";
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        String token = crc.getHeaderString(HttpHeaders.AUTHORIZATION);

        try {
            JWSObject jwsObject = JWSObject.parse(token);
            boolean verified = jwsObject.verify(new MACVerifier(JWT_SECRET));

            if (verified) {
                String payload = jwsObject.getPayload().toString();
                crc.setProperty("payload", payload);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}

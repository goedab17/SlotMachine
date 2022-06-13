package com.example.slotmachinestart.auth;

import com.example.slotmachinestart.LoginResource;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.MACVerifier;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;


/*
    Klasse:  4BHIF
    @author: Daniel Götz
*/
@JWTNeeded
@Provider
@Priority(Priorities.AUTHORIZATION)
public class JWTNeededFilter implements ContainerRequestFilter {



    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        String token = crc.getHeaderString("Authorization");
        System.out.println("crc checking");
        System.out.println(token);
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            boolean verified = jwsObject.verify(new MACVerifier(LoginResource.JWT_SECRET));

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

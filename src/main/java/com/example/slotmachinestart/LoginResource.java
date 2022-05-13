package com.example.slotmachinestart;


import com.example.slotmachinestart.bl.LoginData;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginResource {
    public static final String JWT_SECRET = "this_secret_is_not_looong_enough";
    private String createJWT(String payload) throws JOSEException {
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),new Payload(payload));
        jwsObject.sign(new MACSigner(JWT_SECRET.getBytes()));
        return jwsObject.serialize();
    }

    @POST
    @Produces("application/json")
    public Response login(LoginData loginData){
        if(loginData.getUser().equals("admin")&&loginData.getPwd().equals("admin")) {
            try {
                return Response.ok().header("Authorization", createJWT(loginData.getUser())).build();
            } catch (JOSEException ex) {
                ex.printStackTrace();

            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}

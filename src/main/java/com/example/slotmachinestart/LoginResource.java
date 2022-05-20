package com.example.slotmachinestart;


import com.example.slotmachinestart.bl.Database;
import com.example.slotmachinestart.pojo.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/login")
public class LoginResource {

    public static final String JWT_SECRET = "this_secret_is_not_looong_enough";
    private String createJWT(String payload) throws JOSEException {
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),new Payload(payload));
        jwsObject.sign(new MACSigner(JWT_SECRET.getBytes()));
        System.out.println("testing123");
        return jwsObject.serialize();

    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response login(User user){
        System.out.println("In Login");
        Database.getTheInstance().createUsers(user.getName(),user.getPwd());
        Database.getTheInstance().getUserByName(user.getName());
        System.out.println("user");
        System.out.println();
        if(user.getName().equals("admin")&& user.getPwd().equals("admin")) {
            try {
                return Response.ok().header("Authorization", createJWT(user.getName())).build();
            } catch (JOSEException ex) {
                ex.printStackTrace();

            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}

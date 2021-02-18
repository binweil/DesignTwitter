package com.twitter.controller;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.twitter.service.AuthenticationService;
import com.twitter.utils.AppConfig;
import com.twitter.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(allowCredentials = "true")
public class AuthenticationController {

    @Autowired
    private AWSCognitoIdentityProvider awsCognitoIdentityProvider;

    @RequestMapping(value = "/JSON/create-cognito-user", method = RequestMethod.GET)
    public @ResponseBody String cognitoCreateUser (HttpServletResponse response) throws IOException {
        awsCognitoIdentityProvider.adminCreateUser(new AdminCreateUserRequest()
                .withUserPoolId("us-west-2_3vzP55EYn")
                .withUsername("lamy")
                .withUserAttributes(
                        new AttributeType()
                                .withName("email")
                                .withValue("binweil@uci.edu"),
                        new AttributeType()
                                .withName("email_verified")
                                .withValue("true")
                ));
        return "success";
    }

    @RequestMapping(value = "/JSON/login-cognito-user", method = RequestMethod.POST)
    public @ResponseBody String cognitoLogin (@RequestBody Map<String, String> payload,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws IOException {
        HashMap<String, String> authParams = new HashMap<String, String>();
        authParams.put("USERNAME", payload.get("USERNAME"));
        authParams.put("PASSWORD", payload.get("PASSWORD"));
        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withUserPoolId((String)AppConfig.getParameter("Beta", "userPoolID"))
                .withClientId((String)AppConfig.getParameter("Beta", "clientID"))
                .withAuthParameters(authParams);
        AdminInitiateAuthResult authResult = awsCognitoIdentityProvider.adminInitiateAuth(authRequest);

        String accessToken = null;
        if (authResult != null) {
            AuthenticationResultType resultType = authResult.getAuthenticationResult();
            if (resultType != null) {
                accessToken = authResult.getAuthenticationResult().getAccessToken();
            }
        }
        return "success";
    }

    @RequestMapping(value = "/JSON/logout-cognito-user/{username}", method = RequestMethod.GET)
    public @ResponseBody String cognitoLogout (@PathVariable(value = "username") String username,
                                             HttpServletResponse response) throws IOException {
        AdminUserGlobalSignOutRequest signOutRequest = new AdminUserGlobalSignOutRequest()
                .withUsername(username)
                .withUserPoolId("us-west-2_3vzP55EYn");
        // The AdminUserGlobalSignOutResult returned by this function does not contain any useful information so the
        // result is ignored.
        awsCognitoIdentityProvider.adminUserGlobalSignOut(signOutRequest);
        return "logged out";
    }
}

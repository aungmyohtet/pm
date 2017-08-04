package com.aungmyohtet.pm.web;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.event.RegistrationCompleteEvent;
import com.aungmyohtet.pm.service.UserService;
import com.aungmyohtet.pm.validator.UserFormValidator;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEnconder;

    public void setPasswordEnconder(PasswordEncoder passwordEnconder) {
        this.passwordEnconder = passwordEnconder;
    }

    @Autowired
    @Qualifier("userFormValidator")
    private UserFormValidator userFormValidator;

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private HttpTransport transport;

    @Autowired
    private JsonFactory jsonfactory;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/google", method = RequestMethod.GET)
    public String showSignupFormNew(Model model) {
        model.addAttribute("user", new User());
        return "google";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated @ModelAttribute User user, BindingResult result, Model model, WebRequest request) {
        if (result.hasErrors()) {
            return "signup";
        }

        userFormValidator.validate(user, result);
        if (result.hasErrors()) {
            return "signup";
        }
        String token = UUID.randomUUID().toString();
        user.setPassword(passwordEnconder.encode(user.getPassword()));
        userService.createUserAndVerificationToken(user, token);
        /*try {
            eventPublisher.publishEvent(new RegistrationCompleteEvent(user.getEmail(), token, request.getLocale(), request.getContextPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return "redirect:/";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", this.userService.findAll());
        return "users";
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public String showDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "userDetails";
    }

    @RequestMapping(value = "/testSignupToken", method = RequestMethod.GET)
    @ResponseBody
    public String checkToken(WebRequest request, @RequestParam("id_token") String id_token) {

        System.out.println("in Controller.......");

        System.out.println("controller token=" + id_token);
        String CLIENT_ID = "155108207912-cj5o9d5hdn87fk7otmt8v3qh8v51a58q.apps.googleusercontent.com";
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonfactory).setAudience(Collections.singletonList(CLIENT_ID)).build();
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(id_token);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();
            //int userId = Integer.parseInt(payload.getSubject());
            //System.out.println("User ID: " + userId);
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            System.out.println("emailVerified" + emailVerified);
            System.out.println(">>>>>>>>>>>>> name is " + payload.get("given_name"));
            User user = new User();
            //user.setId(userId);
            user.setFirstName(givenName);
            user.setLastName(familyName);
            user.setEmail(email);
            user.setEnabled(true);
            user.setPassword("1111");
            //userService.createUserAndVerificationToken(user, id_token);
            try {
                //eventPublisher.publishEvent(new RegistrationCompleteEvent(user.getEmail(), id_token, request.getLocale(), request.getContextPath()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/";
        } else {
            System.out.println("Invalid ID token.");
            return "signup";
        }

    }

}

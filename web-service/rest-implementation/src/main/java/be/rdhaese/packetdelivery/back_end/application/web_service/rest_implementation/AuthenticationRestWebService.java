package be.rdhaese.packetdelivery.back_end.application.web_service.rest_implementation;


import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.AuthenticationInternalService;
import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.AuthenticationWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 22/12/2015.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/authenticate")
public class AuthenticationRestWebService implements AuthenticationWebService {

    @Autowired
    private AuthenticationInternalService authenticationInternalService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String authenticate(String username, String password){
       return authenticationInternalService.authenticate(username,password).toString();
    }
}

package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;


import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AuthenticateInternalService;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AuthenticateWebService;
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
public class AuthenticateRestWebService implements AuthenticateWebService {

    @Autowired
    private AuthenticateInternalService authenticateInternalService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public boolean authenticate(String username, String password){
        if (authenticateInternalService.authenticate(username, password)) {
            return true;
        }
        else {
            //TODO is HTTPStatus correct?
           return false;
        }
    }
}

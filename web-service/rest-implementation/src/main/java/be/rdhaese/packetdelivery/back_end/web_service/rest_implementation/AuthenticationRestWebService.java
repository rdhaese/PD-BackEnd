package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;


import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AuthenticationInternalService;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AuthenticationWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public @ResponseBody String authenticate(@PathVariable String username, @RequestBody String password){
       return authenticationInternalService.authenticate(username,password).toString();
    }
}

package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;


import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AuthenticationInternalService;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AuthenticationWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/authenticate")
public class AuthenticationRestWebService implements AuthenticationWebService {

    @Autowired
    private AuthenticationInternalService authenticationInternalService;

    @ResponseBody
    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    @Override
    public
    String authenticate(@PathVariable String username, @RequestBody String password) {
        return authenticationInternalService.authenticate(username, password).toString();
    }
}

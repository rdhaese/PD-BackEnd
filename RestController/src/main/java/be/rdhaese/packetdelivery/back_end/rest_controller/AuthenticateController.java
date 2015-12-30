package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.packetdelivery.back_end.service.AuthenticateService;
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
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public boolean authenticate(String username, String password){
        if (authenticateService.authenticate(username, password)) {
            return true;
        }
        else {
            //TODO is HTTPStatus correct?
           return false;
        }
    }
}

package be.rdhaese.packetdelivery.back_end.service;

import org.springframework.stereotype.Service;

/**
 * Created on 22/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AuthenticateService {

    public boolean authenticate(String username, String password) {
        //TODO connect with Active Directory
        System.out.println("Mocked connecting to Active Directory");
        if ("noob".equals(username.trim().toLowerCase())){
            return false;
        }
        return true;
    }
}

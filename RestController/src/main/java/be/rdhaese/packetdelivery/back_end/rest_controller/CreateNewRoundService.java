package be.rdhaese.packetdelivery.back_end.rest_controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 28/12/2015.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping ("/round")
public class CreateNewRoundService {

    @RequestMapping (value = "/new", method = RequestMethod.GET)
    public String newRound(@RequestParam int amountOfPackets){
        System.out.println("jeej");
        return "Packets: " + Integer.toString(amountOfPackets);
    }
}

package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.PacketInformationDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 30/04/2016.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/test-service")
public class LoadFromUrlTestRestWebService {

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public PacketInformationDTO testPacket1() {
        PacketInformationDTO packetInformationDTO = new PacketInformationDTO();

        packetInformationDTO.setClientName("Bol.com");
        packetInformationDTO.setClientPhone("+31 27/885999");
        packetInformationDTO.setClientEmail("bol-delivery-service@bol.com");
        packetInformationDTO.setClientStreet("Papendorpseweg");
        packetInformationDTO.setClientNumber("100");
        packetInformationDTO.setClientMailbox(null);
        packetInformationDTO.setClientPostalCode("3528");
        packetInformationDTO.setClientCity("Utrecht");

        packetInformationDTO.setDeliveryName("Blue Oase Hotel");
        packetInformationDTO.setDeliveryPhone("065/149678");
        packetInformationDTO.setDeliveryEmail("blue-oase-hotel@gmail.com");
        packetInformationDTO.setDeliveryStreet("Havenstraat");
        packetInformationDTO.setDeliveryNumber("18");
        packetInformationDTO.setDeliveryMailbox(null);
        packetInformationDTO.setDeliveryPostalCode("3600");
        packetInformationDTO.setDeliveryCity("Genk");

        return packetInformationDTO;
    }

    @RequestMapping(value = {"/packet2"}, method = RequestMethod.GET)
    public PacketInformationDTO testPacket2() {
        PacketInformationDTO packetInformationDTO = new PacketInformationDTO();

        packetInformationDTO.setClientName("Bol.com");
        packetInformationDTO.setClientPhone("+31 27/885999");
        packetInformationDTO.setClientEmail("bol-delivery-service@bol.com");
        packetInformationDTO.setClientStreet("Papendorpseweg");
        packetInformationDTO.setClientNumber("100");
        packetInformationDTO.setClientMailbox(null);
        packetInformationDTO.setClientPostalCode("3528");
        packetInformationDTO.setClientCity("Utrecht");

        packetInformationDTO.setDeliveryName("Andromeda Hotel");
        packetInformationDTO.setDeliveryPhone(null);
        packetInformationDTO.setDeliveryEmail(null);
        packetInformationDTO.setDeliveryStreet("Kursaal Westheling");
        packetInformationDTO.setDeliveryNumber("5");
        packetInformationDTO.setDeliveryMailbox(null);
        packetInformationDTO.setDeliveryPostalCode("8400");
        packetInformationDTO.setDeliveryCity("Oostende");

        return packetInformationDTO;
    }

    @RequestMapping(value = "/packet3", method = RequestMethod.GET)
    public PacketInformationDTO testPacket3() {
        PacketInformationDTO packetInformationDTO = new PacketInformationDTO();

        packetInformationDTO.setClientName("Bol.com");
        packetInformationDTO.setClientPhone("+31 27/885999");
        packetInformationDTO.setClientEmail("bol-delivery-service@bol.com");
        packetInformationDTO.setClientStreet("Papendorpseweg");
        packetInformationDTO.setClientNumber("100");
        packetInformationDTO.setClientMailbox(null);
        packetInformationDTO.setClientPostalCode("3528");
        packetInformationDTO.setClientCity("Utrecht");

        packetInformationDTO.setDeliveryName("D'Haese Robin");
        packetInformationDTO.setDeliveryPhone(null);
        packetInformationDTO.setDeliveryEmail(null);
        packetInformationDTO.setDeliveryStreet(null);
        packetInformationDTO.setDeliveryNumber(null);
        packetInformationDTO.setDeliveryMailbox(null);
        packetInformationDTO.setDeliveryPostalCode(null);
        packetInformationDTO.setDeliveryCity(null);

        return packetInformationDTO;
    }
}

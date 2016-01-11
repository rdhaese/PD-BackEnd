package be.rdhaese.packetdelivery.back_end.rest_controller.impl;

import be.rdhaese.packetdelivery.back_end.mapper.impl.CompanyContactDetailsMapper;
import be.rdhaese.packetdelivery.back_end.rest_controller.ContactInformationController;
import be.rdhaese.packetdelivery.back_end.service.CompanyContactDetailsService;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/contact-information")
public class ContactInformationControllerImpl implements ContactInformationController {

    @Autowired
    private CompanyContactDetailsService editCompanyContactDetailsService;
    @Autowired
    private CompanyContactDetailsMapper companyContactDetailsMapper;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ContactDetailsDTO get(){
        return companyContactDetailsMapper.mapToDto(editCompanyContactDetailsService.get());
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public boolean post(@RequestBody ContactDetailsDTO contactDetailsDTO){
        return editCompanyContactDetailsService.save(companyContactDetailsMapper.mapToBus(contactDetailsDTO));
    }

    @Override
    @RequestMapping(value = "/company-name", method = RequestMethod.GET)
    public String getCompanyName() {
        return editCompanyContactDetailsService.getCompanyName();
    }
}
package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.project.dto.ContactDetailsDTO;
import be.rdhaese.project.mapper.CompanyContactDetailsMapper;
import be.rdhaese.project.service.EditCompanyContactDetailsService;
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
public class EditContactInformationController {

    @Autowired
    private EditCompanyContactDetailsService editCompanyContactDetailsService;
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
}

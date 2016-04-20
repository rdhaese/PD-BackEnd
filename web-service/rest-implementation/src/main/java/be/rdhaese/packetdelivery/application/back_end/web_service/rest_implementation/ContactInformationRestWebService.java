package be.rdhaese.packetdelivery.application.back_end.web_service.rest_implementation;


import be.rdhaese.packetdelivery.application.back_end.internal_service.interfaces.CompanyContactDetailsInternalService;
import be.rdhaese.packetdelivery.application.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.application.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.application.back_end.web_service.interfaces.ContactInformationWebService;
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
public class ContactInformationRestWebService implements ContactInformationWebService {

    @Autowired
    private CompanyContactDetailsInternalService editCompanyContactDetailsInternalService;
    @Autowired
    private Mapper<CompanyContactDetails, ContactDetailsDTO> companyContactDetailsMapper;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ContactDetailsDTO get(){
        return companyContactDetailsMapper.mapToDto(editCompanyContactDetailsInternalService.get());
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public boolean post(@RequestBody ContactDetailsDTO contactDetailsDTO){
        return editCompanyContactDetailsInternalService.save(companyContactDetailsMapper.mapToBus(contactDetailsDTO));
    }

    @Override
    @RequestMapping(value = "/company-name", method = RequestMethod.GET)
    public String getCompanyName() {
        return editCompanyContactDetailsInternalService.getCompanyName();
    }
}

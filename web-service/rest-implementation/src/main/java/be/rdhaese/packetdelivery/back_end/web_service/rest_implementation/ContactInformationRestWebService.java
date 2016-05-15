package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;


import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.CompanyContactDetailsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/contact-information")
public class ContactInformationRestWebService implements ContactInformationWebService {

    @Autowired
    private CompanyContactDetailsInternalService companyContactDetailsInternalService;
    @Autowired
    private Mapper<CompanyContactDetails, ContactDetailsDTO> companyContactDetailsMapper;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody ContactDetailsDTO get() throws Exception {
        return companyContactDetailsMapper.mapToDto(companyContactDetailsInternalService.get());
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public boolean post(@RequestBody ContactDetailsDTO contactDetailsDTO) throws Exception {
        return companyContactDetailsInternalService.save(companyContactDetailsMapper.mapToBus(contactDetailsDTO));
    }

    @Override
    @RequestMapping(value = "/company-name", method = RequestMethod.GET)
    public String getCompanyName() throws Exception {
        return companyContactDetailsInternalService.getCompanyName();
    }
}

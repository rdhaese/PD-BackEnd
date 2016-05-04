package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;


import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.CompanyContactDetailsInternalService;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class CompanyContactDetailsInternalServiceImpl implements CompanyContactDetailsInternalService {

    @Autowired
    private CompanyContactDetailsRepository companyContactDetailsRepository;

    @Override
    public CompanyContactDetails get() throws Exception{
        try {
            return companyContactDetailsRepository.get();
        } catch (FileNotFoundException e) {
            //We can create new company details, when saving these, the file will be created
            return new CompanyContactDetails();
        }
    }

    @Override
    public boolean save(CompanyContactDetails companyContactDetails) throws Exception{
            companyContactDetailsRepository.save(companyContactDetails);

            //Return true if the app makes it to here
            return true;

    }

    @Override
    public String getCompanyName() throws Exception{
        CompanyContactDetails companyContactDetails = get();
        if (companyContactDetails == null){
            return null;
        }
        return companyContactDetails.getCompanyName();
    }
}

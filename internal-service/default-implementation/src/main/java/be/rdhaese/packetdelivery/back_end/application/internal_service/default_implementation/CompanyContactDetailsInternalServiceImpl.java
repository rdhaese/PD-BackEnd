package be.rdhaese.packetdelivery.back_end.application.internal_service.default_implementation;


import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.CompanyContactDetailsInternalService;
import be.rdhaese.packetdelivery.back_end.application.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.application.persistence.xml_repositories.interfaces.CompanyContactDetailsRepository;
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
    public CompanyContactDetails get() {
        try {
            return companyContactDetailsRepository.get();
        } catch (FileNotFoundException e) {
            //TODO maybe something else
            return new CompanyContactDetails();
        }catch (IOException e) {
            e.printStackTrace(); //TODO REMOVE
        } catch (JAXBException e) {
            e.printStackTrace(); //TODO REMOVE
        }
        return null;
    }

    @Override
    public boolean save(CompanyContactDetails companyContactDetails) {
        try {
            companyContactDetailsRepository.save(companyContactDetails);
            return true;
        } catch (JAXBException jaxbe) {
            jaxbe.printStackTrace(); //TODO REMOVE
            return false;
        }
    }

    @Override
    public String getCompanyName() {
        CompanyContactDetails companyContactDetails = get();
        if (companyContactDetails == null){
            return null;
        }
        return companyContactDetails.getCompanyName();
    }
}

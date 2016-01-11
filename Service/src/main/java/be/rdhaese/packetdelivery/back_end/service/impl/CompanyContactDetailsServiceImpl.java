package be.rdhaese.packetdelivery.back_end.service.impl;


import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.persistence.CompanyContactDetailsRepository;
import be.rdhaese.packetdelivery.back_end.service.CompanyContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class CompanyContactDetailsServiceImpl implements CompanyContactDetailsService {

    @Autowired
    private CompanyContactDetailsRepository companyContactDetailsRepository;

    @Override
    public CompanyContactDetails get() {
        try {
            return companyContactDetailsRepository.get();
        } catch (IOException e) {
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

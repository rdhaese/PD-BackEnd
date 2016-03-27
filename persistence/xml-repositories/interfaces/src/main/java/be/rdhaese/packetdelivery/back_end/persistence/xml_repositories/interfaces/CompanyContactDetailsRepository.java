package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces;


import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@Repository
public interface CompanyContactDetailsRepository {
    CompanyContactDetails get() throws JAXBException, IOException;
    void save(CompanyContactDetails companyContactDetails) throws JAXBException;
}

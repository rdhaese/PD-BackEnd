package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces;


import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Robin D'Haese
 */
@Repository
public interface CompanyContactDetailsRepository {
    CompanyContactDetails get() throws JAXBException, URISyntaxException, IOException, Exception;

    void save(CompanyContactDetails companyContactDetails) throws JAXBException, IOException, Exception;
}

package be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.model.company_details.CompanyContactDetails;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface CompanyContactDetailsInternalService {
    CompanyContactDetails get();
    boolean save(CompanyContactDetails companyContactDetails);

    String getCompanyName();
}

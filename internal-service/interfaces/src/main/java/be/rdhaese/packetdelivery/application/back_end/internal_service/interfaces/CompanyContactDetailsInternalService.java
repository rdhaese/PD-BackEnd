package be.rdhaese.packetdelivery.application.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.application.back_end.model.company_details.CompanyContactDetails;

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

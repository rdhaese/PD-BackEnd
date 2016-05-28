package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;

/**
 *
 * @author Robin D'Haese
 */
public interface CompanyContactDetailsInternalService {
    CompanyContactDetails get() throws Exception;

    boolean save(CompanyContactDetails companyContactDetails) throws Exception;

    String getCompanyName() throws Exception;
}

package be.rdhaese.packetdelivery.back_end.service;

import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface EditCompanyContactDetailsService {
    CompanyContactDetails get();
    boolean save(CompanyContactDetails companyContactDetails);
}

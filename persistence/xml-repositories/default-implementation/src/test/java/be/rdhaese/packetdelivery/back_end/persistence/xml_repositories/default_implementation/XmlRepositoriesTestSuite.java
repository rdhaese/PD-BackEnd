package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AppStateXmlRepositoryTest.class,
        CompanyContactDetailsXmlRepositoryTest.class,
        OptionsXmlRepositoryTest.class
})
public class XmlRepositoriesTestSuite {
}

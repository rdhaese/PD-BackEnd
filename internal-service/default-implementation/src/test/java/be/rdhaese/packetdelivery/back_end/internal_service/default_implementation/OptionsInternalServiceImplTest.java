package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.back_end.model.options.OptionsCollection;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation.OptionsXmlRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createOptions;
import static org.mockito.Mockito.*;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class OptionsInternalServiceImplTest extends TestCase {

    @InjectMocks
    private OptionsInternalServiceImpl optionsInternalService;

    @Mock
    private OptionsXmlRepository optionsXmlRepository;

    private OptionsCollection optionsCollection;

    @Before
    public void setUp() throws JAXBException, IOException {
        optionsCollection = new OptionsCollection();
        optionsCollection.addOptions(createOptions("user1", "language1", 0, false));
        optionsCollection.addOptions(createOptions("user2", "language2", 0, false));

        when(optionsXmlRepository.getOptionsCollection()).thenReturn(optionsCollection);
        when(optionsXmlRepository.save(any())).thenReturn(true);
    }

    @Test
    public void testGetForKnownUsername() throws Exception {
        TestCase.assertNotNull(optionsInternalService.getFor("user2"));
        verify(optionsXmlRepository, times(1)).getOptionsCollection();
    }

    @Test
    public void testGetForUnknownUsername() throws Exception {
        Options options = optionsInternalService.getFor("unknown user");
        TestCase.assertNull(options.getUser());
        TestCase.assertNotNull(options.getLanguage());
        TestCase.assertNotNull(options.getPrint());
        TestCase.assertNotNull(options.getImageViewer());
        verify(optionsXmlRepository, times(1)).getOptionsCollection();
    }

    @Test
    public void testSaveOptions() throws Exception {
        TestCase.assertTrue(optionsInternalService.save(createOptions("user3", "language3", 0, false)));
        verify(optionsXmlRepository, times(1)).getOptionsCollection();
        verify(optionsXmlRepository, times(1)).save(optionsCollection);
        TestCase.assertEquals(3, optionsCollection.getOptions().size());
    }

    @Test
    public void testSaveNull() throws Exception {
        verifyNoMoreInteractions(optionsXmlRepository);
        TestCase.assertFalse(optionsInternalService.save(null));
    }
}

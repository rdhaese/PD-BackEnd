package be.rdhaese.packetdelivery.back_end.mapper.default_implementation.aspect;

import be.rdhaese.packetdelivery.back_end.mapper.default_implementation.AddressMapper;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ReturnNullAspectTest.Config.class)
public class ReturnNullAspectTest extends TestCase {

    @Autowired
    private Mapper<Address, AddressDTO> mapper;

    @Test
    public void testReturnNullMapToBus() {
        AddressDTO addressDTO = null;
        //noinspection ConstantConditions
        TestCase.assertNull(mapper.mapToBus(addressDTO));
    }

    @Test
    public void testReturnNullMapToDto() {
        Address address = null;
        //noinspection ConstantConditions
        TestCase.assertNull(mapper.mapToDto(address));
    }

    @Configuration
    @EnableAspectJAutoProxy
    static class Config {

        @Bean
        public ReturnNullAspect returnNullAspect() {
            return new ReturnNullAspect();
        }

        @Bean
        public Mapper<Address, AddressDTO> addressMapper() {
            return new AddressMapper();
        }
    }

}

package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation.config;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.LongLatInternalServiceImpl;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.*;
import be.rdhaese.packetdelivery.back_end.mapper.default_implementation.AppStateMapper;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.DeliveryAddressMapper;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.model.app_state.AppState;
import be.rdhaese.packetdelivery.back_end.model.company_details.CompanyContactDetails;
import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.dto.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.mock;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "be.rdhaese.packetdelivery.back_end.web_service.rest_implementation")
public class TestConfig {

    //Mocks
    @Bean
    public AddPacketInternalService addPacketInternalService() {
        return mock(AddPacketInternalService.class);
    }

    @Bean
    public AppInternalService appInternalService() {
        return mock(AppInternalService.class);
    }

    @Bean
    public AuthenticationInternalService authenticationInternalService() {
        return mock(AuthenticationInternalService.class);
    }

    @Bean
    public CompanyContactDetailsInternalService companyContactDetailsInternalService() {
        return mock(CompanyContactDetailsInternalService.class);
    }

    @Bean
    public DeliveryRoundInternalService deliveryRoundInternalService() {
        return mock(DeliveryRoundInternalService.class);
    }

    @Bean
    public LostPacketsInternalService lostPacketsInternalService() {
        return mock(LostPacketsInternalService.class);
    }

    @Bean
    public OptionsInternalService optionsInternalService() {
        return mock(OptionsInternalService.class);
    }

    @Bean
    public ProblematicPacketsInternalService problematicPacketsInternalService() {
        return mock(ProblematicPacketsInternalService.class);
    }

    @Bean
    public RegionsInternalService regionsInternalService() {
        return mock(RegionsInternalService.class);
    }

    @Bean
    public TrackerInternalService trackerInternalService() {
        return mock(TrackerInternalService.class);
    }

    @Bean
    public LongLatInternalService longLatInternalService(){
        return mock(LongLatInternalService.class);
    }

    @Bean
    public DeliveryAddressMapper deliveryAddressMapper() {
        return mock(DeliveryAddressMapper.class);
    }

    @Bean
    public Mapper<Packet, PacketDTO> packetMapper() {
        return mock(Mapper.class);
    }

    @Bean
    public Mapper<AppState, AppStateDTO> appStateMapper() {
        return mock(AppStateMapper.class);
    }

    @Bean
    public Mapper<CompanyContactDetails, ContactDetailsDTO> contactDetailsMapper() {
        return mock(Mapper.class);
    }

    @Bean
    public Mapper<LongLat, LongLatDTO> longLatMapper() {
        return mock(Mapper.class);
    }

    @Bean
    public Mapper<Address, AddressDTO> addressMapper() {
        return mock(Mapper.class);
    }

    @Bean
    public Mapper<Options, OptionsDTO> optionsMapper() {
        return mock(Mapper.class);
    }


    @Bean
    public Mapper<Region, RegionDTO> regionMapper() {
        return mock(Mapper.class);
    }

    @Bean
    public Mapper<LocationUpdate, LocationUpdateDTO> locationUpdateMapper() {
        return mock(Mapper.class);
    }

    @Bean
    public Mapper<Remark, RemarkDTO> remarkMapper() {
        return mock(Mapper.class);
    }


}

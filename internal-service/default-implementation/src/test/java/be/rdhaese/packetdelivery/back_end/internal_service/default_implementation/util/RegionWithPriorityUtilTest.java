package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.comparator.*;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRegion;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.*;
/**
 * Created on 9/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RegionWithPriorityUtil.class, RegionWithPriorityUtilTest.Config.class})
public class RegionWithPriorityUtilTest extends TestCase{

    @Configuration
    static class Config {

        @Bean
        public PacketJpaRepository packetJpaRepository(){
            return mock(PacketJpaRepository.class);
        }

        @Bean
        public Comparator<RegionWithPriority> regionWithPriorityOnPriorityComparator() {
            return new RegionWithPriorityOnPriorityComparator();
        }

        @Bean
        public Comparator<RegionWithPriority> regionWithPriorityOnPacketCountComparator() {
            return new RegionWithPriorityOnPacketCountComparator();
        }

        @Bean
        public Comparator<RegionWithPriority> regionWithPriorityOnRegionCodeComparator() {
            return new RegionWithPriorityOnRegionCodeComparator();
        }
    }

    @Autowired
    private RegionWithPriorityUtil regionWithPriorityUtil;

    @Autowired //Mock -> see config above
    private PacketJpaRepository packetJpaRepository;

    @Test
    public void testGetRegionWithHighestTotalPriority(){
        Region region1 = createRegion(null, "CODE1");
        region1.setId(1L);
        Region region2 = createRegion(null, "CODE2");
        region2.setId(2L);
        Region region3 = createRegion(null, "CODE3");
        region3.setId(3L);
        List<Region> regions = Arrays.asList(region1, region2, region3);
        Packet packet1 = new Packet();
        packet1.setPriority(1);
        Packet packet2 = new Packet();
        packet2.setPriority(2);
        Packet packet3 = new Packet();
        packet3.setPriority(1);
        List<Packet> region1Packets = new ArrayList<>();
        List<Packet> region2Packets = Arrays.asList(packet1, packet3);
        List<Packet> region3Packets = Arrays.asList(packet2);

        when(packetJpaRepository.getForRegion(1L)).thenReturn(region1Packets);
        when(packetJpaRepository.getForRegion(2L)).thenReturn(region2Packets);
        when(packetJpaRepository.getForRegion(3L)).thenReturn(region3Packets);

        assertEquals(region2, regionWithPriorityUtil.getRegionWithHighestTotalPriority(regions));
    }

    @Test
    public void testNullReturnedWhenAllRegionsHaveNoPackets(){
        Region region1 = createRegion(null, "CODE1");
        region1.setId(1L);
        Region region2 = createRegion(null, "CODE2");
        region2.setId(2L);
        Region region3 = createRegion(null, "CODE3");
        region3.setId(3L);
        List<Region> regions = Arrays.asList(region1, region2, region3);

        when(packetJpaRepository.getForRegion(1L)).thenReturn(new ArrayList<Packet>());
        when(packetJpaRepository.getForRegion(2L)).thenReturn(new ArrayList<Packet>());
        when(packetJpaRepository.getForRegion(3L)).thenReturn(new ArrayList<Packet>());

        assertNull(regionWithPriorityUtil.getRegionWithHighestTotalPriority(regions));
    }

    @Test
    public void testGetRegionWithHighestTotalPriorityNullRegions(){
        verifyNoMoreInteractions(packetJpaRepository);
        assertNull(regionWithPriorityUtil.getRegionWithHighestTotalPriority(null));
    }

    @Test
    public void testGetRegionWithHighestTotalPriorityEmptyRegions(){
        verifyNoMoreInteractions(packetJpaRepository);
        assertNull(regionWithPriorityUtil.getRegionWithHighestTotalPriority(new ArrayList<>()));
    }
}

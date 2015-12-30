package be.rdhaese.packetdelivery.back_end.service;

import be.rdhaese.project.model.Packet;
import be.rdhaese.project.persist.PacketJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created on 10/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AddPacketService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMyyyy");
    private static final String ID_SEPARATOR = "-";

    private static LocalDate lastDateChecked = LocalDate.now();
    private static int previousNumber = 0;
    @Autowired
    private PacketJpaRepository packetRepository;

    public String savePacket(Packet packet) {
        generatePacketId(packet);
        packetRepository.save(packet);
        return packet.getPacketId();
    }

    private void generatePacketId(Packet packet) {
        StringBuilder packetId = new StringBuilder();
        if (packet.getClientInfo().getContactDetails().getName().length() <= 1) {
            packetId.append(packet.getClientInfo().getContactDetails().getName());
            while (packetId.length() < 2) {
                packetId.append("_");
            }
        } else {
            packetId.append(packet.getClientInfo().getContactDetails().getName().substring(0, 2));
        }
        packetId.append(ID_SEPARATOR);
        if (packet.getDeliveryInfo().getClientInfo().getContactDetails().getName().length() <= 1) {
            packetId.append(packet.getDeliveryInfo().getClientInfo().getContactDetails().getName());
            while (packetId.length() < 2) {
                packetId.append("_");
            }
        } else {
            packetId.append(packet.getDeliveryInfo().getClientInfo().getContactDetails().getName().substring(0, 2));
        }
        packetId.append(ID_SEPARATOR)
                .append(DATE_FORMAT.format(packet.getStatusChangedOn()))
                .append(ID_SEPARATOR)
                .append(getLast5Numbers(packet.getStatusChangedOn()));
        packet.setPacketId(packetId.toString().toUpperCase());
    }

    private StringBuilder getLast5Numbers(Date date) {
        LocalDate packetDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        StringBuilder numbers = new StringBuilder();
        if (lastDateChecked.isBefore(LocalDate.from(packetDate))) {
            previousNumber = 0;
        }
        numbers.append(previousNumber);
        while (numbers.length() < 5) {
            numbers.insert(0, 0);
        }
        previousNumber++;
        lastDateChecked = packetDate;
        return numbers;
    }
}
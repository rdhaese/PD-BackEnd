package be.rdhaese.packetdelivery.back_end.application.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.application.mapper.interfaces.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.application.model.Remark;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import org.springframework.stereotype.Component;

/**
 * Created on 19/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class RemarkMapper extends AbstractMapper<Remark, RemarkDTO> {
    @Override
    public Remark mapToBus(RemarkDTO dto) {
        if (dto == null) {
            return null;
        }
        Remark remark = new Remark();
        remark.setTimeAdded(dto.getTimeAdded());
        remark.setRemark(dto.getDescription());
        return remark;
    }

    @Override
    public RemarkDTO mapToDto(Remark busObj) {
       if (busObj == null){
           return null;
       }
        RemarkDTO dto = new RemarkDTO();
        dto.setTimeAdded(busObj.getTimeAdded());
        dto.setDescription(busObj.getRemark());
        return dto;
    }
}

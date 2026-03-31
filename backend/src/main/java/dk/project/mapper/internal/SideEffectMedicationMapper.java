package dk.project.mapper.internal;

import dk.project.dto.internal.SideEffectMedicationDTO;
import dk.project.entity.SideEffectMedication;

public class SideEffectMedicationMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static SideEffectMedicationDTO toDTO(SideEffectMedication sideEffectMedication){
        SideEffectMedicationDTO dto = new SideEffectMedicationDTO();
        dto.setId(sideEffectMedication.getId());
        dto.setName(sideEffectMedication.getName());
        dto.setHeader(sideEffectMedication.getHeader());
        return dto;
    }

}

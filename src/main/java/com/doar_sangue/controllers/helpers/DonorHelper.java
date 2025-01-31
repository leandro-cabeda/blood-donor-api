package com.doar_sangue.controllers.helpers;

import com.doar_sangue.controllers.dto.DonorDTO;
import com.doar_sangue.entities.Donor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("donorHelperFromHelper")
public class DonorHelper {

    private final ModelMapper modelMapper;

    public DonorHelper( ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DonorDTO toModel(Donor donor) {
        return modelMapper.map(donor, DonorDTO.class);
    }

    public Donor toEntity(DonorDTO donorDto) {
        return modelMapper.map(donorDto, Donor.class);
    }

    public List<Donor> toEntity(List<DonorDTO> donorDtos) {
        return donorDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<DonorDTO> toModel(List<Donor> donors) {
        return donors.stream().map(this::toModel).collect(Collectors.toList());
    }
}

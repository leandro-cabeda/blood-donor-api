package com.doar_sangue.controllers.helpers;

import com.doar_sangue.controllers.dto.AddressDTO;
import com.doar_sangue.entities.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("addressHelperFromHelper")
public class AddressHelper {

    private final ModelMapper modelMapper;

    public AddressHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AddressDTO toModel(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

    public Address toEntity(AddressDTO addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

    public List<Address> toEntity(List<AddressDTO> addressDtos) {
        return addressDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<AddressDTO> toModel(List<Address> addresses) {
        return addresses.stream().map(this::toModel).collect(Collectors.toList());
    }


}

package at.htl.mappers;

import at.htl.dtos.CustomerDto;
import at.htl.entities.Customer;

public class CustomerMapper {

    public static CustomerDto toDto(Customer customer) {
        if (customer == null) return null;

        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setAge(customer.getAge());
        dto.setEmail(customer.getEmail());
        dto.setCity(customer.getCity());
        dto.setAddress(customer.getAddress());
        dto.setZipCode(customer.getZipCode());
        dto.setKtmScore(customer.getKtmScore());

        return dto;
    }
}

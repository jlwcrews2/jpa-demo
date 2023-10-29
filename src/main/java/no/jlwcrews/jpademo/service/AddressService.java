package no.jlwcrews.jpademo.service;

import no.jlwcrews.jpademo.model.Address;
import no.jlwcrews.jpademo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address addAddress(Address Address) {
        return addressRepository.save(Address);
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }
}

package my.springframework.services;

import lombok.RequiredArgsConstructor;
import my.springframework.api.v1.mapper.VendorMapper;
import my.springframework.api.v1.model.VendorDTO;
import my.springframework.controllers.v1.VendorController;
import my.springframework.domain.Vendor;
import my.springframework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorBy(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        return saveAndReturnVendorDTO(vendor);
    }

    @Override
    public VendorDTO saveVendorByVendorDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnVendorDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(foundVendor -> {
                    if (vendorDTO.getName() != null){
                        foundVendor.setName(vendorDTO.getName());
                    }
                    return vendorRepository.save(foundVendor);
                })
                .map(savedVendor -> {
                    VendorDTO resultVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
                    resultVendorDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));
                    return resultVendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturnVendorDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO resultVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        resultVendorDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return resultVendorDTO;
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}

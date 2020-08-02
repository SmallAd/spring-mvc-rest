package my.springframework.api.v1.mapper;

import my.springframework.api.v1.model.VendorDTO;
import my.springframework.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VendorMapperTest {

    public static final String NAME = "Jimmy";
    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    void vendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(2L);
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertNotNull(vendorDTO);
        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    void vendorDTOToVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        vendorDTO.setVendorUrl("test");

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertNotNull(vendor);
        assertEquals(NAME, vendor.getName());
    }
}
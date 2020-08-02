package my.springframework.services;

import my.springframework.api.v1.mapper.VendorMapper;
import my.springframework.api.v1.model.VendorDTO;
import my.springframework.domain.Vendor;
import my.springframework.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VendorServiceImplTest {

    public static final String VENDOR_NAME = "VendorName";
    public static final String URL = "/api/v1/vendors";
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void getAllVendors() {
        when(vendorRepository.findAll()).thenReturn(Arrays.asList(new Vendor(), new Vendor(), new Vendor()));

        List<VendorDTO> vendors = vendorService.getAllVendors();

        assertNotNull(vendors);
        assertEquals(3, vendors.size());
    }

    @Test
    void getVendorBy() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(VENDOR_NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorBy(1L);

        assertNotNull(vendorDTO);
        assertEquals(VENDOR_NAME, vendorDTO.getName());
        assertEquals(URL + "/1", vendorDTO.getVendorUrl());
    }

    @Test
    void createNewVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(VENDOR_NAME);

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(VENDOR_NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        assertNotNull(savedVendorDTO);
        assertEquals(VENDOR_NAME, savedVendorDTO.getName());
        assertEquals(URL + "/1", savedVendorDTO.getVendorUrl());
    }

    @Test
    void saveVendorByVendorDTO() {

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(VENDOR_NAME);

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(VENDOR_NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.saveVendorByVendorDTO(1L, vendorDTO);

        assertNotNull(savedVendorDTO);
        assertEquals(VENDOR_NAME, savedVendorDTO.getName());
        assertEquals(URL + "/1", savedVendorDTO.getVendorUrl());
    }

    @Test
    void patchVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(VENDOR_NAME);

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(VENDOR_NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.patchVendor(1L, vendorDTO);

        assertNotNull(savedVendorDTO);
        assertEquals(VENDOR_NAME, savedVendorDTO.getName());
        assertEquals(URL + "/1", savedVendorDTO.getVendorUrl());
    }

    @Test
    void deleteVendorById() {

        vendorService.deleteVendorById(1L);

        verify(vendorRepository).deleteById(anyLong());
    }
}
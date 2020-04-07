package com.szabicycle.szabicycle.controller;

import com.szabicycle.szabicycle.model.Product;
import com.szabicycle.szabicycle.payload.UploadFileResponse;
import com.szabicycle.szabicycle.repository.ProductRepository;
import com.szabicycle.szabicycle.service.FileStorageService;
import com.szabicycle.szabicycle.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@AllArgsConstructor
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private ProductService productService;
    private FileStorageService fileStorageService;
    private ProductRepository productRepository;

    @GetMapping("/get-all-product")
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @GetMapping("/get-all-bicycle")
    public List<Product> getAllBicycle(){
        return productRepository.findAllBicycle();
    }

    @GetMapping("/get-all-component")
    public List<Product> getAllComponent(){
        return productRepository.findAllComponent();
    }

    @GetMapping("/product/{productId}")
    public Optional<Product> getProductById(@PathVariable("productId") Long id){
        return productRepository.findById(id);
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("image") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/saveProduct")
    public void saveProduct(@RequestBody Map<String, String>  product){
        productService.saveProduct(product);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("images") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
    }

    @PostMapping("/updateProduct")
    public Product updateProduct(@RequestBody Map<String, String> data){
        return productService.updateProduct(data);
    }
}

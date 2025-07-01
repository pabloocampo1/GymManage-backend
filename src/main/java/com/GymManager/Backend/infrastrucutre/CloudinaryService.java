package com.GymManager.Backend.infrastrucutre;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IOException("Archivo no válido");
        }

        try {
            Map<String, Object> params = ObjectUtils.asMap(
                    "folder", "inventario",
                    "public_id", UUID.randomUUID().toString(),
                    "resource_type", "auto"
            );

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
            return uploadResult.get("secure_url").toString();

        } catch (Exception e) {
            throw new IOException("Error al procesar la imagen: " + e.getMessage(), e);
        }
    }

    public String uploadQrToCloudinary(byte[] qrBytes, String publicId) {
        try {
            Map uploadResult = cloudinary.uploader().upload(qrBytes, ObjectUtils.asMap(
                    "resource_type", "image",
                    "public_id", publicId,
                    "overwrite", true
            ));
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("Error uploading QR to Cloudinary", e);
        }
    }
}
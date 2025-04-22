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
}
package com.commercial.logbook_app.service;

import com.cloudinary.Cloudinary;
import com.commercial.logbook_app.exception.FuncErrorException;
import com.commercial.logbook_app.response.CloudinaryResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

//public class CloudinaryService {
//
//    @Autowired
//    private Cloudinary cloudinary;
//
//    @Transactional
//    public CloudinaryResponse uploadFile(final MultipartFile file, final String fileName) {
//        try {
//            final Map result = cloudinary.uploader().upload(file.getBytes(), Map.of("public_id", "record/payment_receipt/" + fileName));
//            final String url = (String) result.get("secure_url");
//            final String publicId = (String) result.get("public_id");
//            return CloudinaryResponse.builder().publicId(publicId).url(url).build();
//
//        } catch (Exception e) {
//            throw new FuncErrorException("Failed to upload file");
//        }
//    }
//}


@Service
public class CloudinaryService {


    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    public CloudinaryResponse uploadFile(MultipartFile file, String fileName) {
        try {
            Map<String, Object> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    Map.of("public_id", "record/payment_receipt/" + fileName)
            );

            return CloudinaryResponse.builder()
                    .publicId((String) result.get("public_id"))
                    .url((String) result.get("secure_url"))
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Upload failed: " + e.getMessage());
        }
    }

    // 🔥 NEW: delete image
    public void deleteFile(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, Map.of());
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete image: " + e.getMessage());
        }
    }
}
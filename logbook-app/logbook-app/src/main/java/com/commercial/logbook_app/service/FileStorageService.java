//    package com.commercial.logbook_app.service;
//
//    import org.springframework.stereotype.Service;
//    import org.springframework.web.multipart.MultipartFile;
//
//    import java.io.IOException;
//    import java.nio.file.Files;
//    import java.nio.file.Path;
//    import java.nio.file.Paths;
//
//    @Service
//    public class FileStorageService {
//
//        public String store(MultipartFile file, String imageLocation) throws IOException {
//            Path imagePath = Paths.get(imageLocation, file.getOriginalFilename());
//            Files.write(imagePath, file.getBytes());
//            return file.getOriginalFilename();
//        }
//
//        public byte[] get(String fileName, String imageLocation) throws IOException {
//            Path imagePath = Paths.get(imageLocation, fileName);
//            return Files.readAllBytes(imagePath);
//        }
//
//
//    }

package com.commercial.logbook_app.service;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

  public String store(MultipartFile file, String imageLocation) throws IOException {

    Path uploadPath = Paths.get(imageLocation);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

    Path imagePath = uploadPath.resolve(fileName);

    Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

    return fileName;
  }

  public byte[] get(String fileName, String imageLocation) throws IOException {
    Path imagePath = Paths.get(imageLocation, fileName);
    return Files.readAllBytes(imagePath);
  }
}

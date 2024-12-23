package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.exception.UnauthorizedFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Service responsible for processing image recording.
 */
@Slf4j
@Service
public class FileStorageServiceImpl implements FileStorageService {


    @Value("${base.url}")
    private String BASE_URL;

    @Value("${file.upload-dir}")
    private String BASE_DIR;

    public static final String FILE_PREFIX = "rental_";

    public static final String JPG_MIMETYPE = "image/jpeg";

    public static final String JPG_EXTENSION = ".jpg";

    public static final String PNG_MIMETYPE = "image/png";

    public static final String PNG_EXTENSION = ".png";


    /**
     * Saves a file in a local folder and returns its accessible URL.
     *
     * @param file the file to be saved
     * @param ownerId the ID of the owner
     * @param rentalId the ID of the rental
     * @return the URL of the saved file
     * @throws IOException
     * @throws UnauthorizedFileException if the file's MIME type is not authorized
     */
    @Override
    public String saveFile(MultipartFile file, int ownerId, int rentalId) throws IOException, UnauthorizedFileException {
        log.info("Try to save picture in local folder");
        String extension = this.checkFileMimeType(file);

        //Create the directory if not exist
        Path ownerPath = Paths.get(BASE_DIR + "owner_" + ownerId);
        if (!Files.exists(ownerPath)) {
            log.info("create owner_{} directory", ownerId);
            Files.createDirectories(ownerPath);
        }

        Path filePath = this.defineFilePath(ownerPath, rentalId, extension);
        log.info("save the file to this location {}", filePath);

        file.transferTo(filePath);

        return BASE_URL + filePath;
    }

    /**
     * Defines the file path for a given rental based on the owner's directory and file extension
     *
     * @param ownerPath the directory path of the owner
     * @param rentalId the ID of the rental
     * @param extension the file extension
     * @return the path to the file
     */
    private Path defineFilePath(Path ownerPath, int rentalId, String extension) {
        String fileName = FILE_PREFIX + rentalId + extension;
        return ownerPath.resolve(fileName);
    }

    /**
     * Checks the MIME type of file and determines its extension.
     *
     * @param file whose MIME type is to be checked.
     * @return the file extension corresponding to the MIME type
     * @throws UnauthorizedFileException if the file's MIME type is not allowed
     */
    private String checkFileMimeType(MultipartFile file) throws UnauthorizedFileException {
        log.info("Check file mime type");
        String mimeType = file.getContentType();
        if (JPG_MIMETYPE.equals(mimeType)) {
            log.info("file is {} mime type", mimeType);
            return JPG_EXTENSION;
        } else if (PNG_MIMETYPE.equals(mimeType)) {
            log.info("file is {} mime type", mimeType);
            return PNG_EXTENSION;
        } else {
            log.info("file is {} mime type", mimeType);
            log.error("file unauthorized");
            throw new UnauthorizedFileException();
        }

    }
}

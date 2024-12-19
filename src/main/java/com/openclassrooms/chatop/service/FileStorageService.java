package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.exception.UnauthorizedFileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    String saveFile (MultipartFile file, int ownerId, int rentalId) throws IOException, UnauthorizedFileException;
}

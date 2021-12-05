package com.sanvui.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author: VuiSK
 * @created: 01/12/2021-11:52 AM
 * @mailto: sanvankhanh@gmail.com
 */

public interface FileLocalStorageService {
    String saveFile(MultipartFile file, String folderUser) throws IOException;
    Resource loadFileAsResource(String filePath) throws MalformedURLException, FileNotFoundException;
}

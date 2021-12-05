package com.sanvui.service.imp;

import com.sanvui.service.FileLocalStorageService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

/**
 * @author: VuiSK
 * @created: 01/12/2021-11:55 AM
 * @mailto: sanvankhanh@gmail.com
 */

public class S3AwsStorageService implements FileLocalStorageService {
    @Override
    public String saveFile(MultipartFile file, String folderUser) {

        return "";
    }

    @Override
    public Resource loadFileAsResource(String filePath) throws MalformedURLException, FileNotFoundException {
        return null;
    }

    @Override
    public String buildUrl(String fileName) {
        return null;
    }
}

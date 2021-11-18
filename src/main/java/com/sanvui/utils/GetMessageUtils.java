package com.sanvui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: VuiSK
 * @created: 13/11/2021-10:11 AM
 * @mailto: sanvankhanh@gmail.com
 */

public class GetMessageUtils {
    public static String getMessage(String keyValue) {

        Properties properties = new Properties();
        InputStream inputStream = null;

        String value = null;


        try {

            inputStream = new FileInputStream("src/main/resources/ValidationMessages.properties");

//            load properties from file
            properties.load(inputStream);

//            get properties by name
            value = properties.getProperty(keyValue);

        } catch (IOException e) {
            return null;
        } finally {
            // close objects
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}

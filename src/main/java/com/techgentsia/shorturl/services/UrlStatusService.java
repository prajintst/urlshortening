package com.techgentsia.shorturl.services;

import com.techgentsia.shorturl.exception.UrlNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class UrlStatusService {
    public void checkUrlExists(String url) throws UrlNotFoundException {
        try {
            HttpURLConnection huc = (HttpURLConnection)new URL(url).openConnection();
            huc.setRequestMethod("HEAD");
            huc.setInstanceFollowRedirects(false);
            huc.connect() ;
            if(huc.getResponseCode() == HttpURLConnection.HTTP_OK){
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new UrlNotFoundException();

    }
}

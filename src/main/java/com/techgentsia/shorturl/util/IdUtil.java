package com.techgentsia.shorturl.util;

import java.util.Base64;


public class IdUtil {
    public static String idToString(int id){
        return Base64.getUrlEncoder().encodeToString(ByteUtils.intToBytes(id));
    }

    public static int stringToId(String id){

        return ByteUtils.bytesToInt(Base64.getUrlDecoder().decode(id));
    }
}

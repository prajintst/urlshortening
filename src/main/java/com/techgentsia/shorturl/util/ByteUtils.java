package com.techgentsia.shorturl.util;

import java.nio.ByteBuffer;

public class ByteUtils {

    public static byte[] intToBytes(int x) {
        return ByteBuffer.allocate(Integer.BYTES).putInt(0, x).array();
    }

    public static int bytesToInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES)
                .put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getInt();
    }
}
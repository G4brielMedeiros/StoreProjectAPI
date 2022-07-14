package dev.gabriel.storeproject.controller.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class URL {

    public static String decodeParam(String s) {
        return URLDecoder.decode(s, StandardCharsets.UTF_8);
    }

    public static List<Integer> decodeIntegerList(String string) {
        return Arrays.stream(string.split(",")).map(Integer::parseInt).toList();
    }
}

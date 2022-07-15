package dev.gabriel.storeproject.controller.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;

public class URL {

    public static String decodeParam(String s) {
        return URLDecoder.decode(s, StandardCharsets.UTF_8);
    }

    public static List<Integer> decodeIntegerList(String string) {
        return Stream.of(string.split(",")).map(Integer::parseInt).toList();
    }
}

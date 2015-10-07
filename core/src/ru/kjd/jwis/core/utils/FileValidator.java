package ru.kjd.jwis.core.utils;

/**
 * Created by anonymous on 10/1/15.
 */
public interface FileValidator {
    boolean filenameValid(String filename);
    String extractKey(String s);
    String extractValue(String s);
}

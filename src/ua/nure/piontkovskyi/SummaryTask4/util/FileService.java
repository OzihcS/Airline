package ua.nure.piontkovskyi.SummaryTask4.util;

import java.io.File;
import java.util.List;

/**
 * Util for file managing. Useful with servlets.
 */
public interface FileService {

    /**
     * Saves with the specified name to the specified directory from the {@code file} part.
     *
     * @param name         name of the file
     * @param subDirectory relative path to directory to store file in
     * @param file         part that contains the file
     * @return generated file name
     */
    String saveFile(Integer name, String subDirectory, String file);

    /**
     * Saves with the specified name to the specified directory from the {@code file} part.
     *
     * @param name         name of the file
     * @param subDirectory relative path to directory to store file in
     * @param file         part that contains the file
     * @return generated file name
     */
    String saveFile(String name, String subDirectory, String file);

    /**
     * Loads file from using relative path to it
     *
     * @param path relative path to file
     * @return file found
     */
    File getFile(String path);

    /**
     * Removes file with the specified name (with extension)
     *
     * @param name         name of the file to remove
     * @param subDirectory sub directory to look in
     */
    void removeFile(String name, String subDirectory);

    /**
     * Removes a list of files with the specified names (with extensions)
     *
     * @param names        names of the files to remove
     * @param subDirectory sub directory to look in
     */
    void removeFiles(List<String> names, String subDirectory);

    /**
     * Gets the file extension from its name.
     *
     * @param fileName file name
     * @return file extension
     */
    String getExtension(String fileName);
}

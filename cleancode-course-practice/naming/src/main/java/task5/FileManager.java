package task5;

import task5.thirdpartyjar.InvalidDirectoryException;
import task5.thirdpartyjar.InvalidFileTypeException;
import task5.thirdpartyjar.PropertyUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileManager {

    private static final String[] IMAGE_EXTENSIONS = {"jpg", "png"};
    private static final String[] DOCUMENT_EXTENSIONS = {"pdf", "doc"};

    private String basePath = PropertyUtil.loadProperty("basePath");

    private FileExtensionsPredicate fileExtensionsPredicate;

    public File retrieveFile(String fileName) {
        validateFileExtension(fileName);
        return getFile(fileName);
    }

    public List<String> getAllImages() {
        return files(basePath, IMAGE_EXTENSIONS);
    }

    public List<String> getAllDocumentFiles() {
        return files(basePath, DOCUMENT_EXTENSIONS);
    }

    private void validateFileExtension(String fileName) {
        if (isInvalidFileExtension(fileName)) {
            throw new InvalidFileTypeException("File type not Supported: " + fileName);
        }
    }

    private File getFile(String fileName) {
        return Paths.get(buildDirectoryPath(), fileName).toFile();
    }

    private String buildDirectoryPath(){
        return basePath + File.separator;
    }

    private boolean isInvalidFileExtension(String fileName) {
        return isInvalidImage(fileName) && isInvalidDocument(fileName);
    }

    private boolean isInvalidImage(String fileName) {
        fileExtensionsPredicate = new FileExtensionsPredicate(IMAGE_EXTENSIONS);
        return !fileExtensionsPredicate.test(fileName);
    }

    private boolean isInvalidDocument(String fileName) {
        fileExtensionsPredicate = new FileExtensionsPredicate(DOCUMENT_EXTENSIONS);
        return !fileExtensionsPredicate.test(fileName);
    }

    private List<String> files(String directoryPath, String[] allowedExtensions) {
        fileExtensionsPredicate = new FileExtensionsPredicate(allowedExtensions);
        return Arrays.asList(getFileDirectory(directoryPath).list(getFilenameFilterByPredicate(fileExtensionsPredicate)));
    }

    private FilenameFilter getFilenameFilterByPredicate(FileExtensionsPredicate predicate) {
        return (dir, str) -> predicate.test(str);
    }

    private File getFileDirectory(String directoryPath) {
        File fileDirectory = new File(directoryPath);
        validateDirectory(fileDirectory);
        return fileDirectory;
    }

    private void validateDirectory(File fileDirectory) {
        if (isNotDirectory(fileDirectory)) {
            throw new InvalidDirectoryException("Invalid directory found: " + fileDirectory.getAbsolutePath());
        }
    }

    private boolean isNotDirectory(File fileDirectory) {
        return !fileDirectory.isDirectory();
    }

}
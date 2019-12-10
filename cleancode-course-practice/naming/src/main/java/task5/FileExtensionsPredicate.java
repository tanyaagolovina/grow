package task5;


import task5.thirdpartyjar.Predicate;

public class FileExtensionsPredicate implements Predicate<String> {

    private String[] extensions;

    FileExtensionsPredicate(String[] extensions) {
        this.extensions = extensions;
    }

    @Override
    public boolean test(String fileName) {
        for (String extension : extensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}

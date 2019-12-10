package eventloggerapp.annotations;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    @Value("${fileName}")
    private String fileName;

    private File file;

    public FileEventLogger(){}

    public FileEventLogger(String fileName){
        this.fileName = fileName;
    }

    @PostConstruct
    public void init() throws IOException{
        this.file = new File(fileName);
        if(!file.canWrite()){
            throw new IOException("You have not a permission to log events in this file");
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

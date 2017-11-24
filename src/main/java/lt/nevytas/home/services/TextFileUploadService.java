package lt.nevytas.home.services;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by nevyt on 15-Nov-17.
 */
@Service
public class TextFileUploadService implements StorageService {

    public static String UPLOAD_PATH = "upload-dir";
    public static String PROJECT_DIR = System.getProperty("user.dir");
    public List<MultipartFile> files = new ArrayList<>();


    @Override
    public void init() {
        
    }
//TODO --2017 11 19 upload for multiple files
    @Override
    public void store(MultipartFile file) {


//        try {
//            if(!file.isEmpty()) {
//                byte[] bytes = file.getBytes();
//                FileOutputStream fileOuputStream = new FileOutputStream(PROJECT_DIR + "/" + UPLOAD_PATH + "/" + file.getOriginalFilename());
//                fileOuputStream.write(bytes);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}

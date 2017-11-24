package lt.nevytas.home.controllers;

import lt.nevytas.home.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

/**
 * Created by nevyt on 14-Nov-17.
 */
@Controller
public class UploadController {
    @Autowired
    private StorageService storageService;
    public static final String uploadDir = System.getProperty("user.dir") + "/uploads/";

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
// not workig because no initial data

//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(UploadController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList()));

        File file = new File(uploadDir);
        model.addAttribute("files", file.listFiles());

        return "index";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    // MULTIPLE FILE UPLOAD WORKING
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("files") MultipartFile[] files,
                                   RedirectAttributes redirectAttributes) {

        for (MultipartFile file : files) {
            File fileUpload = new File(uploadDir + file.getOriginalFilename());
            String filename = fileUpload.getName();
            String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
            System.out.println(extension);
            if (extension.equals("txt")) {
                try {
                    file.transferTo(fileUpload);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                redirectAttributes.addFlashAttribute("message",
                        "Your .txt files are uploaded successfuly");
            }

        }
        return "redirect:/";
    }


}

package ra.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ra.jpa.service.IPostService;
import ra.jpa.service.UploadService;

import java.io.IOException;

@Controller
public class HomeController {
    @Autowired
    private IPostService postService;
    @Autowired
    private UploadService uploadService;
    @GetMapping
    public String home(@PageableDefault(page = 0,size = 3,sort ={"id"}, direction = Sort.Direction.DESC)Pageable pageable, Model model) {
        model.addAttribute("list", postService.paginationPost(pageable));
        return "index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String url = uploadService.uploadFileToDrive(file);
        model.addAttribute("url", url);
        return "index";
    }
}

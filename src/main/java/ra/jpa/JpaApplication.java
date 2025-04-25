package ra.jpa;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import ra.jpa.model.entity.Post;
import ra.jpa.repository.IPostRepository;
import ra.jpa.service.IPostService;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class JpaApplication {


    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }


    private static  final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    @Bean
    public Drive createDriveCredentials() throws IOException, GeneralSecurityException {
        InputStream inputStream = new ClassPathResource("driver-config.json").getInputStream();
        GoogleCredentials credential = GoogleCredentials.fromStream(inputStream)
                .createScoped(Collections.singleton(DriveScopes.DRIVE));
        return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credential))
                .build();
    }

    @Bean
    public CommandLineRunner runner(IPostService postService){
        return args -> {
            // viết code chạy 1 lần
//            List<Post> list = postService.findAll();
//            list.forEach(System.out::println);
//            System.out.println(postService.findById(3L));

//            postRepository.save(new Post(3L, "Phối đồ đi dự tiêc", "Trang phục bãi biển","mnp.jpg"));
//            postRepository.deleteById(3L);
//            Page<Post> posts = postService.paginationPost(0,2);
//            System.out.println(posts);
        };
    }

}

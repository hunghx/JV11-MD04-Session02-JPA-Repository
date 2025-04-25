package ra.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.jpa.model.entity.Post;

import java.util.List;

public interface IPostService {
    List<Post> findAll();
    Post findById(Long id);
    void save(Post p);
    void deleteById(Long id);

    Page<Post> paginationPost(Pageable pageable);
}

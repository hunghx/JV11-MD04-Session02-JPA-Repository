package ra.jpa.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.jpa.model.entity.Post;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
//    @Query("JPQL") : truy vấn tùy chỉnh
    // truy vấn dựa theo naming method

    List<Post> findAllByTitleContainingOrContentContaining(String title,String content);
    boolean existsByTitle(String title);
    // sử dụng entityManager để custom
}

package ra.jpa.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.jpa.model.dto.PostResponseDto;
import ra.jpa.model.entity.Post;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
//    @Query("JPQL") : truy vấn tùy chỉnh
    // truy vấn dựa theo naming method

    List<Post> findAllByTitleContainingOrContentContaining(String title,String content);
    boolean existsByTitle(String title);
    // sử dụng entityManager để custom

    // Truy vấn nâng cao với JPA
    // Cách 1:  Method Name Query
    // truy vấn theo yêu câu : lấy tất cả ca bản ghi Post khớp với chuỗi tiêu đề tuyền vào
    Page<Post> findAllByTitleContainingAndContentContaining(String title, String content, Pageable pageable);

    // Cách 2: Sử dung @Query
    @Query(value = "SELECT new ra.jpa.model.dto.PostResponseDto(P.id,P.title,P.content) FROM Post P")
    List<PostResponseDto> findAllForUser();

    @Query("select P from Post P where P.title like concat('%',:title,'%')")
    List<Post> searchPostByTitle(@Param("title") String keyword);
    // Cách 3: Sử dụng EntityManager


    // Lưu ý đối với cac thao tác chỉnh sửa dữ liệu thoog qua @ QUery : sử dụng thêm @Modify và @Transactional
    @Modifying
    @Transactional
    @Query("DELETE FROM Post where length(title)>10")
    void deletePostForUser();


}

package ra.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.jpa.model.entity.Post;
import ra.jpa.repository.IPostRepository;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService{
    @Autowired
    private IPostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Post p) {
        postRepository.save(p);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> paginationPost(Pageable pageable) {
        // đại dện cho page , size, direction, orderBy
        Page<Post> pageList = postRepository.findAll(pageable) ;
        return pageList;
    }
}

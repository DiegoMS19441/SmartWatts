package br.com.fiap.smartwatts.repositories;

import br.com.fiap.smartwatts.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

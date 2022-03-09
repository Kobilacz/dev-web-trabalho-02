package devweb.trabalho02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import devweb.trabalho02.model.Artigo;

import java.util.List;

public interface ArtigoRepository extends JpaRepository<Artigo, Long> {
  List<Artigo> findByPublicado(boolean publicado);
  List<Artigo> findByTitulo(String titulo);
}

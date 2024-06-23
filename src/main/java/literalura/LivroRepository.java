package literalura;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @EntityGraph(attributePaths = "autores")
    Livro findByTitulo(String titulo);
    
    @EntityGraph(attributePaths = "autores")
    List<Livro> findByIdioma(String idioma);

    @EntityGraph(attributePaths = "autores")
    List<Livro> findAll();
}
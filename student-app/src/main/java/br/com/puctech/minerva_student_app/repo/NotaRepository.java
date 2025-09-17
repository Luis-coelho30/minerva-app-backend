package br.com.puctech.minerva_student_app.repo;

import br.com.puctech.minerva_student_app.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    @Query(nativeQuery = true,
            value = "SELECT NOTA.* FROM NOTA " +
                    "INNER JOIN DISCIPLINA AS disc ON disc.ID = NOTA.DISCIPLINA_ID" +
                    "WHERE disc.ID = ?1")
    List<Nota> findNotasByDisciplinaId(Long id);
}

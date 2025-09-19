package br.com.puctech.minerva_student_app.repo;

import br.com.puctech.minerva_student_app.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {


    @Query(nativeQuery = true,
            value = "SELECT DISCIPLINA.* FROM DISCIPLINA " +
                    "INNER JOIN USUARIOS AS u ON u.ID = DISCIPLINA.USUARIO_ID" +
                    "WHERE u.email = ?1")
    List<Disciplina> findDisciplinasByUsermail(String email);

    @Query(nativeQuery = true,
            value = "SELECT DISCIPLINA.* FROM DISCIPLINA " +
                    "INNER JOIN USUARIOS AS u ON u.ID = DISCIPLINA.USUARIO_ID" +
                    "WHERE u.email = ?1 AND DISCIPLINA.NOME = ")
    Disciplina findDisciplinasByName(String email, String name);

    @Query(nativeQuery = true,
            value = "DELETE DISCIPLINA.* FROM DISCIPLINA " +
                    "INNER JOIN USUARIOS AS u ON u.ID = DISCIPLINA.USUARIO_ID" +
                    "WHERE u.email = ?1 AND DISCIPLINA.NOME = ")
    Disciplina deleteDisciplinasByName(String email, String name);
}

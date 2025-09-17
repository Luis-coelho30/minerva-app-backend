package br.com.puctech.minerva_student_app.repo;

import br.com.puctech.minerva_student_app.model.Tarefa;
import br.com.puctech.minerva_student_app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query(nativeQuery = true,
            value = "SELECT TAREFA.* FROM TAREFA " +
                    "INNER JOIN USUARIOS AS u ON u.ID = TAREFA.USUARIO_ID" +
                    "WHERE u.email = ?1")
    List<Tarefa> findTaskByUsermail(String email);
}
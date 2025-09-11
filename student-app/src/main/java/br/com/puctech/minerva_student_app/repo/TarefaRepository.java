package br.com.puctech.minerva_student_app.repo;

import br.com.puctech.minerva_student_app.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}

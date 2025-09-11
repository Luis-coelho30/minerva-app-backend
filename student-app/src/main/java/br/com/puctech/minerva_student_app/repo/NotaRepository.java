package br.com.puctech.minerva_student_app.repo;

import br.com.puctech.minerva_student_app.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

}

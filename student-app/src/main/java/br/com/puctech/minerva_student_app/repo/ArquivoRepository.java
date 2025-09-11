package br.com.puctech.minerva_student_app.repo;

import br.com.puctech.minerva_student_app.model.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    
}

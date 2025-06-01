package br.com.l2code.mscaixas.repository;

import br.com.l2code.mscaixas.model.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaixasRepository extends JpaRepository<Caixa, Long> {
}

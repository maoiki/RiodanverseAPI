package riordanverse.riordanverse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import riordanverse.riordanverse.entities.Mitologia;
import riordanverse.riordanverse.repositories.MitologiaRepository;

@Service
public class MitologiaService {

	@Autowired
	private MitologiaRepository mitologiaRepository;

	private void criarMitologia(String nome) {
		// Verifica se a mitologia com o nome já existe
		Mitologia existente = mitologiaRepository.findByNome(nome);
		if (existente != null) {
			throw new IllegalArgumentException("Já existe uma mitologia com o nome: " + nome);
		}

		// Cria uma nova mitologia se não existir
		Mitologia novaMitologia = new Mitologia();
		novaMitologia.setNome(nome);
		mitologiaRepository.save(novaMitologia);
	}


	@Transactional
	public void criarMitologiasIniciais() {
		if (mitologiaRepository.count() == 0) {
			criarMitologia("greco-romana");
			criarMitologia("egipcia");
			criarMitologia("nordica");
		}
	}

	public Mitologia getMitologiaById(Integer idMitologia) {
		Optional<Mitologia> mitologia = mitologiaRepository.findById(idMitologia);
		return mitologia.get();
	}


	public Mitologia getMitologiaByNome(String nome) {
		return mitologiaRepository.findByNome(nome);
	}

	public List<Mitologia> getAllMitologias() {
		return mitologiaRepository.findAll();
	}

	public Mitologia salvar(Mitologia mitologia) {
		String nome = mitologia.getNome();
		Mitologia existente = mitologiaRepository.findByNome(nome);

		if (existente != null) {
			throw new RuntimeException("Já existe uma mitologia com o nome: " + nome);
		}

		return mitologiaRepository.save(mitologia);
	}

	public Mitologia atualizar(Mitologia mitologia) {
		String nome = mitologia.getNome();
		Mitologia existente = getMitologiaByNome(nome);

		if (existente != null) {
			throw new RuntimeException("Já existe uma mitologia com o nome: " + nome);
		}

		if (mitologia.getId() == null) {
			throw new RuntimeException("É necessário informar o id da mitologia a ser atualizada.");
		}

		return mitologiaRepository.save(mitologia);
	}

	public void remover(Integer idMitologia) {
		Mitologia existente = getMitologiaById(idMitologia);

		if (existente == null) {
			throw new RuntimeException("Não foi encontrada uma mitologia com esse id.");
		}

		mitologiaRepository.deleteById(idMitologia);
	}

}

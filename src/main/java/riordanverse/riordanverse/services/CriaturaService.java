package riordanverse.riordanverse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import riordanverse.riordanverse.entities.Criatura;
import riordanverse.riordanverse.entities.Mitologia;
import riordanverse.riordanverse.repositories.CriaturaRepository;

@Service
public class CriaturaService {

	@Autowired
	private CriaturaRepository criaturaRepository;

	@Autowired
	private MitologiaService mitologiaService;

	private void criarCriatura(String nome, String mitologiaNome) {
		//verifica se já existe uma criatura com esse nome
		Criatura existente = criaturaRepository.findByNome(nome);

		if (existente != null) {
			throw new IllegalArgumentException("Já existe uma criatura com o nome: " + nome);
		}

		//Cria nova criatura caso não exista algum com esse nome
		Criatura novaCriatura = new Criatura();
		novaCriatura.setNome(nome);

		Mitologia mitologia = mitologiaService.getMitologiaByNome(mitologiaNome);
		novaCriatura.setMitologia(mitologia);

		criaturaRepository.save(novaCriatura);
	}

	@Transactional
	public void criarCriaturasIniciais() {
		if (criaturaRepository.count() == 0) {
			criarCriatura("deus greco-romano", "greco-romana");
			criarCriatura("semideus", "greco-romana");
			criarCriatura("sátiro", "greco-romana");

			criarCriatura("Serpopardo", "egipcia");
			criarCriatura("Tjesu Heru", "egipcia");
			criarCriatura("Criosfinge", "egipcia");

			criarCriatura("Jörmungandr", "nordica");
			criarCriatura("Draugr", "nordica");
			criarCriatura("Troll", "nordica");
		}
	}

	public List<Criatura> getAllCriaturas() {
		return criaturaRepository.findAll();
	}

	public Criatura getCriaturaById(Integer idCriatura) {
		Optional<Criatura> criatura = criaturaRepository.findById(idCriatura);
		return criatura.get();
	}

	public Criatura getCriaturaByNome(String nome) {
		return criaturaRepository.findByNome(nome);
	}

	public List<Criatura> getCriaturasByMitologia(String mitologiaNome) {
		Mitologia mitologia = mitologiaService.getMitologiaByNome(mitologiaNome);
		return criaturaRepository.findByMitologia(mitologia);
	}

	public Criatura salvar(Criatura criatura) {
		return criaturaRepository.save(criatura);
	}

	public Criatura atualizar(Criatura criatura) {
		return criaturaRepository.save(criatura);
	}

	public void remover(Integer idCriatura) {
		criaturaRepository.deleteById(idCriatura);
	}

}

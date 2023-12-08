package riordanverse.riordanverse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import riordanverse.riordanverse.entities.Acampamento;
import riordanverse.riordanverse.services.AcampamentoService;

@RestController
@RequestMapping("/acamp")
public class AcampamentoController {

	@Autowired
	AcampamentoService acampamentoService;

	@GetMapping("/nome/{nomeAcampamento}")
	public Acampamento getAcampamentoByNome(@PathVariable String nomeAcampamento) {
		Acampamento acampamento = acampamentoService.getAcampamentoByNome(nomeAcampamento);

		if (acampamento == null) {
			throw new IllegalStateException("Não foram encontrados resultados para essa busca!");
		}
		return acampamento;
	}

	@GetMapping("/all")
	public List<Acampamento> getAllAcampamentos() {
		List<Acampamento> acampamentos = acampamentoService.getAllAcampamentos();

		if (acampamentos.isEmpty()) {
			throw new IllegalStateException("Não foram encontrados resultados para essa busca!");
		}

		return acampamentos;
	}

	@GetMapping("/id/{idAcamp}")
	public Acampamento getAcampById(@PathVariable Integer idAcamp) {
		Acampamento acamp = acampamentoService.getAcampamentoById(idAcamp);

		if (acamp == null) {
			throw new IllegalStateException("Não foram encontrados resultados para essa busca!");
		}

		return acamp;
	}

	@PostMapping
	@Secured(value = {"ROLE_FUNCIONARIO", "ROLE_ADMIN"})
	public String cadastrarAcampamento(@RequestBody Acampamento acampamento) {
		String nome = acampamento.getNome();
		Acampamento existente = acampamentoService.getAcampamentoByNome(nome);

		if (existente != null) {
			throw new IllegalStateException("Já existe um acampamento com o nome: " + nome);
		}

		acampamentoService.salvar(acampamento);

		String feedback = "Acampamento " + nome + " cadastrado com sucesso!";
		return feedback;
	}

	@PutMapping
	@Secured(value = {"ROLE_FUNCIONARIO", "ROLE_ADMIN"})
	public String atualizarAcampamento(@RequestBody Acampamento acampamento) {
		String nome = acampamento.getNome();
		Acampamento existente = acampamentoService.getAcampamentoByNome(nome);

		if (existente != null) {
			throw new IllegalStateException("Já existe um acampamento com o nome: " + nome);
		}

		acampamentoService.atualizar(acampamento);
		String feedback = "Acampamento " + nome + " atualizado com sucesso!";
		return feedback;
	}

	@DeleteMapping("/id/{idAcamp}")
	@Secured(value = {"ROLE_ADMIN"})
	public String removerAcamp(@PathVariable Integer idAcamp) {
		Acampamento existente = acampamentoService.getAcampamentoById(idAcamp);

		acampamentoService.remover(idAcamp);
		String feedback = "Acampamento " + existente.getNome() + " removido com sucesso!";
		return feedback;
	}

}

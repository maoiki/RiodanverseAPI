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

import riordanverse.riordanverse.entities.Criatura;
import riordanverse.riordanverse.services.CriaturaService;

@RestController
@RequestMapping("/criatura")
public class CriaturaController {

	@Autowired
	CriaturaService criaturaService;

	@GetMapping("/all")
	public List<Criatura> getAllCriaturas() {
		List<Criatura> criaturas = criaturaService.getAllCriaturas();

		if (criaturas.isEmpty()) {
			throw new IllegalStateException("Não foram encontrados resultados para essa busca!");
		}

		return criaturas;
	}

	@GetMapping("/nome/{nomeCriatura}")
	public Criatura getCriaturaByNome(@PathVariable String nomeCriatura) {
		Criatura criatura = criaturaService.getCriaturaByNome(nomeCriatura);

		if (criatura == null) {
			throw new IllegalStateException("Não foram encontrados resultados para essa busca!");
		}
		return criatura;
	}

	@GetMapping("/mitologia/{mitologiaCriatura}")
	public List<Criatura> getCriaturasByMitologia(@PathVariable String mitologiaCriatura) {
		List<Criatura> criaturas = criaturaService.getCriaturasByMitologia(mitologiaCriatura);

		if (criaturas.isEmpty()) {
			throw new IllegalStateException("Não foram encontrados resultados para essa busca!");
		}

		return criaturas;
	}

	@PostMapping
	@Secured(value = {"ROLE_FUNCIONARIO", "ROLE_ADMIN"})
	public String cadastrarCriatura(@RequestBody Criatura criatura) {
		String nome = criatura.getNome();
		Criatura existente = criaturaService.getCriaturaByNome(nome);
		//Saber como faz pra aparecer a mitologia aqui

		if (existente != null) {
			throw new IllegalStateException("Já existe uma criatura com o nome: " + nome);
		}

		criaturaService.salvar(criatura);
		String feedback = "Criatura " + nome + " cadastrada com sucesso!";
		return feedback;
	}

	@PutMapping
	@Secured(value = {"ROLE_FUNCIONARIO", "ROLE_ADMIN"})
	public String atualizarCriatura(@RequestBody Criatura criatura) {
		String nome = criatura.getNome();
		Criatura existente = criaturaService.getCriaturaByNome(nome);

		if (existente != null) {
			throw new IllegalStateException("Já existe uma criatura com o nome: " + nome);
		}

		criaturaService.atualizar(criatura);
		String feedback = "Criatura " + nome + " atualizada com sucesso!";
		return feedback;
	}

	@DeleteMapping("/id/{idCriatura}")
	@Secured(value = {"ROLE_ADMIN"})
	public String removerCriatura(@PathVariable Integer idCriatura) {
		Criatura existente = criaturaService.getCriaturaById(idCriatura);

		criaturaService.remover(idCriatura);
		String feedback = "Criatura " + existente.getNome() + " removida com sucesso!";
		return feedback;
	}

}

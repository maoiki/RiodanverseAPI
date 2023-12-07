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

import riordanverse.riordanverse.entities.Mitologia;
import riordanverse.riordanverse.services.MitologiaService;

@RestController
@RequestMapping("/mitologia")
public class MitologiaController {

	@Autowired
	MitologiaService mitologiaService;

	@GetMapping("{idMitologia}")
	public Mitologia getMitologiaById(@PathVariable Integer idMitologia) {
		Mitologia mitologia = mitologiaService.getMitologiaById(idMitologia);
		return mitologia;
	}

	@GetMapping("/nome/{nomeMitologia}")
	public Mitologia getMitologiaByNome(@PathVariable String nomeMitologia) {
		Mitologia mitologia = mitologiaService.getMitologiaByNome(nomeMitologia);
		return mitologia;
	}

	@GetMapping("/all")
	public List<Mitologia> getAllMitologias() {
		List<Mitologia> mitologias = mitologiaService.getAllMitologias();
		return mitologias;
	}

	@PostMapping
	@Secured(value = {"ROLE_FUNCIONARIO", "ROLE_ADMIN"})
	public String cadastrarMitologia(@RequestBody Mitologia mitologia) {
		String nome = mitologia.getNome();
		Mitologia existente = mitologiaService.getMitologiaByNome(nome);

		if (existente != null) {
			throw new IllegalStateException("Já existe uma mitologia com o nome: " + nome);
		}

		mitologiaService.salvar(mitologia);

		String feedback = "Mitologia " + nome + " cadastrada com sucesso!";
		return feedback;
	}

	@PutMapping
	@Secured(value = {"ROLE_FUNCIONARIO", "ROLE_ADMIN"})
	public String atualizarMitologia(@RequestBody Mitologia mitologia) {
		String nome = mitologia.getNome();
		Mitologia existente = mitologiaService.getMitologiaByNome(nome);

		if (existente != null) {
			throw new IllegalStateException("Já existe uma mitologia com o nome: " + nome);
		}

		mitologiaService.atualizar(mitologia);
		String feedback = "Mitologia " + nome + " atualizada com sucesso!";
		return feedback;
	}

	@DeleteMapping("/id/{idMito}")
	@Secured(value = {"ROLE_ADMIN"})
	public String removerMito(@PathVariable Integer idMito) {
		Mitologia existente = mitologiaService.getMitologiaById(idMito);

		mitologiaService.remover(idMito);
		String feedback = "Mitologia " + existente.getNome() + " removida com sucesso!";
		return feedback;
	}

}

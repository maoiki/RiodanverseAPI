package riordanverse.riordanverse.controllers;

import java.util.List;
import java.util.NoSuchElementException;

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

		if(mitologia == null){
			throw new NoSuchElementException("Não foram encontrados resultados para essa busca!");
		}
		
		return mitologia;
	}

	@GetMapping("/nome/{nomeMitologia}")
	public Mitologia getMitologiaByNome(@PathVariable String nomeMitologia) {
		Mitologia mitologia = mitologiaService.getMitologiaByNome(nomeMitologia);

		if(mitologia == null){
			throw new NoSuchElementException("Não foram encontrados resultados para essa busca!");
		}

		return mitologia;
	}

	@GetMapping("/all")
	public List<Mitologia> getAllMitologias() {
		List<Mitologia> mitologias = mitologiaService.getAllMitologias();

		if(mitologias.isEmpty()){
			throw new NoSuchElementException("Não foram encontrados resultados para essa busca!");
		}

		return mitologias;
	}

	@PostMapping
	@Secured(value = {"ROLE_FUNCIONARIO", "ROLE_ADMIN"})
	public String cadastrarMitologia(@RequestBody Mitologia mitologia) {
		try {
			mitologiaService.salvar(mitologia);

			String nome = mitologia.getNome();
			return "Mitologia " + nome + " cadastrada com sucesso!";
		} catch (RuntimeException e) {
			return "Erro ao cadastrar mitologia: " + e.getMessage();
		}
	}

	@PutMapping
	@Secured(value = {"ROLE_FUNCIONARIO", "ROLE_ADMIN"})
	public String atualizarMitologia(@RequestBody Mitologia mitologia) {
		try {
			mitologiaService.atualizar(mitologia);

			String nome = mitologia.getNome();
			return "Mitologia " + nome + " atualizada com sucesso!";
		} catch (RuntimeException e) {
			return "Erro ao atualizar mitologia: " + e.getMessage();
		}
	}

	@DeleteMapping("/id/{idMito}")
	@Secured(value = {"ROLE_ADMIN"})
	public String removerMito(@PathVariable Integer idMito) {
		try {
			mitologiaService.remover(idMito);
			
			Mitologia mitologia = mitologiaService.getMitologiaById(idMito);
			return "Mitologia " + mitologia.getNome() + " removida com sucesso!";
		} catch (RuntimeException e) {
			return "Erro ao deletar usuário: " + e.getMessage();
		}
	}

}

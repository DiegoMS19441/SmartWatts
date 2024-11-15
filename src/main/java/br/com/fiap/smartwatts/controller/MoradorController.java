package br.com.fiap.smartwatts.controller;

import br.com.fiap.smartwatts.model.Morador;
import br.com.fiap.smartwatts.model.Residencia;
import br.com.fiap.smartwatts.repositories.MoradorRepository;
import br.com.fiap.smartwatts.repositories.ResidenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("morador")
public class MoradorController {

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private ResidenciaRepository residenciaRepository;

    @GetMapping("home")
    public String home() {
        return "morador/home";
    }

    @GetMapping("cadastrar")
    public String cadastrar(Morador morador, Model model) {
        List<Residencia> residencias = residenciaRepository.findAll();
        model.addAttribute("residencias", residencias);
        return "morador/cadastro";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Morador morador, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("residencias", residenciaRepository.findAll()); // Reenvia residências se houver erro
            return "morador/cadastro";
        }
        moradorRepository.save(morador);
        redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
        return "redirect:/morador/cadastrar";
    }

    @GetMapping("listar")
    public String listarMoradores(Model model) {
        model.addAttribute("moradores", moradorRepository.findAll());
        return "morador/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Morador morador = moradorRepository.findById(id).orElse(null);
        model.addAttribute("morador", morador);
        model.addAttribute("residencias", residenciaRepository.findAll());
        return "morador/editar";
    }

    @PostMapping("editar")
    @Transactional
    public String editar(@Valid Morador morador, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("residencias", residenciaRepository.findAll()); // Reenvia residências se houver erro
            return "morador/editar";
        }
        moradorRepository.save(morador);
        redirectAttributes.addFlashAttribute("mensagem", "Atualizado com sucesso");
        return "redirect:/morador/listar";
    }

    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        moradorRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Morador removido");
        return "redirect:/morador/listar";
    }

}

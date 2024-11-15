package br.com.fiap.smartwatts.controller;

import br.com.fiap.smartwatts.model.Residencia;
import br.com.fiap.smartwatts.repositories.ResidenciaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("residencia")
public class ResidenciaController {

    @Autowired
    private ResidenciaRepository residenciaRepository;

    @GetMapping("home")
    public String home() {
        return "residencia/home";
    }


    @GetMapping("cadastrar")
    public String cadastrar(Model model) {
        model.addAttribute("residencia", new Residencia());
        return "residencia/cadastro";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Residencia residencia, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "residencia/cadastro";
        }
        residenciaRepository.save(residencia);
        redirectAttributes.addFlashAttribute("mensagem", "Residência cadastrada com sucesso!");
        return "redirect:/residencia/cadastrar";
    }

    @GetMapping("listar")
    public String listarResidencias(Model model) {
        model.addAttribute("residencias", residenciaRepository.findAll());
        return "residencia/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("residencia", residenciaRepository.findById(id).orElse(null));
        return "residencia/editar";
    }

    @PostMapping("editar")
    @Transactional
    public String editar(@Valid Residencia residencia, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "residencia/editar";
        }
        residenciaRepository.save(residencia);
        redirectAttributes.addFlashAttribute("mensagem", "Residência atualizada com sucesso!");
        return "redirect:/residencia/listar";
    }

    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        residenciaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Residência removida");
        return "redirect:/residencia/listar";
    }
}

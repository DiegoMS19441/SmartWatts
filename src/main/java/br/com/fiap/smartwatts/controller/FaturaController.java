package br.com.fiap.smartwatts.controller;

import br.com.fiap.smartwatts.model.Bandeira;
import br.com.fiap.smartwatts.model.Fatura;
import br.com.fiap.smartwatts.model.Residencia;
import br.com.fiap.smartwatts.repositories.FaturaRepository;
import br.com.fiap.smartwatts.repositories.ResidenciaRepository;
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
@RequestMapping("fatura")
public class FaturaController {

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private ResidenciaRepository residenciaRepository;

    @GetMapping("home")
    public String home() {
        return "fatura/home";
    }

    @GetMapping("cadastrar")
    public String cadastrar(Fatura fatura, Model model) {
        List<Residencia> residencias = residenciaRepository.findAll();
        model.addAttribute("residencias", residencias);
        model.addAttribute("bandeira", Bandeira.values());
        return "fatura/cadastro";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Fatura fatura, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("residencias", residenciaRepository.findAll()); // Reenvia residências se houver erro
            model.addAttribute("bandeira", Bandeira.values());
            return "fatura/cadastro";
        }
        faturaRepository.save(fatura);
        redirectAttributes.addFlashAttribute("mensagem", "Fatura cadastrada com sucesso!");
        return "redirect:/fatura/cadastrar";
    }

    @GetMapping("listar")
    public String listarFaturas(Model model) {
        model.addAttribute("faturas", faturaRepository.findAll());
        return "fatura/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Fatura fatura = faturaRepository.findById(id).orElse(null);
        model.addAttribute("fatura", fatura);
        model.addAttribute("residencias", residenciaRepository.findAll());
        model.addAttribute("bandeira", Bandeira.values());
        return "fatura/editar";
    }

    @PostMapping("editar")
    @Transactional
    public String editar(@Valid Fatura fatura, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("residencias", residenciaRepository.findAll()); // Reenvia residências se houver erro
            model.addAttribute("bandeira", Bandeira.values());
            return "fatura/editar";
        }
        faturaRepository.save(fatura);
        redirectAttributes.addFlashAttribute("mensagem", "Fatura atualizada com sucesso");
        return "redirect:/fatura/listar";
    }

    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        faturaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Fatura removida");
        return "redirect:/fatura/listar";
    }

}

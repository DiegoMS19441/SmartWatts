package br.com.fiap.smartwattts.controller;

import br.com.fiap.smartwattts.model.Morador;
import br.com.fiap.smartwattts.repositories.MoradorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("morador")
public class MoradorController {


    @GetMapping("home")
    public String home(){
        return "morador/home";
    }

    @Autowired
    private MoradorRepository moradorRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Morador morador, Model model){
        return "morador/cadastro";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Morador morador, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "morador/cadastro";
        }
        moradorRepository.save(morador);
        redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
        return "redirect:/morador/cadastrar";
    }


    @GetMapping("listar")
    public String listarMoradores(Model model) {
        model.addAttribute("Moradores", moradorRepository.findAll());
        return "Morador/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("Morador", moradorRepository.findById(id));
        return "Morador/editar";
    }

    @PostMapping("editar")
    public String editar(@Valid Morador Morador, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "Morador/editar";
        }
        moradorRepository.save(Morador);
        redirectAttributes.addFlashAttribute("mensagem", "Atualizado com sucesso");
        return "redirect:/Morador/listar";
    }

    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        moradorRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Morador removido");
        return "redirect:/Morador/listar";
    }
}



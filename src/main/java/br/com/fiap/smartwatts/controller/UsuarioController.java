package br.com.fiap.smartwatts.controller;

import br.com.fiap.smartwatts.dto.UserForm;
import br.com.fiap.smartwatts.model.Usuario;
import br.com.fiap.smartwatts.repositories.RoleRepository;
import br.com.fiap.smartwatts.repositories.UsuarioRepository;
import br.com.fiap.smartwatts.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("usuario")
public class UsuarioController {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("home")
    public String home() {
        return "usuario/home";
    }

    @GetMapping("cadastrar")
    public String cadastrar(Model model){
        model.addAttribute("usuario", new UserForm());
        model.addAttribute("roles", roleRepository.findAll());
        return "usuario/cadastro";
    }

    @PostMapping("cadastrar")
    public String cadastrar(@Valid UserForm useForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            return "usuario/cadastro";
        }
        usuarioService.salvar(useForm.getUsername(),
                passwordEncoder.encode(useForm.getPassword()), useForm.getRoles());
        return "redirect:/login";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("usuario", new UserForm());
        model.addAttribute("roles", roleRepository.findAll());
        return "usuario/editar";
    }

    @PostMapping("editar")
    @Transactional
    public String editar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            return "usuario/editar";
        }
        usuarioRepository.save(usuario);
        redirectAttributes.addFlashAttribute("mensagem", "Atualizado com sucesso");
        return "redirect:/usuario/listar";
    }

    @GetMapping("listar")
    public String listarUsuario(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuario/listar";
    }



}

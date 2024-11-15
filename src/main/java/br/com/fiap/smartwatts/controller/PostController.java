package br.com.fiap.smartwatts.controller;

import br.com.fiap.smartwatts.model.Post;
import br.com.fiap.smartwatts.model.Residencia;
import br.com.fiap.smartwatts.model.Usuario;
import br.com.fiap.smartwatts.repositories.PostRepository;
import br.com.fiap.smartwatts.repositories.ResidenciaRepository;
import br.com.fiap.smartwatts.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ResidenciaRepository residenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Página inicial de posts
    @GetMapping("home")
    public String home() {
        return "post/home";
    }

    // Formulário de cadastro de post
    @GetMapping("cadastrar")
    public String cadastrar(Post post, Model model) {
        List<Residencia> residencias = residenciaRepository.findAll();
        model.addAttribute("residencias", residencias);
        return "post/cadastro";
    }

    // Cadastro de post
    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Post post, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("residencias", residenciaRepository.findAll());
            return "post/cadastro";
        }

        // Captura o usuário logado e associa ao post
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Nome do usuário logado
        Usuario usuarioLogado = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")); // Lança exceção se não encontrar o usuário

        post.setUsuario(usuarioLogado); // Associa o usuário logado ao post

        postRepository.save(post);
        redirectAttributes.addFlashAttribute("mensagem", "Postagem realizada com sucesso!");
        return "redirect:/post/listar";
    }

    // Listar posts
    @GetMapping("listar")
    public String listarPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "post/listar";
    }

    // Formulário para editar post
    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            model.addAttribute("post", post);
            model.addAttribute("residencias", residenciaRepository.findAll());
        }
        return "post/editar";
    }

    // Atualizar post
    @PostMapping("editar")
    @Transactional
    public String editar(@Valid Post post, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("residencias", residenciaRepository.findAll());
            return "post/editar";
        }
        postRepository.save(post);
        redirectAttributes.addFlashAttribute("mensagem", "Post atualizado com sucesso!");
        return "redirect:/post/listar";
    }

    // Remover post
    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        postRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Post removido!");
        return "redirect:/post/listar";
    }
}

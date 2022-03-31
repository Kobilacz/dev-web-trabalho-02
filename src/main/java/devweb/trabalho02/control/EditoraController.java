package devweb.trabalho02.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import devweb.trabalho02.model.Artigo;
import devweb.trabalho02.repository.ArtigoRepository;

@Controller
class EditoraController {
  @Autowired
  ArtigoRepository artRep;

  @GetMapping("/editora")
  public String showForm(Authentication a, Model model) {
    Artigo artigo = new Artigo();
    model.addAttribute("artigo", artigo);
    if (a != null)
      return "editora";

    return "redirect:oauth2/authorization/cognito";
  }

  @PostMapping("/editora")
  public String registerForm(Authentication a, @ModelAttribute("artigo") Artigo artigo) {
    if (a != null) {
      artRep.save(artigo);
      return "redirect:/editora/artigos";
    }

    return "redirect:oauth2/authorization/cognito";
  }

  @GetMapping("/editora/artigos")
  public String showAll(Authentication a, Model model) {
    model.addAttribute("artigos", artRep.findAll());
    if (a != null)
      return "listArtigos";

    return "redirect:oauth2/authorization/cognito";
  }

  @GetMapping("/editora/artigos/{id}")
  public String showById(Authentication a, Model model, @PathVariable("id") long id) {
    if (a == null)
      return "redirect:oauth2/authorization/cognito";

    Optional<Artigo> data = artRep.findById(id);
    if (data.isPresent()) {
      model.addAttribute("artigo", data.get());
      return "artigo";
    } else {
      return "listArtigos";
    }
  }

  @RequestMapping("/editora/artigos/excluir/{id}")
  public String deleteById(Authentication a, Model model, @PathVariable("id") long id) {
    if (a == null)
      return "redirect:oauth2/authorization/cognito";

    artRep.deleteById(id);
    return "redirect:/editora/artigos";
  }

  @RequestMapping("/editora/artigos/excluir-todos")
  public String deleteAll(Authentication a) {
    if (a == null)
      return "redirect:oauth2/authorization/cognito";

    artRep.deleteAll();
    return "redirect:/editora/artigos";
  }

}
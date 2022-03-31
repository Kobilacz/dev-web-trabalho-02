package devweb.trabalho02.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import devweb.trabalho02.model.Artigo;
import devweb.trabalho02.repository.ArtigoRepository;

@Controller
class EditoraController {
  @Autowired
  ArtigoRepository artRep;

  /*
   * Obs.: retirando os comentários do código, ao fazer o logout
   * você será redirecionado para a tela de login novamente.
   */
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
      return "editora";
    }

    return "redirect:oauth2/authorization/cognito";
  }
}
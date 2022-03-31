package devweb.trabalho02.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devweb.trabalho02.model.Artigo;
import devweb.trabalho02.repository.ArtigoRepository;

@RestController
@RequestMapping("/api")
public class ArtigoController {

  @Autowired
  ArtigoRepository artRep;

  /// POST /api/artigos -> criar artigo
  @PostMapping("/artigos")
  public ResponseEntity<Artigo> createArtigo(@ModelAttribute("artigo") Artigo art) {
    try {

      Artigo _art = artRep.save(new Artigo(art.getTitulo(), art.getResumo(), art.getPublicado()));

      return new ResponseEntity<>(_art, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // GET /api/artigos -> listar todos os artigos ou um artigo dado um título
  @GetMapping("/artigos")
  public ResponseEntity<List<Artigo>> getAllArtigos(@RequestParam(required = false) String titulo) {
    try {
      List<Artigo> la = new ArrayList<Artigo>();
      if (titulo == null) {
        artRep.findAll().forEach(la::add);
      } else {
        artRep.findByTitulo(titulo).forEach(la::add);
      }

      if (la.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(la, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // GET /api/artigos/:id -> listar artigo dado um id
  @GetMapping("/artigos/{id}")
  public ResponseEntity<Artigo> getArtigoById(@PathVariable("id") long id) {
    Optional<Artigo> data = artRep.findById(id);
    if (data.isPresent()) {
      return new ResponseEntity<>(data.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // PUT /api/artigos/:id -> atualizar artigo dado um id
  @PutMapping("/artigos/{id}")
  public ResponseEntity<Artigo> updateArtigo(@PathVariable("id") long id, @RequestBody Artigo art) {
    Optional<Artigo> data = artRep.findById(id);
    if (data.isPresent()) {
      Artigo ar = data.get();
      ar.setPublicado(art.getPublicado());
      ar.setResumo(art.getResumo());
      ar.setTitulo(art.getTitulo());

      return new ResponseEntity<>(artRep.save(ar), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // DEL /api/artigos/:id -> remover artigo dado um id
  @DeleteMapping("/artigos/{id}")
  public ResponseEntity<HttpStatus> deleteArtigo(@PathVariable("id") long id) {
    try {
      artRep.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // DEL /api/artigos -> remover todos os artigos
  @DeleteMapping("/artigos")
  public ResponseEntity<HttpStatus> deleteAllArtigo() {
    try {
      artRep.deleteAll();
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // GET /api/artigos/publicado -> listar artigo por publicado
  @GetMapping("/artigos/{publicado}")
  public ResponseEntity<HttpStatus> getArtigoByPublicado(@PathVariable("publicado") boolean publicado) {
    try {
      artRep.findByPublicado(publicado);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
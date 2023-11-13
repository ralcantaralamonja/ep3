package pe.isil.Saturno_1431.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.isil.Saturno_1431.model.Curso;
import pe.isil.Saturno_1431.repository.CursoRepository;

import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository; //DAO-Curso

    //INDEX: CUANDO CARGA LA PAGINA: SELECT * FROM curso
    //GET
    //si se desea devolver una vista(HTML) se debe devolver un STRING
    @GetMapping("") //localhost:8080/cursos
    String index(Model model){
        List<Curso> cursos = cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "index"; //retornamos el nombre de la vista o HTML con el atributo cursos
    }


}

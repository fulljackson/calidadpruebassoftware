package com.proyecto.capruso.Controller;

import java.util.Objects;
import javax.script.ScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.capruso.Entity.Usuario;
import com.proyecto.capruso.Service.UsuarioService;
import ch.qos.logback.core.model.Model;

@Controller
public class StartController {

    @Autowired
    private UsuarioService usuarioservice;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("usuario", new Usuario());
        return mav;
    }

    @PostMapping("/")
    public String login(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttrs)
            throws ScriptException {
        Usuario oauthUser = usuarioservice.login(usuario.getUsername(), usuario.getPassword());
        if (Objects.nonNull(oauthUser)) {
            String perfil = oauthUser.getPerfil();
            if (perfil.equals("Administrador")) {
                return "redirect:/dashboardadministrador/";
            } else if (perfil.equals("Empleado")) {
                return "redirect:/dashboardempleado/";
            } else {
                redirectAttrs
                        .addFlashAttribute("mensaje",
                                "Credenciales incorrectas. Verifique su Dni o contraseña")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/";
            }
        } else {
            redirectAttrs
                    .addFlashAttribute("mensaje",
                            "Credenciales incorrectas. Verifique su Dni o contraseña")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/";
        }
    }

    @GetMapping("/dashboardadministrador/")
    public String inicioadministradorget(Model model) {
        return "dashboardadministrador";
    }

    @GetMapping("/dashboardempleado/")
    public String inicioempleadoget(Model model) {
        return "dashboardempleado";
    }
}

package com.proyecto.capruso.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.proyecto.capruso.Entity.Usuario;
import com.proyecto.capruso.Service.UsuarioService;

@Controller
@RequestMapping("/usuariosadministrador/")
public class UsuarioAdministradorController {

    @Autowired
    private UsuarioService service;
    String carpeta = "Usuario/";

    @GetMapping("/")
    public String mostrar(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Usuario> listausuarios = service.getAll(pageRequest);
        int totalPage = listausuarios.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("listausuarios", listausuarios.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return carpeta + "usuariosadministrador";
    }

    @PostMapping("/registrar")
    public String registrar(
            @RequestParam Map<String, Object> params,
            @RequestParam("dni") String dni,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("nombres") String nombres,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("celular") String celular,
            @RequestParam("perfil") String perfil,
            Model model) {
        Usuario u = new Usuario();
        u.setUsername(dni);
        u.setPassword(contrasena);
        u.setNombres(nombres);
        u.setApellidos(apellidos);
        u.setCelular(celular);
        u.setPerfil(perfil);
        u.setFecharegistro(LocalDateTime.now());

        service.guardar(u);
        return mostrar(params, model);
    }

    @PostMapping("/actualizar")
    public String actualizar(
            @RequestParam Map<String, Object> params,
            @RequestParam("dni") String dni,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("nombres") String nombres,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("celular") String celular,
            @RequestParam("perfil") String perfil,
            Model model) {
        Usuario u = new Usuario();
        u.setUsername(dni);
        u.setPassword(contrasena);
        u.setNombres(nombres);
        u.setApellidos(apellidos);
        u.setCelular(celular);
        u.setPerfil(perfil);
        u.setFecharegistro(LocalDateTime.now());

        service.guardar(u);
        return mostrar(params, model);
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam Map<String, Object> params,
            @RequestParam("dato") String dato, Model model) {
        List<Usuario> usuarios = service.buscar(dato);
        model.addAttribute("usuarios", usuarios);
        return mostrar(params, model);
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Map<String, Object> params,
            @RequestParam("dni") String dni, Model model) {
        service.eliminar(dni);
        return mostrar(params, model);
    }

    @GetMapping("/orden_asc")
    public String ordenarAsc(Model model) {
        List<Usuario> usuarios = service.OrdenarAscendente();
        model.addAttribute("usuarios", usuarios);
        return carpeta + "usuariosadministrador";
    }

    @GetMapping("/orden_desc")
    public String ordenarDesc(Model model) {
        List<Usuario> usuarios = service.OrdenarDescendente();
        model.addAttribute("usuarios", usuarios);
        return carpeta + "usuariosadministrador";
    }
}

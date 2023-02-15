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
import com.proyecto.capruso.Entity.Ingreso;
import com.proyecto.capruso.Entity.Producto;
import com.proyecto.capruso.Entity.Usuario;
import com.proyecto.capruso.Service.IngresoService;
import com.proyecto.capruso.Service.ProductoService;
import com.proyecto.capruso.Service.UsuarioService;

@Controller
@RequestMapping("/ingresosadministrador/")
public class IngresoAdministradorController {

    @Autowired
    private IngresoService service;
    @Autowired
    private UsuarioService usuarioservice;
    @Autowired
    private ProductoService productoservice;
    String carpeta = "Ingreso/";

    @GetMapping("/")
    public String mostrar(@RequestParam Map<String, Object> params, Model model) {
        List<Usuario> listausuarios = usuarioservice.listar();
        List<Producto> listaproductos = productoservice.listar();
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Ingreso> listaingresos = service.getAll(pageRequest);
        int totalPage = listaingresos.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("listaingresos", listaingresos.getContent());
        model.addAttribute("listausuarios", listausuarios);
        model.addAttribute("listaproductos", listaproductos);
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return carpeta + "ingresosadministrador";
    }

    @PostMapping("/registrar")
    public String registrar(
            @RequestParam Map<String, Object> params,
            @RequestParam("codigo") String codigo,
            @RequestParam("usuario") Usuario usuario,
            @RequestParam("producto") Producto producto,
            @RequestParam("cantidad") int cantidad,
            Model model) {
        Ingreso i = new Ingreso();
        i.setCodigo(codigo);
        i.setUsuario(usuario);
        i.setProducto(producto);
        i.setCantidad(cantidad);
        i.setFecharegistro(LocalDateTime.now());

        service.guardar(i);
        return mostrar(params, model);
    }

    @PostMapping("/actualizar")
    public String actualizar(
            @RequestParam Map<String, Object> params,
            @RequestParam("codigo") String codigo,
            @RequestParam("usuario") Usuario usuario,
            @RequestParam("producto") Producto producto,
            @RequestParam("cantidad") int cantidad,
            Model model) {
        Ingreso i = new Ingreso();
        i.setCodigo(codigo);
        i.setUsuario(usuario);
        i.setProducto(producto);
        i.setCantidad(cantidad);
        i.setFecharegistro(LocalDateTime.now());

        service.guardar(i);
        return mostrar(params, model);
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam Map<String, Object> params,
            @RequestParam("dato") String dato, Model model) {
        List<Ingreso> ingresos = service.buscar(dato);
        model.addAttribute("ingresos", ingresos);
        return mostrar(params, model);
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Map<String, Object> params,
            @RequestParam("codigo") String codigo, Model model) {
        service.eliminar(codigo);
        return mostrar(params, model);
    }

    @GetMapping("/orden_asc")
    public String ordenarAsc(Model model) {
        List<Ingreso> ingresos = service.OrdenarAscendente();
        model.addAttribute("ingresos", ingresos);
        return carpeta + "ingresosadministrador";
    }

    @GetMapping("/orden_desc")
    public String ordenarDesc(Model model) {
        List<Ingreso> ingresos = service.OrdenarDescendente();
        model.addAttribute("ingresos", ingresos);
        return carpeta + "ingresosadministrador";
    }
}
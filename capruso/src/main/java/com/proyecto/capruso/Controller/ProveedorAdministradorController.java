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
import com.proyecto.capruso.Entity.Proveedor;
import com.proyecto.capruso.Service.ProveedorService;

@Controller
@RequestMapping("/proveedoresadministrador/")
public class ProveedorAdministradorController {

    @Autowired
    private ProveedorService service;
    String carpeta = "Proveedor/";

    @GetMapping("/")
    public String mostrar(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Proveedor> listaproveedores = service.getAll(pageRequest);
        int totalPage = listaproveedores.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("listaproveedores", listaproveedores.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return carpeta + "proveedoresadministrador";
    }

    @PostMapping("/registrar")
    public String registrar(
            @RequestParam Map<String, Object> params,
            @RequestParam("ruc") String ruc,
            @RequestParam("razon") String razon,
            @RequestParam("celular") String celular,
            @RequestParam("direccion") String direccion,
            Model model) {
        Proveedor p = new Proveedor();
        p.setRuc(ruc);
        p.setRazon(razon);
        p.setCelular(celular);
        p.setDireccion(direccion);
        p.setFecharegistro(LocalDateTime.now());

        service.guardar(p);
        return mostrar(params, model);
    }

    @PostMapping("/actualizar")
    public String actualizar(
            @RequestParam Map<String, Object> params,
            @RequestParam("ruc") String ruc,
            @RequestParam("razon") String razon,
            @RequestParam("celular") String celular,
            @RequestParam("direccion") String direccion,
            Model model) {
        Proveedor p = new Proveedor();
        p.setRuc(ruc);
        p.setRazon(razon);
        p.setCelular(celular);
        p.setDireccion(direccion);
        p.setFecharegistro(LocalDateTime.now());

        service.guardar(p);
        return mostrar(params, model);
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam Map<String, Object> params,
            @RequestParam("dato") String dato, Model model) {
        List<Proveedor> proveedores = service.buscar(dato);
        model.addAttribute("proveedores", proveedores);
        return mostrar(params, model);
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Map<String, Object> params,
            @RequestParam("ruc") String ruc, Model model) {
        service.eliminar(ruc);
        return mostrar(params, model);
    }

    @GetMapping("/orden_asc")
    public String ordenarAsc(Model model) {
        List<Proveedor> proveedores = service.OrdenarAscendente();
        model.addAttribute("proveedores", proveedores);
        return carpeta + "proveedoresadministrador";
    }

    @GetMapping("/orden_desc")
    public String ordenarDesc(Model model) {
        List<Proveedor> proveedores = service.OrdenarDescendente();
        model.addAttribute("proveedores", proveedores);
        return carpeta + "proveedoresadministrador";
    }
}
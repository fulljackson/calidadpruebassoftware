package com.proyecto.capruso.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.proyecto.capruso.Entity.Proveedor;
import com.proyecto.capruso.Service.ProveedorService;

@Controller
@RequestMapping("/proveedoresempleado/")
public class ProveedorEmpleadoController {

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
        return carpeta + "proveedoresempleado";
    }
}
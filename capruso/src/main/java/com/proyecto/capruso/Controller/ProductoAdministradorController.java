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
import com.proyecto.capruso.Entity.Producto;
import com.proyecto.capruso.Entity.Proveedor;
import com.proyecto.capruso.Service.ProductoService;
import com.proyecto.capruso.Service.ProveedorService;

@Controller
@RequestMapping("/productosadministrador/")
public class ProductoAdministradorController {

    @Autowired
    private ProductoService service;
    @Autowired
    private ProveedorService proveedorservice;
    String carpeta = "Producto/";

    @GetMapping("/")
    public String mostrar(@RequestParam Map<String, Object> params, Model model) {

        List<Proveedor> listaproveedores = proveedorservice.listar();
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Producto> listaproductos = service.getAll(pageRequest);
        int totalPage = listaproductos.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("listaproductos", listaproductos.getContent());
        model.addAttribute("listaproveedores", listaproveedores);
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return carpeta + "productosadministrador";
    }

    @PostMapping("/registrar")
    public String registrar(
            @RequestParam Map<String, Object> params,
            @RequestParam("codigo") String codigo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("costo") Double costo,
            @RequestParam("venta") Double venta,
            @RequestParam("stock") int stock,
            @RequestParam("proveedor") Proveedor proveedor,
            Model model) {
        Producto p = new Producto();
        p.setCodigo(codigo);
        p.setDescripcion(descripcion);
        p.setCosto(costo);
        p.setVenta(venta);
        p.setStock(stock);
        p.setProveedor(proveedor);
        p.setFecharegistro(LocalDateTime.now());

        service.guardar(p);
        return mostrar(params, model);
    }

    @PostMapping("/actualizar")
    public String actualizar(
            @RequestParam Map<String, Object> params,
            @RequestParam("codigo") String codigo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("costo") Double costo,
            @RequestParam("venta") Double venta,
            @RequestParam("stock") int stock,
            @RequestParam("proveedor") Proveedor proveedor,
            Model model) {
        Producto p = new Producto();
        p.setCodigo(codigo);
        p.setDescripcion(descripcion);
        p.setCosto(costo);
        p.setVenta(venta);
        p.setStock(stock);
        p.setProveedor(proveedor);
        p.setFecharegistro(LocalDateTime.now());

        service.guardar(p);
        return mostrar(params, model);
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam Map<String, Object> params,
            @RequestParam("dato") String dato, Model model) {
        List<Producto> productos = service.buscar(dato);
        model.addAttribute("productos", productos);
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
        List<Producto> productos = service.OrdenarAscendente();
        model.addAttribute("productos", productos);
        return carpeta + "productosadministrador";
    }

    @GetMapping("/orden_desc")
    public String ordenarDesc(Model model) {
        List<Producto> productos = service.OrdenarDescendente();
        model.addAttribute("productos", productos);
        return carpeta + "productosadministrador";
    }
}
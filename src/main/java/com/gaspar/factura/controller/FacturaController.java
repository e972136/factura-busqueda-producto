package com.gaspar.factura.controller;

import com.gaspar.factura.entity.DetalleSolicitud;
import com.gaspar.factura.entity.Producto;
import com.gaspar.factura.service.ProductoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@CrossOrigin
public class FacturaController {

    private final ProductoService productoService;

    public FacturaController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/factura")
    public ModelAndView factura(
            @RequestParam(required = false) String codigo,
            @ModelAttribute DetalleSolicitud detalle,
            HttpServletRequest request
    ){
//        System.out.println("codigo "+codigo);
//        System.out.println("detalle"+detalle);
        ModelAndView mv = new ModelAndView("factura");

        if(codigo!=null && codigo.length()>0){
            Producto producto = productoService.findByCodigoA(codigo);
            if(producto!=null){
                detalle = new DetalleSolicitud(
                        producto.getId(),
                        producto.getCodigoA(),
                        producto.getDescripcion(),
                        producto.getPrecioVenta().toString(),
                        producto.getPrecioVenta1().toString(),
                        producto.getPrecioVenta2().toString(),
                        producto.getPrecioVenta3().toString(),
                        "",
                        0
                );
            }
        }

        if(detalle==null){
            detalle = new DetalleSolicitud(null,null,null,"0.00","0.00","0.00","0.00","",0);
        }

        mv.addObject("codigo",codigo);
        mv.addObject("detalle",detalle);
        return mv;
    }

    @PostMapping("/busqueda")
    public ModelAndView buscar(
            @ModelAttribute DetalleSolicitud detalle,
            HttpServletRequest request
    ){
        System.out.println("detalle "+detalle);
        ArrayList<DetalleSolicitud> carrito = obtenerCarrito(request);
        carrito.add(detalle);
        guardarCarrito(carrito,request);
        ModelAndView mav = new ModelAndView("redirect:/factura");
        mav.addObject("detalle",new DetalleSolicitud(null,null,null,"0.00","0.00","0.00","0.00","",0));

        return mav;
    }


    private ArrayList<DetalleSolicitud> obtenerCarrito(HttpServletRequest request){
        ArrayList<DetalleSolicitud> carrito = (ArrayList<DetalleSolicitud>) request.getSession().getAttribute("carrito");
        if(carrito == null){
            carrito = new ArrayList<>();
        }
        return  carrito;
    }

    private  void guardarCarrito(ArrayList<DetalleSolicitud> carrito,HttpServletRequest request ){
        request.getSession().setAttribute("carrito",carrito);
    }


}

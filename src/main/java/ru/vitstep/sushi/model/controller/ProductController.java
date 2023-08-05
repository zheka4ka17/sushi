package ru.vitstep.sushi.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vitstep.sushi.model.Product;
import ru.vitstep.sushi.model.Type;
import ru.vitstep.sushi.model.service.ProductService;
import ru.vitstep.sushi.model.service.TypeService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/menu")
public class ProductController {

    private final ProductService productService;
    private final TypeService typeService;

    @Autowired
    public ProductController(ProductService productService, TypeService typeService) {
        this.productService = productService;
        this.typeService = typeService;
    }

    @GetMapping
    public String menu(Model model, @RequestParam(name = "findTitle",required = false) String findTitle) {
     if (!(findTitle ==null) && !findTitle.isBlank())
        {
           model.addAttribute("products", productService.findByTitle(findTitle));
        }
  else{
        List<Type> types = typeService.findAll();
        for (Type type : types)
            model.addAttribute(type.getTitle(), productService.findByType(type));
     }
        return "menu/menu";
    }

    @GetMapping("/sushi")
    public String getSushi(Model model){
   List<Product> sushi=productService.findAll().stream().filter(product -> product.getType().getId()==1).collect(Collectors.toList());
   model.addAttribute("sushis", sushi);
        return "menu/sushi";
    }
    @GetMapping("/sets")
    public String getSets(Model model){
        List<Product> sets=productService.findAll().stream().filter(product -> product.getType().getId()==2).collect(Collectors.toList());
        model.addAttribute("sets", sets);
        return "menu/set";
    }

    @GetMapping("/drinks")
    public String getSDrinks(Model model){
        List<Product> drinks=productService.findAll().stream().filter(product -> product.getType().getId()==3).collect(Collectors.toList());
        model.addAttribute("drinks", drinks);
        return "menu/drink";
    }


    @GetMapping("/sauce")
    public String getSauses(Model model){
        List<Product> sauces=productService.findAll().stream().filter(product -> product.getType().getId()==4).collect(Collectors.toList());
        model.addAttribute("sauces", sauces);
        return "menu/sauce";
    }


    @GetMapping("/other")
    public String getOthers(Model model){
        List<Product> others=productService.findAll().stream().filter(product -> product.getType().getId()==5).collect(Collectors.toList());
        model.addAttribute("others", others);
        return "menu/sauce";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model){
        model.addAttribute("product",productService.findById(id));
        return "menu/show";

    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("newProduct", new Product());
        model.addAttribute("types",typeService.findAll());
        return "menu/new_product";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("newProduct") Product product){
        productService.save(product);
        return "redirect:/menu";
    }



}


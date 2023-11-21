package com.luxury.controller;

import com.luxury.model.Category;
import com.luxury.model.Product;
import com.luxury.model.ProductForm;
import com.luxury.service.itf.ICategoryService;
import com.luxury.service.itf.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Value("/Users/apple/Desktop/Module4/webApp/Validation/src/main/webapp/WEB-INF/views/static/image/")
    private String file_upload;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    public ModelAndView showHome(@PageableDefault(value = 3)Pageable pageable){
        Page<Product> products = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("products",products);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        Iterable<Category> category = categoryService.findAll();
        modelAndView.addObject("product",new ProductForm());
        modelAndView.addObject("category",category);
        return modelAndView;
    }
    @PostMapping("/create")
    public String save(@Validated @ModelAttribute("product") ProductForm productForm, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<Category> category = categoryService.findAll();
            model.addAttribute("category",category);
            return "/create";
        } else {
        Optional<Category> category = categoryService.findOneById(productForm.getCategory().getId());
        MultipartFile multipartFile = productForm.getImage();
        String nameFile = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(file_upload + nameFile));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
            if (category.isPresent()) {
                Product product = new Product(productForm.getName(), productForm.getQuantity(), nameFile, productForm.getDescription(), productForm.getManufacture(), category.get());
                productService.save(product);
            }
            return "redirect:/products";
    }
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable Long id, RedirectAttributes redirectAttributes){
        productService.delete(id);
        redirectAttributes.addFlashAttribute("message","Delete has successfully");
        redirectAttributes.addFlashAttribute("color","green");
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id){
        Optional<Product> product = productService.findOneById(id);
        Iterable<Category> category = categoryService.findAll();
        if (product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("update");
            modelAndView.addObject("product",product.get());
            modelAndView.addObject("category",category);
            return modelAndView;
        }
        return new ModelAndView("error");
    }

    @PostMapping("/update/{id}")
    public String update(@Validated @ModelAttribute("product") ProductForm productForm,BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "/update";
        } else {
            Optional<Product> product1 = productService.findOneById(id);
            Product product = null;
            if (product1.isPresent()) {
               product = new Product(id, productForm.getName(), productForm.getQuantity(), product1.get().getImage(), productForm.getDescription(), productForm.getManufacture(), productForm.getCategory());
            }
            MultipartFile multipartFile = productForm.getImage();
            if (multipartFile.isEmpty()) {
               String nameFile = multipartFile.getOriginalFilename();
                try {
                    FileCopyUtils.copy(multipartFile.getBytes(), new File(file_upload + nameFile));
                    assert product != null;
                    product.setImage(nameFile);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            productService.save(product);
            return "redirect:/products";
        }
    }



}

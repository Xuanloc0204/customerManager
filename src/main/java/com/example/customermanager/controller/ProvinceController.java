package com.example.customermanager.controller;

import com.example.customermanager.model.DTO.ProvinceDTO;
import com.example.customermanager.model.Province;
import com.example.customermanager.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    // Read

    @GetMapping
    public ModelAndView listProvinces() {
        ModelAndView modelAndView = new ModelAndView("province/list");
        Iterable<ProvinceDTO> province = provinceService.countCustomerByProvice();
        modelAndView.addObject("province", province);
        return modelAndView;
    }

    // Create
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public String saveProvince(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "Added a new province successfully");
        return "redirect:/province";
    }

    // Update
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("provinces/edit");
            modelAndView.addObject("province", province.get());
            return modelAndView;
        } else {
            return new ModelAndView("error/404");
        }
    }

    @PostMapping("/edit/{id}")
    public String updateProvince(@ModelAttribute("province") Province province, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Province> currentProvince = provinceService.findById(id);
        if (currentProvince.isPresent()) {
            province.setId(id);
            provinceService.save(province);
            redirectAttributes.addFlashAttribute("message", "Updated province successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Province not found");
        }
        return "redirect:/province";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteProvince(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        provinceService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Delete province successfully");
        return "redirect:/province";
    }
}
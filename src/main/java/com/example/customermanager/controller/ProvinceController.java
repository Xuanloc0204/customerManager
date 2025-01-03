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
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    // Read

    @GetMapping
    public ModelAndView listProvinces() {
        ModelAndView modelAndView = new ModelAndView("provinces/list");
        Iterable<ProvinceDTO> provinces = provinceService.countCustomerByProvice();
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    // Create
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("provinces/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public String saveProvince(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "Added a new province successfully");
        return "redirect:/provinces";
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
        return "redirect:/provinces";
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteProvince(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            try {
                provinceService.remove(id);
                redirectAttributes.addFlashAttribute("message", "Deleted province successfully");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Cannot delete province. It may have associated customers.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Province not found");
        }
        return "redirect:/provinces";
    }
}
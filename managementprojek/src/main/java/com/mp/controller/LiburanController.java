package com.mp.controller;

import com.mp.model.Liburan;
import com.mp.service.ILiburanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LiburanController {

    @Autowired
    private ILiburanService liburanService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-liburans", method = RequestMethod.GET)
    public String showLiburans(ModelMap model) {
        String name = getLoggedInUserName(model);
        model.put("liburans", liburanService.getLiburansByUser(name));
        // model.put("Liburans", service.retrieveLiburans(name));
        return "list-liburans";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value = "/add-liburan", method = RequestMethod.GET)
    public String showAddLiburanPage(ModelMap model) {
        model.addAttribute("liburan", new Liburan());
        return "liburan";
    }

    @RequestMapping(value = "/delete-Liburan", method = RequestMethod.GET)
    public String deleteLiburan(@RequestParam long id) {
        liburanService.deleteLiburan(id);
        // service.deleteLiburan(id);
        return "redirect:/list-liburans";
    }

    @RequestMapping(value = "/update-liburan", method = RequestMethod.GET)
    public String showUpdateLiburanPage(@RequestParam long id, ModelMap model) {
        Liburan liburan = liburanService.getLiburanById(id).get();
        model.put("liburan", liburan);
        return "liburan";
    }

    @RequestMapping(value = "/update-liburan", method = RequestMethod.POST)
    public String updateLiburan(ModelMap model, @Valid Liburan liburan, BindingResult result) {

        if (result.hasErrors()) {
            return "liburan";
        }

        liburan.setUserName(getLoggedInUserName(model));
        liburanService.updateLiburan(liburan);
        return "redirect:/list-liburans";
    }

    @RequestMapping(value = "/add-liburan", method = RequestMethod.POST)
    public String addLiburan(ModelMap model, @Valid Liburan liburan, BindingResult result) {

        if (result.hasErrors()) {
            return "liburan";
        }

        liburan.setUserName(getLoggedInUserName(model));
        liburanService.saveLiburan(liburan);
        return "redirect:/list-liburans";
    }
}

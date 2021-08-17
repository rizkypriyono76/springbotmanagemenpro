package com.mp.controller;

import com.mp.model.Jadwalmeet;
import com.mp.service.JadwalmeetService;
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
public class JadwalmeetController {

    @Autowired
    JadwalmeetService jadwalmeetService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-jadwalmeets", method = RequestMethod.GET)
    public String showjadwalmeets(ModelMap model) {
        String name = getLoggedInUserName(model);
        model.put("jadwalmeets", jadwalmeetService.getJadwalmeetsByUser(name));
        // model.put("jadwalmeets", service.retrievejadwalmeets(name));
        return "list-jadwalmeets";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value = "/add-jadwalmeet", method = RequestMethod.GET)
    public String showAddJadwalmeetPage(ModelMap model) {
        model.addAttribute("jadwalmeet", new Jadwalmeet());
        return "jadwalmeet";
    }

    @RequestMapping(value = "/delete-jadwalmeet", method = RequestMethod.GET)
    public String deletejadwalmeet(@RequestParam long id) {
        jadwalmeetService.deleteJadwalmeet(id);
        return "redirect:/list-jadwalmeets";
    }

    @RequestMapping(value = "/update-jadwalmeet", method = RequestMethod.GET)
    public String showUpdateJadwalmeetPage(@RequestParam long id, ModelMap model) {
        Jadwalmeet jadwalmeet = jadwalmeetService.getJadwalmeetById(id).get();
        model.put("jadwalmeet", jadwalmeet);
        return "jadwalmeet";
    }

    @RequestMapping(value = "/update-jadwalmeet", method = RequestMethod.POST)
    public String updateJadwalmeet(ModelMap model, @Valid Jadwalmeet jadwalmeet, BindingResult result) {

        if (result.hasErrors()) {
            return "jadwalmeet";
        }

        jadwalmeet.setUserName(getLoggedInUserName(model));
        jadwalmeetService.updateJadwalmeet(jadwalmeet);
        return "redirect:/list-jadwalmeets";
    }

    @RequestMapping(value = "/add-jadwalmeet", method = RequestMethod.POST)
    public String addJadwalmeet(ModelMap model, @Valid Jadwalmeet jadwalmeet, BindingResult result) {

        if (result.hasErrors()) {
            return "jadwalmeet";
        }

        jadwalmeet.setUserName(getLoggedInUserName(model));
        jadwalmeetService.saveJadwalmeet(jadwalmeet);
        return "redirect:/list-jadwalmeets";
    }
}

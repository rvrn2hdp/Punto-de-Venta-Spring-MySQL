/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author pcc
 */
@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {

        return "home";
    }

}

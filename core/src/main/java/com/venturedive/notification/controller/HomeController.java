package com.venturedive.notification.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** Created by Safeer Ansari on 20/02/2018. */
@Controller
public class HomeController {

  @Value("${api.version}")
  String apiVersion;

  @Value("${api.swaggerEnable}")
  Boolean swaggerEnable;

  @GetMapping(path = "/")
  public String welcome() {
    return "forward:api.info";
  }

  @GetMapping(path = "api.info")
  public String home(Model model) {
    model.addAttribute("apiVersion", apiVersion);
    model.addAttribute("swaggerEnable", swaggerEnable);
    return "index";
  }
}

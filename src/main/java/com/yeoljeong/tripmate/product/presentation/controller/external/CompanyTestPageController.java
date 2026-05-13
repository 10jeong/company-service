package com.yeoljeong.tripmate.product.presentation.controller.external;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class CompanyTestPageController {

  @GetMapping("/test-page")
  public String testPage() {
    return "forward:/index.html";
  }
}
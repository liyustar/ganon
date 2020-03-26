package com.lyx.ganon.admin.func.controller;

import com.lyx.ganon.admin.func.service.TinyUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/func/tinyurl")
public class TinyUrlController {

    @Autowired
    private TinyUrlService tinyUrlService;

    @GetMapping("/{path}")
    public String getUrl(@PathVariable String path) {
        return "redirect:" + tinyUrlService.getSrc(path);
    }

    @PostMapping
    @ResponseBody
    public String addTinyUrl(@RequestParam String sourceUrl) {
        return tinyUrlService.add(sourceUrl);
    }

}

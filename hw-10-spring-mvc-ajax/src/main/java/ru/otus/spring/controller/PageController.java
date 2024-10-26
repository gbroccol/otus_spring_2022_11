package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PageController {

    @GetMapping("/")
    public String mainPage() { return "index"; }

    @GetMapping("/save")
    public String save() { return "save"; }
}

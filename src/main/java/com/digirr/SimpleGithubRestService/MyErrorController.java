package com.digirr.SimpleGithubRestService;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public String error() {
        return "<center><H1>Endpoint does not exist</H1></center>";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
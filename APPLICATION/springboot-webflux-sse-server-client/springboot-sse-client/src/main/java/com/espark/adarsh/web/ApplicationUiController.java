package com.espark.adarsh.web;

import com.espark.adarsh.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Slf4j
@Controller
public class ApplicationUiController {

    @Autowired
    ApplicationService applicationService;

    @RequestMapping("/")
    public String index(final Model model) {
        // data streaming, data driven mode.
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(applicationService.getFluxStreamFromRemoteServer(), 1);
        model.addAttribute("data", reactiveDataDrivenMode);
        return "index";
    }
}

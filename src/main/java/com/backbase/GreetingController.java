package com.backbase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    private GreetingService helloWorldService;

    @Autowired
    public GreetingController(GreetingService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @RequestMapping(value = "/")
    public ModelAndView great() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Greeting...");
        }
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("greeting", helloWorldService.greet());
        return modelAndView;
    }


}

package com.tayhantechnologies.ipace.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {



/**
 * Created by jt070 on 16/03/2018.
 */


    private static final Logger log = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(value = "/devices/")
    public String devices() {
        return "devices";
    }

    @RequestMapping(value = "/index/")
    public String index() {
        return "index3";
    }

    @RequestMapping(value = "/portstream/")
    public String portstream() {
        return "portstream";
    }

    @CrossOrigin(origins = "http://127.0.0.1:8080")
    @RequestMapping(value = "/discover/")
    public String discover() {
        return "discover";
    }

    @RequestMapping(value = "/stream/")
    public String stream() {
        return "stream";
    }
}







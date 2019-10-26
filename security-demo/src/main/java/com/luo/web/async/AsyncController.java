package com.luo.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/order")
    public String order() throws Exception {
        logger.info("main thread start");
        Thread.sleep(100);
        logger.info("main thread return");

        return "success";
    }
}

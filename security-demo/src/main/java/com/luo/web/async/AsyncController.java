package com.luo.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * [nio-8080-exec-2] com.luo.web.async.AsyncController        : main thread start
     * [nio-8080-exec-2] com.luo.web.async.AsyncController        : main thread return
     */
   /* @RequestMapping("/order")
    public String order() throws Exception {
        logger.info("main thread start");
        Thread.sleep(100);
        logger.info("main thread return");

        return "success";
    }
*/

    /**
     * 2019-10-26 13:55:46.732  INFO 4636 --- [nio-8080-exec-1] com.luo.web.async.AsyncController        : main thread start
     * 2019-10-26 13:55:46.734  INFO 4636 --- [nio-8080-exec-1] com.luo.web.async.AsyncController        : main thread return
     * <p>
     * 2019-10-26 13:55:46.766  INFO 4636 --- [      MvcAsync1] com.luo.web.async.AsyncController        : co thread start
     * 2019-10-26 13:55:47.768  INFO 4636 --- [      MvcAsync1] com.luo.web.async.AsyncController        : co thread ret
     */


    @RequestMapping("/order")
    public Callable<String> order() throws Exception {
        logger.info("main thread start");
        Callable<String> res = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("co thread start");
                Thread.sleep(1000);
                logger.info("co thread ret");
                return null;
            }
        };
        logger.info("main thread return");
        return res;
    }

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order3")
    public DeferredResult<String> order3() throws Exception {
        logger.info("main thread start");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        logger.info("main thread return");
        return result;
    }
}

package com.luo.code;

import com.luo.core.validation.code.ImageCode;
import com.luo.core.validation.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 当原来的代码 不适合 现在的需求的时候，不是在原来的代码上修改，
 * 而是 新加一段代码
 */
//@Component("imageCodeGenerator")
public class DemoValidateCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(HttpServletRequest req) {
        System.out.println("--code gen");
        return null;
    }
}

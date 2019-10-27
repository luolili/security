package com.luo.core.validation.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {
    ImageCode generate(HttpServletRequest req);
}

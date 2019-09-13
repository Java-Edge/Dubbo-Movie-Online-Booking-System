package com.javaedge.guns.rest.modular.example;

import com.javaedge.guns.rest.common.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 常规控制器
 *
 * @author JavaEdge
 */
@Controller
@RequestMapping("/hello")
public class ExampleController {

    @RequestMapping("")
    public ResponseEntity hello() {
        //System.out.println(simpleObject.getUser());

        System.out.println(CurrentUser.getCurrentUser());

        return ResponseEntity.ok("请求成功!");
    }
}

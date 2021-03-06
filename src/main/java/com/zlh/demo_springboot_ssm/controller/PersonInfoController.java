package com.zlh.demo_springboot_ssm.controller;

import com.zlh.demo_springboot_ssm.domain.PersonInfo;
import com.zlh.demo_springboot_ssm.mapper.PersonAccountMapper;
import com.zlh.demo_springboot_ssm.mapper.PersonInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PersonInfoController {

    @Autowired(required = false)
    private PersonInfoMapper personInfoMapper;

    //替换了页面中的body内容，head内容也被清空了？？
    @RequestMapping("/resbody")
    @ResponseBody
    public String resBody() {
        return "Hello,@Controller+@ResponseBodyD!!";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public String resBody(HttpServletRequest request) {
        String[] userName = request.getParameterValues("name");
        String userSex = request.getParameter("sex");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        return "getUser===" + userName.length + "--" + userSex + "--" + age + "--" + address;
    }

    @RequestMapping("/person-index")
    public String personIndex() {
        return "person-index";
    }

    //method默认是允许所有方式，指定get或者post则只允许get或者post
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public ModelAndView queryById(HttpServletRequest request) {

        String id = request.getParameter("id");
        PersonInfo person = personInfoMapper.selectById(id);
        ModelMap map = new ModelMap();
        map.put("person", person);
        return new ModelAndView("person-index", map);
        //return getPersons();
    }

    //配置多个页面
    @RequestMapping(value = {"/queryAll", "/persons"} ,method = RequestMethod.POST)
    public ModelAndView getPersons() {
        ModelMap map = new ModelMap();
        List<PersonInfo> persons = personInfoMapper.selectAll();
        map.put("personList", persons);
        return new ModelAndView("persons", map);
    }


    /* for test
    @RequestMapping("/accounts")
    public ModelAndView test1() {
        return new ModelAndView("index");
        //return getPersons();
    }

    @RequestMapping("/accounts/mi")
    public ModelAndView test2() {
        return new ModelAndView("index");
        //return getPersons();
    }
    @RequestMapping("/accounts/accounts/mi")
    public ModelAndView qtest3() {
        return new ModelAndView("index");
        //return getPersons();
    }

    @RequestMapping("/accounts/persons")
    public ModelAndView test4() {
        return new ModelAndView("person-index");
    }

    @RequestMapping("/accounts/person-index")
    public ModelAndView test5() {
        return new ModelAndView("persons");
    }


     */
}

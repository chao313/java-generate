package demo.spring.boot.demospringboot.demo2.controller;


import java.util.List;

import demo.spring.boot.demospringboot.demo2.vo.THostVo;
import demo.spring.boot.demospringboot.demo2.service.THostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/THostController")
public class THostController {

    @Autowired
    private THostService service;

    @PostMapping(value = "/insert")
    public Boolean insert(THostVo vo) {
        return service.insert(vo);
    }

    @PostMapping(value = "/inserts")
    public Boolean insert(List<THostVo> vos) {
        return service.insert(vos);
    }

    @PostMapping(value = "/queryBase")
    public List<THostVo> queryBase(THostVo query) {
        return service.queryBase(query);
    }

    @PostMapping(value = "/updateBase")
    public Boolean updateBase(THostVo source, THostVo target) {
        return service.updateBase(source, target);
    }

    @PostMapping(value = "/updateBaseIncludeNull")
    public Boolean updateBaseIncludeNull(THostVo source, THostVo target) {
        return service.updateBaseIncludeNull(source, target);
    }

    @PostMapping(value = "/deleteBase")
    public Boolean deleteBase(THostVo vo) {
        return service.deleteBase(vo);
    }


    @PostMapping(value = "/queryByPrimaryKey")
    public THostVo queryByPrimaryKey(Integer id, String account) {
       return service.queryByPrimaryKey(id, account);
    }

    @PostMapping(value = "/deleteByPrimaryKey")
    public boolean deleteByPrimaryKey(Integer id, String account) {
       return service.deleteByPrimaryKey(id, account);
    }
}

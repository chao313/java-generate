package demomaster.controller;


import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.framework.RequestUpdate;
import org.apache.commons.lang3.StringUtils;
import demomaster.vo.TMultiTermVoVo;
import demomaster.service.TMultiTermVoService;
import demomaster.vo.plugin.TMultiTermVoNoPriVo;
import demomaster.vo.plugin.TMultiTermVoPriVo;
import demomaster.vo.TMultiTermVoMultiTermVo;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/TMultiTermVoController")
@Slf4j
public class TMultiTermVoController {

    @Autowired
    private TMultiTermVoService service;

    /**
     * 插入一条记录: 请求体是json
     *
     * @param vo
     * @return 成功和失败都返回Response，具体的结果在response的
     * code   :状态码
     * content:具体返回值
     */
    @PostMapping(value = "/insert")
    public Response insert(@RequestBody TMultiTermVoVo vo) {
       Response response = new Response();
        try {
            Boolean result = service.insert(vo);
            response.setCode(Code.System.OK);
            response.setContent(result);
            log.info("success result -> {} ", result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }

    /**
     * 插入多条记录: 请求体是json
     *
     * @param vos
     * @return 成功和失败都返回Response，具体的结果在response的
     * code   :状态码
     * content:具体返回值
     */
    @PostMapping(value = "/inserts")
    public Response insert(@RequestBody List<TMultiTermVoVo> vos) {
        Response response = new Response();
        try {
            Boolean result = service.insert(vos);
            response.setCode(Code.System.OK);
            response.setContent(result);
            log.info("success result -> {} ", result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }


    /**
     * 多条件查询语句,每个字段只要不为null就是查询条件
     *
     * @param query
     * @return 成功和失败都返回Response，具体的结果在response的
     * code   :状态码
     * content:具体返回值
     */
    @PostMapping(value = "/queryBase")
    public Response queryBase(@RequestBody TMultiTermVoVo query) {
        Response response = new Response();
        try {
            List<TMultiTermVoVo> result = service.queryBase(query);
            response.setCode(Code.System.OK);
            response.setContent(result);
            log.info("success result -> {} ", result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }

    /**
      * 多条件查询语句,每个字段只要不为null就是查询条件
      * 这里添加了分页插件，能够返回的数据包含页码，下一页... , 自动查询count
      *
      * @param query
      * @param pageNum 页码 默认值为1
      * @param pageSize 每页的size 默认值为10
      * @return 成功和失败都返回Response，具体的结果在response的
      * code   :状态码
      * content:具体返回值
      */
    @PostMapping(value = "/queryBasePageHelper")
    public Response queryBasePageHelper(@RequestBody TMultiTermVoVo query,
                                        @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
                                        @RequestParam(value = "order", required = false) String order) {
         Response response = new Response();
         try {
             PageHelper.startPage(pageNum, pageSize);
             if (StringUtils.isNotBlank(order)) {
                 PageHelper.orderBy(order);
             }
             List<TMultiTermVoVo> result = service.queryBase(query);
             PageInfo pageInfo = new PageInfo(result);
             response.setCode(Code.System.OK);
             response.setContent(pageInfo);
             log.info("success pageInfo -> {} ", pageInfo);
         } catch (Exception e) {
             response.setCode(Code.System.FAIL);
             response.setMsg(e.getMessage());
             response.addException(e);
             log.error("异常 -> {} ", e.getMessage(), e);
         }
         return response;
     }

    /**
      * 多条件查询语句,每个字段只要不为null就是查询条件(这里是多维条件,包含like,not like)
      * 这里添加了分页插件，能够返回的数据包含页码，下一页... , 自动查询count
      *
      * @param query
      * @param pageNum 页码 默认值为1
      * @param pageSize 每页的size 默认值为10
      * @return 成功和失败都返回Response，具体的结果在response的
      * code   :状态码
      * content:具体返回值
      */
    @PostMapping(value = "/queryMultiTermPageHelper")
    public Response queryMultiTermPageHelper(@RequestBody TMultiTermVoMultiTermVo query,
                                             @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                             @RequestParam(value = "order", required = false) String order) {
         Response response = new Response();
         try {
             PageHelper.startPage(pageNum, pageSize);
             if (StringUtils.isNotBlank(order)) {
                 PageHelper.orderBy(order);
             }
             List<TMultiTermVoVo> result = service.queryMultiTerm(query);
             PageInfo pageInfo = new PageInfo(result);
             response.setCode(Code.System.OK);
             response.setContent(pageInfo);
             log.info("success pageInfo -> {} ", pageInfo);
         } catch (Exception e) {
             response.setCode(Code.System.FAIL);
             response.setMsg(e.getMessage());
             response.addException(e);
             log.error("异常 -> {} ", e.getMessage(), e);
         }
         return response;
    }


    /**
     * 多条件更新语句,
     * source每个字段只要不为null就是更新数据 -> 慎用
     * target每个字段只要不为null就是查询条件 -> 慎用
     *
     * @param source
     * @param target
     * @return 成功和失败都返回Response，具体的结果在response的
     * code   :状态码
     * content:具体返回值
     */
    @PostMapping(value = "/updateBase")
    public Response updateBase(@RequestBody RequestUpdate<TMultiTermVoVo,TMultiTermVoVo> update) {
        Response response = new Response();
        try {
            Boolean result = service.updateBase(update.getSource(), update.getTarget());
            response.setCode(Code.System.OK);
            response.setContent(result);
            log.info("success result -> {} ", result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }

    /**
     * 多条件更新语句,(包含null)
     * source每个字段都是更新数据 -> 慎用
     * target每个字段只要不为null就是查询条件 -> 慎用
     *
     * @param source
     * @param target
     * @return 成功和失败都返回Response，具体的结果在response的
     * code   :状态码
     * content:具体返回值
     */
    @PostMapping(value = "/updateBaseIncludeNull")
    public Response updateBaseIncludeNull(@RequestBody RequestUpdate<TMultiTermVoVo,TMultiTermVoVo> update) {
        Response response = new Response();
        try {
            Boolean result = service.updateBaseIncludeNull(update.getSource(), update.getTarget());
            response.setCode(Code.System.OK);
            response.setContent(result);
            log.info("success result -> {} ", result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }

    /**
     * 多条件查询语句,
     * 每个字段只要不为null就是查询条件
     *
     * @param vo
     * @return 成功和失败都返回Response，具体的结果在response的
     * code   :状态码
     * content:具体返回值
     */
    @PostMapping(value = "/deleteBase")
    public Response deleteBase(@RequestBody TMultiTermVoVo vo) {
        Response response = new Response();
        try {
            Boolean result = service.deleteBase(vo);
            response.setCode(Code.System.OK);
            response.setContent(result);
            log.info("success result -> {} ", result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }



    /**
     * 主键查询语句,
     * 因为是主键 -> 查询返回的是一条记录
     *
     * @param dbName
     * @param dbTableName
     * 
     * @return 成功和失败都返回Response，具体的结果在response的
     * code   :状态码
     * content:具体返回值
     */
    @GetMapping(value = "/queryByPrimaryKey")
    public Response queryByPrimaryKey(
            @RequestParam String dbName, 
            @RequestParam String dbTableName
    ) {
        Response response = new Response();
        try {
            TMultiTermVoVo result = service.queryByPrimaryKey(dbName, dbTableName);
            response.setCode(Code.System.OK);
            response.setContent(result);
            log.info("success result -> {} ", result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }


    /**
     * 主键删除语句,
     * 因为是主键 -> 删除的是一条记录
     *
     * @param dbName
     * @param dbTableName
     * 
     * @return 成功和失败都返回Response，具体的结果在response的
     * code   :状态码
     * content:具体返回值
     */
    @GetMapping(value = "/deleteByPrimaryKey")
    public Response deleteByPrimaryKey(
           @RequestParam String dbName, 
           @RequestParam String dbTableName
     ) {
        Response response = new Response();
        try {
            Boolean result = service.deleteByPrimaryKey(dbName, dbTableName);
            response.setCode(Code.System.OK);
            response.setContent(result);
            log.info("success result -> {} ", result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }

    /**
     * 根据PrimaryKey更新，会根据主键去更新其他的值(空值不覆盖有值)
     *
     * @param update.source 只包含非主键的字段
     * @param update.target 只包含主键的字段
     * @param dbName
     * @param dbTableName
     * 
     * @return 成功和失败都返回Response，具体的结果在response的
     * code   :状态码
     * content:具体返回值
     */
    @PostMapping(value = "/updateByPrimaryKey")
    public Response updateByPrimaryKey(@RequestBody RequestUpdate<TMultiTermVoNoPriVo,TMultiTermVoPriVo> update) {
        Response response = new Response();
        try {
            Boolean result = service.updateByPrimaryKey(update.getSource(), update.getTarget());
            response.setCode(Code.System.OK);
            response.setContent(result);
            log.info("success result -> {} ", result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;
    }
}

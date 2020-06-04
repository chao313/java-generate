package demo.spring.boot.demospringboot.controller.db;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.mybatis.service.SchemataService;
import demo.spring.boot.demospringboot.mybatis.service.TablesService;
import demo.spring.boot.demospringboot.mybatis.vo.SchemataVo;
import demo.spring.boot.demospringboot.mybatis.vo.TablesVo;
import demomaster.service.TControllerService;
import demomaster.service.TProjectService;
import demomaster.vo.TControllerVo;
import demomaster.vo.TProjectVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2018/4/6    Created by   juan
 */

@RestController
@RequestMapping(value = "/db/generate")
@Slf4j
public class DbGenerateController {


    @Autowired
    private SchemataService schemataService;

    @Autowired
    private TablesService tablesService;

    @Autowired
    private TProjectService tProjectService;

    @Autowired
    private TControllerService tControllerService;

    /**
     * 下载全部的前后端
     *
     * @param dataBase
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "生成到数据库")
    @GetMapping("/generateToDb")
    @Transactional
    public Response generateToDb(
            @ApiParam(example = "docker2") @RequestParam(value = "dataBase") String dataBase) throws Exception {
        Response response = new Response();
        try {
            SchemataVo schemataQuery = new SchemataVo();
            schemataQuery.setSchemaName(dataBase);
            /**生成项目信息*/
            List<SchemataVo> schemataVos = schemataService.queryBase(schemataQuery);
            schemataVos.forEach(schemataVo -> {
                TProjectVo tProjectVo = new TProjectVo();
                tProjectVo.setDbName(schemataVo.getSchemaName());
                tProjectVo.setProjectPort("80");
                tProjectService.insert(tProjectVo);
            });
            /**生成Controller信息*/
            TablesVo tablesVoQuery = new TablesVo();
            tablesVoQuery.setTableSchema(dataBase);
            List<TablesVo> tablesVos = tablesService.queryBase(tablesVoQuery);
            tablesVos.forEach(tablesVo -> {
                TControllerVo tControllerVoQuery = new TControllerVo();
                tControllerVoQuery.setDbName(dataBase);
                tControllerVoQuery.setDbTableName(tablesVo.getTableName());
                tControllerVoQuery.setDbTableComment(tablesVo.getTableComment());
                tControllerVoQuery.setClassName("Controller");
                tControllerVoQuery.setFileName("Controller.java");
                tControllerService.insert(tControllerVoQuery);
            });
            response.setCode(Code.System.OK);
            response.setContent(true);
            log.info("success result -> {} ", true);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.getMessage());
            response.addException(e);
            log.error("异常 -> {} ", e.getMessage(), e);
        }
        return response;

    }


}

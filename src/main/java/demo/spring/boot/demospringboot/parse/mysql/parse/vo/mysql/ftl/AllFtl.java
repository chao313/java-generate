package demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql.ftl;

import lombok.Data;

@Data
public class AllFtl {
    private VoFtl voFtl;
    private DAOFtl daoFtl;
    private ServiceFtl serviceFtl;
    private ServiceImplFtl serviceImplFtl;
    private MapperFtl mapperFtl;
    private ControllerFtl controllerFtl;
}

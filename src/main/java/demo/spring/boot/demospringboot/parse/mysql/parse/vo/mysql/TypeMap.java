package demo.spring.boot.demospringboot.parse.mysql.parse.vo.mysql;

/**
 * 整数类型：BIT、TINY INT、SMALL INT、MEDIUM INT、 INT、 BIG INT
 * <p>
 * 浮点数类型：FLOAT、DOUBLE、DECIMAL
 * <p>
 * 字符串类型：CHAR、VARCHAR、TINY TEXT、TEXT、MEDIUM TEXT、LONGTEXT、TINY BLOB、BLOB、MEDIUM BLOB、LONG BLOB
 * <p>
 * 日期类型：Date、DateTime、TimeStamp、Time、Year
 * <p>
 * 其他数据类型：BINARY、VARBINARY、ENUM、SET、Geometry、Point、MultiPoint、LineString、MultiLineString、Polygon、GeometryCollection等
 * <p>
 */


import java.util.HashMap;
import java.util.Map;

/**
 * 其他类型暂时留作下一版本
 */

public class TypeMap {

    private final static Map<String, String> typeMap = new HashMap<>();

    static {

        //bit
        typeMap.put("bit", "Boolean");

        //int
        typeMap.put("tinyint", "Integer");
        typeMap.put("smallint", "Integer");
        typeMap.put("mediumint", "Integer");
        typeMap.put("int", "Integer");
        typeMap.put("bigint", "Long");

        //double
        typeMap.put("float", "Float");
        typeMap.put("double", "Double");
        typeMap.put("decimal", "Double");

        //string
        typeMap.put("char", "String");
        typeMap.put("varchar", "String");
        typeMap.put("tinytext", "String");
        typeMap.put("text", "String");
        typeMap.put("mediumtext", "String");
        typeMap.put("longtext", "String");

        //blob
        typeMap.put("blob", "byte[]");
        typeMap.put("tinyblob", "byte[]");
        typeMap.put("mediumblob", "byte[]");
        typeMap.put("longblob", "byte[]");

        //blob
        typeMap.put("date", "Date");
        typeMap.put("time", "Time");
        typeMap.put("datetime", "Timestamp");
        typeMap.put("timestamp", "Timestamp");
        typeMap.put("year", "Date");

        //...
    }

    public static Map<String, String> getTypeMap() {
        return typeMap;
    }


}

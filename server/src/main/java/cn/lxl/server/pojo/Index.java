package cn.lxl.server.pojo;

import lombok.Data;

/**
 * @author xinleili
 */
@Data
public class Index {

    private String tableName;

    private String columnName;

    private String indexName;

    private String indexType;

    private String uniqueness;
}

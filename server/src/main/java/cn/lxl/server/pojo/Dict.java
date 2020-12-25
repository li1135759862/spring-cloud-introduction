package cn.lxl.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.ibatis.type.StringTypeHandler;
import org.apache.ibatis.type.TypeHandler;

/**
 * @author xinleili
 */
@TableName(value = "tsys_dict_entry",autoResultMap = true)
@Data
public class Dict {

    @TableId(value = "dict_entry_code",type = IdType.UUID)
    private String dictEntryCode;

    @TableField("kind_code")
    private String kindCode;

    @TableField(value = "dict_entry_name",typeHandler = IntegerTypeHandler.class)
    private String dictEntryName;

    @TableField("ctrl_flag")
    private String ctrlFlag;

    @TableField("lifecycle")
    private String lifecycle;

    @TableField("remark")
    private String remark;


    private String urid;
    private String name;
    private String menu_name;

    private String tableName;
    private String columnName;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private String columnType;
    private String columnComment;
    private String characterMaximumLength;
}

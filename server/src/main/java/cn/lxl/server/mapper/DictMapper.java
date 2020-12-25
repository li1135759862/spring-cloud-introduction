package cn.lxl.server.mapper;

import cn.lxl.server.pojo.Dict;
import cn.lxl.server.pojo.Index;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author xinleili
 */

public interface DictMapper extends BaseMapper<Dict> {


    List<Index> lists();

    List<Dict> string();

    List<Dict> tableName();

    List<Dict> unifiedpaycode();

    List<Dict> column();

    List<Dict> columnComment();
}

package cn.lxl.server.web;

import cn.lxl.server.mapper.DictMapper;
import cn.lxl.server.pojo.Dict;
import cn.lxl.server.pojo.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xinleili
 */
@RestController
@RequestMapping("/DictWeb")
public class DictWeb {

    @Autowired
    private DictMapper dictMapper;

    @RequestMapping("/web")
    public String web(){
        dictMapper.string().forEach(e->
                System.out.println("TSYS_SUBTRANS_"+e.getUrid().toUpperCase()+" = "+e.getName()));
        return "dict";
    }

    /**
     *ALTER TABLE `ats7`.`accounts` CHARACTER SET = utf8mb4, COLLATE = utf8mb4_0900_ai_ci;
     * ALTER TABLE `ats7`.`t_budgetadjust` ENGINE = InnoDB;
     * ALTER TABLE `ats7`.`accounts MODIFY COLUMN `URID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0';
     * */
    @RequestMapping("/unifiedpaycode")
    public String unifiedpaycode(){
        List<Dict> list = dictMapper.unifiedpaycode();
        list.forEach(e->{
            if(e!=null){
                System.out.println("update  t_unifiedpaycode set name = '" + e.getName() + "' where urid='" +e.getUrid()+"';");
            }
        });
        return "dict";
    }


    @RequestMapping("/tableName")
    public String tableName(){
        List<Dict> list = dictMapper.tableName();
        list.forEach(e->{
            if(e!=null){
                System.out.println("ALTER TABLE " + e.getTableName() + " CHARACTER SET = utf8mb4, COLLATE = utf8mb4_0900_ai_ci;");
                System.out.println("ALTER TABLE " + e.getTableName() + " ENGINE = InnoDB;");
            }
        });
        return "dict";
    }

    /**
     * ALTER TABLE `ats7`.`tsys_user`
     * MODIFY COLUMN `user_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户编码' AFTER `urid`;
     * */
    @RequestMapping("/column")
    public String column(){
        List<Dict> list = dictMapper.column();
        list.forEach(
                e->{
                    if(e!=null){
                        StringBuilder sb = new StringBuilder();
                        sb.append("ALTER TABLE ");
                        sb.append(e.getTableName());
                        sb.append(" MODIFY COLUMN ");
                        sb.append(" ").append(e.getColumnName()).append(" ");
                        sb.append(" ").append("varchar(").append(e.getCharacterMaximumLength()).append(") ");
                        sb.append(" CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  ;");
                        if(!StringUtils.isEmpty(e.getColumnDefault())){
                            sb = new StringBuilder(sb.substring(0, sb.length() - 2));
                            sb.append(" DEFAULT '").append(e.getColumnDefault()).append("'  ;");
                        }
                        if(!StringUtils.isEmpty(e.getColumnComment())){
                            sb = new StringBuilder(sb.substring(0, sb.length() - 2));
                            sb.append(" COMMENT '").append(e.getColumnComment()).append("' ;");
                        }else {
                            sb = new StringBuilder(sb.substring(0, sb.length() - 2));
                            sb.append(" COMMENT '").append(" 资金系统基础字段，不做业务参考 ").append("' ;");
                        }
                        System.out.println(sb.toString());
                    }
                }
        );
        return "dict";
    }

    @RequestMapping("/columnComment")
    public String columnComment(){
        List<Dict> list = dictMapper.columnComment();
        list.forEach(
                e->{
                    if(e!=null){
                        StringBuilder sb = new StringBuilder();
                        sb.append("ALTER TABLE ");
                        sb.append(e.getTableName());
                        sb.append(" MODIFY COLUMN ");
                        sb.append(" ").append(e.getColumnName()).append(" ");
                        sb.append(" ").append(e.getColumnType()).append(") ");

                        if(!StringUtils.isEmpty(e.getColumnDefault())){
                            sb = new StringBuilder(sb.substring(0, sb.length() - 2));
                            sb.append(" DEFAULT '").append(e.getColumnDefault()).append("'  ;");
                        }
                        sb = new StringBuilder(sb.substring(0, sb.length() - 2));
                        sb.append(" COMMENT '").append(" 资金系统基础字段，不做业务参考 ").append("' ;");
                        System.out.println(sb.toString());
                    }
                }
        );
        return "dict";
    }


    @RequestMapping("/index")
    public void index(){
        List<Index> list = dictMapper.lists();
        Map<String, List<Index>> map = list.stream()
                .collect(Collectors.toMap(Index::getIndexName,
                        p->{
                            List<Index> indexList = new ArrayList<>();
                            indexList.add(p);
                            return indexList;
                        },
                        (List<Index> value1, List<Index> value2) -> {
                            value1.addAll(value2);
                            return value1;
                        }
                        ));

        map.forEach((k,v)->{
            StringBuffer sb = new StringBuffer();
            if (CollectionUtils.isEmpty(v)) {

            }else if (v.size() == 1){
                Index index = v.get(0);
                if(!index.getColumnName().equalsIgnoreCase("urid")){
                    sb.append("ALTER TABLE ").append(index.getTableName());
                    if(index.getUniqueness().equalsIgnoreCase("UNIQUE")){
                        sb.append(" ADD UNIQUE INDEX ");
                    }else {
                        sb.append(" ADD INDEX ");
                    }

                    sb.append(index.getIndexName())
                            .append("( ").append(index.getColumnName()).append( ");");
                    System.out.println(sb.toString());
                }

            }else if (v.size() > 1){
                Index index = v.get(0);
                String column=v.stream().map(Index::getColumnName).collect(Collectors.joining(","));
                sb.append("ALTER TABLE ").append(index.getTableName());
                        if(index.getUniqueness().equalsIgnoreCase("UNIQUE")){
                            sb.append(" ADD UNIQUE INDEX ");
                        }else {
                            sb.append(" ADD INDEX ");
                        }

                        sb.append(index.getIndexName())
                        .append("( ")
                        .append(column)
                        .append(" );");
                System.out.println(sb.toString());
            }
        });
    }


    @RequestMapping("/indexDel")
    public void indexDel(){
        List<Index> list = dictMapper.lists();
        Map<String, List<Index>> map = list.stream()
                .collect(Collectors.toMap(Index::getIndexName,
                        p->{
                            List<Index> indexList = new ArrayList<>();
                            indexList.add(p);
                            return indexList;
                        },
                        (List<Index> value1, List<Index> value2) -> {
                            value1.addAll(value2);
                            return value1;
                        }
                ));

        map.forEach((k,v)->{
            StringBuffer sb = new StringBuffer();
            if (CollectionUtils.isEmpty(v)) {

            }else if (v.size() == 1){
                Index index = v.get(0);
                if(index.getUniqueness().equalsIgnoreCase("UNIQUE")
                        &&index.getColumnName().equalsIgnoreCase("urid")){
                    sb.append("ALTER TABLE ").append(index.getTableName()).append(" DROP INDEX ")
                            .append(index.getIndexName()).append(";");
                    System.out.println(sb.toString());
                }


            }
        });
    }
}

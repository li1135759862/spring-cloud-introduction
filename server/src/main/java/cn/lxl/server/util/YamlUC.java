package cn.lxl.server.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * @author xinleili
 */
@Slf4j
public class YamlUC {

    private static Map<String, String> fileDir = new HashMap<>();

    private static Set<String> exist = new HashSet<>();

    private static Set<String> sets = new HashSet<>();


    public static Set<String> getExist() {
        return exist;
    }

    public static void setExist(Set<String> exist) {
        YamlUC.exist = exist;
    }

    public static Set<String> getSets() {
        return sets;
    }

    public static void setSets(Set<String> sets) {
        YamlUC.sets = sets;
    }

    static {
        fileDir.put("F_INS_ACCOUNTING_", "/Users/xinleili/git/br/Insurance/fingard-insurance-accounting/fingard-insurance-accounting-provider/src/main/resources/");
        fileDir.put("F_INS_ACCOUNTS_", "/Users/xinleili/git/br/Insurance/fingard-insurance-accounts/fingard-insurance-accounts-provider/src/main/resources/");
        fileDir.put("F_INS_ARP_", "/Users/xinleili/git/br/Insurance/fingard-insurance-arp/fingard-insurance-arp-provider/src/main/resources/");
        fileDir.put("F_INS_BASE_", "/Users/xinleili/git/br/Insurance/fingard-insurance-base/fingard-insurance-base-provider/src/main/resources/");
        fileDir.put("F_INS_CHECK_", "/Users/xinleili/git/br/Insurance/fingard-insurance-check/fingard-insurance-check-provider/src/main/resources/");
        fileDir.put("F_INS_INNER_APPLY_", "/Users/xinleili/git/br/Insurance/fingard-insurance-innerapply/fingard-insurance-innerapply-provider/src/main/resources/");
        fileDir.put("F_INS_MONITOR_", "/Users/xinleili/git/br/Insurance/fingard-insurance-monitor/fingard-insurance-monitor-provider/src/main/resources/");
        fileDir.put("F_INS_SETTLEMENT_", "/Users/xinleili/git/br/Insurance/fingard-insurance-settlement/fingard-insurance-settlement-provider/src/main/resources/");
    }


    public static void searchfileDir(){
        fileDir.values().forEach(e->{
            searchFile(e);
            //System.out.println(e.toString());
        });
    }

    public static void searchFile(String fileName){
        File file = new File(fileName);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files!=null){
                for (File f : files) {
                    if(f.isDirectory()){
                        searchFile(f.getPath());
                    }else {
                        loadYaml(f.getPath());
                    }
                }
            }
        }
    }




    public static void loadYaml(String fileName) {
        if(!(fileName.indexOf(".yaml")>0)){
            return;
        }
        try {
            //初始化Yaml解析器
            Yaml yaml = new Yaml();
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            //读入文件
            Object object= yaml.load(fileInputStream);
            if(object instanceof LinkedHashMap){
                LinkedHashMap<Object, Object> result = (LinkedHashMap<Object, Object>) object;
                Object tsvcinterface = result.get("TSVCINTERFACE");
                if(tsvcinterface instanceof ArrayList){
                    ArrayList<Object> tsvcinterface1 = (ArrayList<Object>) tsvcinterface;
                    if(!tsvcinterface1.isEmpty()){
                        tsvcinterface1.forEach(s->{
                            if(s instanceof LinkedHashMap){
                                String value = (String) ((LinkedHashMap)s).get("C_EXPLAIN");
                                //System.out.println(value);
                                if(!StringUtils.isEmpty(value)&&!exist.contains(value.trim())){
                                    sets.add(value.trim());
                                }
                                value = (String) ((LinkedHashMap)s).get("C_DICNAME");
                                //System.out.println(value);
                                if(!StringUtils.isEmpty(value)&&!exist.contains(value.trim())){
                                    sets.add(value.trim());
                                }
                            }
                        });
                    }
                }
                Object tsvcviewconfig = result.get("TSVCVIEWCONFIG");
                if(tsvcviewconfig instanceof ArrayList){
                    ArrayList<Object> tsvcinterface1 = (ArrayList<Object>) tsvcviewconfig;
                    if(!tsvcinterface1.isEmpty()){
                        tsvcinterface1.forEach(s->{
                            if(s instanceof LinkedHashMap){
                                String value = (String) ((LinkedHashMap)s).get("C_VIEWNAME");
                                //System.out.println(value);
                                if(!StringUtils.isEmpty(value)&&!exist.contains(value.trim())){
                                    sets.add(value.trim());
                                }
                                value = (String) ((LinkedHashMap)s).get("C_HYPERLINK");
                                //System.out.println(value);
                                if(!StringUtils.isEmpty(value)&&!exist.contains(value.trim())){
                                    String[] ss = value.split(",");
                                    sets.add(ss[0].trim());
                                }
                                value = (String) ((LinkedHashMap)s).get("C_EVENT");
                                //System.out.println(value);
                                if(!StringUtils.isEmpty(value)&&!exist.contains(value.trim())){
                                    String[] ss = value.split(",");
                                    sets.add(ss[0].trim());
                                }
                                value = (String) ((LinkedHashMap)s).get("C_VIEWNAME");
                                //System.out.println(value);
                                if(!StringUtils.isEmpty(value)&&!exist.contains(value.trim())){
                                    sets.add(value.trim());
                                }

                                value = (String) ((LinkedHashMap)s).get("C_DICNAME");
                                //System.out.println(value);
                                if(!StringUtils.isEmpty(value)&&!exist.contains(value.trim())){
                                    sets.add(value.trim());
                                }
                            }
                        });
                    }
                }


//                ArrayList<LinkedHashMap> tsvcviewconfig = (ArrayList<LinkedHashMap>) result.get("TSVCVIEWCONFIG");
//                if(!CollectionUtils.isEmpty(tsvcviewconfig)){
//                    tsvcviewconfig.forEach(s->{
//                        String value = (String) s.get("C_VIEWNAME");
//                        System.out.println(value);
//                        if(!exist.contains(value)){
//                            sets.add(value);
//                        }
//                    });
//                }
            }

        }catch (Exception e){
            log.error(e.getMessage(), e);
        }

    }

    /**
     * 把中文转成Unicode码
     *
     * @param str
     * @return
     */
    public static String chinaToUnicode(String str) {
        if(StringUtils.isEmpty(str) ||!str.matches("^[\u4e00-\u9fa5]+$")){
            return str;
        }
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);

            if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

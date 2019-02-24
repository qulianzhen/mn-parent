package package ${currentFilePkg};

import com.mn.commonbean.tkmapper.MyMapper;
import ${javaPackagesMap["[java].ftl"]}.${entityName};

public interface ${entityName?uncap_first}Mapper extends MyMapper<${entityName}> {

}
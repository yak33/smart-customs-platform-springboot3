/*
* All Rights Reserved: Copyright [2024] [Zhuang Pan (paynezhuang@gmail.com)]
* Open Source Agreement: Apache License, Version 2.0
* For educational purposes only, commercial use shall comply with the author's copyright information.
* The author does not guarantee or assume any responsibility for the risks of using software.
*
* Licensed under the Apache License, Version 2.0 (the "License").
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import com.smart.customs.infrastructure.domain.BaseVO;
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>
<#if entityLombokModel>
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
</#if>
<#if entitySerialVersionUID>
    import java.io.Serial;
</#if>

/**
* ${table.comment!} Entity 实体类
*
* @Author ${author}
* @ProjectName panis-boot
* @ClassName ${package.Entity}.${entity}
* @CreateTime ${date}
*/

<#if entityLombokModel>
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
</#if>
<#if table.convert>
@TableName("${schemaName}${table.name}")
</#if>
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass} {
<#else>
    public class ${entity} {
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.comment!?length gt 0>
    /**
    * ${field.comment}
    */
    </#if>
    <#assign fieldName = field.propertyName>
    <#if field.name?starts_with("is_")>
        <#assign fieldName = fieldName?substring(2)?uncap_first>
    @TableField("${field.annotationColumnName}")
    </#if>
    private ${field.propertyType} ${fieldName};

</#list>
}
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

package ${dtoPackageName!};

<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
</#if>
import java.io.Serializable;

/**
* ${table.comment!} 查询 DTO 对象
*
* @Author ${author}
* @ProjectName panis-boot
* @ClassName ${dtoPackageName!}.${entity}SearchDTO
* @CreateTime ${date}
*/

<#if entityLombokModel>
@Getter
@Setter
</#if>
<#if springdoc>
@Schema(name = "${entity}SearchDTO", description = "${table.comment!} 查询 DTO 对象")
</#if>
<#if entitySerialVersionUID>
public class ${entity}SearchDTO implements Serializable {
<#else>
public class ${entity} {
</#if>

<#------------  BEGIN 字段循环遍历  ------------>
<#assign searchColumns = tableColumnList?filter(column -> column.search == '1')>
<#list searchColumns as column>
    <#if column.columnComment!?length gt 0>
    @Schema(description = "${column.columnComment!}")
    </#if>
    private ${column.javaType} ${column.propertyName};

</#list>
<#------------  END 字段循环遍历  ------------>
}
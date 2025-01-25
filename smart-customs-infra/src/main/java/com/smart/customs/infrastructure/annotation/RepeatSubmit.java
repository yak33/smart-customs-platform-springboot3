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

package com.smart.customs.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 防止重复提交注解
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.annotation.RepeatSubmit
 * @CreateTime 2024/12/2 - 14:12
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {

    /**
     * 间隔时间(单位:s)，默认 5 秒
     * <p>
     * -1：表示不限制
     */
    int interval() default 5;

    /**
     * 提示消息
     */
    String message() default "重复请求，请稍后重试";
}

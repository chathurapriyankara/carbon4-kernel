/*
*Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/
package org.wso2.carbon.utils.carbonutils;

import junit.framework.TestCase;
import org.wso2.carbon.CarbonException;
import org.wso2.carbon.utils.CarbonUtils;

import java.io.ByteArrayInputStream;

/**
 * This class contains CarbonUtils test cases
 */
public class CarbonUtilsTest extends TestCase {

    public void testReplaceSystemVariablesInXml() throws Exception {

        String fileData = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><!DOCTYPE sampleVal "
                + "[  <!ENTITY sampleVal SYSTEM \"file:///media/sample\">]><configuration>"
                + "<driverClassName>com.mysql.jdbc.Driver</driverClassName><url>jdbc:mysql://localhost:3306/test</url>"
                + "<username>&sampleVal;</username><password encrypted=\"true\">test</password></configuration>";

        CarbonException carbonException = null;
        try {
            CarbonUtils.replaceSystemVariablesInXml(new ByteArrayInputStream(fileData.getBytes()));
        } catch (CarbonException e) {
            carbonException = e;
        }

        assertEquals("XML external entity was allowed","Error in building Document", carbonException.getMessage());
    }
}

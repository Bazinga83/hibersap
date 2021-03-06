/*
 * Copyright (c) 2008-2014 akquinet tech@spree GmbH
 *
 * This file is part of Hibersap.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hibersap.configuration;

import org.hibersap.HibersapException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
* @author Carsten Erker
*/
public final class Environment {

    public static final String HIBERSAP_XML_FILE = "/META-INF/hibersap.xml";
    public static final String VERSION = readHibersapVersion();
    private static final String HIBERSAP_VERSION_FILE = "hibersap-version.properties";
    private static final String HIBERSAP_VERSION_PROPERTY_KEY = "hibersap-version";

    private Environment() {
        // should not be instantiated
    }

    private static String readHibersapVersion() {
        String version;
        InputStream inputStream = null;
        try {
            inputStream = Environment.class.getResourceAsStream( "/" + HIBERSAP_VERSION_FILE );
            final Properties properties = new Properties();
            properties.load( inputStream );
            version = properties.getProperty( HIBERSAP_VERSION_PROPERTY_KEY );
        } catch ( IOException e ) {
            throw new HibersapException( "Can not load file " + HIBERSAP_VERSION_FILE
                                                 + ". This file is part of the hibersap-core library and should always be there." );
        } finally {
            if ( inputStream != null ) {
                try {
                    inputStream.close();
                } catch ( IOException e ) {
                    // ignore
                }
            }
        }
        return version;
    }
}

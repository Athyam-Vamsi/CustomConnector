package com.mycompany;

import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;

@Xml(prefix="CustomConnector")
@Extension(name="restConnector")
@Configurations(CustomConfinguration.class)
public class CustomExtension {

}

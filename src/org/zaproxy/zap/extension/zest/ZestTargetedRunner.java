/*
 * Zed Attack Proxy (ZAP) and its related class files.
 * 
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
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

package org.zaproxy.zap.extension.zest;

import org.apache.log4j.Logger;
import org.parosproxy.paros.network.HttpMessage;
import org.parosproxy.paros.view.View;
import org.zaproxy.zap.extension.script.TargetedScript;

public class ZestTargetedRunner extends ZestZapRunner implements TargetedScript {

	private ExtensionZest extension = null;
	private ZestScriptWrapper script = null;
	
    private static Logger logger = Logger.getLogger(ZestTargetedRunner.class);

	public ZestTargetedRunner(ExtensionZest extension, ZestScriptWrapper script) {
		super(extension, script);
		this.extension = extension;
		this.script = script;
	}

	@Override
	public void invokeWith(HttpMessage msg) {
		try {
			this.extension.clearResults();
			this.run(script.getZestScript(), ZestZapUtils.toZestRequest(msg));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			script.setLastException(e);
			if (View.isInitialised()) {
				// Also write to Output tab
				View.getSingleton().getOutputPanel().append(e.getMessage() + e.getStackTrace());
			}
		}
	}
}

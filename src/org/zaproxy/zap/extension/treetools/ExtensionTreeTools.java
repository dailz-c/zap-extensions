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
package org.zaproxy.zap.extension.treetools;

import java.net.MalformedURLException;
import java.net.URL;

import org.parosproxy.paros.Constant;
import org.parosproxy.paros.extension.ExtensionAdaptor;
import org.parosproxy.paros.extension.ExtensionHook;

public class ExtensionTreeTools extends ExtensionAdaptor {
	
	public static final String NAME = "TreeTools";
	private PopupMenuTreeTools popupMenuTreeTools = null;
	//private ExtensionHook hook = null;  chs removed when going from alpha to beta
	/**
     * 
     */
    public ExtensionTreeTools() {
        super("TreeTools");
        this.setOrder(667);	// Want this to be as low as possible :)
	}
	
	@Override
	public void hook(ExtensionHook extensionHook) {
	    super.hook(extensionHook);
	    //hook = extensionHook;  chs removed when going from alpha to beta
	    if (getView() != null) {
            extensionHook.getHookMenu().addPopupMenuItem(getPopupMenuTreeTools());
	    }
	}
	
	private PopupMenuTreeTools getPopupMenuTreeTools() {
		if (popupMenuTreeTools == null) {
			popupMenuTreeTools = new PopupMenuTreeTools();
		}
		return popupMenuTreeTools;
		
	}

	@Override
	public String getAuthor() {
		return "Carl Sampson";
	}

	@Override
	public String getDescription() {
		return Constant.messages.getString("treetools.desc");
	}

	@Override
	public URL getURL() {
		try {
			return new URL("http://www.chs.us");
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	@Override
	public boolean canUnload() {
		return true;
	}
}
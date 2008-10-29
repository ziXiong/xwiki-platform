/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.xpn.xwiki.wysiwyg.client.util.internal;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.xpn.xwiki.wysiwyg.client.util.DOMUtils;
import com.xpn.xwiki.wysiwyg.client.util.Document;

/**
 * Contains methods from {@link DOMUtils} that require a different implementation in Internet Explorer.
 * 
 * @version $Id$
 */
public class IEDOMUtils extends DOMUtils
{
    /**
     * {@inheritDoc}
     * 
     * @see DOMUtils#getComputedStyleProperty(Element, String)
     */
    public native String getComputedStyleProperty(Element el, String propertyName) /*-{
        return el.currentStyle[propertyName];
    }-*/;

    /**
     * {@inheritDoc}
     * 
     * @see DOMUtils#importNode(Document, Node, boolean)
     */
    public Node importNode(Document doc, Node externalNode, boolean deep)
    {
        switch (externalNode.getNodeType()) {
            case Node.TEXT_NODE:
                return doc.createTextNode(externalNode.getNodeValue());
            case Node.ELEMENT_NODE:
                Element externalElement = Element.as(externalNode);
                Element internalElement = doc.xCreateElement(externalElement.getTagName());
                JsArrayString attrNames = getAttributeNames(externalElement);
                for (int i = 0; i < attrNames.length(); i++) {
                    String attrName = attrNames.get(i);
                    internalElement.setAttribute(attrName, externalElement.getAttribute(attrName));
                }
                if (deep) {
                    // TODO
                }
                return internalElement;
            default:
                throw new IllegalArgumentException("Cannot import node of type " + externalNode.getNodeType() + "!");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see DOMUtils#getAttributeNames(Element)
     */
    public native JsArrayString getAttributeNames(Element element) /*-{
        var attrNames = [];
        for(var i = 0; i < element.attributes.length; i++){
            if(element.attributes[i].specified) {
                attrNames.push(element.attributes[i].nodeName);
            }
        }
        return attrNames;
    }-*/;
}

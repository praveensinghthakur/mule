/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transport.xmpp;

import org.mule.api.endpoint.MalformedEndpointException;
import org.mule.endpoint.UserInfoEndpointURIBuilder;
import org.mule.transport.xmpp.i18n.XmppMessages;

import java.net.URI;
import java.util.Properties;

/**
 * Does the same as the UserInfoEndpointBuilder but also ensures that a path is set
 * on the uri. The path is used as either the groupChat name or the recipient name of
 * a one on one chat.
 */
// TODO xmpp: is this class still necessary?
public class XmppEndpointURIBuilder extends UserInfoEndpointURIBuilder
{
    protected void setEndpoint(URI uri, Properties props) throws MalformedEndpointException
    {
        if (uri.getPath().length() == 0)
        {
            throw new MalformedEndpointException(XmppMessages.noRecipientInUri(), uri.toString());
        }
        if (props.getProperty(XmppConnector.XMPP_GROUP_CHAT, "false").equalsIgnoreCase("true"))
        {
            if (props.getProperty(XmppConnector.XMPP_NICKNAME, null) == null)
            {
                throw new MalformedEndpointException(XmppMessages.nicknameMustBeSet(), uri.toString());
            }
        }
        super.setEndpoint(uri, props);
    }
}

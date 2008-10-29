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
package com.xpn.xwiki.wysiwyg.client.ui.cmd;

import java.util.ArrayList;

/**
 * A collection of {@link CommandListener}. It provides an easy way of notifying all the listeners when a
 * {@link Command} is executed.
 * 
 * @version $Id$
 */
public class CommandListenerCollection extends ArrayList<CommandListener>
{
    /**
     * Field required by all {@link java.io.Serializable} classes.
     */
    private static final long serialVersionUID = 7719987791305106197L;

    /**
     * Notifies all the listeners in this collection.
     * 
     * @param sender The command manager that executed the command.
     * @param command The command executed.
     * @param param The parameter used when the command was executed.
     */
    public void fireCommand(CommandManager sender, Command command, String param)
    {
        for (CommandListener listener : this) {
            listener.onCommand(sender, command, param);
        }
    }
}

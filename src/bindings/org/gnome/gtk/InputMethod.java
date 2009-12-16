/*
 * InputMethod.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.EventKey;
import org.gnome.glib.Object;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
/**
 * Complex input handling.
 * 
 * <p>
 * <i>In GTK, these are <code>GtkIMContext</code> objects. This class and its
 * concrete subclasses are presented here according to the Java naming
 * conventions.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.14
 */
/*
 * We are not exposing this as an application programming interface for you to
 * implement your own input methods; if you need to create a custom input
 * method it needs to be done in C and via GTK's dynamic module loading
 * machinery.
 */
public abstract class InputMethod extends Object
{
    protected InputMethod(long pointer) {
        super(pointer);
    }

    /**
     * Signal emitted when the input method completes its composition.
     * 
     * @author Andrew Cowie
     * @since 4.0.14
     */
    public interface Commit extends GtkIMContext.CommitSignal
    {
        public void onCommit(InputMethod source, String str);
    }

    /**
     * Hookup a <code>InputMethod.Commit</code> handler.
     * 
     * @since 4.0.14
     */
    public void connect(InputMethod.Commit handler) {
        GtkIMContext.connect(this, handler, false);
    }

    /**
     * Find out whether the input method has handled a keystroke, or whether
     * it needs to be further handled or propegated.
     * <p>
     * This is for use in <code>Widget.KeyPressEvent</code> and
     * <code>Widget.KeyReleaseEvent</code> handlers when hooking up an input
     * method to a Widget, where the code will look something like:
     * 
     * <pre>
     * drawing.connect(new Widget.KeyPressEvent() {
     *     public boolean onKeyPressEvent(Widget source, EventKey event) {
     *         if (input.filterKeypress(event)) {
     *             return true;
     *         }
     * 
     *         // or carry on with your logic,
     *         if (doSomething()) {
     *             return true;
     *         }
     * 
     *         // otherwise progegate the keystroke up to the default handler
     *         return false;
     *     }
     * });
     * </pre>
     * 
     * You need to do call this for both key presses and releases (at least,
     * for the default input method to work right, anyway).
     * 
     * @since 4.0.14
     */
    public boolean filterKeypress(EventKey event) {
        return GtkIMContext.filterKeypress(this, event);
    }

    /**
     * How hould the InputMethod provide feedback to the user? The default is
     * <code>true</code>, which is to ask the Widget to display in-line,
     * communicating the preedit string via the signal handlers. Otherwise,
     * the InputMethod can attempt to use some alternative (internal) means.
     * 
     * @since 4.0.14
     */
    public void setUsePreedit(boolean setting) {
        GtkIMContext.setUsePreedit(this, setting);
    }
}
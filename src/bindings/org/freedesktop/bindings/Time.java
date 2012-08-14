/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.freedesktop.bindings;

/*
 * This code imported into java-gnome from the SlashTime project's
 * com.operationaldynamics.slashtime.NativeTime; it's mostly here because we
 * needed access to three (and only three) native functions from the C library
 * in the `slashtime` program, and making that application's build
 * infrastructure native aware was a lot of pain for not much benefit. So here
 * they are, java-gnome style.
 */

/**
 * Utility functions to format date/time groups in timezone aware fashion.
 * These methods work off the timezone data installed on your system and are
 * accessed directly through the standard C library.
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
public class Time
{
    private Time() {}

    /**
     * Adjust the timezone being used for formatted time/date output
     * calculations.
     * 
     * <p>
     * <b>This will change the timezone as far as the entire program is
     * concerned.</b> If you have some reason to restore the original setting,
     * use {@link Environment#getEnv(String) getEnv()}.
     * 
     * <p>
     * <i>This works by changing the environment variable <code>TZ</code> and
     * then calling <code>tzset()</code>. That may or may not be what you
     * want. We've made it static since this impacts the <b>entire</b>
     * process. If someone can figure out a way to change the timezone that
     * <code>strftime()</code> thinks it is in without doing this, then please
     * let us know.</i>
     * 
     * @param zoneinfo
     *            A String of the form "Australia/Sydney", "America/Toronto",
     *            "America/New_York", or "Europe/London", etc. Some zones have
     *            definitive abbreviations, notably Universal Time,
     *            Co-ordinated as "UTC". In case it wasn't obvious, these are
     *            files in <code>/usr/share/zoneinfo/</code> and are relative
     *            to that path.
     * @see <a
     *      href="http://manpages.courier-mta.org/htmlman3/tzset.3.html">tzset(3)</a>
     */
    /*
     * TODO Figure out what affect this has on the Java Date and Calendar
     * classes, if any.
     */
    public static void setTimeZone(final String zoneinfo) {
        if (zoneinfo == null) {
            throw new IllegalArgumentException("zoneinfo can't be null");
        }
        if (zoneinfo.equals("")) {
            throw new IllegalArgumentException("zoneinfo can't be blank");
        }

        tzset(zoneinfo);
    }

    private static final native void tzset(String zoneinfo);

    /**
     * Output the date per the descriptor given in the <code>format</code>
     * parameter.
     * 
     * <p>
     * Feel free to call this several times to get isolated Strings with just
     * the bits you want. For example, we do
     * 
     * <pre>
     * long when;
     * 
     * when = System.currentTimeMillis();
     * 
     * ... = formatTime(&quot;%H:%M&quot;, when);
     * ... = formatTime(&quot;%a,%e %b %y&quot;, when);
     * ... = formatTime(&quot;%Z&quot;, when);
     * </pre>
     * 
     * in quick succession to get time, date, and timezone, respectively, just
     * the way we like it.
     * 
     * <p>
     * <i>This is a wrapper around <code>strftime()</code> from the standard C
     * library. It is exposed because the formatting is done according to the
     * value of the <code>TZ</code> environment variable, which in turn draws
     * from the system zoneinfo libraries, data which is <b>much</b> more up
     * to date than what Java offers.</i>
     * 
     * @param when
     *            the number of seconds since Epoch being the date/time group
     *            you wish to present according to <code>format</code>.
     * @see <a
     *      href="http://manpages.courier-mta.org/htmlman3/strftime.3.html">strftime(3)</a>
     */
    public static String formatTime(final String format, final long when) {
        if (format == null) {
            throw new IllegalArgumentException("format can't be null");
        }
        if (format.equals("")) {
            throw new IllegalArgumentException(
                    "Not really much point in a format String with nothing in it");
        }
        return strftime(format, when);
    }

    private static final native String strftime(String format, long timestamp);

    /**
     * Compose a timestamp (the number of seconds since epoch) from individual
     * components of a date/time group. Takes into account the current system
     * timezone setting.
     * 
     * <p>
     * <i>This is essentially a wrapper around <code>mktime()</code>.</i>
     * 
     * @param year
     *            the year, four digits
     * @param month
     *            the month, range <code>1-12</code>
     * @param day
     *            the day, range <code>1-{28,29,30,31}</code> (depending on
     *            the month, of course)
     * @param hour
     *            the hour, range <code>0-23</code>
     * @param minute
     *            the minute, <code>0-59</code>
     * @param second
     *            the second, <code>0-59</code>
     * @see <a
     *      href="http://manpages.courier-mta.org/htmlman3/ctime.3.html">mktime(3)</a>
     */
    public static long makeTime(final int year, final int month, final int day, final int hour,
            final int minute, final int second) {
        if (year < 0) {
            throw new IllegalArgumentException("Year");
        }
        if ((month < 1) || (month > 12)) {
            throw new IllegalArgumentException("Month");
        }
        if ((day < 1) || (day > 31)) {
            throw new IllegalArgumentException("Day");
        }
        if ((hour < 0) || (hour > 23)) {
            throw new IllegalArgumentException("Hour");
        }
        if ((minute < 0) || (minute > 59)) {
            throw new IllegalArgumentException("Minute");
        }
        if ((second < 0) || (second > 59)) {
            throw new IllegalArgumentException("Second");
        }

        return mktime(year, month, day, hour, minute, second);
    }

    private static final native long mktime(int year, int month, int day, int hour, int minute,
            int second);
}

/*
 * GtkLabel.c
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 *
 *                      THIS FILE WILL BE GENERATED CODE!
 *
 * To modify its contents or behaviour, either update the generation program,
 * change the information in the source defs file, or implement an override
 * for this class.
 */

#include <jni.h>
#include <gtk/gtk.h>

#include "org_gnome_gtk_GtkLabel.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkLabel_gtk_1label_1new
  (JNIEnv *env, jclass cls, jstring _str)
{
	GtkWidget* label;
	gchar* str;
	
	// translate arg str
	str = (gchar*) (*env)->GetStringUTFChars(env, _str, NULL);
	if (str == NULL) {
		return 0; /* OutOfMemoryError already thrown */
	}

	// call constructor
	label = gtk_label_new(str);

	// cleanup arg str
	(*env)->ReleaseStringUTFChars(env, _str, str);

	// return address
	return (jlong) label;
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkLabel_gtk_1label_1set_1label
  (JNIEnv *env, jclass cls, jlong _self, jstring _str)
{
	GtkLabel* self;
	const gchar* str;

	// translate arg self
	self = (GtkLabel*) _self;

	// translate arg str
	str = (gchar*) (*env)->GetStringUTFChars(env, _str, NULL);
	if (str == NULL) {
		return; /* OutOfMemoryError already thrown */
	}

	// call function
	gtk_label_set_label(self, str);

	// cleanup arg self

	// cleanup arg str
	(*env)->ReleaseStringUTFChars(env, _str, str);
}

JNIEXPORT jstring JNICALL
Java_org_gnome_gtk_GtkLabel_gtk_1label_1get_1label
  (JNIEnv *env, jclass cls, jlong _self)
{
	GtkLabel* self;
	const gchar* label;

	// translate arg self
	self = (GtkLabel*) _self;

	// call function
	label = gtk_label_get_label(self);

	// cleanup arg self

	// return string
	return (*env)->NewStringUTF(env, label);
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkLabel_gtk_1label_1set_1text
  (JNIEnv *env, jclass cls, jlong _self, jstring _str)
{
	GtkLabel* self;
	const gchar* str;

	// translate arg self
	self = (GtkLabel*) _self;

	// translate arg str
	str = (gchar*) (*env)->GetStringUTFChars(env, _str, NULL);
	if (str == NULL) {
		return; /* OutOfMemoryError already thrown */
	}

	// call function
	gtk_label_set_text(self, str);

	// cleanup arg self

	// cleanup arg str
	(*env)->ReleaseStringUTFChars(env, _str, str);
}

JNIEXPORT jstring JNICALL
Java_org_gnome_gtk_GtkLabel_gtk_1label_1get_1text
  (JNIEnv *env, jclass cls, jlong _self)
{
	GtkLabel* self;
	const gchar* label;

	// translate arg self
	self = (GtkLabel*) _self;

	// call function
	label = gtk_label_get_text(self);

	// cleanup arg self

	// return string
	return (*env)->NewStringUTF(env, label);
}


JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkLabel_gtk_1label_1set_1use_1markup
  (JNIEnv *env, jclass cls, jlong _self, jboolean _setting)
{
	GtkLabel* self;
	gboolean setting;

	// translate arg self
	self = (GtkLabel*) _self;
	
	// translate arg setting
	setting = (gboolean) _setting;

	// call function
	gtk_label_set_use_markup(self, setting);

	// cleanup arg self
	
	// cleanup arg setting
}


JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkLabel_gtk_1label_1set_1angle
  (JNIEnv *env, jclass cls, jlong _self, jdouble _angle)
{
	GtkLabel* self;
	gdouble angle;

	// translate arg self
	self = (GtkLabel*) _self;
	
	// translate arg angle
	angle = (gdouble) _angle;

	// call function
	gtk_label_set_angle(self, angle);

	// cleanup arg self
}


JNIEXPORT jdouble JNICALL
Java_org_gnome_gtk_GtkLabel_gtk_1label_1get_1angle
  (JNIEnv *env, jclass cls, jlong _self)
{
	GtkLabel* self;
	gdouble angle;

	// translate arg self
	self = (GtkLabel*) _self;

	// call function
	angle = gtk_label_get_angle(self);
	
	// cleanup arg self
	
	// return double
	return (jdouble) angle;
}

# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html


# If you keep the line number information, uncomment this to
# hide the original source file email.

# Basic
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
-keep class *

# Fabric
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
#-keepresourcexmlelements manifest/application/meta-data@email=io.fabric.ApiKey
#printmapping mapping.txt


# Dagger 2
-dontwarn com.google.errorprone.annotations.**

# Retrofit 2.X
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-dontwarn okio.**
-keepattributes Signature
-keepattributes Exceptions
-keepattributes Annotation
-dontwarn okhttp3.**
-dontwarn okio.**

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
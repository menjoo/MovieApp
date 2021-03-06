# Region Retrofit

    # Retain generic type information for use by reflection by converters and adapters.
    -keepattributes Signature
    # Retain service method parameters.
    -keepclassmembernames,allowobfuscation interface * {
        @retrofit2.http.* <methods>;
    }
    # Ignore annotation used for build tooling.
    -dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# End region Retrofit

# Region OkHttp

-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# End region OkHttp

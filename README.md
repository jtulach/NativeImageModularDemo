# Modular API & Impl Demo

Creates two Java modules. `Api` module defines and API and locates it
via `ServiceLoader`. `Impl` module implements the API and provides
different implementation. Test as:
```bash
NativeImageModularDemo$ mvn clean install
NativeImageModularDemo$ mvn -q -Pjvm -f api exec:exec
NativeImageModularDemo with following modules:
  Api
Found no API provider. Include Impl JPMS module!
NativeImageModularDemo$ mvn -q -Pjvm -f impl exec:exec
NativeImageModularDemo with following modules:
  Api
  Impl
Hi from Impl!
```

With that working, let try GraalVM's `native-image`!

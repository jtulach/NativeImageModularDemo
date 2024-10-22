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

```bash
NativeImageModularDemo$ mvn clean install -Pnative
NativeImageModularDemo$ ./impl/target/NativeImageModularDemo
NativeImageModularDemo with following modules:
  Impl
  Api
Found no API provider. Fix native-image support for modular ServiceLoader!
```
As can be seen `native-image` properly enables both JPMS modules - e.g.
`Api` and `Impl`, but the service registered by `Impl` isn't visible
automatically.


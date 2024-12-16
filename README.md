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

### Fixed in Dec 2024

> The fix has been merged as [#10202](https://github.com/oracle/graal/pull/10202)
> and will be part of GraalVM 24.2. We will also backport to GraalVM 23.1.

This demo works with [0c2731c6e441a7617013a4cfd4fcce4209e3890b GraalVM](https://github.com/oracle/graal/commit/0c2731c6e441a7617013a4cfd4fcce4209e3890b).
I used:
```bash
graal/vm$ mx --env ni-ce build
graal/vm$ export JAVA_HOME=`mx --env ni-ce graalvm-home`
```
and then used the generated JVM as:
```bash
NativeImageModularDemo$ mvn clean install -Pnative
NativeImageModularDemo$ $ ./impl/target/NativeImageModularDemo
NativeImageModularDemo with following modules:
  Api
  Impl
Hi from Impl!
```
Everything is working as expected. Thank you, GraalVM!

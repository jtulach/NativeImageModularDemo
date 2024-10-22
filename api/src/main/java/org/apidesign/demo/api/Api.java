package org.apidesign.demo.api;

import java.util.ServiceLoader;

public interface Api {
    public String hello();

    public static void main(String[] args) {
        var hint = "Include Impl JPMS module!";
        System.out.println("NativeImageModularDemo with following modules:");
        for (var m : ModuleLayer.boot().modules()) {
            if (m.getName().startsWith("jdk.")) {
                continue;
            }
            if (m.getName().startsWith("java.")) {
                continue;
            }
            if (m.getName().startsWith("org.graalvm.")) {
                continue;
            }
            if (m.getName().equals("Impl")) {
                hint = "Fix native-image support for modular ServiceLoader!";
            }
            System.out.println("  " + m.getName());
        }

        var loader = ServiceLoader.load(Api.class);
        var msg = loader.findFirst().
            map(api -> api.hello()).
            orElse("Found no API provider. " + hint);
        System.out.println(msg);
    }

}

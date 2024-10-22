module Impl {
    requires Api;

    provides org.apidesign.demo.api.Api with
            org.apidesign.demo.impl.Impl;
}

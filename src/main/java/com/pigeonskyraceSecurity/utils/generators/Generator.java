package com.pigeonskyraceSecurity.utils.generators;

public interface Generator<T, R> {
    R generate (T ...payload);
}

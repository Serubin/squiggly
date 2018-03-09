package com.github.bohnman.squiggly.core.variable;

import javax.annotation.Nullable;
import java.util.Arrays;

import static com.github.bohnman.core.lang.CoreAssert.notNull;

public class CompositeVariableResolver implements SquigglyVariableResolver {

    private final Iterable<SquigglyVariableResolver> resolvers;

    public CompositeVariableResolver(SquigglyVariableResolver... resolvers) {
        this(Arrays.asList(resolvers));
    }

    public CompositeVariableResolver(Iterable<SquigglyVariableResolver> resolvers) {
        this.resolvers = notNull(resolvers);
    }

    @Nullable
    @Override
    public Object resolveVariable(String name) {
        Object value = null;

        for (SquigglyVariableResolver resolver : resolvers) {
            value = resolver.resolveVariable(name);

            if (value != null) {
                break;
            }
        }

        return value;
    }
}

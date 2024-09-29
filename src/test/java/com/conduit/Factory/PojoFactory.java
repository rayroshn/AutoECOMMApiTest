package com.conduit.Factory;

import br.com.six2six.fixturefactory.Fixture;

public class PojoFactory {


    private PojoFactory() {
    }

    public static <T> T getObject(String label ,Class<T> type)
    {

     return type.cast(Fixture.from(type).gimme(label));

    }

}

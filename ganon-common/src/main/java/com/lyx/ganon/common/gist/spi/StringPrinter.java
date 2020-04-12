package com.lyx.ganon.common.gist.spi;

import java.util.ServiceLoader;

/**
* @author liyuxing
 */
public class StringPrinter {

    public void pringAllString() {
        ServiceLoader<IStringSupplier> iStringSuppliers = ServiceLoader.load(IStringSupplier.class);
        iStringSuppliers.forEach(iStringSupplier -> System.out.println(iStringSupplier.getString()));
    }

}

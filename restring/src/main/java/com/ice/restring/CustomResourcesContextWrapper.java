package com.ice.restring;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

/**
 * A context wrapper which provide another Resources instead of the original one.
 */
public class CustomResourcesContextWrapper extends ContextWrapper {

    protected Resources resources;

    public CustomResourcesContextWrapper(Context base, Resources resources) {
        super(base);
        this.resources = resources;
    }

    @Override
    public Resources getResources() {
        return resources;
    }
}

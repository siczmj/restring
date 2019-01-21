package com.ice.restring;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

/**
 * Main Restring context wrapper which wraps the context for providing another layout inflater &amp; resources.
 */
public class RestringContextWrapper extends ContextWrapper {

    protected RestringLayoutInflater layoutInflater;
    protected ViewTransformerManager viewTransformerManager;

    public static RestringContextWrapper wrap(Context context,
                                              StringRepository stringRepository,
                                              ViewTransformerManager viewTransformerManager) {
        return new RestringContextWrapper(context, stringRepository, viewTransformerManager);
    }

    protected RestringContextWrapper(Context base,
                                   StringRepository stringRepository,
                                   ViewTransformerManager viewTransformerManager) {
        super(
                new CustomResourcesContextWrapper(
                        base,
                        new RestringResources(base.getResources(), stringRepository))
        );
        this.viewTransformerManager = viewTransformerManager;
    }

    @Override
    public Object getSystemService(String name) {
        if (LAYOUT_INFLATER_SERVICE.equals(name)) {
            if (layoutInflater == null) {
                layoutInflater = new RestringLayoutInflater(LayoutInflater.from(getBaseContext()), this, viewTransformerManager, true);
            }
            return layoutInflater;
        }

        return super.getSystemService(name);
    }
}
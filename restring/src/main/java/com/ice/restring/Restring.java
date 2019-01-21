package com.ice.restring;

import android.content.Context;
import android.content.ContextWrapper;

import com.ice.restring.transformers.BottomNavigationViewTransformer;
import com.ice.restring.transformers.SupportToolbarTransformer;
import com.ice.restring.transformers.TextInputLayoutTransformer;
import com.ice.restring.transformers.TextViewTransformer;
import com.ice.restring.transformers.ToolbarTransformer;

import java.util.Map;

/**
 * Entry point for Restring. it will be used for initializing Restring components, setting new strings,
 * wrapping activity context.
 */
public class Restring {

    private static boolean isInitialized = false;
    private static StringRepository stringRepository;
    private static ViewTransformerManager viewTransformerManager;

    /**
     * Initialize Restring with default configuration.
     *
     * @param context of the application.
     */
    public static void init(Context context) {
        init(context, RestringConfig.getDefault());
    }

    /**
     * Initialize Restring with the specified configuration.
     *
     * @param context of the application.
     * @param config  of the Restring.
     */
    public static void init(Context context, RestringConfig config) {
        if (isInitialized) {
            return;
        }

        isInitialized = true;
        initStringRepository(context, config);
        initViewTransformer();
    }

    /**
     * Wraps context of an activity to provide Restring features.
     *
     * @param base context of an activity.
     * @return the Restring wrapped context.
     */
    public static ContextWrapper wrapContext(Context base) {
        return RestringContextWrapper.wrap(base, stringRepository, viewTransformerManager);
    }

    /**
     * Init Restring and wraps context of an activity to provide Restring features in one step.
     *
     * @param base   context of an activity.
     * @param config of the Restring.
     * @return the Restring wrapped context.
     */
    public static ContextWrapper initContext(Context base, RestringConfig config) {
        init(base, config);
        return RestringContextWrapper.wrap(base, stringRepository, viewTransformerManager);
    }

    /**
     * Set strings of a language.
     *
     * @param language   the strings are for.
     * @param newStrings the strings of the language.
     */
    public static void setStrings(String language, Map<String, String> newStrings) {
        stringRepository.setStrings(language, newStrings);
    }

    /**
     * Set a single string for a language.
     *
     * @param language the string is for.
     * @param key      the string key.
     * @param value    the string value.
     */
    public static void setString(String language, String key, String value) {
        stringRepository.setString(language, key, value);
    }

    static StringRepository getStringRepository() {
        return stringRepository;
    }

    private static void initStringRepository(Context context, RestringConfig config) {
        if (config.stringRepository != null) {
            stringRepository = config.stringRepository;
        } else {
            if (config.isPersist()) {
                stringRepository = new SharedPrefStringRepository(context);
            } else {
                stringRepository = new MemoryStringRepository();
            }
        }

        if (config.getStringsLoader() != null) {
            new StringsLoaderTask(config.getStringsLoader(), stringRepository).run();
        }
    }

    private static void initViewTransformer() {
        if (viewTransformerManager == null) {
            viewTransformerManager = new ViewTransformerManager();
            viewTransformerManager.registerTransformer(new TextViewTransformer());
            viewTransformerManager.registerTransformer(new ToolbarTransformer());
            viewTransformerManager.registerTransformer(new SupportToolbarTransformer());
            viewTransformerManager.registerTransformer(new BottomNavigationViewTransformer());
            viewTransformerManager.registerTransformer(new TextInputLayoutTransformer());
        }
    }

}

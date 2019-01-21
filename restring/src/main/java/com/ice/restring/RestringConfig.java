package com.ice.restring;

/**
 * Contains configuration properties for initializing Restring.
 */
public class RestringConfig {

    protected boolean persist;
    protected StringsLoader stringsLoader;
    protected StringRepository stringRepository;

    public boolean isPersist() {
        return persist;
    }

    public StringsLoader getStringsLoader() {
        return stringsLoader;
    }

    public StringRepository getStringRepository() {
        return stringRepository;
    }

    private RestringConfig() {
    }

    public static class Builder {
        private boolean persist;
        private StringsLoader stringsLoader;
        private StringRepository stringRepository;

        public Builder persist(boolean persist) {
            this.persist = persist;
            return this;
        }

        public Builder stringsLoader(StringsLoader loader) {
            this.stringsLoader = loader;
            return this;
        }

        public Builder stringRepository(StringRepository repository) {
            this.stringRepository = repository;
            return this;
        }

        public RestringConfig build() {
            RestringConfig config = new RestringConfig();
            config.persist = persist;
            config.stringsLoader = stringsLoader;
            config.stringRepository = stringRepository;
            return config;
        }
    }

    static RestringConfig getDefault() {
        return new Builder()
                .persist(true)
                .build();
    }
}
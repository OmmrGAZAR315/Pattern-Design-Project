package org.example.dpproject.DB;

public enum DatabaseConfig {
    DB_CLASS(System.getProperty("DB_CLASS")),
    DB_PLATFORM_CLASS(System.getProperty("DB_PLATFORM_CLASS")),
    DB_PACKAGE_PATH(System.getProperty("DB_PACKAGE_PATH")),
    DB_CONNECTION(System.getProperty("DB_CONNECTION")),
    HOST(System.getProperty("DB_HOST")),
    PORT(System.getProperty("DB_PORT")),
    DATABASE(System.getProperty("DB_DATABASE")),
    USERNAME(System.getProperty("DB_USERNAME")),
    PASSWORD(System.getProperty("DB_PASSWORD"));

    private final String value;

    DatabaseConfig(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

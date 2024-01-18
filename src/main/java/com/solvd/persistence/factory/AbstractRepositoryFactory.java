package com.solvd.persistence.factory;

public class AbstractRepositoryFactory {
    public static RepositoryFactory createFactory(String type) {
        RepositoryFactory result;
        switch (type) {
            case "RELATIONAL":
                result = new RelationalRepositoryFactory();
                break;
            case "NOSQL":
                result = new NoSQLRepositoryFactory();
                break;
            default:
                throw new RuntimeException("Unable to find a needed factory");
        }
        return result;
    }
}

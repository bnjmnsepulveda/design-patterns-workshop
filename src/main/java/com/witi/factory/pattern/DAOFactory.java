package com.witi.factory.pattern;

public interface DAOFactory<T> {
    T getDAO();
}

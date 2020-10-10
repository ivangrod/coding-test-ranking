package com.idealista.adrankingchallenge.application;

public interface UseCase<T, R> {

  R execute(T param);
}

package com.test.itau.shared.usecase;

public interface UseCase<INPUT, OUTPUT> {
    OUTPUT execute(INPUT input);
}

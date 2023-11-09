package cs2030s.fp;

import java.util.Objects;

/**
 * CS2030S PE2 Question 1
 * AY20/21 Semester 2
 *
 * @author eunice koh
 */

public class Try<T> {
    private final T value;
    private final Throwable throwable;
    private final boolean isSuccess;

    private Try(T value) {
        this.value = value;
        this.throwable = null;
        this.isSuccess = true;
    }

    private Try(Throwable throwable) {
        this.value = null;
        this.throwable = throwable;
        this.isSuccess = false;
    }

    public static <T> Try<T> of(Producer<T> producer) {
        try {
            return new Try<>(producer.produce());
        } catch (Throwable t) {
            return new Try<>(t);
        }
    }

    public static <T> Try<T> success(T value) {
        return new Try<>(value);
    }

    public static <T> Try<T> failure(Throwable throwable) {
        return new Try<>(throwable);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailure() {
        return !isSuccess;
    }

    public T get() throws Throwable {
        if (isSuccess) {
            return value;
        } else {
            throw (Throwable) throwable;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Try<?> other = (Try<?>) obj;
        if (isSuccess && other.isSuccess) {
            return Objects.equals(value, other.value);
        } else if (isFailure() && other.isFailure()) {
            return throwable.toString().equals(other.throwable.toString());
        }
        return false;
    }

    public <R> Try<R> map(Transformer<? super T, ? extends R> transformer) {
        if (isSuccess) {
            try {
                return Try.success(transformer.transform(value));
            } catch (Throwable t) {
                return Try.failure(t);
            }
        } else {
            return Try.failure(throwable);
        }
    }

    public <U> Try<U> flatMap(Transformer<? super T, Try<U>> transformer) {
        if (isSuccess) {
            try {
                return transformer.transform(value);
            } catch (Throwable t) {
                return Try.failure(t);
            }
        } else {
            return Try.failure(throwable);
        }
    }

}
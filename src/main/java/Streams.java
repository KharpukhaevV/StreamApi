import java.util.*;
import java.util.function.*;

public class Streams<T> {
    private final Generator<T> generator;
    private List<T> value;

    private Streams(Generator<T> generator) {
        this.generator = generator;
        this.value = new ArrayList();
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams<>(generatorContext -> {
            for (T item : list) {
                generatorContext.emit(item);
            }
        });
    }

    public Streams<T> filter(Predicate<T> predicate) {
        return new Streams((generatorContext) -> {
            this.generator.generate((value) -> {
                if (predicate.test(value)) {
                    generatorContext.emit(value);
                }

            });
        });
    }

    public <R> Streams<R> map(Function<T, R> func) {
        return new Streams((generatorContext) -> {
            this.generator.generate((value) -> {
                generatorContext.emit(func.apply(value));
            });
        });
    }

    public Streams<T> distinct() {
        generator.generate(val -> addValue(val));
        Streams<T> streams = new Streams<>(generatorContext -> {
            List<T> list = new ArrayList();
            for (T item : this.value) {
                if (!list.contains(item)) {
                    list.add(item);
                    generatorContext.emit(item);
                }
            }
        });
        return streams;
    }

    public <E, D> Map<E, D> toMap(Mapper<T, E> key, Mapper<T, D> value) {
        Map<E, D> map = new HashMap<>();
        generator.generate(val -> addValue(val));
        for (T p : this.value) {
            map.put(key.get(p), value.get(p));
        }
        return map;
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        generator.generate(val -> addValue(val));
        for (T p : this.value) {
            list.add(p);
        }
        return list;
    }

    public T findFirst() {
        generator.generate(val -> addValue(val));
        return this.value.isEmpty() ? null : this.value.get(0);
    }

    public int count() {
        generator.generate(val -> addValue(val));
        return this.value.size();
    }

    public Streams<T> limit(int lim) {
        generator.generate(val -> addValue(val));
        return new Streams<>(generatorContext -> {
            for (int i = 0; i < lim; i++) {
                generatorContext.emit(this.value.get(i));
            }
        });
    }

    public  void forEach(Consumer<? super T> action) {
        generator.generate(val -> addValue(val));
        Objects.requireNonNull(action);
        for (T t : this.value) {
            action.accept(t);
        }
    }

    private void addValue(T... t) {
        value.addAll(Arrays.asList(t));
    }

    public String toString() {
        return "Streams{, value=" + this.value + '}';
    }
}
